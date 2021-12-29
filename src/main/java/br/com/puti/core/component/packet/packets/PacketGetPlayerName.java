package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to get a player's name.
 * 
 * Returned: String name
 */
@SuppressWarnings("serial")
public class PacketGetPlayerName extends BungeePacket implements Serializable {
	
	private UUID uuid;
	
	/**
	 * Instantiates a new PacketGetPlayerName.
	 *
	 * @param uuid the player's uuid
	 */
	public PacketGetPlayerName(UUID uuid) {
		this.uuid = uuid;
		this.type = BungeePacketType.GETPLAYERNAME;
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
