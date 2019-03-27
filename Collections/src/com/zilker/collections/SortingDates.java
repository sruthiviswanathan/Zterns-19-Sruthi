package com.zilker.collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SortingDates {
	
	private final static Logger logger =  
            Logger.getLogger(SortingDates.class.getName());
	
	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in);
		logger.log(Level.INFO, "Enter the number of dates: ");
		int number = scanner.nextInt();
		
		ArrayList<String> datesList = new ArrayList<String>();
		
		while(number-- > 0) {
			logger.log(Level.INFO, "Enter the date(dd/mm/yyyy): ");
			String date = scanner.next();
			
			datesList.add(date);
		}
		
		// ASCENDING SORT
		
		sortDates(datesList, 0);
		
		logger.log(Level.INFO,"ascending order");
		for(String dat : datesList) {
			System.out.println(dat);
		}
		
		// DESCENDING SORT
		sortDates(datesList, 1);
		logger.log(Level.INFO,"descending order");
		for(String dat : datesList) {
			System.out.println(dat);
		}
	}
	
	private static void sortDates(ArrayList<String> dates, int flag){
		Collections.sort(dates, new Comparator<String> () {
			DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
			@Override
			public int compare(String arg0, String arg1) {
				try {
					if(flag==0) {
					return dateFormat.parse(arg0).compareTo(dateFormat.parse(arg1));
					} else {
					return dateFormat.parse(arg1).compareTo(dateFormat.parse(arg0));
					}
				} catch(ParseException e) {
					throw new IllegalArgumentException();
				}
			}
			
		});
			
	}
	
}