package dao;

import java.util.List;

import models.User;

public interface UserDao 
{
	/**
	 * Inserts a new user into the persistence layer
	 * @param user the user to insert
	 * @return the newly added user object
	 */
	public User addUser(User user);
	
	/**
	 * Retrieves a user by id
	 * @param userId the id to search by
	 * @return the user object
	 */
	public User getUser(Integer userId);
	
	/**
	 * Retrieves a user by username and password
	 * @param username
	 * @param pass
	 * @return the user object
	 */
	public User getUser(String username, String pass);
	
	/**
	 * Retrieves all users in the persistence layer
	 * @return a list of all users
	 */
	public List<User> getAllUsers();
	
	/**
	 * Updates a specific user
	 * @param u the user to update
	 * @return the newly updated user object
	 */
	public User updateUser(User u);
	
	/**
	 * Deletes a user from the persistence layer
	 * @param u the user to remove
	 * @return true if successful; false if not
	 */
	public boolean removeUser(User u);
	
	/**
	 * Retrieves a user by email
	 * @param email
	 * @return the user object
	 */
	public User getUser(String email);
}
