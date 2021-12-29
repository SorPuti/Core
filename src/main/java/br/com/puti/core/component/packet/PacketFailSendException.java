package br.com.puti.core.component.packet;

/**
 * Is thrown if Packetsend fails.
 */
@SuppressWarnings("serial")
public class PacketFailSendException extends RuntimeException {
	
	public PacketFailSendException() {
		super();
	}
	
	public PacketFailSendException(String message) {
		super(message);
	}
}