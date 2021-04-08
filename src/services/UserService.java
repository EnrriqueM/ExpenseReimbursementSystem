package services;

import dao.UserDao;
import dao.UserDaoImpl;
import driver.ErsAppDriver;
import exceptions.InvalidCredentialsException;
import exceptions.UsernameAlreadyExistsException;
import models.User;

/**
 * This class should contain the business logic for performing operations on users
 */
public class UserService 
{
	//Properties
	private UserDao userDao;
	
	//Constructor
	public UserService() {
		this.userDao = new UserDaoImpl();
	}
	
	public UserService(UserDao uDao)
	{
		this.userDao = uDao;
	}
	
	/**
	 * Validates the username and password, and return the User object for that user
	 * @throws InvalidCredentialsException if either username is not found or password does not match
	 * @return the User who is now logged in
	 */
	public User login(String username, String password)
	{
		//Attempt to find user
		User user = userDao.getUser(username, password);
		
		//If null, throw invalid credential
		if(user == null || !user.getPassword().equals(password))
		{
			String msg = "Username or password is invalid";
			ErsAppDriver.logger.error(msg);
			throw new InvalidCredentialsException(msg);
		}
		
		//Otherwise save the in session and return the user
		//SessionCache.setCurrentUser(user);
		return user;
	}
	
	/**
	 * Creates the specified User in the persistence layer
	 * @param newUser the User to register
	 * @throws UsernameAlreadyExistsException if the given User's username is taken
	 */
	public User register(User newUser) 
	{
		//Attempt to find an existing username
		User findExistingUser = userDao.getUser(newUser.getUsername(), newUser.getPassword());
		
		//If Null, username is available. Throw exception it taken
		if (findExistingUser == null)
		{
			//Add a new user
			userDao.addUser(newUser);
		}
		else
		{
			String msg = "User name already exists";
			ErsAppDriver.logger.error(msg);
			
			//Username is taken
			throw new UsernameAlreadyExistsException(msg);
		}
		
		return findExistingUser;
	}
	
	public boolean checkUn(String un)
	{
		boolean available = false;
		
		//Attempt to find an existing username
		User findExistingUser = userDao.getUser(un, "void");
		
		//If Null, username is available. Throw exception it taken
		if (findExistingUser == null)
		{
			//User name is available
			available = true;
		}
		
		
		return available;
	}
	
	public boolean checkEmail(String email)
	{
		boolean available = false;
		
		//Attempt to find an existing email
		User findExistingUser = userDao.getUser(email);
		
		//If Null, username is available. Throw exception it taken
		if (findExistingUser == null)
		{
			//User name is available
			available = true;
		}
		
		return available;
	}
}
