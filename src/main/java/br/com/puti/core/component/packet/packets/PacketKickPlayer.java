package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to kick a player from the network.
 */
@SuppressWarnings("serial")
public class PacketKickPlayer extends BungeePacket implements Serializable {
	
	private UUID uuid;
	private String message;
	
	/**
	 * Instantiates a new PacketKickPlayer.
	 *
	 * @param uuid uuid
	 * @param message message
	 */
	public PacketKickPlayer(UUID uuid, String message) {
		this.uuid = uuid;
		this.message = message;
		this.type = BungeePacketType.KICKPLAYER;
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
