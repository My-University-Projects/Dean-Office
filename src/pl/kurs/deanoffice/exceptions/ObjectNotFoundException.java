package pl.kurs.deanoffice.exceptions;

public class ObjectNotFoundException extends Exception {

	private String message;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String message){
		super(message);
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}

}
