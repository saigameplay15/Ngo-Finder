package com.project.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.transactions.Transaction;

/**
 * Servlet implementation class hello
 */
@WebServlet("/hello")
public class hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Double lat,lon; 
    private static Transaction transaction;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		try {
			lat = Double.valueOf((String) request.getSession().getAttribute("lat"));
			lon = Double.valueOf((String) request.getSession().getAttribute("lon"));
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("username", null);
			response.sendRedirect("Login");
			return;
		}
		String filter = request.getParameter("filter");
		ArrayList<String[]> result = transaction.fetchFilteredNgo(lat, lon, filter);
		request.getSession().setAttribute("results", result);
		getServletContext()
		.getRequestDispatcher("/main_index.jsp")
		.forward(request, response);
	}
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("username", null);
		response.sendRedirect("/User");
	}

}
