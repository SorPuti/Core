package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to check if a player is online.
 * 
 * Returned: IsOnlineResult result
 */
@SuppressWarnings("serial")
public class PacketIsPlayerOnline extends BungeePacket implements Serializable {
	
	private UUID uuid = null;
	private String name = null;
	
	/**
	 * Instantiates a new PacketIsPlayerOnline.
	 *
	 * @param uuid uuid
	 */
	public PacketIsPlayerOnline(UUID uuid) {
		this.uuid = uuid;
		this.type = BungeePacketType.ISPLAYERONLINE;
		this.shouldanswer = true;
	}
	
	/**
	 * Instantiates a new PacketIsPlayerOnline.
	 *
	 * @param name name
	 */
	public PacketIsPlayerOnline(String name) {
		this.name = name;
		this.type = BungeePacketType.ISPLAYERONLINE;
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
	
	/**
	 * Gets the name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
}
