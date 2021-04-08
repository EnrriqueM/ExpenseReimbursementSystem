package api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import models.Reimbursement;
import models.User;
import models.UserType;
import servlets.login;

/**
 * Servlet implementation class GetAllReimbursements
 */
@WebServlet({ "/api/GetAllReimbursements", "/api/getAllReimbursements" })
public class GetAllReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementDao rDao;
	static final Logger LOGGER = Logger.getLogger(GetAllReimbursements.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllReimbursements() {
        super();
        
        rDao = new ReimbursementDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("sessUser");
		
		//If no user found, return an error
		//No regular employee should have access
		if(u == null || u.getUserType() == UserType.EMPLOYEE)
		{
			response.setStatus(403);
			LOGGER.warn("No Auth user in session");
			return;
		}
		
		//Fetch reimb from dao
		List<Reimbursement> userReimbList = rDao.getReimbursements();
		
		response.setStatus(200);
		response.setContentType("application/json");
		response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userReimbList));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
