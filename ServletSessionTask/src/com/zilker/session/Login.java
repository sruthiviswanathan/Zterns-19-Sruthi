package com.zilker.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		 HttpSession session=request.getSession(); 
		String pass = request.getParameter("userPass");
		String email = request.getParameter("userEmail");
		   System.out.println(pass);
		   System.out.println(email);		   
		String name="";
		int count=0;
		Connection conn = null;

		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","root@123");

//			String query ="select email,pass,name from REGISTERUSER";
			PreparedStatement ps = conn.prepareStatement("SELECT EMAIL,PASS,NAME FROM REGISTERUSER");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(email.equals(rs.getString(1)) &&  pass.equals(rs.getString(2)) ) {
					
					count =1;
					name = rs.getString(3);
				    session.setAttribute("uname",name); 
				    response.sendRedirect("Profile");
				}
			}
			if(count==0) {
				System.out.println("invalid credentials");
				response.sendRedirect("login.html");
			}
			
			
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

}
