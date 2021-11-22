package colind.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import colind.search.Search;
import colind.search.Zelle;


public abstract class TableBuilder {

	public enum Days {MONTAG, DIENSTAG, MITTWOCH, DONNERSTAG, FREITAG, SAMSTAG};
	
	public static Zelle[][] build(File f) {
		//TODO aus der Datei ein Array[][] aus einzelnen Zellen machen
		
		int row = 0;
		int column = 0;
		Scanner scanner = null;
		String s = "";

		
		
		
		try {
			scanner = new Scanner(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Array mit Zellen instanzieren
	Zelle[][] table  = new Search().suche(f);

	
	/*
	//Reihen
	for(int i = 0; i < table.length;i++) {
		
		//Spalten
		for(int e = 0; e < table[i].length; e++) {
			
		}
	}
	*/
	Map<String, Integer> breiten = getWidth(f);
	Days currentDay = Days.MONTAG;
	Zelle currentCell;
	System.out.println("Reihen: " + table.length);
	System.out.println("Spalten: " + table[0].length);
	
	while(scanner.hasNext()) {
		s = scanner.nextLine();
		
		//erste Zelle in Zeile -> Tage zurücksetzen, Reihe addieren
		if(s.contains("row-label-one") && !s.contains("td.")) {
			currentDay = Days.MONTAG;
			column = 0;
			row++;
			continue;
		}
		
		
		
		if(s.contains("object-cell-border") && !s.contains("td.")) {
			column++;
			for(int i = 0; i < Integer.parseInt(s.substring(s.indexOf("rowspan") + 9, s.indexOf(">") -1)); i++) {
				
					table[row + i -1][column - 1] = new Zelle(true,currentDay, (i == 0)? true:false);
			}
			
		}
		
		//Nächste Zelle
		else if(s.contains("cell-border") && !s.contains("td.")) {
			Boolean oCell = false;
			Boolean oStartCell = false;
			column++;
			
			//TODO Tag berechnen mit der Breite neue Zelle mit params füllen und 
			
			table[row-1][column-1] = new Zelle(oCell,currentDay, oStartCell);
			
		}
		
		//Ende eines tages
		if(s.contains("border-right") && s.contains("cell-border")) {
			nextDay(currentDay);
		}
		
		if(row > 0 && column > 0)
		currentCell = table[row-1][column-1];
		else
		currentCell = new Zelle();
		
		
		//Wenn momentane Zelle eine Objektzelle ist
		if(currentCell.getObjektZelle() && !currentCell.getObjektstartzelle()) {
			column--;
			//Wenn Montag ist 
			if(currentDay == Days.MONTAG && breiten.get("Montag") == 1) {
				currentDay = nextDay(currentDay);
				continue;
			}
			else {
				continue;
			}
		}
	}
	
	
	for(int i = 0; i < table.length;i++) {
		
		for(int e = 0; e < table[0].length;e++) {
			System.out.println(table[i][e]);
		}
		
	}
	return table;
	}
	
	//Breite der tage wird berechnet und als Map zurückgegeben
	public static Map<String, Integer> getWidth(File f){
		Map<String, Integer> width = new HashMap<String, Integer>();
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width.put("Montag", breiten.get(0));
		width.put("Dienstag", breiten.get(1));
		width.put("Mittwoch", breiten.get(2));
		width.put("Donnerstag", breiten.get(3));
		width.put("Freitag", breiten.get(4));
		width.put("Samstag", breiten.get(5));
		return width;
	}
	
	//Sets the day to the next Day
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
	
}
