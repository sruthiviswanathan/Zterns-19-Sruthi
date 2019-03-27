package com.zilker.servlet.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zilker.servlet.bean.Student;
import com.zilker.servlet.delegate.StudentDelegate;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
		ArrayList<Student> stud = new ArrayList<Student>();
		Student student = new Student();
		StudentDelegate studentDelegate = new StudentDelegate();
		
		stud = studentDelegate.DisplayStudentDetails(student);
		request.setAttribute("studentList", stud); 

		getServletConfig().getServletContext().getRequestDispatcher("/Pages/displayusers.jsp").forward(request,response);
		}catch(Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		
		StudentDelegate studentDelegate = new StudentDelegate();
		Student student = new Student();
		String name = request.getParameter("userName");
		String pass = request.getParameter("userPass");
		String email = request.getParameter("userEmail");
		String country = request.getParameter("userCountry");
		
		student.setName(name);
		student.setPassword(pass);
		student.setEmail(email);
		student.setCountry(country);
		
		if (studentDelegate.insertStudentDetails(student)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Pages/Success.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("/Pages/Retry.jsp");
		}
		
		}catch(Exception e) {
			response.sendRedirect("/Pages/Retry.jsp");
		}
	}

}
