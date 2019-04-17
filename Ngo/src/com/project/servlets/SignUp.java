package com.project.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.transactions.Transaction;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Transaction transaction;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		transaction = (Transaction)request.getSession().getAttribute("Transaction");
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		getServletContext()
		.getRequestDispatcher("/signup.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		int k = -1;
		if (transaction == null) {
			transaction = new Transaction();
			request.getSession().setAttribute("Transaction", transaction);
		}
		while (k == -1) {
			if ((k=transaction.exists(email)) == 1) {
				break;
			} else if (k == 0) {
				String type = request.getParameter("Type");
				String lat = request.getParameter("lat");
				String lon = request.getParameter("lon");
				String name = request.getParameter("Name");
				String ph_no = request.getParameter("Phone number");
				String pass = request.getParameter("psw");
				transaction.addNgo(email, name, ph_no, pass, lat, lon, type);
				request.getSession().setAttribute("username", email);
				break;
			}
		}
		response.sendRedirect("Login");
	}

}
