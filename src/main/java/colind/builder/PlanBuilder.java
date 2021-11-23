package colind.builder;

import java.util.List;

import colind.entities.Stundenplan;
import colind.entities.Veranstaltung;

public class PlanBuilder {

	Stundenplan stundenplan;
	
	public PlanBuilder(List<Veranstaltung> vas) {
		stundenplan = new Stundenplan();
		for(Veranstaltung v : vas) {
			stundenplan.addVeranstaltung(v, v.getTag());
		}
		stundenplan.print();
	}
	
}
