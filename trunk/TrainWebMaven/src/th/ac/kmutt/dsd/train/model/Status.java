package th.ac.kmutt.dsd.train.model;

public class Status {

	// Success
	public static final Status SUCCESS				= new Status(1000, "Success");
	public static final Status SUCCESS_WITH_ERROR	= new Status(1001, "Success with error");
	
	// Error
	public static final Status FAILURE				= new Status(2000, "Failure");
	public static final Status DUPLICATE_ID			= new Status(2001, "Duplicate ID");
	
	// Client Error
	public static final Status INVALID				= new Status(3000, "Invalid");
	public static final Status INVALID_FORMAT		= new Status(3001, "Invalid request format parameters");
	public static final Status REQUESTED_FIELD_EMPTY= new Status(3002, "Requested field is empty");
	
	private int code;
	private String message;
	
	public Status(){
		super();
	}
	
	public Status(int code,String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
