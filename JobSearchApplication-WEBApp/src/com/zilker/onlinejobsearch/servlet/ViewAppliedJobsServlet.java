package com.zilker.onlinejobsearch.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zilker.onlinejobsearch.beans.Company;
import com.zilker.onlinejobsearch.beans.User;
import com.zilker.onlinejobsearch.delegate.CompanyDelegate;
import com.zilker.onlinejobsearch.delegate.UserDelegate;

/**
 * Servlet implementation class ViewAppliedJobsServlet
 */
@WebServlet("/ViewAppliedJobsServlet")
public class ViewAppliedJobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAppliedJobsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session=request.getSession(); 
			if(session.getAttribute("email")==null){
				response.sendRedirect("index.jsp");
			}
			ArrayList<Company> appliedJobs = new ArrayList<Company>();
			//HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email");
			User user= new User();
			Company company = new Company();
			user.setEmail(email);
			UserDelegate userDelegate = new UserDelegate();
			CompanyDelegate companyDelegate = new CompanyDelegate();
			int userId=0;
			userId = userDelegate.fetchUserId(user);
			user.setUserId(userId);
			
			appliedJobs=companyDelegate.viewAppliedJobs(user);
			if (appliedJobs.isEmpty()) {
				//System.out.println("No interested users!!");
				request.setAttribute("noAppliedJobs","yes");
				
			} else {
				for (Company i : appliedJobs) {
					request.setAttribute("appliedJobs", appliedJobs);
					
				}
			}
			getServletConfig().getServletContext().getRequestDispatcher("/Pages/jsp/viewinterestedjobs.jsp").forward(request,response);	
		}catch(Exception e) {
			request.setAttribute("exception",e);
			RequestDispatcher rd = request.getRequestDispatcher("Pages/jsp/error.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
