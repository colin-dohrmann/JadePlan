package colind.stundenplan;

import java.sql.Time;

public class Veranstaltung {

	private String name;
	private String dozent;
	private String raum;
	private int dauer;
	private Time beginn;
	
	//Überladener Konstruktor
	public Veranstaltung(String name, String dozent, String raum, int dauer, String beginn) {
		this.name = name;
		this.dozent = dozent;
		this.raum = raum;
		this.dauer = dauer; //Integer Wert 1 = 15min | Bsp. 34= 34*15min
		this.beginn = Time.valueOf(beginn); //Format muss "hh:mm:ss" sein
	}
	
	//Standard Konstruktor
	public Veranstaltung() {
		this.name = "Keine Daten";
		this.dozent = "Keine Daten";
		this.raum = "Keine Daten";
		this.dauer = 0;
		this.beginn = Time.valueOf("08:15:00");
	}
}
