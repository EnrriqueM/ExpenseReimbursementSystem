package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import models.User;
import services.ReimbursementService;

/**
 * Servlet implementation class CreateReimbursement
 */
@WebServlet({ "/CreateReimbursement", "/createReimbursement" })
@MultipartConfig // This annotation tells the server that this servlet has
// complex data other than forms
public class CreateReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementService rService;
	private static DecimalFormat df = new DecimalFormat("0.00");
	static final Logger LOGGER = Logger.getLogger(CreateReimbursement.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReimbursement() {
        super();
        
        rService = new ReimbursementService();
        LOGGER.info("Create reimbursements init");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get User from session
		User u = (User) request.getSession().getAttribute("sessUser");
		
		//If no user found, return an error
		if(u == null)
		{
			response.setStatus(403);
			response.sendRedirect(request.getContextPath() + "/login");
			LOGGER.warn("Create Reimb - No user in session");
			return;
		}
		
		//Ensure this page cannot be catched
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
				
		request.getRequestDispatcher("resources/Pages/newReimbursement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Ensure this page cannot be catched
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
				
		//Get user from session
		User u = (User) request.getSession().getAttribute("sessUser");
		
		//If no user found, return an error
		if(u == null)
		{
			LOGGER.warn("Create Reimb - No user in session");
			response.setStatus(403);
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		String amount = request.getParameter("amount");
		String descr = request.getParameter("description");
		String type = request.getParameter("reimbType");
		
		System.out.println(amount);
		
		// Uploading a file requires the data to be sent in "parts", because
		// one HTTP packet might not be big
		// enough anymore for all of the data. Here we get the part that has
		// the file data
		/*Part content = request.getPart("file");

		InputStream is = null;
		ByteArrayOutputStream os = null;
		byte[] fileContent = null;

		try {
			is = content.getInputStream();
			os = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			while (is.read(buffer) != -1) {
				os.write(buffer);
			}
			
			fileContent = os.toByteArray();

		} catch (IOException e) {
			System.out.println("Could not upload file!");
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
		}*/
		
		//Cast amount to double
		double amountDbl = Double.parseDouble(amount);
		//Cast type to int
		int typeInt = Integer.parseInt(type);
		
		System.out.println("Amount: " + df.format(amountDbl)  + " Type: " + typeInt + " Desc: " + descr);
		
		rService.createNewReimb(u, amountDbl, descr, null, typeInt);
		//Forward to success page, TEMP TO DASHABORD
		response.setStatus(201);
		response.sendRedirect(request.getContextPath() + "/dashboard");
	}

}
