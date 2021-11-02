package colind.request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class Response {

	private HttpURLConnection con;
	private StringBuilder planString;
	
	public Response(HttpURLConnection con) throws IOException {
		this.con = con;
		
		//Response
		//codierte Response decodieren und in ein String schreiben
		
		//Response ist mit GZIP codiert, daher decodieren
		GZIPInputStream gzip = new GZIPInputStream(con.getInputStream());
		
		
		//decodierte Bytes werden in Chars gewandelt 
		InputStreamReader reader = new InputStreamReader(gzip);
		
		//effizientere Form zum Lesen der Daten, Wrapper für InputStreamReader
		BufferedReader in = new BufferedReader(reader);
		
		//Daten werden in StringBuilder geschrieben, da das schreiben per .append() schneller ist als mit einem normalenS tring
		String readed;
		StringBuilder builder = new StringBuilder();
		
		//jede Zeile wird aus dem BufferedReader gelesen, in ein String zwischengespeichert und an den StringBuilder angehängt bis
		//keine Zeile mehr vorhanden ist
		try {
			while((readed = in.readLine()) != null) {
				builder.append(readed);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(readed);
		//Builder wird in String gewandelt und ausgegeben
		System.out.println(builder.toString());
		
		this.planString = builder;
	}
	
	public void buildFile() throws IOException {
		//In File Schreiben
				File test = new File("src/test/resources/outputFile.html");
				test.createNewFile();
				Boolean b = test.canWrite();
				
				//Logger
				Logger canWrite = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
				canWrite.setLevel(Level.ALL);
				if(test.canWrite()) {
					canWrite.info("Anwendung kann schreiben");
				}
				else {
					canWrite.warning("Anwendung kann nicht schreiben!");
				}
				//Daten werden in die Datei geschrieben
				System.out.println("Datei wurde erzeugt: " + test.exists());
				BufferedWriter writer = new BufferedWriter(new FileWriter(test));
				writer.write(this.planString.toString());
				writer.close();	
	}

	//Getter und Setter
	public HttpURLConnection getCon() {
		return con;
	}

	public void setCon(HttpURLConnection con) {
		this.con = con;
	}

	public StringBuilder getPlanString() {
		return planString;
	}

	public void setPlanString(StringBuilder planString) {
		this.planString = planString;
	}

	
	
	
}
