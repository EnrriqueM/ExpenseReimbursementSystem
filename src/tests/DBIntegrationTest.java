package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import models.Reimbursement;
import models.ReimbursementStatus;
import models.ReimbursementType;
import models.User;
import models.UserType;

public class DBIntegrationTest {
	//Init vars
	UserDao uDao = new UserDaoImpl();
	ReimbursementDao rDao = new ReimbursementDaoImpl();
	User u = new User();
	Reimbursement r = new Reimbursement();
	
	//ARRANGE
	@Before
	public void setUpAccountAndUser()
	{
		//Create Employee User
		u.setEmail("test@gmail.com");
		u.setFirstName("Arron");
		u.setLastName("Alcomnuray");
		u.setUsername("aaron97");
		u.setPassword("ThePassword1234");
		u.setUserType(UserType.EMPLOYEE);
		uDao.addUser(u);
		
		//Create a reimbursement
		r.setAmount(25.99);
		r.setDescription("Get yo money bak");
		r.setAuthor_id(u.getId());
		r.setType(ReimbursementType.FOOD);
		r.setStatus(ReimbursementStatus.PENDING);
		rDao.addReimbursement(r);
	}
	
	@After
	public void tearDown()
	{
		//Clear DB
		/*List<Reimbursement> allReimb = rDao.getReimbursements();
		for(Reimbursement reimb : allReimb)
		{
			rDao.removeReimbursement(reimb);
		}
		
		List<User> allUser = uDao.getAllUsers();
		for(User user : allUser)
		{
			uDao.removeUser(user);
		}*/
		
		uDao.removeUser(u);
	}
	
	/*
	 * REIMBURSEMENT INTEGRATION TESTS
	 */
	
	@Test
	public void testAddAccount() {
		//ACT
		Reimbursement fromDB = rDao.getReimbursement(r.getId());
		
		//ASSERT
		assertEquals(r, fromDB);
	}
	
	@Test
	public void testGetAllAccounts() {
		//ARRANGE
		Reimbursement secondReimb = new Reimbursement();
		secondReimb.setId(2);
		secondReimb.setAuthor_id(u.getId());
		secondReimb.setAmount(432.10);
		secondReimb.setType(ReimbursementType.LODGING);
		secondReimb.setStatus(ReimbursementStatus.APPROVED);
		rDao.addReimbursement(secondReimb);
		
		//ACT
		List<Reimbursement> all = rDao.getReimbursements();
		
		//ASSERT
		assertNotEquals(all.size(), 1);
	}
	
	@Test
	public void testUpdateAccount() {
		//ARRANGE
		
		//Create a finance manager first
		//Create Employee User
		User fm = new User();
		fm.setEmail("iman@gmail.com");
		fm.setFirstName("Itali");
		fm.setLastName("Manokey");
		fm.setUsername("iMan1");
		fm.setPassword("1234");
		fm.setUserType(UserType.FINANCE_MANAGER);
		uDao.addUser(fm);
		
		//Update reimbursement
		r.setStatus(ReimbursementStatus.APPROVED);
		r.setResolver_id(fm.getId());
		
		//ACT
		Reimbursement updated = rDao.updateReimbursement(r);
		
		//Remove user
		uDao.removeUser(fm);
				
		//ASSERT
		assertEquals(r, updated);
	}
	
	@Test
	public void testDeleteAccount() {
		//ACT
		boolean success = rDao.removeReimbursement(r);
		
		//ASSERT
		if (success) {
			assertEquals(rDao.getReimbursementsByUser(u).size(), 0);
		} 
	}
	
	@Test
	public void testGetAccountsByUser() {
		//ARRANGE
		
		//Create Employee User
		User fm = new User();
		fm.setEmail("iman1@gmail.com");
		fm.setFirstName("Itali");
		fm.setLastName("Manokey");
		fm.setUsername("iMan21");
		fm.setPassword("1234");
		fm.setUserType(UserType.EMPLOYEE);
		uDao.addUser(fm);
		
		//Set up a new reimb under prev user
		Reimbursement secondReimb = new Reimbursement();
		secondReimb.setAmount(25.99);
		secondReimb.setDescription("Get yo money bak");
		secondReimb.setAuthor_id(fm.getId());
		secondReimb.setType(ReimbursementType.OTHER);
		secondReimb.setStatus(ReimbursementStatus.DENIED);
		rDao.addReimbursement(secondReimb);
		
		//ACT
		
		// only secondAccount should be retrieved since it belongs to user 2
		List<Reimbursement> test = rDao.getReimbursementsByUser(fm);
		//Check the number of reimbursements submitted by fm
		
		//ASSERT
		
		//Remove user
		uDao.removeUser(fm);
		
		assertEquals(test.size(), 1);
		//Check if correct reimbursement was retrieve
		assertEquals(secondReimb, test.get(0));
	}
	
	/*
	 * USER INTEGRATION TESTS
	 */
	
	@Test
	public void testAddAndGetUser() 
	{
		//ACT
		
		User actual = uDao.getUser(u.getId());
		
		//ASSERT
		assertEquals(u, actual);
	}
	
	@Test
	public void testGetUserByUsernameAndPassword() 
	{
		//ACT
		
		User actual = uDao.getUser(u.getUsername(), u.getPassword());
		
		//ASSERT
		assertEquals(u, actual);
	}
	
	@Test
	public void testGetUserByEmail() 
	{
		//ACT
		
		User actual = uDao.getUser(u.getEmail());
		User dne = uDao.getUser("NotAnEmail@me.com");
		
		//ASSERT
		assertEquals(u, actual);
		assertEquals(dne, null);
	}
	
	@Test
	public void testGetAllUsers() 
	{
		//ARRANGE
		User second = new User();
		second.setId(2);
		second.setUsername("test2");
		second.setPassword("someTestPassword");
		uDao.addUser(second);
		
		//ACT
		
		List<User> allUsers = uDao.getAllUsers();
		uDao.removeUser(second);
		
		//ASSERT
		
		assertNotEquals(allUsers.size(), 1);
	}
	
	@Test
	public void testUpdateUser() {
		//ACT
		u.setFirstName("Charlie");
		uDao.updateUser(u);
		
		//ASSERT
		assertEquals(uDao.getUser(u.getId()).getFirstName(), "Charlie");
	}
	
	@Test
	public void testDeleteUser() 
	{
		//ARRANGE
		int prevSize = uDao.getAllUsers().size();
		
		//ACT
		boolean success = uDao.removeUser(u);
		
		//ASSERT
		if (success) {
			assertEquals(uDao.getAllUsers().size(), prevSize-1);
		}
	}
}
