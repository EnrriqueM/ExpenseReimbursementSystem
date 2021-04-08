package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import models.User;
import models.UserType;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(Dashboard.class);
       
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
		//Ensure this page cannot be catched
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
				
		//Attempt to pull in current user
		//If no user sign in, then redirect to login page
		User u = (User) request.getSession().getAttribute("sessUser");
		if(u == null)
		{
			//No user found, forward to login page
			/*RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/login");
		    dispatcher.forward(request, response);*/
			response.setStatus(403);
			LOGGER.warn("Dashboard - no user in session");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		else if(u.getUserType() == UserType.EMPLOYEE)
		{
			request.getRequestDispatcher("resources/Pages/dashboard.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("resources/Pages/fmDashboard.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
