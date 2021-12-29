package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Kicks all Players on the network.
 */
@SuppressWarnings("serial")
public class PacketKickAllPlayers extends BungeePacket implements Serializable {
	
	private String message;
	
	/**
	 * Instantiates a new PacketKickAllPlayers.
	 *
	 * @param message message
	 */
	public PacketKickAllPlayers(String message) {
		this.message = message;
		this.type = BungeePacketType.KICKALLPLAYERS;
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
