package colind.builder;

import java.io.File;
import java.io.FileNotFoundException;
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
	Zelle currentCell = new Zelle();
	Boolean currentOCell = false;
	System.out.println("Reihen: " + table.length);
	System.out.println("Spalten: " + table[0].length);
	
	//nur zum Test
	List<Veranstaltung> veranstaltungen = new LinkedList<Veranstaltung>();
	
	while(scanner.hasNext()) {
		
		
		
	}
	
	
	for(int i = 0; i < table.length;i++) {
		
		for(int e = 0; e < table[0].length;e++) {
			System.out.println(table[i][e]);
		}
		
	}
	
	System.out.println(breiten);
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
