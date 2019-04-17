package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class NgoHome
 */
@WebServlet("/NgoHome")
public class NgoHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ngo;
	private static Transaction transaction;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public NgoHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		ngo = request.getParameter("NgoName");
		request.getSession().setAttribute("ngo",ngo);
		
		ArrayList<String[]> list = transaction.fetchEvents(ngo);
		request.getSession().setAttribute("events-list", list);
		
		getServletContext()
		.getRequestDispatcher("/ngo_home.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String btn = request.getParameter("btn");
		String user = (String) request.getSession().getAttribute("username");
		String ngo = (String) request.getSession().getAttribute("ngo");
		if (btn.equals("Subscribe")) {
			int length = transaction.subscribe(user,ngo);
			if (length < 1) {
				response.getWriter().println("Subscription successful");
			}
			else if (length == 1){
				response.getWriter().println("Already Subscribed");
			}
		}
		else if (btn.equals("Unsubscribe")) {
			int length = transaction.unsubscribe(user, ngo);
			if (length < 1) {
				response.getWriter().println("You haven't Subscribed Yet");
			} else if (length == 1) {
				response.getWriter().println("Unsubscription Successful");
			}
		}
		else if (btn.equals("Donate")) {
			//implement donate
			return;
		}
		else if (btn.equals("Share")) {
			//implement share
			return;
		}
		else {
			response.sendRedirect("Login");
			return;
		}
	}
}
