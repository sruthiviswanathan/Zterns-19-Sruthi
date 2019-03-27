package com.zilker.onlinejobsearch.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




public class HttpRequestUtil {

	public Object getRequest(String url) throws Exception {

		StringBuffer response = new StringBuffer();
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	
	public Object postRequest(String url,String body)throws Exception {
		StringBuffer response = new StringBuffer();
		try {
			  
		        URL obj = new URL(url);
		        HttpURLConnection con = (HttpURLConnection) obj.openConnection();      
		        con.setRequestMethod("POST");
		        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		        con.setRequestProperty("Content-Type","application/json");
		        con.setDoOutput(true);
		        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		        wr.writeBytes(body);
		        wr.flush();
		        wr.close();
		          
		        BufferedReader in = new BufferedReader(
		                new InputStreamReader(con.getInputStream()));
		        String output;
		        while ((output = in.readLine()) != null) {
		         response.append(output);
		        }
		        in.close();
			
		}catch(Exception e) {
			throw e;
		}
		return response;
	}
	
	public Object putRequest(String url,String body)throws Exception {
		StringBuffer response = new StringBuffer();
		try {
			  
		        URL obj = new URL(url);
		        HttpURLConnection con = (HttpURLConnection) obj.openConnection();      
		        con.setRequestMethod("PUT");
		        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		        con.setRequestProperty("Content-Type","application/json");
		        con.setDoOutput(true);
		        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		        wr.writeBytes(body);
		        wr.flush();
		        wr.close();
		          
		        BufferedReader in = new BufferedReader(
		                new InputStreamReader(con.getInputStream()));
		        String output;
		        while ((output = in.readLine()) != null) {
		         response.append(output);
		        }
		        in.close();
			
		}catch(Exception e) {
			throw e;
		}
		return response;
	}


	public Object deleteRequest(String url) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer response = new StringBuffer();
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("DELETE");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
