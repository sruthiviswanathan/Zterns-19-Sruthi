package com.zilker.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CapitalizeCharacters{
	static Scanner sc = new Scanner(System.in);
	static Logger logger = Logger.getLogger(CapitalizeCharacters.class.getName());
	public static void main(String args[]){
			
		try {

			File file1 = new File("/home/dell/Desktop/test1.txt");
			
			if (file1.createNewFile())
				logger.log(Level.INFO, "File created");
			else
				logger.log(Level.INFO, "Error in creating file");
			FileReader read1 = new FileReader(file1);
			
			NewClass newclass = new NewClass(read1);
			BufferedWriter writerfile1 = new BufferedWriter(new FileWriter(file1));
			String test1 = "welcome";
			writerfile1.write(test1);
			writerfile1.close();
			newclass.readLine();
	
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	
}
