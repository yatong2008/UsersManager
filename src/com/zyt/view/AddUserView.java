package com.zyt.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserView extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<img src='imgs/hello_logo.jpg' /> <a href='MainFrame'>Go back to the main page</a><a href='LoginServlet'>Log out</a><hr/>");

		out.print("<h1>Add User</h1>");
		out.print("<form action='/UsersManager/UserClServlet?type=add' method='post'>");
		out.print("<table border=1px>");
		out.print("<tr><td>ID</td><td><input type='text' name='id' readonly value='Invalid'></td></tr>");
		out.print("<tr><td>user name</td><td><input type='text' name='username' value=''></td></tr>");
		out.print("<tr><td>email</td><td><input type='text' name='email' value=''></td></tr>");
		out.print("<tr><td>grade</td><td><input type='text' name='grade' value=''></td></tr>");
		out.print("<tr><td>password</td><td><input type='text' name='password' value=''></td></tr>");
		out.print("<tr><td><input type='submit' value='Add'/></td><td><input type='reset' value='reset'/></td></tr>");
		out.print("</table>");
		out.print("</from>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
