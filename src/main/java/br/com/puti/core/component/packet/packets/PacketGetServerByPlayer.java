package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to get name of a player's server.
 * 
 * Returned: String server
 */
@SuppressWarnings("serial")
public class PacketGetServerByPlayer extends BungeePacket implements Serializable {
	
	private UUID uuid;
	
	/**
	 * Instantiates a new PacketGetServerByPlayer.
	 *
	 * @param uuid the player's uuid
	 */
	public PacketGetServerByPlayer(UUID uuid) {
		this.uuid = uuid;
		this.type = BungeePacketType.GETSERVERBYPLAYER;
		this.shouldanswer = true;
	}
	
	/**
	 * Gets the uuid.
	 *
	 * @return uuid
	 */
	public UUID getUUID() {
		return uuid;
	}
	
}
