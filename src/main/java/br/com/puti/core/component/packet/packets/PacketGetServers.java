package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to get the names of all servers.
 * 
 * Returned: List<String> servers
 */
@SuppressWarnings("serial")
public class PacketGetServers extends BungeePacket implements Serializable {
	
	/**
	 * Instantiates a new PacketGetServers.
	 */
	public PacketGetServers() {
		this.type = BungeePacketType.GETSERVERS;
		this.shouldanswer = true;
	}
	
}
