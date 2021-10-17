import java.io.IOException;

import colind.request.Connection;
import colind.request.Response;

public class MainClass {

	public static void main(String[] args) {
		try {
			Connection connection = new Connection("https://www.jade-hs.de/apps/infosys/splan.php");
			Response r = connection.connect("MIT WInf4", 41);
			r.buildFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
