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

import com.project.transactions.Transaction;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet({ "/Dashboard", "/" })
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Transaction transaction;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires", -1);
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		ArrayList<String[]> list = transaction.fetchNgoEvents((String)request.getSession().getAttribute("username"));
		if (list == null) {
			request.getSession().setAttribute("username", null);
			response.sendRedirect("Login");
		}
		request.getSession().setAttribute("Events", list);
		getServletContext()
		.getRequestDispatcher("/dashboard.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("username", null);
		response.sendRedirect("/Ngo");
	}
}
