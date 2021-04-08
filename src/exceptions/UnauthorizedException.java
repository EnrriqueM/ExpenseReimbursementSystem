package exceptions;

/**
 * Thrown if user does not have permission to perform the operation
 */
public class UnauthorizedException extends RuntimeException
{
	private static final long serialVersionUID = 3289845689692326438L;

	public UnauthorizedException(String errMsg)
	{
		super(errMsg);
	}
	
	public UnauthorizedException(String errMsg, Throwable e)
	{
		super(errMsg, e);
	}
}
