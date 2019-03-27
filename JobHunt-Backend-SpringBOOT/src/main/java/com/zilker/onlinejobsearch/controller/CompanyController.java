package com.zilker.onlinejobsearch.controller;


import java.net.URLDecoder;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.onlinejobsearch.beans.ApplyJob;
import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.CompanyDetails;
import com.zilker.onlinejobsearch.beans.CompanyReviews;

import com.zilker.onlinejobsearch.beans.JobVacancy;
import com.zilker.onlinejobsearch.customException.ApplicationException;
import com.zilker.onlinejobsearch.delegate.CompanyDelegate;
import com.zilker.onlinejobsearch.delegate.JobDelegate;
import com.zilker.onlinejobsearch.delegate.UserDelegate;
import com.zilker.onlinejobsearch.utils.ResponseGeneratorUtil;

@RestController
public class CompanyController {

	@Autowired
	CompanyDelegate companyDelegate;

	@Autowired
	UserDelegate userDelegate;

	@Autowired
	JobDelegate jobDelegate;
	
	@Autowired
	ResponseGeneratorUtil responseUtil;

	/*
	 * fetch all company details and display findByJobs page
	 */
	@GetMapping("/companies")
	public <T> ResponseEntity<?> DisplayAllCompanies() {
		ArrayList<CompanyDetails> companyDetails = null;
		try {
			companyDetails = companyDelegate.displayCompanies();
			return responseUtil.successResponse(companyDetails);
		} catch(ApplicationException e){	
			return responseUtil.errorResponse(e);
		}
		
	}

	/*
	 * controller to add a new company
	 */
	@PostMapping("/companies")
	public <T> ResponseEntity<?> AddNewCompany(@RequestBody CompanyDetails company) {	
		
		try {
			
			if(companyDelegate.addNewCompany(company)) {
			return responseUtil.generateMessage("Success");
			}else {
				return responseUtil.generateMessage("Error Adding company");
			}
		} catch (ApplicationException e) {
			
			return responseUtil.errorResponse(e);
		}
		
	}

	/*
	 * controller to retrieve all details and vacancy in company
	 */
	@GetMapping("/company/{companyName}/{id}")
	public <T> ResponseEntity<?> findCompany(@PathVariable("companyName") String companyName,
			@PathVariable("id") int userId) throws Exception {
		Company companyDetails = null;
		try {
			String company = URLDecoder.decode(companyName,"UTF-8");
			int companyId = companyDelegate.fetchCompanyId(company);
			companyDetails = companyDelegate.retrieveVacancyByCompany(companyId, userId);
			return responseUtil.successResponse(companyDetails);
		
		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}

	}

