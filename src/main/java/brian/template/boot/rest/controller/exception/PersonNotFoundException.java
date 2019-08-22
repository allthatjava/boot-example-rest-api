package brian.template.boot.rest.controller.exception;

public class PersonNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException() {
		super();
	}
	
	public PersonNotFoundException(String message) {
		super(message);
	}
	
	public PersonNotFoundException(Throwable e, String message) {
		super(message, e );
	}
	
}
