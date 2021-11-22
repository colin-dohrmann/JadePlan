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
	
	//Noch implementieren
/*	public void addVeranstaltung( Veranstaltung va) {
		switch (va.getTag()) {
		case value:
			
			break;

		default:
			break;
		}
	}*/
	
	public Map<Days, List<Veranstaltung>> getTag() {
		return Tag;
	}
//Weg damit
	public void setTag(Map<Days, List<Veranstaltung>> tag) {
		Tag = tag;
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
	
}
