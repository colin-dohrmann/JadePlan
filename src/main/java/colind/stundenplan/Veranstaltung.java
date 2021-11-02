package colind.stundenplan;

import java.sql.Time;

import colind.builder.PlanBuilder;

public class Veranstaltung {

	private String name;
	private String dozent;
	private String raum;
	private int dauer;
	private Time beginn;
	private String tag;
	
	//Überladener Konstruktor
	public Veranstaltung(String name, String dozent, String raum, int dauer, String beginn, String tag) {
		this.name = name;
		this.dozent = dozent;
		this.raum = raum;
		this.dauer = dauer; //Integer Wert 1 = 15min | Bsp. 34= 34*15min
		this.beginn = Time.valueOf(beginn); //Format muss "hh:mm:ss" sein
		this.setTag(tag);
	}
	
	//Standard Konstruktor
	public Veranstaltung() {
		this.name = "Keine Daten";
		this.dozent = "Keine Daten";
		this.raum = "Keine Daten";
		this.dauer = 0;
		this.beginn = Time.valueOf("08:15:00");
		this.setTag(tag);
		
	}

	//Getter und Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDozent() {
		return dozent;
	}

	public void setDozent(String dozent) {
		this.dozent = dozent;
	}

	public String getRaum() {
		return raum;
	}

	public void setRaum(String raum) {
		this.raum = raum;
	}

	public int getDauer() {
		return dauer;
	}

	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	public Time getBeginn() {
		return beginn;
	}

	public void setBeginn(Time beginn) {
		this.beginn = beginn;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
