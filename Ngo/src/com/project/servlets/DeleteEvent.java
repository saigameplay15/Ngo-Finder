package com.project.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.transactions.Transaction;

/**
 * Servlet implementation class DeleteEvent
 */
@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Transaction transaction;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("/Ngo");
			return;
		}
		String name = request.getParameter("EventName");
		if (transaction == null) {
			transaction = new Transaction();
		}
		String username = (String)request.getSession().getAttribute("username");
		transaction.addToPastEvents(username, name);
		transaction.deleteEvent(username, name);
		response.getWriter().append("Event Saved Successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("/Ngo");
			return;
		}
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		String name = request.getParameter("EventName");
		String username = (String)request.getSession().getAttribute("username");
		transaction.deleteEvent(username, name);
		response.getWriter().append("Event Successfully Deleted");
	}

}
