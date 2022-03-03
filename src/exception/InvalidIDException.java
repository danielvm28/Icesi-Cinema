package exception;

public class InvalidIDException extends Exception{
	public InvalidIDException() {
		super("The introduced ID is invalid, try again");
	}
}
