package colind.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import colind.builder.TableBuilder.Days;

public class Stundenplan {

	private final int id;
	private Map<Days, List<Veranstaltung>> Tag;
	
	public Stundenplan() {
		this.id =  1;
		this.Tag = new HashMap<Days, List<Veranstaltung>>();
		
		this.Tag.put(Days.MONTAG, new LinkedList<Veranstaltung>()); 
		this.Tag.put(Days.DIENSTAG, new LinkedList<Veranstaltung>()); 
		this.Tag.put(Days.MITTWOCH, new LinkedList<Veranstaltung>()); 
		this.Tag.put(Days.DONNERSTAG, new LinkedList<Veranstaltung>()); 
		this.Tag.put(Days.FREITAG, new LinkedList<Veranstaltung>()); 
		this.Tag.put(Days.SAMSTAG, new LinkedList<Veranstaltung>()); 
		
	}
	
	
	public Map<Days, List<Veranstaltung>> getTag() {
		return Tag;
	}

	public int getId() {
		return id;
	}

	//Noch implementieren
	public List<Veranstaltung> getTag(String tag) {
		
		return new LinkedList<Veranstaltung>();
	}
	
	public void addVeranstaltung(Veranstaltung va, Days tag) {
		List<Veranstaltung> liste = this.Tag.get(tag);
		liste.add(va);
		this.Tag.put(tag, liste);
	}
	
	public void print() {
		System.out.println("----------MONTAG----------");
		for(Veranstaltung v : this.Tag.get(Days.MONTAG)) {
			System.out.println("<------------------------------->");
			System.out.println(v.getName());
			System.out.println(v.getDozent());
			System.out.println(v.getRaum());
			System.out.println(v.getBeginn());
			System.out.println(v.getDauer());
		}
		System.out.println("----------DIENSTAG----------");
		for(Veranstaltung v : this.Tag.get(Days.DIENSTAG)) {
			System.out.println("<------------------------------->");
			System.out.println(v.getName());
			System.out.println(v.getDozent());
			System.out.println(v.getRaum());
			System.out.println(v.getBeginn());
			System.out.println(v.getDauer());
		}
		System.out.println("----------MITTWOCH----------");
		for(Veranstaltung v : this.Tag.get(Days.MITTWOCH)) {
			System.out.println("<------------------------------->");
			System.out.println(v.getName());
			System.out.println(v.getDozent());
			System.out.println(v.getRaum());
			System.out.println(v.getBeginn());
			System.out.println(v.getDauer());
		}
		System.out.println("----------DONNERSTAG----------");
		for(Veranstaltung v : this.Tag.get(Days.DONNERSTAG)) {
			System.out.println("<------------------------------->");
			System.out.println(v.getName());
			System.out.println(v.getDozent());
			System.out.println(v.getRaum());
			System.out.println(v.getBeginn());
			System.out.println(v.getDauer());
		}
		System.out.println("----------FREITAG----------");
		for(Veranstaltung v : this.Tag.get(Days.FREITAG)) {
			System.out.println("<------------------------------->");
			System.out.println(v.getName());
			System.out.println(v.getDozent());
			System.out.println(v.getRaum());
			System.out.println(v.getBeginn());
			System.out.println(v.getDauer());
		}
		System.out.println("----------SAMSTAG----------");
		for(Veranstaltung v : this.Tag.get(Days.SAMSTAG)) {
			System.out.println("<------------------------------->");
			System.out.println(v.getName());
			System.out.println(v.getDozent());
			System.out.println(v.getRaum());
			System.out.println(v.getBeginn());
			System.out.println(v.getDauer());
		}
	}
	
}
