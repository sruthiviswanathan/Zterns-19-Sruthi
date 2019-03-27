package com.zilker.onlinejobsearch.delegate;



import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zilker.onlinejobsearch.Exception.ApplicationException;
import com.zilker.onlinejobsearch.Exception.EmailAlreadyExistsException;
import com.zilker.onlinejobsearch.Exception.VacancyAlreadyAppliedException;
import com.zilker.onlinejobsearch.beans.ApplyJob;
import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.CompanyReviews;
import com.zilker.onlinejobsearch.beans.JobRequest;
import com.zilker.onlinejobsearch.beans.JobReviews;
import com.zilker.onlinejobsearch.beans.JobVacancy;
import com.zilker.onlinejobsearch.beans.LoginResponse;
import com.zilker.onlinejobsearch.beans.User;
import com.zilker.onlinejobsearch.beans.UserDetails;
import com.zilker.onlinejobsearch.utils.HttpRequestUtil;


@Service
public class UserDelegate {

	HttpRequestUtil util = new HttpRequestUtil();
	
	public LoginResponse register(String name, String email,String password,String confirmPassword,String companyName,String designation,String skills) throws ApplicationException {
		// TODO Auto-generated method stub
		LoginResponse registerResponse = new LoginResponse();
		try {
			Gson gson = new Gson();
			User user = new User();
			user.setUserName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setCompany(companyName);
			user.setDesignation(designation);
			user.setSkills(skills);
			user.setConfirmPassword(confirmPassword);
			String json = gson.toJson(user);
			String url = "http://localhost:8081/users";	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			String status = (String) data.get("isSuccess");
			if(status.equals("true")) {		
			JSONObject data1 = (JSONObject) data.get("responseBody");
			registerResponse= gson.fromJson(data1.toString(),LoginResponse.class);
			}else {
				throw new EmailAlreadyExistsException();
			}
			
		} catch(EmailAlreadyExistsException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("exception","exception");
		}
		return registerResponse;
	}
	
	public LoginResponse login(User user) throws Exception {
		// TODO Auto-generated method stub
		LoginResponse registerResponse = new LoginResponse();
		try {
			Gson gson = new Gson();
			String json = gson.toJson(user);
			
	        String url = "http://localhost:8081/userslogin";
	        StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	 	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			registerResponse= gson.fromJson(data1.toString(),LoginResponse.class);
			
		} catch (Exception e) {	
			throw e;
		}
		return registerResponse;
	}

	public LoginResponse registerAsAdmin(String name, String email, String password,String companyId) throws ApplicationException {
		// TODO Auto-generated method stub
		LoginResponse registerResponse = new LoginResponse();
		try {
			Gson gson = new Gson();
			User user = new User();
			user.setUserName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setCompany(companyId);
			user.setDesignation("admin");
			user.setRoleId(2);
			
			String json = gson.toJson(user);
			String url = "http://localhost:8081/users-admin";	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			String status = (String) data.get("isSuccess");
			if(status.equals("true")) {	
			JSONObject data1 = (JSONObject) data.get("responseBody");
			registerResponse= gson.fromJson(data1.toString(),LoginResponse.class);
			}else {
				throw new EmailAlreadyExistsException();
			}
		} catch(EmailAlreadyExistsException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ApplicationException("exception","exception");
		}
		return registerResponse;
	}
	


	public String requestNewVacancy(String email,int userId,String jobId,String location,String salary) throws Exception {
		// TODO Auto-generated method stub
		String message = "";
		try {
			Gson gson = new Gson();
			JobRequest jobrequest = new JobRequest();
			jobrequest.setJobId(Integer.parseInt(jobId));
			jobrequest.setLocation(location);
			jobrequest.setSalary(Float.parseFloat(salary));
			String json = gson.toJson(jobrequest);
			String url = "http://localhost:8081/users-request/?id="+userId+"&email="+email;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			message = (String) data1.get("message");
			
			
		} catch (Exception e) {
			throw e;
		}
		return message;
	}


	public UserDetails retrieveUserData(int userId)throws Exception {
		// TODO Auto-generated method stub
		UserDetails userData = null;
		try {
			Gson gson = new Gson();
			String url = "http://localhost:8081/users/"+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			userData= gson.fromJson(data1.toString(),UserDetails.class);

		} catch (Exception e) {
			throw e;
		}

		return userData;
	}

	public String markContacted(int userId, String emailId, String jobDesignation, String location)throws Exception  {
		// TODO Auto-generated method stub
		String message = "";
		try {
			Gson gson = new Gson();
			ApplyJob applyJobs = new ApplyJob();
			applyJobs.setJobRole(jobDesignation);
			applyJobs.setLocation(location);
			applyJobs.setEmail(emailId);	
			String json = gson.toJson(applyJobs);
			String url = "http://localhost:8081/contacted-users?id="+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.putRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			message = (String) data1.get("message");
		
		} catch (Exception e) {
			throw e;
		}
		return message;
	}
	
