package colind.search;

import colind.builder.TableBuilder.Days;

public class Zelle {

	private Boolean objektZelle;
	private Days tag;
	private Boolean objektstartzelle;
	
	

	public Zelle(Boolean objectCell, Days currentDay, Boolean objectStartCell) {
		this.objektZelle = objectCell;
		this.tag = currentDay;
		this.objektstartzelle = objectStartCell;
		
	}
	
	public Boolean getObjektZelle() {
		return objektZelle;
	}

	public void setObjektZelle(Boolean objektZelle) {
		this.objektZelle = objektZelle;
	}

	public Days getTag() {
		return tag;
	}

	public void setTag(Days tag) {
		this.tag = tag;
	}

	public Boolean getObjektstartzelle() {
		return objektstartzelle;
	}

	public void setObjektstartzelle(Boolean objektstartzelle) {
		this.objektstartzelle = objektstartzelle;
	}
	
}
