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
 * Servlet implementation class Login
 */
@WebServlet({ "/Login", "/" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Transaction transaction;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		if (request.getSession().getAttribute("username") != null) {
			response.sendRedirect("Subscribed");
			return;
		}
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		transaction = new Transaction();
		request.getSession().setAttribute("Transaction", transaction);
		getServletContext()
			.getRequestDispatcher("/login.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("username");
		String pass = request.getParameter("password");
		if (transaction == null) {
			transaction = new Transaction();
		}
		if (email == null || pass == null) {
			response.sendRedirect("Login");
		}
		int k = -1;
		while (k == -1) {
			if ((k = transaction.isUser(email,pass)) == 1) {
				break;
			} else if (k == 0) {
				//display error
				response.sendRedirect("Login");
				return;
			}
		}
		
		request.getSession().setAttribute("username", email);
		response.sendRedirect("Subscribed");
	}
}
