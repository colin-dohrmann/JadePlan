package colind.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Search{

	//Stundenplan als leeres Array aus Zellen
    public Zelle[][] suche(File f) {
 
         
        Scanner scanner = null;
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        String s = "";
        String last = "";
        boolean days = false;
        int zähler = 0;
        Zelle[][] table = null;
        boolean abbruch = false;

        while (scanner.hasNext() && !abbruch) {
            last = s;
            s = scanner.nextLine();
            
            //Breite der Tage ermitteln
            if((last.contains("<td></td>") && s.contains("solid #")  ) || days){
                if(!days){
                    days = true;
                }
                //Wenn ein Tag im String ist
                if(s.contains("col-label-one")){
                    int i = s.indexOf("colspan=") + 9;
                    zähler += Integer.parseInt(String.valueOf(s.charAt(i)));

                }
                //Wenn Zeile vorbei ist
                if(days && s.contains("</tr>")){
                    days = false;
                    table = new Zelle[56][zähler];
                    abbruch = true;
                }
                
            }
            
        }
        //System.out.println(table);
        scanner.close();
        
        //Größe der Tabelle bestimmen
       // Search_Cols sc = new Search_Cols(table);
       // Boolean[][] b = sc.search(f);
        //sc.output(b);
        
        return table;
    }

}