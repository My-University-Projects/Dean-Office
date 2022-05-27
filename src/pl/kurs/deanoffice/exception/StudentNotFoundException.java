package pl.kurs.deanoffice.exception;

public class StudentNotFoundException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentNotFoundException() {
		super("Student with provided id has not been found");
	}

}
