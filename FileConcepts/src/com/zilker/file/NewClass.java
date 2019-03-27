package com.zilker.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class NewClass extends BufferedReader{

	NewClass(FileReader file){
			super(file);
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String readLine() throws IOException {
		// TODO Auto-generated method stub
		File file2 = new File("/home/dell/Desktop/test2.txt");
		BufferedWriter wfile1 = new BufferedWriter(new FileWriter(file2));
		String i =super.readLine(); 
		char array[] = i.toCharArray();
		for(int j=0 ; j<i.length();j++){
			if(j % 2 == 0){
				System.out.print(Character.toUpperCase(array[j]));
				wfile1.write(Character.toUpperCase(array[j]));
			}else{
				System.out.print(array[j]);
				wfile1.write(array[j]);
			}
		}
		
		wfile1.close();
		return " ";
	}


}
