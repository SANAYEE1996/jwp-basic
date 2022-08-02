package next.template.config;

public class DataAccessException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataAccessException() {
		super();
	}
	
	public DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writabletStackTrace) {
		super(message, cause, enableSuppression, writabletStackTrace);
	}
	
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
