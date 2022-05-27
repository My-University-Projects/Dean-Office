package pl.kurs.deanoffice.exception;

public class TeacherNotFoundException extends ObjectNotFoundException {

	public TeacherNotFoundException() {
		super("Teacher with provided id has not been found");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
