package com.zilker.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class BasicFile {
	static Scanner sc = new Scanner(System.in);
	static Logger logger = Logger.getLogger(BasicFile.class.getName());

	public static void main(String args[]) {

		try {

			File file1 = new File("/home/dell/Desktop/test1.txt");
			File file2 = new File("/home/dell/Desktop/test2.txt");
			File file3 = new File("/home/dell/Desktop/test3.txt");

			if (file1.createNewFile() && file2.createNewFile() && file3.createNewFile())
				logger.log(Level.INFO, "Files created");
			else
				logger.log(Level.INFO, "Error in creating files");

			BufferedWriter writerfile1 = new BufferedWriter(new FileWriter(file1));
			String test1 = sc.nextLine();
			writerfile1.write(test1);
			writerfile1.close();
			BufferedWriter writerfile2 = new BufferedWriter(new FileWriter(file2));
			String test2 = sc.nextLine();
			writerfile2.write(test2);
			writerfile2.close();

			BufferedReader reader1 = new BufferedReader(new FileReader(file1));
			BufferedWriter writer3 = new BufferedWriter(new FileWriter(file3));
			BufferedReader reader2 = new BufferedReader(new FileReader(file2));

			String i;
			logger.log(Level.INFO, "read1");
			while ((i = reader1.readLine()) != null) {
				writer3.write(i);
				logger.log(Level.INFO, i);
				writer3.flush();
			}
			logger.log(Level.INFO, "read2");
			String s;
			while ((s = reader2.readLine()) != null) {
				writer3.write(s);
				logger.log(Level.INFO, s);
				writer3.flush();
			}
			writer3.close();

			reader1.close();
			reader2.close();

			if (file1.delete() && file2.delete()) {
				logger.log(Level.INFO, "deleted both the files");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

	}
}
