package colind.request;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import colind.builder.*;
public class Connection {

	private final String locationURL = "https://www.jade-hs.de/apps/infosys/splan.php";
	private HttpURLConnection con;
	private String studiengang = "MIT WInf1";
	private int kw = 1;
	
	
	public Connection(String locationURL) throws IOException {
		
		//Neue URL anlegen
		URL url = new URL(locationURL);
		
		//Neue Connection aus URL erstellen
			this.con = (HttpURLConnection) url.openConnection();
			
			//HTTP Request Methode und das die Connection ein Output ist
			this.con.setRequestMethod("POST");
			this.con.setDoOutput(true);
			
	}
	
	
	public Response connect(String studiengang, int kw) throws UnsupportedEncodingException, IOException {
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("Bezeichnung", studiengang); //"MIT WInf4"
		parameters.put("KW", String.valueOf(kw));
		parameters.put("path", "Studenten-Sets");
		parameters.put("template", "Set3");
		//parameters.put("weeks", "41"); wird nicht gebraucht
		parameters.put("days", "1-6");
		parameters.put("periods", "1-56");
		parameters.put("Width", "0");
		parameters.put("Height", "0");
		
		//Header bauen Wichtig um eine Connection überhaupt aufzuabeun
				//TODO Herausfinden welche überhaupt benötigt werden
						this.con.setRequestProperty("Host", "www.jade-hs.de");
						this.con.setRequestProperty("Connection", "keep-alive");
						this.con.setRequestProperty("Content-Length", "109");
						this.con.setRequestProperty("Cache-Control", "max-age=0");
						this.con.setRequestProperty("sec-ch-ua", "'Not;A Brand';v='99', 'Opera GX';v='79', 'Chromium';v='93'");
						this.con.setRequestProperty("sec-ch-ua-mobile", "?0");
						this.con.setRequestProperty("sec-ch-ua-platform", "'Windows'");
						this.con.setRequestProperty("Upgrade-Insecure-Requests", "1");
						this.con.setRequestProperty("Origin", "http://127.0.0.1:5500");
						this.con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
						this.con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36 OPR/79.0.4143.73");
						this.con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9 Sec-Fetch-Site: cross-site");
						this.con.setRequestProperty("Sec-Fetch-Site", "cross-site");
						this.con.setRequestProperty("Sec-Fetch-Mode", "navigate");
						this.con.setRequestProperty("Sec-Fetch-User", "?1");
						this.con.setRequestProperty("Sec-Fetch-Dest", "document");
						this.con.setRequestProperty("Referer", "http://127.0.0.1:5500/");
						this.con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
						this.con.setRequestProperty("Accept-Language", "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7");
				

				//Parameter in die Request schreiben 
				DataOutputStream out = new DataOutputStream(con.getOutputStream());
				out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
				out.flush();
				out.close();
				
				
				
				//Timeouts definieren und Verbindung öffnen
				con.setConnectTimeout(5000);
				con.setReadTimeout(5000);
				con.connect();
				
				//Redirects sollen nicht automatisch verfolgt werden
				con.setInstanceFollowRedirects(false);
				HttpURLConnection.setFollowRedirects(false);
				
				
				//Status der Response abfragen
				int status = con.getResponseCode();
				
				//verschiedene Status Codes abfangen und bearbeiten
				if(status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM) {
					String location = con.getHeaderField("Location");
					URL newURL = new URL(location);
					con = (HttpURLConnection) newURL.openConnection();
				}
				
				return new Response(con);
				
		
	}


	//Getter und Setter
	public String getStudiengang() {
		return studiengang;
	}


	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}


	public int getKw() {
		return kw;
	}


	public void setKw(int kw) {
		this.kw = kw;
	}
}
