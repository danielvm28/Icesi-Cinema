package exception;

public class FilmOverlappingException extends Exception{
	public FilmOverlappingException() {
		super("There is already a scheduled movie at this time, please select other time");
	}
}
