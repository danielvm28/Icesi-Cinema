package exception;

public class DoubledSpectatorException extends Exception{
	public DoubledSpectatorException() {
		super("This spectator is already registered for this film");
	}
}
