package br.com.puti.core.component.database.response;

public enum DataBaseResponseType {

	SUCESS("Process was successful."), FAILED("Process failed."), UNKNOWN("Unknown process result."),
	REQUEST_401("Data not found.",401), REQUEST_404("Database nout found.(not connected)",404),REQUEST_301("Process was successful.",301);

	private String message;
	private int code = 0;

	DataBaseResponseType(String message) {
		this.message = message;
		this.code = 0;
	}
	
	DataBaseResponseType(String message,int code) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
	
	public int hasCode() {
		return this.code;
	}
}
