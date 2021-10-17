package colind.builder;

import java.io.IOException;
import java.net.HttpURLConnection;


//Nicht nötig entfernen
public class FullResponseBuilder {
public static String getFullResponse(HttpURLConnection con) throws IOException {
	StringBuilder fullResponseBuilder = new StringBuilder();
	
	fullResponseBuilder.append(con.getResponseCode())
	.append(" ")
	.append(con.getResponseMessage())
	.append("\n");
	
	return fullResponseBuilder.toString();
}
}
