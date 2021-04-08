package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exceptions.InvalidCredentialsException;
import models.User;
import services.UserService;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService;
	static final Logger LOGGER = Logger.getLogger(login.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();

        //Init Service
        uService = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "HELL0" + "</h1>");*/
		
		request.getRequestDispatcher("resources/Pages/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//Get parameters
		String un = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		//Check credentials
		try 
		{
			User sessUser = uService.login(un, pwd);
			
			//Save user to session
			request.getSession().setAttribute("sessUser", sessUser);
			
			//Forward to dashboard page if success
			/*RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/dashboard");
		    dispatcher.forward(request, response);*/
			
			response.sendRedirect(request.getContextPath() + "/dashboard");
		}
		catch(InvalidCredentialsException e)
		{
			LOGGER.info("User Failed to Log in");
			System.out.println(e.getMessage());
			doGet(request, response);
		}
	}

}
