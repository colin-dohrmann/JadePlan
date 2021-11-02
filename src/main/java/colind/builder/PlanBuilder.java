package colind.builder;

import java.io.File;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import colind.stundenplan.Stundenplan;
import colind.stundenplan.Veranstaltung;

public abstract class PlanBuilder {
	
	
	public static Stundenplan build(File datei) {
		
		Stundenplan stundenplan = new Stundenplan();
		List<Veranstaltung> veranstaltungen = searchPlan(datei);
		
		
		
		
		return stundenplan;
		
	}
	
	public static List<Veranstaltung> searchPlan(File f) {
		
		StringBuilder builder = new StringBuilder();
	        List<Veranstaltung> veranstaltungen = new LinkedList<Veranstaltung>();
	        String tag ="Montag";
	        int dayCounter = 0;

	         try {
	             Scanner scanner = new Scanner(f);
	             boolean  isOCell = false;
	             StringBuilder cell = new StringBuilder();
	             int c = 0;
	             String last = "";
	             Veranstaltung v = null;
	             String time = "";
	             
	             
	             //Dursucht das Dokument
	             while(scanner.hasNext()) 
	             {
	            	 //Nächste Zeile lesen
	                String s = scanner.nextLine();
	                
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
	                //nächster Tag
	                if( (s.contains("class='object-cell") || s.contains("style=" + "border-right") ) ) {
	                	
	                	if(dayCounter >= 7)
	                		dayCounter = 0;
	                	switch (dayCounter) {
						case 1: //Montag
							tag = "Montag";
							break;
						case 2: //Dienstag
							tag = "Dienstag";
							break;
						case 3: //Mittwoch
							tag = "Mittwoch";
							break;
						case 4: //Donnerstag
							tag = "Donnerstag";
							break;
						case 5: //Freitag
							tag = "Freitag";
							break;
						case 6: //Samstag
							tag = "Samstag";
							break;
							
						default:
							break;
						}
	                	
	                	dayCounter++;
	                }
	                
	                //Wenn eine Objekt Zelle gefunden wird
	                if(s.contains("<!-- START OBJECT-CELL -->")) {
	           
	                    isOCell = true;
	                    c = 0;
	                    
	                    //Dauer ermitteln
	                    int index = last.indexOf("rowspan");
	                    int endIndex = last.indexOf("'>");
	                    String dauer = last.substring(index + 9, endIndex);
	                    
	                    v = new Veranstaltung();
	                    v.setDauer(Integer.parseInt(dauer));
	                    
	                   }
	                
	                //Letzte Zeile der Objekt Zelle
	                else if(s.contains("<!-- END OBJECT-CELL -->")){
	                    isOCell = false;
	                    c = 0;
	                    v.setBeginn(Time.valueOf(time));
	                    v.setDauer(c);
	                    veranstaltungen.add(v);
	                    System.out.println(dayCounter);
	                    System.out.println(v.getName());
	                    System.out.println(v.getTag());
	                    
	                }

	                if(isOCell && s.contains("<td align='center'>")){
	                 
	                    c++;
	                    String t = s.trim();
	                   String wert = s.substring(s.indexOf("'>") + 2, s.indexOf("</"));
	                   v.setTag(tag);
	                    switch (c) {
	                    
						case 1:
							v.setName(wert);
							break;

						case 2:
							v.setDozent(wert);
							break;
						case 3:
							v.setRaum(wert);
							break;
						
						default:
							break;
						}
	                   }
	                last = s;
	             }
	             
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
		
	         return veranstaltungen;
	}
	
	public static void searchCell(StringBuilder cell) {
		
		cell.indexOf("td", 0);
		
		
	}
	
}
