package colind.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Search_Cols {

    private Boolean[][] map = null;
    public Search_Cols(Boolean[][] b){
        this.map = b;
        
        //Fill mit False -> Zelle ist nich verdeckt
        for(int i = 0; i < this.map[0].length; i++){
            
            for(int e = 0; e < this.map.length; e++){
                this.map[e][i] = false;
                
            }
        }
      
    }

    public Boolean[][] search(File f){
        int globalCounter = 0;
        int currentDay = 1;
        Scanner scan;
        String s = "";
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return this.map;
        }

        while(scan.hasNextLine()){
            
           s = scan.nextLine();

            if(s.contains("row-label-one") && !s.contains("td.row-label-one")){
                currentDay = 1;
                globalCounter++;
            }
            else if(s.contains("object-cell-border") && !s.contains("td.object-cell-border")){
                int a = s.indexOf("rowspan=") + 9;
                int b = s.indexOf(">") - 1;
                String s2 = s.substring(s.indexOf("rowspan=") + 9, s.indexOf(">") - 1);
                int length = Integer.parseInt(s2);

                for(int i = 0; i < length; i++){
                    this.map[globalCounter - 1 + i][currentDay] = true;
                }
            }

            if(s.contains("<td") && s.contains("border-right") && globalCounter > 1){
                currentDay++;
            }

        }

        scan.close();
        return this.map;
    }

    public void output(Boolean[][] array){

        for(int i = 0; i < 8; i++){
            for(int e = 0; e < 55; e++){
                System.out.println(array[e][i]);
            }
        }
    }

    public Boolean[][] getArray(){
        return this.map;
    }
    
    public Map<String, Integer> getWidth(File f){
    	Map<String, Integer> breiteMap = new HashMap<String, Integer>();
    	
    	
    	
    	return breiteMap;
    }

    
}