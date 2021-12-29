package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to send a message to a player.
 */
@SuppressWarnings("serial")
public class PacketMessagePlayer extends BungeePacket implements Serializable {

	private UUID uuid;
	private String message;
	
	/**
	 * Instantiates a new PacketMessagePlayer.
	 *
	 * @param uuid uuid
	 * @param message message
	 */
	public PacketMessagePlayer(UUID uuid, String message) {
		this.uuid = uuid;
		this.message = message;
		this.type = BungeePacketType.MESSAGEPLAYER;
		this.shouldanswer = false;
	}
	
	/**
	 * Gets the uuid.
	 *
	 * @return uuid
	 */
	public UUID getUUID() {
		return uuid;
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
