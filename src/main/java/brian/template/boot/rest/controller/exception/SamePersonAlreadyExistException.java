package brian.template.boot.rest.controller.exception;

public class SamePersonAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SamePersonAlreadyExistException() {
		super();
	}
	
	public SamePersonAlreadyExistException(String message) {
		super(message);
	}
	
	public SamePersonAlreadyExistException(Throwable e, String message) {
		super(message, e );
	}
	
}
