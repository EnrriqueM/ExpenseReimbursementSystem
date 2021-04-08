package tests;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import dao.ReimbursementDao;
import exceptions.UnauthorizedException;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import models.UserType;
import services.ReimbursementService;

public class ReimbServiceTest {
	@Mock
	ReimbursementDao dao;
	
	@InjectMocks
	ReimbursementService actSrv;
	
	private Reimbursement getNewApprovedReimbursement() {
		Reimbursement a = new Reimbursement();
		a.setStatus(ReimbursementStatus.APPROVED);
		a.setAmount(26);
		return a;
	}
	
	
	@Test
	public void testCreateNewAccount() {
		User dummyUser = new User();
		dummyUser.setId(1);
		//Reimbursement act = actSrv.createNewReimb(dummyUser, 25.0, "Sample Description", null, 2);
		Reimbursement act = new Reimbursement();
		act.setStatus(ReimbursementStatus.PENDING);
		act.setAmount(25.0);
		assertEquals(act.getAmount(), 25.0, 0.01);
		assertFalse(act.getStatus().equals(ReimbursementStatus.PENDING));
	}
	
	@Test
	public void testUserCanViewAccountBalances() {
		User dummyUser = new User();
		Reimbursement act = actSrv.createNewReimb(dummyUser, 25.0, "Sample Description", null, 2);
		act.setStatus(ReimbursementStatus.APPROVED);

		assertEquals(act.getStatus(), ReimbursementStatus.APPROVED);
	}
	
	@Test
	public void testManagerCanApproveAccount() {
		User dummyEmpl = new User();
		dummyEmpl.setUserType(UserType.EMPLOYEE);
		User dummyCustomer = new User();
		dummyCustomer.setUserType(UserType.FINANCE_MANAGER);
		Reimbursement act = actSrv.createNewReimb(dummyEmpl, 25.0, "Sample Description", null, 2);
		assertFalse(act.getStatus().equals( ReimbursementStatus.APPROVED));
		//actSrv.approveOrRejectReimb(act, true);
		//assertTrue(act.isApproved());
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testCustomerCannotApproveAccount() {
		User dummyCustomer = new User();
		dummyCustomer.setUserType(UserType.EMPLOYEE);
		Reimbursement act = actSrv.createNewReimb(dummyCustomer, 25.0, 
				"Sample Description", null, 2);
		actSrv.approveOrRejectReimb(1, dummyCustomer, true);
	}
	
}
