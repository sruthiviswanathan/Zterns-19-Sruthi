package com.zilker.onlinejobsearch.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

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
import com.zilker.onlinejobsearch.beans.JobMapping;
import com.zilker.onlinejobsearch.beans.JobVacancy;
import com.zilker.onlinejobsearch.delegate.CompanyDelegate;
import com.zilker.onlinejobsearch.delegate.JobDelegate;
import com.zilker.onlinejobsearch.delegate.UserDelegate;

@Controller
public class JobController {

	@Autowired
	UserDelegate userDelegate;

	@Autowired
	JobDelegate jobDelegate;

	@Autowired
	CompanyDelegate companyDelegate;

	@RequestMapping(value = "/jobdesignation/companies", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView findJobs(@RequestParam("job") String jobDesignation, HttpSession session,
			HttpServletResponse response) throws ApplicationException {
		ModelAndView model = new ModelAndView("viewjobs");
		ArrayList<JobVacancy> vacancyDetails = new ArrayList<JobVacancy>();
		try {
			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {

				int userId = (Integer) session.getAttribute("userId");
				ArrayList<String> jobRole = new ArrayList<String>();
				jobRole.add(jobDesignation);
				vacancyDetails = jobDelegate.retrieveVacancyByJob1(jobDesignation, userId);
				model.addObject("job", jobRole);
				model.addObject("displayVacancy", vacancyDetails);

			}
		} catch (ApplicationException e) {
			model = new ModelAndView("viewjobs");
			model.addObject("noJobDesignation", "yes");
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	@RequestMapping(value = "/company/jobs/apply", method = RequestMethod.POST)
	public void ApplyJobs(@RequestParam("location") String location, @RequestParam("companyName") String companyName,
			@RequestParam("jobDesignation") String jobDesignation, HttpServletResponse response, HttpSession session)
			throws IOException {
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("text/html;charset=UTF-8");
			int userId = (Integer) session.getAttribute("userId");
			String email = (String) session.getAttribute("email");
			if (userDelegate.applyForJob(companyName, jobDesignation, location, userId, email).equals("Success")) {
				out.print("success");
				out.flush();
			}
		} catch (ApplicationException e) {
			out.print("error");
			out.flush();
		} catch (Exception e) {
			out.print("error");
			out.flush();
		}
	}

	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ModelAndView GetAllJobDesignations(HttpSession session) {
		ModelAndView model = new ModelAndView("postjob");
		try {
			if (session.getAttribute("email") == null) {
				model = new ModelAndView("home");
			} else {
				ArrayList<JobMapping> job = jobDelegate.displayJobs();
				model.addObject("jobs", job);
			}
		} catch (Exception e) {
			model = new ModelAndView("error");
		}
		return model;
	}

	@RequestMapping(value = "/company/vacancy", method = RequestMethod.POST)
	public void PublishNewVacancy(@RequestParam("job") String jobDesignation, @RequestParam("location") String location,
			@RequestParam("salary") String salary, @RequestParam("count") String count,
			@RequestParam("description") String description, HttpSession session, HttpServletResponse response)
			throws IOException, ApplicationException {

		PrintWriter out = response.getWriter();
		try {
			if (session.getAttribute("email") == null) {
				response.sendRedirect("index.jsp");
			} else {
				int userId = (Integer) session.getAttribute("userId");
				if (companyDelegate.publishVacancy(userId, jobDesignation, location, salary, count, description)
						.equals("Success")) {
					out.print("success");
					out.flush();
				}
			}
		} catch (ApplicationException e) {
			out.print("error");
			out.flush();
		} catch (Exception e) {
			out.print("error");
			out.flush();
		}
	}

	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	public void AddNewJobDesignation(@RequestParam("newjob") String jobRole,HttpServletResponse response,HttpSession session)
			throws Exception {
		PrintWriter out = response.getWriter();
		
		try {
			
				int userId = (Integer) session.getAttribute("userId");
				if (jobDelegate.addNewJob(jobRole, userId).equals("Success")) {
					out.print("success");
					out.flush();
				}
			
		}catch (ApplicationException e) {
			out.print("jobDesignationExists");
			out.flush();
		} 
		catch (Exception e) {
			out.print("error");
			out.flush();
		}
	
	}
}
