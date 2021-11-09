import java.io.File;
import java.util.List;
import colind.builder.PlanBuilder;
import colind.stundenplan.Stundenplan;
import colind.stundenplan.Veranstaltung;

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
		File f = new File("/Users/dohrmann/Documents/GitHub/JadePlan/src/test/resources/outputFile.html");
		Stundenplan plan = PlanBuilder.build(f);
	
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
