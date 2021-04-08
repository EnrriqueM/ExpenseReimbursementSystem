package exceptions;

/**
 * Should be thrown when a user tries to login with invalid credentials
 */
public class InvalidCredentialsException extends RuntimeException{
	private static final long serialVersionUID = -6573307333524845568L;
	
	public InvalidCredentialsException(String errMsg)
	{
		super(errMsg);
	}
	
	public InvalidCredentialsException(String errMsg, Throwable e)
	{
		super(errMsg, e);
	}
}
