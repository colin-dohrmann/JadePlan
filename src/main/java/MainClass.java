import java.io.File;
import java.io.IOException;
import java.util.List;

import colind.builder.PlanBuilder;
import colind.builder.TableBuilder;
import colind.entities.Veranstaltung;
import colind.request.Connection;
import colind.request.Response;


public class MainClass {
	
	private static String studiengang = "MIT WINF1";
	private static int kw = 38;
	
	public static void main(String[] args) {
		
		//Überprüfen ob args[] passen
		if(args.length > 2) {
			System.out.println("Fehler bitte Parameter Studiengang und KW ergänzen");
			return;
		}
		else if(args.length == 2) {
			try {
				studiengang = args[0];
				kw = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.out.println("Fehler bitte korrekte Parameter angeben");
				return;
			}
		}
		else {
			
		}
		
		File f = null;
		try {
			Connection connection = new Connection("https://www.jade-hs.de/apps/infosys/splan.php");

			Response r = connection.connect(studiengang, kw);
			f = r.buildFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		List<Veranstaltung> vas = TableBuilder.build(f);
		
		PlanBuilder pb = new PlanBuilder(vas);
		

	}
}
