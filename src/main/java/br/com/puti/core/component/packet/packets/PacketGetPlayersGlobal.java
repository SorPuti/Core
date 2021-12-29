package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to resolve the uuid of all players.
 * 
 * Returned: List<UUID> uuids
 */
@SuppressWarnings("serial")
public class PacketGetPlayersGlobal extends BungeePacket implements Serializable {
	
	/**
	 * Instantiates a PacketGetPlayersGlobal.
	 */
	public PacketGetPlayersGlobal() {
		this.type = BungeePacketType.GETPLAYERSGLOBAL;
		this.shouldanswer = true;
	}
	
}
