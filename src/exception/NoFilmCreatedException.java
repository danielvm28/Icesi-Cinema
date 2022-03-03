package exception;

public class NoFilmCreatedException extends Exception{
	public NoFilmCreatedException() {
		super("There are no films to watch yet");
	}
}
