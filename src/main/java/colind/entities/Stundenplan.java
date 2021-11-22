package colind.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Stundenplan {

	private final int id;
	private Map<String, List<Veranstaltung>> Tag;
	
	public Stundenplan() {
		this.id =  1;
		this.Tag = new HashMap<String, List<Veranstaltung>>();
		
		this.Tag.put("Montag", new LinkedList<Veranstaltung>()); 
		this.Tag.put("Dienstag", new LinkedList<Veranstaltung>()); 
		this.Tag.put("Mittwoch", new LinkedList<Veranstaltung>()); 
		this.Tag.put("Donnerstag", new LinkedList<Veranstaltung>()); 
		this.Tag.put("Freitag", new LinkedList<Veranstaltung>()); 
		this.Tag.put("Samstag", new LinkedList<Veranstaltung>()); 
		
	}
	
	//Noch implementieren
/*	public void addVeranstaltung( Veranstaltung va) {
		switch (va.getTag()) {
		case value:
			
			break;

		default:
			break;
		}
	}*/
	
	public Map<String, List<Veranstaltung>> getTag() {
		return Tag;
	}
//Weg damit
	public void setTag(Map<String, List<Veranstaltung>> tag) {
		Tag = tag;
	}

	public int getId() {
		return id;
	}

	//Noch implementieren
	public List<Veranstaltung> getTag(String tag) {
		
		return new LinkedList<Veranstaltung>();
	}
	
}
