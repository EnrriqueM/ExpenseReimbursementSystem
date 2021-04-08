package services;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import exceptions.UnauthorizedException;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import models.UserType;

public class ReimbursementService 
{
	private ReimbursementDao reimbDao;
	
	public ReimbursementService() {
		this.reimbDao = new ReimbursementDaoImpl();
	}
	
	/**
	 * Creates a new reimbursement claim for a given User
	 * @return the Account object that was created
	 */
	public Reimbursement createNewReimb(User u, double amount, String descr, byte[] file, int type) {
		Reimbursement newReimb = new Reimbursement();
		
		//Set owner id  = to this user id
		newReimb.setAuthor_id(u.getId());
		newReimb.setAmount(amount);
		newReimb.setDescription(descr);
		newReimb.setFile(file);
		newReimb.setStatus(ReimbursementStatus.PENDING);
		switch(type)
		{
		case 1:
			newReimb.setType(ReimbursementType.LODGING);
			break;
		case 2:
			newReimb.setType(ReimbursementType.FOOD);
			break;
		case 3:
			newReimb.setType(ReimbursementType.TRAVEL);
			break;
		default:
			newReimb.setType(ReimbursementType.OTHER);
				
		}

		//Add to DB
		reimbDao.addReimbursement(newReimb);
		
		return newReimb;
	}
	
	/**
	 * Approve or reject an account.
	 * @param a
	 * @param approval
	 * @throws UnauthorizedException if logged in user is not an Finance Manager
	 * @return true if account is approved, or false if denied
	 */
	public boolean approveOrRejectReimb(int reimbId, User u, boolean approval) {
		
		if(u.getUserType() == UserType.FINANCE_MANAGER)
		{
			//Query the reimb
			Reimbursement reimb = reimbDao.getReimbursement(reimbId);
			
			if(reimb != null)
			{
				reimb.setStatus(approval ? ReimbursementStatus.APPROVED : ReimbursementStatus.DENIED);
				reimb.setResolver_id(u.getId());

				//Update DB
				reimbDao.updateReimbursement(reimb);
			}
		}
		else
		{
			throw new UnauthorizedException("Unauthorized action");
		}

		return approval;
	}
}
