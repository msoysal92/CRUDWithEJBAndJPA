package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.AccountFacade;
import entities.Account;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/account")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private AccountFacade accountFacade;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			request.setAttribute("listAccounts", accountFacade.findAll());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			if (action.equalsIgnoreCase("add"))
				request.getRequestDispatcher("add.jsp").forward(request, response);
			else if (action.equalsIgnoreCase("delete")) {
				try {
					int userId = Integer.parseInt(request.getParameter("userid"));
					if (userId == 1) {
						request.setAttribute("error", "You don't have permission for delete first account!");
						response.sendRedirect("account");
					} else {
						accountFacade.remove(accountFacade.find(userId));
						response.sendRedirect("account");
					}
				} catch (Exception e) {
					request.setAttribute("error", e.getMessage());
					request.setAttribute("listAccounts", accountFacade.findAll());
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} else if (action.equalsIgnoreCase("edit")) {
				int userId = Integer.parseInt(request.getParameter("userid"));
				request.setAttribute("account", accountFacade.find(userId));
				request.getRequestDispatcher("edit.jsp").forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("add")) {
			try {
				Account account = new Account();
				account.setAge(Integer.parseInt(request.getParameter("age")));
				account.setEmail(request.getParameter("email"));
				account.setFullname(request.getParameter("fullname"));
				account.setPassword(request.getParameter("password"));
				account.setUsername(request.getParameter("username"));
				accountFacade.create(account);
				response.sendRedirect("account");
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("add.jsp").forward(request, response);
			}

		} else if (action.equalsIgnoreCase("edit")) {
			try {
				System.out.println("a");
				Account account = accountFacade.find(Integer.parseInt(request.getParameter("userid")));
				account.setAge(Integer.parseInt(request.getParameter("age")));
				account.setEmail(request.getParameter("email"));
				account.setFullname(request.getParameter("fullname"));
				if (!request.getParameter("password").isEmpty())
					account.setPassword(request.getParameter("password"));
				account.setUsername(request.getParameter("username"));
				System.out.println("b");
				accountFacade.edit(account);
				response.sendRedirect("account");
				System.out.println("c");
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				request.setAttribute("account", accountFacade.find(Integer.parseInt(request.getParameter("userid"))));
				request.getRequestDispatcher("edit.jsp").forward(request, response);
			}

		}
	}

}
