package com.zilker.file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJson {

	private final static Logger LOGGER =  
            Logger.getLogger(XmlToJson.class.getName());
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader bufferedReader = null;
		String line = "", result = "";
		
		try {
			 bufferedReader = new BufferedReader(new FileReader("/home/dell/Downloads/xmlParse.txt")); 
	            
	         line = bufferedReader.readLine(); 
	         
	         while (line != null) 
	         { 
	            result = result + line;
	            line = bufferedReader.readLine(); 
	         } 
	         
	         //converting xml to json
	 		JSONObject obj = XML.toJSONObject(result);
	 		
	 		System.out.println(obj.toString());
	         
		} catch(IOException e) {
			LOGGER.log(Level.SEVERE, e.toString());
		}	
	}
}