package com.zilker.onlinejobsearch.delegate;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.zilker.onlinejobsearch.Exception.ApplicationException;
import com.zilker.onlinejobsearch.Exception.JobDesignationAlreadyExistsException;
import com.zilker.onlinejobsearch.Exception.JobDesignationNotFoundException;
import com.zilker.onlinejobsearch.beans.JobMapping;
import com.zilker.onlinejobsearch.beans.JobVacancy;
import com.zilker.onlinejobsearch.utils.HttpRequestUtil;

@Service
public class JobDelegate {
	
	HttpRequestUtil util = new HttpRequestUtil();
	
	public ArrayList<JobVacancy> retrieveVacancyByJob1(String jobDesignation, int userId) throws ApplicationException {
		// TODO Auto-generated method stub
		ArrayList<JobVacancy> vacancies = new ArrayList<JobVacancy>();
		try {
			String job = URLEncoder.encode(jobDesignation, "UTF-8" ); 
			String url = "http://localhost:8081/jobdesignation/"+job+"/"+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);			
			JsonObject root = new JsonParser().parse(response.toString()).getAsJsonObject();
			boolean flag = root.get("isSuccess").getAsBoolean();
			if(flag == true) {
				JSONParser parser = new JSONParser(); 
				JSONObject data = (JSONObject) parser.parse(response.toString());     
				JSONArray jsonArray = (JSONArray) data.get("responseBody");
				vacancies = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<JobVacancy>>(){}.getType());
			}
			else {
				throw new JobDesignationNotFoundException();
			}
					
		} catch (JobDesignationNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return vacancies;
	}

	public ArrayList<JobMapping> displayJobs() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<JobMapping> job = null;
		try {
			
			String url = "http://localhost:8081/jobs";	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONArray jsonArray = (JSONArray) data.get("responseBody");
			job = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<JobMapping>>(){}.getType());
			
		} catch (Exception e) {
			throw e;
		}
		return job;
	}

	public String addNewJob(String jobRole, int userId) throws ApplicationException {
		// TODO Auto-generated method stub
		
		String message="";
		try {
			Gson gson = new Gson();
			JobMapping job = new JobMapping();
			job.setJobRole(jobRole);	
			String json = gson.toJson(job);
			String url = "http://localhost:8081/jobs?id="+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			message = (String) data1.get("message");
			if(message.equals("Success")) {
				
			}else {
				throw new JobDesignationAlreadyExistsException();
			}	
			
			
			
		}catch(JobDesignationAlreadyExistsException e) {
			throw e;
		}
		
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}

		return message;

	}	
	
}