	/*
	 * controller to find vacancies by location
	 */
	@GetMapping("/location/{location}/{id}")
	public  <T> ResponseEntity<?> findByLocation(@PathVariable("location") String location,
			@PathVariable("id") int userId) throws Exception {
		ArrayList<JobVacancy> retrieveByLocation = null;
		try {
			String Location = URLDecoder.decode(location,"UTF-8");
			retrieveByLocation = companyDelegate.retrieveVacancyByLocation(Location, userId);
			return responseUtil.successResponse(retrieveByLocation);

		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	
	}

	/*
	 * controller to view all reviews of a company
	 */
	@GetMapping("/company/reviews/{companyName}")
	public <T> ResponseEntity<?> viewCompanyReviews(@PathVariable("companyName") String companyName)throws Exception {
		Company reviews = null;
		try {
			String company = URLDecoder.decode(companyName,"UTF-8");
			int companyId = companyDelegate.fetchCompanyId(company);
			reviews = userDelegate.retrieveReview(companyId);
			return responseUtil.successResponse(reviews);
		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}

	/*
	 * controller to view all reviews of all interview process of a company
	 */
	@GetMapping(value = "/company/interviews/{companyName}")
	public <T> ResponseEntity<?>  viewReviewsOnInterviewProcess(@PathVariable("companyName") String companyName)throws Exception {
		Company interviewProcess = null;
		try {
			String company = URLDecoder.decode(companyName,"UTF-8");
			int companyId = companyDelegate.fetchCompanyId(company);
			interviewProcess = userDelegate.retrieveInterviewProcess(companyId);
			return responseUtil.successResponse(interviewProcess);
		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}

	/*
	 * controller to rate a company
	 */
	@PostMapping(value = "/company/rate")
	public <T> ResponseEntity<?> RateACompany(@RequestParam("id") int userId, @RequestParam("company") String companyName,
			@RequestBody CompanyReviews reviewsRating) throws Exception{
		Company reviews = null;
		try {
			String company = URLDecoder.decode(companyName,"UTF-8");
			int companyId = companyDelegate.fetchCompanyId(company);
			if (userDelegate.reviewAndRateCompany(userId, companyId, reviewsRating)) {
				reviews = userDelegate.retrieveReview(companyId);
				return responseUtil.successResponse(reviews);
			}else {
				return responseUtil.generateMessage("Error Adding Review");
			}
		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}

	/*
	 * controller to fetch applied users by admin
	 */
	@GetMapping("/applied-users/{id}")
	public <T> ResponseEntity<?> AppliedUsers(@PathVariable("id") int userId) {
		ArrayList<ApplyJob> appliedUsers = null;
		try {
			int companyId = userDelegate.fetchCompanyIdByAdmin(userId);
			appliedUsers = companyDelegate.viewAppliedUsers(companyId);
			return responseUtil.successResponse(appliedUsers);

		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}

	/*
	 * controller to mark users as contacted
	 */
	@PutMapping(value = "/contacted-users")
	public ResponseEntity<?> UpdateContactedUsers(@RequestParam("id") int userId, @RequestBody ApplyJob applyJobs){
		try {
			int companyId = userDelegate.fetchCompanyIdByAdmin(userId);
			int jobId = jobDelegate.fetchJobId(applyJobs.getJobRole());
			if (userDelegate.markContacted(userId, companyId, jobId, applyJobs)) {
				return responseUtil.generateMessage("Success");
			}else {
				return responseUtil.generateMessage("Error");
			}

		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}

	/*
	 * controller to fetch all published jobs by admin
	 */
	@GetMapping(value = "company/jobspublished/{id}")
	public <T> ResponseEntity<?> ViewPublishedJobs(@PathVariable("id") int userId) {
		Company vacancyDetails = null;
		try {
			int companyId = userDelegate.fetchCompanyIdByAdmin(userId);
			vacancyDetails = companyDelegate.retrieveVacancyByCompanyAdmin(companyId);
			return responseUtil.successResponse(vacancyDetails);
		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}

	/*
	 * controller to update published jobs
	 */
	@PutMapping(value = "company/jobspublished")
	public <T> ResponseEntity<?> UpdatePublishedJobs(@RequestParam("id") int userId,
			@RequestParam("jobdesignation") String jobDesignation,@RequestParam("location") String oldLocation, @RequestBody JobVacancy jobVacancy) throws Exception{

		Company companies = null;
		try {

			String job = URLDecoder.decode(jobDesignation,"UTF-8");
			String location = URLDecoder.decode(oldLocation,"UTF-8");
			int oldJobId = jobDelegate.fetchJobId(job);
			int companyId = userDelegate.fetchCompanyIdByAdmin(userId);
			if (userDelegate.UpdateVacancy(oldJobId,location,companyId, userId, jobVacancy)) {
				companies = companyDelegate.retrieveVacancyByCompanyAdmin(companyId);
				return responseUtil.successResponse(companies);
			}else {
				return responseUtil.generateMessage("Error");
			}

		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}

	/*
	 * controller to update published jobs
	 */
	@DeleteMapping(value = "company/jobspublished")
	public <T> ResponseEntity<?> DeletePublishedJobs(@RequestParam("id") int userId,
			@RequestParam("jobdesignation") String jobDesignation,@RequestParam("location") String oldLocation) throws Exception {

		Company companies = null;
		try {
			String job = URLDecoder.decode(jobDesignation,"UTF-8");
			String location = URLDecoder.decode(oldLocation,"UTF-8");
			int oldJobId = jobDelegate.fetchJobId(job);
			int companyId = userDelegate.fetchCompanyIdByAdmin(userId);
			if (companyDelegate.removeVacancy(companyId,location,userId, oldJobId)) {
				companies = companyDelegate.retrieveVacancyByCompanyAdmin(companyId);
				return responseUtil.successResponse(companies);
			}else {
				return responseUtil.generateMessage("Error");
			}

		} catch (ApplicationException e) {
			return responseUtil.errorResponse(e);
		}
	}
}
