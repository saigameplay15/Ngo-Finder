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
 * Servlet implementation class PastEvent
 */
@WebServlet("/PastEvent")
public class PastEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Transaction transaction;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PastEvent() {
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
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		try {
			ArrayList<String[]> list = transaction.fetchPastEvents((String)request.getSession().getAttribute("username"));
			request.getSession().setAttribute("pastevents", list);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Dashboard");
		}
		getServletContext()
		.getRequestDispatcher("/pastevents.jsp")
		.forward(request, response);
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
		transaction.deletePastEvent((String)request.getSession().getAttribute("username"), name);
		response.getWriter().append("Event Deleted Successfully");
	}

}
