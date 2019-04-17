package com.project.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.transactions.Transaction;

/**
 * Servlet implementation class EditEvent
 */
@WebServlet("/EditEvent")
public class EditEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Transaction transaction;
	private static String n;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}
		
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		request.getSession().setAttribute("edit", null);
		
		request.getSession().setAttribute("ename", "");
		request.getSession().setAttribute("desc", "");
		request.getSession().setAttribute("loc", "");
		request.getSession().setAttribute("sd", "");
		request.getSession().setAttribute("ed", "");
		request.getSession().setAttribute("st", "");
		request.getSession().setAttribute("et", "");
		
		if (request.getParameter("event_name") != null) {
			request.getSession().setAttribute("edit", "1");
			String name = request.getParameter("event_name");
			n = name;
			request.getSession().setAttribute("ename", name);
			request.getSession().setAttribute("desc", request.getParameter("description"));
			request.getSession().setAttribute("loc", request.getParameter("location"));
			request.getSession().setAttribute("sd", request.getParameter("sd"));
			request.getSession().setAttribute("ed", request.getParameter("ed"));
			request.getSession().setAttribute("st", request.getParameter("st"));
			request.getSession().setAttribute("et", request.getParameter("et"));
			
			
			getServletContext()
			.getRequestDispatcher("/editEvent.jsp")
			.forward(request, response);
			
		} else {
			getServletContext()
			.getRequestDispatcher("/addevent.jsp")
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		if (request.getSession().getAttribute("edit") != null) {
			transaction.deleteEvent((String)request.getSession().getAttribute("username"), n);
		}
		
		request.getSession().setAttribute("edit", null);
		request.getSession().setAttribute("ename", null);
		request.getSession().setAttribute("desc", null);
		request.getSession().setAttribute("loc", null);
		request.getSession().setAttribute("sd", null);
		request.getSession().setAttribute("ed", null);
		request.getSession().setAttribute("st", null);
		request.getSession().setAttribute("et", null);
		request.setAttribute("relocate", true);
		
		String name = request.getParameter("EventName");
		String desc = request.getParameter("Description");
		String loc = request.getParameter("Location");
		String sd = request.getParameter("StartDate");
		String ed = request.getParameter("EndDate");
		String st = request.getParameter("StartTime");
		String et = request.getParameter("EndTime");
		transaction.addEvent((String)request.getSession().getAttribute("username"), sd, ed, name, desc, st, et, loc);
	}
}
