package com.project.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.transactions.Transaction;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/Login"})
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
		if (request.getSession().getAttribute("username") != null) {
			response.sendRedirect("Dashboard");
			return;
		}
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		transaction = new Transaction();
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		request.getSession().setAttribute("Transaction",transaction);
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
		int k = -1;
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		while (k == -1) {
			if ((k = transaction.isNgo(email,pass)) == 1) {
				break;
			} else if (k == 0) {
				//display error
				response.sendRedirect("Login");
				return;
			}
		}
		
		request.getSession().setAttribute("username", email);
		response.sendRedirect("/Ngo");
	}
}
