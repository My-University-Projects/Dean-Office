package pl.kurs.deanoffice.exception;

public class SubjectNotFoundException extends ObjectNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubjectNotFoundException() {
		super("Subject with provided id has not been found");
		// TODO Auto-generated constructor stub
	}

}
