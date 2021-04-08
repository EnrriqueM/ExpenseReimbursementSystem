package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import models.Reimbursement;
import models.User;
import models.UserType;

/**
 * Servlet implementation class ViewDetails
 */
@WebServlet({ "/viewdetails", "/ViewDetails", "/viewDetails" })
public class ViewDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementDao rDao;
	static final Logger LOGGER = Logger.getLogger(ViewDetails.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDetails() {
        super();
        
        rDao = new ReimbursementDaoImpl();
        LOGGER.info("ViewDetails init");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("reimbId");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		//Attempt to pull in current user
		//If no user sign in, then redirect to login page
		User u = (User) request.getSession().getAttribute("sessUser");
		if(u == null)
		{
			LOGGER.warn("View - No user in session");
			response.setStatus(403);
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		//Check if id was passed
		if(id == null)
		{
			LOGGER.info("View - No reimb id was passed");
			response.setStatus(404);
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		
		
		Reimbursement reimb = rDao.getReimbursement(Integer.parseInt(id));
		
		//Check if reimb was retireved
		if(reimb == null)
		{
			LOGGER.warn("View - No reimb of id: " + id + " found");
			response.setStatus(404);
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		//Check if reimb author id is equal to current user id
		//Fm can view as well
		else if(reimb.getAuthor_id().equals(u.getId()) || u.getUserType() == UserType.FINANCE_MANAGER)
		{
			request.getSession().setAttribute("reimb", reimb);
			request.getRequestDispatcher("resources/Pages/viewDetails.jsp").forward(request, response);
			return;
		}
		
		//Author id and current user id do not match
		LOGGER.warn("View - Unauthorize attempt to view a reimb by unatorized employee");
		response.sendRedirect(request.getContextPath() + "/dashboard");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
