package th.ac.kmutt.dsd.train.pojo.db;

import org.primefaces.json.JSONObject;

public class ReturnObject {
	
	private int code;
	private String message;
	private JSONObject data;
	
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
	public JSONObject getData() {
		return data;
	}
	public void setData(JSONObject data) {
		this.data = data;
	}
	
	
	
}
