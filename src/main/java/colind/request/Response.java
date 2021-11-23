package colind.request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class Response {

	private HttpURLConnection con;
	private List<String> liste = new LinkedList<String>();
	private final String PATH = "src/main/resources/outputFile.html";
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
		
		//jede Zeile wird aus dem BufferedReader gelesen, in ein String zwischengespeichert und an den StringBuilder angehängt bis
		//keine Zeile mehr vorhanden ist
		
		try {
			while((readed = in.readLine()) != null) {

				liste.add(readed);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public File buildFile() throws IOException {
		//In File Schreiben
				
				File output = new File(PATH);
				output.createNewFile();
				Boolean b = output.canWrite();
				
				//Logger
				Logger canWrite = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
				canWrite.setLevel(Level.ALL);
				if(output.canWrite()) {
					canWrite.info("Anwendung kann schreiben");
				}
				else {
					canWrite.warning("Anwendung kann nicht schreiben!");
				}
				
				//Daten werden in die Datei geschrieben
				BufferedWriter writer = new BufferedWriter(new FileWriter(output));
				
				//Datei schreiben Zeile für Zeile und leere Zeilen ausschließen
				for(String line:liste) {
					if(line.trim().length() > 0) {
						writer.write(line);
						writer.newLine();
					}
						
					
				}
				writer.close();	
				
				
			
				return output;
	}

	//Getter und Setter
	public HttpURLConnection getCon() {
		return con;
	}

	public void setCon(HttpURLConnection con) {
		this.con = con;
	}

	
}
