package exception;

public class FullTheatreException extends Exception{
	public FullTheatreException() {
		super("It's not possible to register more spectators, this film has reached its maximum spectator capacity");
	}
}
