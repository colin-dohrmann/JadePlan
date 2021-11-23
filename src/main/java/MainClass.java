import java.io.File;
import java.util.List;

import colind.builder.PlanBuilder;
import colind.builder.TableBuilder;
import colind.entities.Stundenplan;
import colind.entities.Veranstaltung;
import colind.search.Zelle;

public class MainClass {

	public static void main(String[] args) {
		/*
		try {
			Connection connection = new Connection("https://www.jade-hs.de/apps/infosys/splan.php");
			Response r = connection.connect("MIT WInf4", 41);
			r.buildFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		File f = new File("D:\\VS Code\\Github\\JadePlan\\src\\test\\resources\\outputFile.html");
		//Stundenplan plan = PlanBuilder.build(f);
		List<Veranstaltung> vas = TableBuilder.build(f);
		
		PlanBuilder pb = new PlanBuilder(vas);
		
		/*
		for (Veranstaltung veranstaltung : liste) {
			System.out.println(veranstaltung.getName());
			//System.out.println(veranstaltung.getDauer());
			System.out.println(veranstaltung.getDozent());
			System.out.println(veranstaltung.getRaum());
			System.out.println("");
		}
		*/
	}
}
