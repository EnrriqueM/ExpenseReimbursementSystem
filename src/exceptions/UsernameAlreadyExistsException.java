package exceptions;

/**
 * Should be thrown when a user tries to register with a username 
 * that already exists in the system
 */
public class UsernameAlreadyExistsException extends RuntimeException
{
	private static final long serialVersionUID = 1712843686624302051L;
	
	public UsernameAlreadyExistsException(String errMsg)
	{
		super(errMsg);
	}
	
	public UsernameAlreadyExistsException(String errMsg, Throwable e)
	{
		super(errMsg, e);
	}
}
