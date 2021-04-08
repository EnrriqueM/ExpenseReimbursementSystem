package driver;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import models.Reimbursement;
import models.ReimbursementType;
import models.User;

public class ErsAppDriver {
	
	public final static Logger logger = Logger.getLogger(ErsAppDriver.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*System.out.println("Hello");
		User u = new User();
		
		
		UserDao uDao = new UserDaoImpl();
		User sub = uDao.getUser(1);
		
		System.out.println(u);
		System.out.println(sub);
		
		ReimbursementDao rDao = new ReimbursementDaoImpl();
		Reimbursement r = new Reimbursement();
		r.setAmount(25.99);
		r.setDescription("Get yo money bak");
		r.setAuthor_id(u.getId());
		r.setType(ReimbursementType.FOOD);
		//System.out.println(r.getFile().length);
		if(r.getFile() == null)
		{
			System.out.println("File does not exist");
		}
		else
		{
			System.out.println("File does exist");
		}*/
		
		
		logger.setLevel(Level.ALL);
	}

}
