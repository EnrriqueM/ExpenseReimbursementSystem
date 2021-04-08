package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exceptions.UsernameAlreadyExistsException;
import models.User;
import models.UserType;
import services.UserService;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService;
	static final Logger LOGGER = Logger.getLogger(register.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();

        uService = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get register.jsp
		request.getRequestDispatcher("resources/Pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Set up a new user
		User newUser = new User();
		
		//Get parameters
		String email = request.getParameter("email");
		String un = request.getParameter("username");
		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");
		String pwd = request.getParameter("password");
		String uType = request.getParameter("userRadio");
		
		newUser.setEmail(email);
		newUser.setFirstName(fn);
		newUser.setLastName(ln);
		newUser.setUsername(un);
		newUser.setPassword(pwd);
		if (uType.equals("fm"))
		{
			newUser.setUserType(UserType.FINANCE_MANAGER);
		}
		else
		{
			newUser.setUserType(UserType.EMPLOYEE);
		}
		
		//Try to create a new user, Throw if username or email is taken
		try
		{
			uService.register(newUser);
			
			//Clear any temp data
			request.getSession().removeAttribute("tempUser");
			
			//Forward to log in page
			/*RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/login");
		    dispatcher.forward(request, response);*/
			response.sendRedirect(request.getContextPath() + "/login");
		}
		catch(UsernameAlreadyExistsException e)
		{
			LOGGER.warn(e.getMessage());
			System.out.println(e.getMessage());
			// Populate the fields into a variable that will be stored in the session
			request.getSession().setAttribute("tempUser", newUser);
			doGet(request, response);
		}
		
	}

}
