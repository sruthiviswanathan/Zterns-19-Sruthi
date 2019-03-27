package com.zilker.onlinejobsearch.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zilker.onlinejobsearch.Exception.ApplicationException;
import com.zilker.onlinejobsearch.beans.ApplyJob;
import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.CompanyDetails;
import com.zilker.onlinejobsearch.beans.JobMapping;
import com.zilker.onlinejobsearch.beans.JobVacancy;
import com.zilker.onlinejobsearch.delegate.CompanyDelegate;
import com.zilker.onlinejobsearch.delegate.JobDelegate;
import com.zilker.onlinejobsearch.delegate.UserDelegate;

@Controller
public class CompanyController {

	@Autowired
	CompanyDelegate companyDelegate;

	@Autowired
	UserDelegate userDelegate;

	@Autowired
	JobDelegate jobDelegate;

	/*
	 * fetch all company details and display findByJobs page
	 */
	@RequestMapping(value = "/findjobs", method = RequestMethod.GET)
	public ModelAndView DisplayFindJobs(HttpSession session) {
		ModelAndView model = new ModelAndView("findjob");
		try {

			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				ArrayList<CompanyDetails> companyDetails = companyDelegate.displayCompanies();
				model.addObject("companyList", companyDetails);
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * fetch all company details and display findByCompany page
	 */
	@RequestMapping(value = "/findcompany", method = RequestMethod.GET)
	public ModelAndView DisplayFindCompany(HttpSession session) {
		ModelAndView model = new ModelAndView("findcompany");
		try {

			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				ArrayList<CompanyDetails> companyDetails = companyDelegate.displayCompanies();
				model.addObject("companyList", companyDetails);
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}
	
	
	/*
	 * fetch all company details and display findByLocation page
	 */
	@RequestMapping(value = "/findlocation", method = RequestMethod.GET)
	public ModelAndView DisplayFindLocation(HttpSession session) {
		ModelAndView model = new ModelAndView("searchbylocation");
		try {

			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				ArrayList<CompanyDetails> companyDetails = companyDelegate.displayCompanies();
				model.addObject("companyList", companyDetails);
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	
	/*
	 * controller to add a new company
	 */
	@RequestMapping(value = "/companies", method = RequestMethod.POST)
	public void AddNewCompany(@RequestParam("companyName") String companyName,
			@RequestParam("websiteUrl") String websiteUrl, @RequestParam("companyLogo") String companyLogo,HttpServletResponse response,
			HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		try {
				if (companyDelegate.addNewCompany(companyName, websiteUrl, companyLogo).equals("Success")) {
					
					out.print("success");
					out.flush();
				} else {
					out.print("error");
					out.flush();
				}
		
		}catch(ApplicationException e) {
			out.print("companyExists");
			out.flush();
		}
	
		catch (Exception e) {
			out.print("error");
			out.flush();
		}
	
	}

	
	/*
	 * controller to retrieve all details and vacancy in company
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public ModelAndView findCompany(@RequestParam("companyName") String companyName, HttpSession session) {
		ModelAndView model = new ModelAndView("companydetails");
			Company company = new Company();
		try {

			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
					int userId = (Integer) session.getAttribute("userId");
					company = companyDelegate.retrieveVacanciesByCompany(companyName,userId);
					ArrayList<CompanyDetails> companyDetails = company.getCompanyDetails();
					ArrayList<JobVacancy> vacancyDetails = company.getJobVacancy();
					model.addObject("displayCompany", companyDetails);
					model.addObject("displayVacancies", vacancyDetails);
						
			}
		} catch (ApplicationException e) {
			model = new ModelAndView("errorcompanyresults");
			model.addObject("noCompany","yes");
			model.addObject("companyList", e.getErrorData());
		}
		
		catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	
	/*
	 * controller to find vacancies by location
	 */
	@RequestMapping(value = "/location/companies", method = RequestMethod.GET)
	public ModelAndView findByLocation(@RequestParam("location") String location, HttpSession session) {
		ModelAndView model = new ModelAndView("viewbylocation");
		ArrayList<JobVacancy> vacancies = new ArrayList<JobVacancy>();
		try {

			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				int userId = (Integer) session.getAttribute("userId");
				vacancies = companyDelegate.retrieveVacancyByLocation(location, userId);
				model.addObject("retrieveByLocation", vacancies);
			}
		} catch (ApplicationException e) {
			model = new ModelAndView("viewbylocation");
		}
		
		catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * controller to view all reviews of a company
	 */
	@RequestMapping(value = "/company/reviews", method = RequestMethod.POST)
	public ModelAndView viewCompanyReviews(@RequestParam("company") String companyName, HttpSession session) {
		ModelAndView model = new ModelAndView("viewallreviews");
		Company reviews = null;
		try {
			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				reviews = companyDelegate.retrieveReviewOfACompany(companyName);
				model.addObject("displayCompany", reviews.getCompanyDetails());
				model.addObject("displayCompanyReviews", reviews.getCompanyReviews());
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * controller to view all reviews of all interview process of a company
	 */
	@RequestMapping(value = "/company/interviews", method = RequestMethod.POST)
	public ModelAndView viewReviewsOnInterviewProcess(@RequestParam("company") String companyName,
			HttpSession session) {
		ModelAndView model = new ModelAndView("interviewprocess");
		Company interviews = null;
		try {

			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				interviews = companyDelegate.retrieveInterviewsOfACompany(companyName);
				model.addObject("displayCompany", interviews.getCompanyDetails());
				model.addObject("displayInterviewProcess", interviews.getCompanyInterviews());
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * controller to display rating page
	 */
	@RequestMapping(value = "/company/rate", method = RequestMethod.GET)
	public ModelAndView ViewRatingPage(@RequestParam("companyname") String companyName, HttpSession session) {
		ModelAndView model = new ModelAndView("reviewandrating");
		try {
			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				ArrayList<JobMapping> job = jobDelegate.displayJobs();
				model.addObject("jobs", job);
				model.addObject("companyname", companyName);
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * controller to rate a company
	 */
	@RequestMapping(value = "/company/rate", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView RateACompany(@RequestParam("company") String companyName, @RequestParam("rating") String rating,
			@RequestParam("review") String review, @RequestParam("job") String jobRole,
			@RequestParam("interview") String interviewProcess, HttpSession session) {
		ModelAndView model = new ModelAndView("viewallreviews");
		Company rate = null;
		try {
			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {

				int userId = (Integer) session.getAttribute("userId");
				rate= userDelegate.reviewAndRateACompany(userId,companyName,rating,review,jobRole,interviewProcess);
				model.addObject("displayCompany", rate.getCompanyDetails());
				model.addObject("displayCompanyReviews", rate.getCompanyReviews());
				
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * controller to fetch applied users
	 */
	@RequestMapping(value = "/applied-users", method = RequestMethod.GET)
	public ModelAndView AppliedUsers(HttpSession session) {
		ModelAndView model = new ModelAndView("viewinterestedusers");
		try {
			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {

				int userId = (Integer) session.getAttribute("userId");
				ArrayList<ApplyJob> appliedUsers = companyDelegate.viewAppliedUsers(userId);
				model.addObject("appliedUsers", appliedUsers);

			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * controller to mark users as contacted
	 */
	@RequestMapping(value = "/contacted-users", method = RequestMethod.POST)
	public void UpdateContactedUsers(@RequestParam("location") String location, @RequestParam("emailId") String emailId,
			@RequestParam("job") String jobDesignation, HttpServletResponse response, HttpSession session)
			throws IOException {
		PrintWriter out = response.getWriter();
		try {
			if (session.getAttribute("email") == null) {
				response.sendRedirect("home.jsp");
			} else {

				response.setContentType("text/html;charset=UTF-8");
				int userId = (Integer) session.getAttribute("userId");
				if (userDelegate.markContacted(userId, emailId, jobDesignation, location).equals("Success")) {
					out.print("success");
					out.flush();
				}
			}
		} catch (Exception e) {

			out.print("error");
			out.flush();
		}
	}

	/*
	 * controller to fetch all published jobs by admin
	 */
	@RequestMapping(value = "company/jobspublished", method = RequestMethod.GET)
	public ModelAndView ViewPublishedJobs(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("viewpublishedjobs");
		try {

			HttpSession session = request.getSession();
			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				int userId = (Integer) session.getAttribute("userId");
				Company vacancyDetails = companyDelegate.retrieveVacancyByCompanyAdmin(userId);
				model.addObject("jobs", vacancyDetails.getJobs());
				model.addObject("vacancyDetails", vacancyDetails.getJobVacancy());
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	/*
	 * controller to update published jobs
	 */
	@RequestMapping(value = "/company/jobspublished", method = RequestMethod.POST)
	public void UpdatePublishedJobs(@RequestParam("do") String action,@RequestParam("jobdesignation") String jobDesignation,
			@RequestParam("oldlocation") String oldLocation,
			@RequestParam("job") String newJobDesignation,@RequestParam("location") String location,
			@RequestParam("description") String jobDescription,@RequestParam("salary") String salary,
			@RequestParam("count") String count,HttpServletResponse response,HttpSession session) throws IOException {
	
		Company vacancies = null;
		PrintWriter out = response.getWriter();
		try {
			
				System.out.println(action);
				int userId = (Integer) session.getAttribute("userId");
					if ("UPDATE".equals(action)) {
					vacancies = userDelegate.UpdateVacancy(jobDesignation,oldLocation,userId,newJobDesignation,location,jobDescription,salary,count);
					if(vacancies != null) {
						out.print("updateSuccess");
						out.flush();
					}
				}else if ("DELETE".equals(action)) {
					vacancies = companyDelegate.removeVacancy(userId,jobDesignation,oldLocation);
					if (vacancies != null) {
						out.print("deleteSuccess");
						out.flush();
					}
				}
				
		} catch (Exception e) {
			out.print("error");
			out.flush();
		}
		
	}
}
