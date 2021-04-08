package dao;

import java.util.List;

import models.Reimbursement;
import models.User;

public interface ReimbursementDao {
	/**
	 * Adds a new reimbursement into the persistence layer
	 * @param r the reimbursement object to add
	 * @return the same reimbursement that was added
	 */
	public Reimbursement addReimbursement(Reimbursement r);
	
	/**
	 * Retrieves a reimbursement
	 * @param reimbId the id of the reimbursement to retrieve
	 * @return the reimbursement object
	 */
	public Reimbursement getReimbursement(Integer reimbId);
	
	/**
	 * Retrieves all reimbursements
	 * @return a list of all reimbursements
	 */
	public List<Reimbursement> getReimbursements();
	
	/**
	 * Retrieves reimbursements by a particular user
	 * @param u the user object to search by
	 * @return a list of reimbursements that the user submitted
	 */
	public List<Reimbursement> getReimbursementsByUser(User u);
	
	/**
	 * Updates a specific reimbursement
	 * @param r the reimbursement to update
	 * @return the updated reimbursement
	 */
	public Reimbursement updateReimbursement(Reimbursement r);
	
	/**
	 * Deletes a reimbursement from the persistence layer
	 * @param r the reimbursement to delete
	 * @return true if the deletion was successful; false if not
	 */
	public boolean removeReimbursement(Reimbursement r);
}
