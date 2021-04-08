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
import exceptions.UnauthorizedException;
import models.Reimbursement;
import models.User;
import models.UserType;
import services.ReimbursementService;

/**
 * Servlet implementation class ResolveReimb
 */
@WebServlet({ "/ResolveReimb", "/resolve" })
public class ResolveReimb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementDao rDao;
	private ReimbursementService rService;
	static final Logger LOGGER = Logger.getLogger(ResolveReimb.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolveReimb() {
        super();
        
        rDao = new ReimbursementDaoImpl();
        rService = new ReimbursementService();
        LOGGER.info("resolve reimb init");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("reimbId");
		
		//Ensure this page cannot be catched
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		//Attempt to pull in current user
		//If no user sign in, then redirect to login page
		User u = (User) request.getSession().getAttribute("sessUser");
		if(u == null)
		{
			LOGGER.warn("ResolveReimb - No user in session");
			response.setStatus(403);
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		//Check if id was passed
		if(id == null)
		{
			LOGGER.warn("ResolveReimb - No id passed");
			response.setStatus(404);
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		
		
		Reimbursement reimb = rDao.getReimbursement(Integer.parseInt(id));
		
		//Check if reimb was retireved
		if(reimb == null)
		{
			LOGGER.warn("ResolveReimb - No Reimb of id: " + id + " found");
			response.setStatus(404);
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		//Check if reimb author id is equal to current user id
		//Fm can view as well
		else if(u.getUserType() == UserType.FINANCE_MANAGER)
		{
			request.getSession().setAttribute("reimb", reimb);
			LOGGER.info("ResolveReimb - Reimb of id: " + id + " is being modify");
			request.getRequestDispatcher("resources/Pages/resolveReimb.jsp").forward(request, response);
			return;
		}
		else
		{
			LOGGER.warn("ResolveReimb - Unauthorize attempt to modify Reimb");
			request.getRequestDispatcher("resources/Pages/viewDetails.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ensure this page cannot be catched
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		String resolve = request.getParameter("resolved");
		String id = request.getParameter("id");
		
		//Check if id was passed
		if(id == null)
		{
			response.setStatus(404);
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		//Attempt to pull in current user
		//If no user sign in, then redirect to login page
		User u = (User) request.getSession().getAttribute("sessUser");
		if(u == null)
		{
			LOGGER.warn("ResolveReimb - No user in session");
			response.setStatus(403);
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		try
		{
			int reimbId = Integer.parseInt(id);
			
			rService.approveOrRejectReimb(reimbId, u, resolve.equals("1") ? true : false);
			
			LOGGER.info("ResolveReimb - Reimb of id: " + id + " has been modify");
		}
		catch(UnauthorizedException e)
		{
			LOGGER.warn("ResolveReimb - Unauthorize attempt to modify Reimb");
			System.out.println(e.getMessage());
		}
		
		response.sendRedirect(request.getContextPath() + "/dashboard");
	}

}
