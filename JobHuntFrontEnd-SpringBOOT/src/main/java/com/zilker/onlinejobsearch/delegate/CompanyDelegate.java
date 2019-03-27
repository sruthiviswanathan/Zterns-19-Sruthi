package com.zilker.onlinejobsearch.delegate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import org.springframework.stereotype.Service;

import com.zilker.onlinejobsearch.Exception.ApplicationException;
import com.zilker.onlinejobsearch.Exception.CompanyAlreadyExistsException;
import com.zilker.onlinejobsearch.Exception.CompanyNotFoundException;
import com.zilker.onlinejobsearch.Exception.LocationNotFoundException;
import com.zilker.onlinejobsearch.Exception.VacancyAlreadyPublishedException;
import com.zilker.onlinejobsearch.beans.ApplyJob;
import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.CompanyDetails;
import com.zilker.onlinejobsearch.beans.JobVacancy;
import com.zilker.onlinejobsearch.utils.HttpRequestUtil;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


@Service
public class CompanyDelegate {
	
	HttpRequestUtil util = new HttpRequestUtil();

	public ArrayList<CompanyDetails> displayCompanies() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CompanyDetails> companyDetails = null;
		try {
			
			String url = "http://localhost:8081/companies";	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONArray jsonArray = (JSONArray) data.get("responseBody");
			companyDetails = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<CompanyDetails>>(){}.getType());
			
		} catch (Exception e) {
			throw e;
		}
		return companyDetails;
	}
	
	public Company retrieveVacancyByCompanyAdmin(int userId) throws Exception {
		// TODO Auto-generated method stub
		Company vacancies = new Company();
		try {
			Gson gson = new Gson();
			String url = "http://localhost:8081/company/jobspublished/"+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			vacancies= gson.fromJson(data1.toString(),Company.class);

		} catch (Exception e) {
			throw e;
		}

		return vacancies;
	}


	public ArrayList<JobVacancy> retrieveVacancyByLocation(String location,int userId) throws ApplicationException {
		// TODO Auto-generated method stub
		ArrayList<JobVacancy> vacancies = new ArrayList<JobVacancy>();
		try {
			String Location = URLEncoder.encode(location, "UTF-8" ); 
			String url = "http://localhost:8081/location/"+Location+"/"+userId;	
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
				throw new LocationNotFoundException();
			}
			
			
		} catch (LocationNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return vacancies;
	}


	public String addNewCompany(String companyName,String websiteUrl,String companyLogo) throws ApplicationException {
		// TODO Auto-generated method stub
		String message="";
		try {
			Gson gson = new Gson();
			CompanyDetails company = new CompanyDetails();
			company.setCompanyName(companyName);
			company.setCompanyWebsiteUrl(websiteUrl);
			company.setCompanyLogo(companyLogo);
			
			String json = gson.toJson(company);
			String url = "http://localhost:8081/companies";	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			message = (String) data1.get("message");
			if(message.equals("Success")) {
				
			}else {
				throw new CompanyAlreadyExistsException();
			}
			
		} catch(CompanyAlreadyExistsException e) {
			throw e;
		}
		
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}

		return message;
	}


	public Company retrieveVacanciesByCompany(String companyName, int userId) throws ApplicationException {
		// TODO Auto-generated method stub
		Company companyDetails = new Company();
		ArrayList<CompanyDetails> displayCompanies = new ArrayList<CompanyDetails>();
		try {
			String company = URLEncoder.encode(companyName, "UTF-8" ); 
			Gson gson = new Gson();
			String url = "http://localhost:8081/company/"+company+"/"+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);			
			JsonObject root = new JsonParser().parse(response.toString()).getAsJsonObject();
			boolean flag = root.get("isSuccess").getAsBoolean();
			if(flag == true) {
				JSONParser parser = new JSONParser(); 
				JSONObject data = (JSONObject) parser.parse(response.toString());     
				JSONObject data1 = (JSONObject) data.get("responseBody");
				companyDetails= gson.fromJson(data1.toString(),Company.class);
			}
			else {
				 displayCompanies = displayCompanies();
				throw new CompanyNotFoundException();
			}
		
			
		}catch(CompanyNotFoundException e) {
			e.setErrorData(displayCompanies);
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("Exception","Exception");
		}
		return companyDetails;
	}


	public String publishVacancy(int userId,String jobDesignation,String location,String salary,String count,String description) throws ApplicationException {
		// TODO Auto-generated method stub
		String message = "";
		try {
			Gson gson = new Gson();
			JobVacancy jobVacancy = new JobVacancy();
			jobVacancy.setJobId(Integer.parseInt(jobDesignation));
			jobVacancy.setLocation(location);
			jobVacancy.setJobDescription(description);
			jobVacancy.setSalary(Float.parseFloat(salary));
			jobVacancy.setVacancyCount(Integer.parseInt(count));
			
			String json = gson.toJson(jobVacancy);
			String url = "http://localhost:8081/company/vacancy?id="+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			message = (String) data1.get("message");
			if(message.equals(null)) {
				throw new VacancyAlreadyPublishedException();
			}

			
		} catch(VacancyAlreadyPublishedException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("Exception","exception");
		}
		return message;
	}


	public Company removeVacancy(int userId,String jobDesignation,String oldLocation) throws Exception {
		// TODO Auto-generated method stub
		Company vacancies = null;
		try {
			
			Gson gson = new Gson();
			String oldjob = URLEncoder.encode(jobDesignation, "UTF-8" ); 
			String oldlocation = URLEncoder.encode(oldLocation, "UTF-8" ); 
			String url = "http://localhost:8081/company/jobspublished?id="+userId+"&jobdesignation="+oldjob+"&location="+oldlocation;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.deleteRequest(url);	        
			
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			boolean status = (Boolean) data.get("isSuccess");
			if(status == true) {		
			JSONObject data1 = (JSONObject) data.get("responseBody");
			vacancies = gson.fromJson(data1.toString(),Company.class);
			}
			
		}catch(Exception e) {
			throw e;
		}
		return vacancies;
	}

	public 	ArrayList<ApplyJob> viewAppliedUsers(int userId)throws Exception{
		// TODO Auto-generated method stub
		ArrayList<ApplyJob> appliedUsers = null;
		try {
			
			String url = "http://localhost:8081/applied-users/"+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONArray jsonArray = (JSONArray) data.get("responseBody");
			appliedUsers = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ApplyJob>>(){}.getType());
			
		} catch (Exception e) {
			throw e;
		}
		return appliedUsers;
	}
	

	public ArrayList<ApplyJob> viewAppliedJobs(int userId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ApplyJob> appliedJobs = null;
		try {
			
			String url = "http://localhost:8081/users/jobs?id="+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONArray jsonArray = (JSONArray) data.get("responseBody");
			appliedJobs = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<ApplyJob>>(){}.getType());
			
		} catch (Exception e) {
			throw e;
		}
		return appliedJobs;
	}


	public Company retrieveReviewOfACompany(String companyName) throws Exception{
		// TODO Auto-generated method stub
		Company reviews = null;
		try {
			Gson gson = new Gson();
			String company = URLEncoder.encode(companyName, "UTF-8" ); 
			String url = "http://localhost:8081/company/reviews/"+company;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			reviews= gson.fromJson(data1.toString(),Company.class);
			
		} catch (Exception e) {
			throw e;
		}
		return reviews;
	}


	public Company retrieveInterviewsOfACompany(String companyName) throws Exception{
		// TODO Auto-generated method stub
		Company interviews = null;
		try {
			Gson gson = new Gson();
			String company = URLEncoder.encode(companyName, "UTF-8" ); 
			String url = "http://localhost:8081/company/interviews/"+company;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			interviews= gson.fromJson(data1.toString(),Company.class);
			
		} catch (Exception e) {
			throw e;
		}
		return interviews;
	}

}
