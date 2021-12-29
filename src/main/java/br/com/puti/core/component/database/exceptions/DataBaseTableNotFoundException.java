package br.com.puti.core.component.database.exceptions;

public class DataBaseTableNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5162710183389028792L;

	public DataBaseTableNotFoundException() {
		super("DataBaseTableNotFoundException(?:?)");
	}

	public DataBaseTableNotFoundException(String s) {
		super("Database of table `"+s+"` not found.");
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