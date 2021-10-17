package colind.builder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


// Baut die URL für die Request zusammen
public class ParameterStringBuilder {

	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException{
		StringBuilder result = new StringBuilder();
		
		//Eingegebene Strings werden in ordentliches Format für die URL gewandelt
		for(Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}
		
		//URL in String wandeln und letztes "=" entfernen
		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length()-1) : resultString;
	}
}
