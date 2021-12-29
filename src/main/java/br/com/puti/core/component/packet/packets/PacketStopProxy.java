package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to stop the proxy.
 */
@SuppressWarnings("serial")
public class PacketStopProxy extends BungeePacket implements Serializable {
	
	private String message = null;
	
	/**
	 * Instantiates a new PacketStopProxy.
	 */
	public PacketStopProxy() {
		this.type = BungeePacketType.STOPPROXY;
		this.shouldanswer = false;
	}
	
	/**
	 * Instantiates a new PacketStopProxy.
	 *
	 * @param message message
	 */
	public PacketStopProxy(String message) {
		this.message = message;
		this.type = BungeePacketType.STOPPROXY;
		this.shouldanswer = false;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
}
