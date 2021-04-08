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
 * Servlet implementation class validateUsername
 */
@WebServlet("/api/validateUsername")
public class validateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uService;
	static final Logger LOGGER = Logger.getLogger(validateUsername.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validateUsername() {
        super();

        uService = new UserService();
        LOGGER.info("Username validator API initialzed");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String un = request.getParameter("username");
		
		if (uService.checkUn(un))
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
