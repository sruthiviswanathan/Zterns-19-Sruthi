package com.zilker.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		//PrintWriter out = response.getWriter();

		String name = request.getParameter("userName");
		String pass = request.getParameter("userPass");
		String email = request.getParameter("userEmail");
		String country = request.getParameter("userCountry");

		Connection conn = null;

		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","root@123");

			PreparedStatement ps = conn.prepareStatement("insert into REGISTERUSER values(?,?,?,?)");

			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setString(3, email);
			ps.setString(4, country);

			int i = ps.executeUpdate();
			if (i > 0) {
				response.sendRedirect("login.html");
			}else {
				System.out.println("Registeration failed");
			}
				//out.print("You are successfully registered...");

		} catch (Exception e2) {
			System.out.println(e2);
		}

		//out.close();

	}

}
