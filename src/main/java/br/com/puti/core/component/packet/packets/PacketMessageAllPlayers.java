package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to send a message to all players on the network.
 */
@SuppressWarnings("serial")
public class PacketMessageAllPlayers extends BungeePacket implements Serializable {
	
	private String message;
	
	/**
	 * Instantiates a new PacketMessageAllPlayers.
	 *
	 * @param message message
	 */
	public PacketMessageAllPlayers(String message) {
		this.message = message;
		this.type = BungeePacketType.MESSAGEALLPLAYERS;
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
