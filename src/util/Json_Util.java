package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json_Util {
	
	public static JSONObject Json_Util(String[] args) {
		JSONParser parser = new JSONParser();

        try {        
            URL oracle = new URL(args[0]); // URL to Parse
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
           
            String inputLine;
            while ((inputLine = in.readLine()) != null) {              
                JSONArray a = (JSONArray) parser.parse(inputLine);
               
                // Loop through each item
                for (Object o : a) {
                    JSONObject obj = (JSONObject) o;
                    
                    if(args[1] == "Mixer") {
                    Boolean online = (Boolean) obj.get("online");
                    if(online == true) {
                    	return obj;
                    }
                    }
                    else if(args[1] == "Twitch"){
                    String type = (String) obj.get("type");
                    if(type == "live") {
                    	return obj;
                    }
                    }else if (args[1] == "test") {
                    	return obj;
                    }
                    
                    System.out.println("\n");
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		return null;
	}
   
    public static void main(String[] args) {
          
    }  
}