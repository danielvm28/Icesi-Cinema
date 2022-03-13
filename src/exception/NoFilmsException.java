
package exception;

public class NoFilmsException extends Exception{
	public NoFilmsException() {
		super("There are no films yet. Make sure to create at least one before trying to register an spectator");
	}
}
