package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import services.UserService;
import servlets.login;

/**
 * Servlet implementation class validateEmail
 */
@WebServlet("/api/validateEmail")
public class validateEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(validateEmail.class);
	
	private UserService uService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validateEmail() {
        super();
        
        uService = new UserService();
        LOGGER.info("Email Validator API initialzes");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String un = request.getParameter("email");
		
		if (uService.checkEmail(un))
		{
			response.setStatus(202);
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
