package com.project.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.transactions.Transaction;

/**
 * Servlet implementation class Subscribed
 */
@WebServlet("/Subscribed")
public class Subscribed extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Transaction transaction;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subscribed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		ArrayList<String[]> events = transaction.fetchSubscribedEvents((String)request.getSession().getAttribute("username"));
		request.getSession().setAttribute("result", events);
		getServletContext()
		.getRequestDispatcher("/new_index.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String lat = request.getParameter("lat");
		String lon = request.getParameter("lon");
		request.getSession().setAttribute("lat", lat);
		request.getSession().setAttribute("lon", lon);
		response.sendRedirect("hello?filter=None");
	}

}
