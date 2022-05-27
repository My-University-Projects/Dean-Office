package pl.kurs.deanoffice.exception;

public class GradeNotFoundException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GradeNotFoundException() {
		super("Grade with provided id has not been found");
	}

}
