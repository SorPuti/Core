package br.com.puti.core.component.database.exceptions;

public class DataBaseUndefinedException extends RuntimeException {

	private static final long serialVersionUID = 5162710183389028792L;

	public DataBaseUndefinedException() {
		super("Database undefined execption code: None");
	}

	public DataBaseUndefinedException(String s) {
		super("Database undefined execption code: "+s);
	}

	private transient int extendedMessageState;
	private transient String extendedMessage;

	public synchronized Throwable fillInStackTrace() {
		if (extendedMessageState == 0) {
			extendedMessageState = 1;
		} else if (extendedMessageState == 1) {
			extendedMessage = getExtendedNPEMessage();
			extendedMessageState = 2;
		}
		return super.fillInStackTrace();
	}

	public String getMessage() {
		String message = super.getMessage();
		if (message == null) {
			synchronized (this) {
				if (extendedMessageState == 1) {
					extendedMessage = getExtendedNPEMessage();
					extendedMessageState = 2;
				}
				return extendedMessage;
			}
		}
		return message;
	}

	private native String getExtendedNPEMessage();
}