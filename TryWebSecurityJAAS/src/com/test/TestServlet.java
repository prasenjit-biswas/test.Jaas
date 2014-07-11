package com.test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response){
		System.out.println(" in doGet ");
		System.out.println(" u r authenticated");
		String authHeader= "" + request.getHeader("Authorization");
		System.out.println(" authHeader : "+authHeader);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response){
		System.out.println(" in doPost ");
	}
}
