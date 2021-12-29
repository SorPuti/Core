package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to get a player's uuid by their name.
 * 
 * Returned: UUID uuid
 */
@SuppressWarnings("serial")
public class PacketGetPlayerUUID extends BungeePacket implements Serializable {
	
	private String name;
	
	/**
	 * Instantiates a new PacketGetPlayerUUID.
	 *
	 * @param name name
	 */
	public PacketGetPlayerUUID(String name) {
		this.name = name;
		this.type = BungeePacketType.GETPLAYERUUID;
		this.shouldanswer = true;
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