	public String applyForJob(String companyName,String jobDesignation,String location,int userId,String email)throws ApplicationException  {
		// TODO Auto-generated method stub
		String message = "";
		try {
			Gson gson = new Gson();
			ApplyJob company = new ApplyJob();
			company.setEmail(email);
			company.setUserId(userId);
			company.setCompanyName(companyName);
			company.setJobRole(jobDesignation);
			company.setLocation(location);
			
			String json = gson.toJson(company);
			String url = "http://localhost:8081/company/jobs/apply";	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			message = (String) data1.get("message");
			if(message.equals("Error")) {
				throw new VacancyAlreadyAppliedException();
			}
			
			
			
		}catch(VacancyAlreadyAppliedException e) {
			throw e;
		}
		catch (Exception e) {
			
			throw new ApplicationException("Exception","exception");
		}
		return message;
	}

	public String updateUserProfile(String userName, String companyName, String designation, String skills,int userId) throws Exception{
		// TODO Auto-generated method stub
		String message="";
		try {
			Gson gson = new Gson();
			User user = new User();
			user.setUserId(userId);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			user.setCurrentTime(dtf.format(now));
			user.setUserName(userName);
			user.setCompany(companyName);
			user.setDesignation(designation);
			user.setSkills(skills);
			String json = gson.toJson(user);
			String url = "http://localhost:8081/users/"+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.putRequest(url,json);	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			message = (String) data1.get("message");
		
		}catch(Exception e) {
			throw e;
		}
		return message;
	}

	public Company reviewAndRateACompany(int userId, String companyName, String rating, String review, String jobRole,
			String interviewProcess) throws Exception {
		// TODO Auto-generated method stub
		Company reviewAndRating = new Company();
		try {
			Gson gson = new Gson();
			CompanyReviews reviews = new CompanyReviews();
			JobReviews interviews = new JobReviews();
			String company = URLEncoder.encode(companyName, "UTF-8" ); 
			reviews.setCompanyName(company);
			reviews.setRating(Float.parseFloat(rating));
			reviews.setReviews(review);
			interviews.setJobRole(jobRole);
			interviews.setInterviewProcess(interviewProcess);
			reviews.setJobReviews(interviews);
			String json = gson.toJson(reviews);
			
	        String url = "http://localhost:8081/company/rate?id="+userId+"&company="+company;
	        StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.postRequest(url,json);	 	        
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONObject data1 = (JSONObject) data.get("responseBody");
			reviewAndRating= gson.fromJson(data1.toString(),Company.class);
			
		} catch (Exception e) {
			throw e;
		}
		return reviewAndRating;
	}

	public ArrayList<Integer> adminDetails(int userId) throws Exception{
		// TODO Auto-generated method stub
		ArrayList<Integer> admin = null;
		try {
			String url = "http://localhost:8081/admin?id="+userId;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.getRequest(url);
			JSONParser parser = new JSONParser(); 
			JSONObject data = (JSONObject) parser.parse(response.toString());     
			JSONArray jsonArray = (JSONArray) data.get("responseBody");
			admin = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Integer>>(){}.getType());

		} catch (Exception e) {
			throw e;
		}

		return admin;
	}

	public Company UpdateVacancy(String jobDesignation,String oldLocation,int userId,String newJobDesignation,String location,
			String jobDescription,String salary,String count) throws Exception {
		// TODO Auto-generated method stub
		//String message ="";
		Company vacancies = null;
		try {
			
			Gson gson = new Gson();
			String oldjob = URLEncoder.encode(jobDesignation, "UTF-8" ); 
			String oldlocation = URLEncoder.encode(oldLocation, "UTF-8" ); 
			JobVacancy jobVacancy = new JobVacancy();
			int newJobId = Integer.parseInt(newJobDesignation);
			jobVacancy.setJobId(newJobId);
			jobVacancy.setLocation(location);
			jobVacancy.setJobDescription(jobDescription);
			jobVacancy.setSalary(Float.parseFloat(salary));
			jobVacancy.setVacancyCount(Integer.parseInt(count));
			
			String json = gson.toJson(jobVacancy);
			String url = "http://localhost:8081/company/jobspublished?id="+userId+"&jobdesignation="+oldjob+"&location="+oldlocation;	
			StringBuffer response = new StringBuffer();
			response =  (StringBuffer) util.putRequest(url,json);	        
			
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
}


