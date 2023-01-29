package com.uniquedeveloper.registration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletResponse reponse;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		String uname=request.getParameter("name");
		String uemail=request.getParameter("email");
		String upwd=request.getParameter("pass");
		String umobile=request.getParameter("contact");
	    Connection con=null;

		
		RequestDispatcher dispatcher=null;
		try
		{
			Class.forName("com.mysql.cj.jbdc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MY_DB?useSSL=false", "root","albina123");
			PreparedStatement pst=con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)" );
			pst.setString(1,uname);
			pst.setString(2, upwd);
			pst.setString(3, uemail);
			pst.setString(4, umobile);
			
			int rowCount=pst.executeUpdate();
			dispatcher=request.getRequestDispatcher("registration.jsp");
			if(rowCount>0)
			{
				request.setAttribute("status","sucess");
				
			}
			else
			{
				request.setAttribute("status", "failed");
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
      finally
      {
    	  try {
    		 // con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MY_DB", "root","albina123");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
}
}