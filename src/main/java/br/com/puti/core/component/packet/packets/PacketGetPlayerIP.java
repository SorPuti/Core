package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to get a player's IP.
 * 
 * Returned: InetSocketAddress address.
 */
@SuppressWarnings("serial")
public class PacketGetPlayerIP extends BungeePacket implements Serializable {
	
	private UUID uuid;
	
	/**
	 * Instantiates a PacketGetPlayerIP.
	 *
	 * @param uuid the player's uuid
	 */
	public PacketGetPlayerIP(UUID uuid) {
		this.uuid = uuid;
		this.type = BungeePacketType.GETPLAYERIP;
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
