package com.zilker.collections;
import java.util.Map; 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CountingVotes 
{ 
	private final static Logger logger =  
            Logger.getLogger(CountingVotes.class.getName());
	
    static void countFreq(String arr[]) 
    { 
   
        TreeMap<String, Integer> tmap = new TreeMap<String, Integer>(); 
         TreeSet<String> tset = new TreeSet<String>();
        for (int i = 0; i < arr.length; i++) 
        { 
            Integer c = tmap.get(arr[i]);
            if (tmap.get(arr[i]) == null) 
               tmap.put(arr[i], 1); 
            else
              tmap.put(arr[i], ++c); 
        } 
  
       
        int max = tmap.values().stream().max(Integer::compare).get();
              
        for (Map.Entry m:tmap.entrySet()){	
        			if((int)m.getValue() == max){
        				tset.add(String.valueOf(m.getKey()));
        			}
        }
        System.out.println(tset.first());     
    } 
 
    public static void main (String[] args) 
    { 
    	Scanner sc = new Scanner(System.in);
    	logger.log(Level.INFO, "Enter total no of votes: ");
    	int number=sc.nextInt();
    	sc.nextLine();
    	String arr[] = new String[number];
    	logger.log(Level.INFO, "Enter votes one by one:");
    	for(int i=0;i<number;i++){
    	arr[i] = sc.nextLine();
    	}
    
        countFreq(arr); 
    } 
} 