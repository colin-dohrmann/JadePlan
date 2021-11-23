package colind.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import colind.entities.Veranstaltung;
import colind.search.Search;
import colind.search.Zelle;

//Klasse baut den Stundenplan aus der HTTP Response
//Warum auch immer ich die abstract gemacht habe...
public abstract class TableBuilder {

	public enum Days {MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG};
	
	public static List<Veranstaltung> build(File f) {
		

		Scanner scanner = null;
		String s = "";
		String last = "";
		String time = "";

		
		
		
		try {
			scanner = new Scanner(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Array mit Zellen instanzieren
	Zelle[][] table  = new Search().suche(f);

	
	Map<Days, Integer> breiten = getWidth(f);
	Days currentDay = Days.MONTAG;
	Zelle currentCell = new Zelle();
	
	//Liste mit Veranstaltungen in dem Stundenlan, die zurückgegeben werden
	List<Veranstaltung> veranstaltungen = new LinkedList<Veranstaltung>();
	
	for(int i = 0; i < table.length; i++) {
		for(int e = 0; e < table[e].length; e++) {
			table[i][e] = new Zelle();
		}
	}
	
	
for(int i = 0; i < table.length; i++) {
	

	for(int e = 0; e < table[0].length; e++) {
		//Momentane Zelle ist:
		currentCell = table[i][e];
		
		//bestimmen welcher Tag ist
		if(e < breiten.get(Days.MONTAG)) {
			currentDay = Days.MONTAG;
		}
		else if(e < breiten.get(Days.MONTAG) + breiten.get(Days.DIENSTAG)) {
			currentDay = Days.DIENSTAG;
		}
		else if(e < breiten.get(Days.MONTAG) + breiten.get(Days.DIENSTAG) + breiten.get(Days.MITTWOCH)) {
			currentDay = Days.MITTWOCH;
		}
		else if(e < breiten.get(Days.MONTAG) + breiten.get(Days.DIENSTAG) + breiten.get(Days.MITTWOCH) + breiten.get(Days.DONNERSTAG)) {
			currentDay = Days.DONNERSTAG;
		}
		else if(e < breiten.get(Days.MONTAG) + breiten.get(Days.DIENSTAG) + breiten.get(Days.MITTWOCH) + breiten.get(Days.DONNERSTAG)
				+ breiten.get(Days.FREITAG)) {
			currentDay = Days.FREITAG;
		}
		else if(e < breiten.get(Days.MONTAG) + breiten.get(Days.DIENSTAG) + breiten.get(Days.MITTWOCH) + breiten.get(Days.DONNERSTAG)
				+ breiten.get(Days.FREITAG) + breiten.get(Days.SAMSTAG)) {
			currentDay = Days.SAMSTAG;
		}
		
		//Wenn jetzige Zelle ein Objekt beinhaltet
		if(currentCell.getObjektZelle()) {
			//skip
			continue;
		}
		else {
			
			while( (!s.contains("cell-border")  && scanner.hasNext()) || s.contains("td.")) {
				s = scanner.nextLine();
				
				  //Zeit ermitteln
                if(s.contains("row-label-one") && !s.contains("td.row-label-one")) {
                	time = s.substring(s.indexOf("'>") + 2, s.indexOf("</td"));
                	if(time.length() <= 4) {
                    	time = "0" + time + ":00";
                    }
                    else {
                    	time = time + ":00";
                    }
                	
                }
				
				
			} //End While
			//Wenn s als nächstes ein Objektstart hat
			if(s.contains("object-cell-border")) {

				//Schleife zum schreiben der Objektzellen
				for(int f1 = 0; f1 < Integer.parseInt(s.substring(s.indexOf("rowspan") + 9, s.indexOf(">") -1)); f1++) {
					
					table[i + f1][e ] = new Zelle(true,currentDay, (f1 == 0)? true:false);
				}//End For
				int dauer = Integer.parseInt(s.substring(s.indexOf("rowspan") + 9, s.indexOf(">") -1));
				int counter = 0;
				Veranstaltung va = new Veranstaltung();
				va.setDauer(dauer);
				va.setTag(currentDay);
				va.setBeginn(Time.valueOf(time));
				
				//Inner Object
				while(!s.contains("<!-- END OBJECT-CELL -->")) {
					s = scanner.nextLine();
					if(s.contains("<td align='center'>")) {
						String wert = s.substring(s.indexOf("'>") + 2, s.indexOf("</"));

						switch (counter) {
						case 0:
							va.setName(wert);
							break;
						case 1:
							va.setDozent(wert);
							break;
						case 2:
							va.setRaum(wert);
							break;
							
						default:
							break;
						}
						
						counter++;
						
					} // End If
				} // End while inner Object Cell
				veranstaltungen.add(va);
			}//End If o-c-b
			else {
				table[i][e].setTag(currentDay);
			}
			if(scanner.hasNext())
				s = scanner.nextLine();
		}//End Else
	}// End For e
} //End For i
	
	//TODO Aufräumen

	return veranstaltungen;
	}
	
	//Breite der tage wird berechnet und als Map zurückgegeben
	public static Map<Days, Integer> getWidth(File f){
		Map<Days, Integer> width = new HashMap<Days, Integer>();
		List<Integer> breiten = new LinkedList<Integer>();
		try {
			Scanner scann = new Scanner(f);
			String s = "";
			String last = "";
			while(scann.hasNext()) {
				last = s;
				s = scann.nextLine();
				
				if(s.contains("col-label-one") && last.contains("<td></td>")) {
					while(s.contains("col-label-one")) {
						 int i = s.indexOf("colspan=") + 9;
		                 
		                 breiten.add(Integer.parseInt(String.valueOf(s.charAt(i))));
		                 s = scann.nextLine();		
					}
				}
			}
			scann.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width.put(Days.MONTAG, breiten.get(0));
		width.put(Days.DIENSTAG, breiten.get(1));
		width.put(Days.MITTWOCH, breiten.get(2));
		width.put(Days.DONNERSTAG, breiten.get(3));
		width.put(Days.FREITAG, breiten.get(4));
		width.put(Days.SAMSTAG, breiten.get(5));
		return width;
	}
	
	//Sets the day to the next Day
	//Wohl egal 
	public static Days nextDay(Days currentDay) {
		Days nextDay;
		switch (currentDay) {
		case MONTAG:
			nextDay = Days.DIENSTAG;
			break;
		case DIENSTAG:
			nextDay = Days.MITTWOCH;
			break;
		case MITTWOCH:
			nextDay = Days.DONNERSTAG;
	break;
		case DONNERSTAG:
			nextDay = Days.FREITAG;
	break;
		case FREITAG:
			nextDay = Days.SAMSTAG;
	break;
		case SAMSTAG:
			nextDay = Days.MONTAG;
	break;

		default:
			nextDay = Days.MONTAG;
			break;
		}
		
	return nextDay;
	}
	
	public static void consoleLog(Zelle[][] table) {
		for(int i = 0; i < table.length; i++ ) {
			for(int e = 0; e < table[0].length; e++){
				if(table[i][e].getObjektstartzelle()) {
					System.out.println();
				}
			}
		}
	}
}
