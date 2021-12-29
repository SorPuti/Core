package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to resolve the uuid of all players on a server.
 * 
 * Returned: List<UUID> uuids
 */
@SuppressWarnings("serial")
public class PacketGetPlayersServer extends BungeePacket implements Serializable {
	
	/** The server. */
	private String server;
	
	/**
	 * Instantiates a new PacketGetPlayersServer.
	 *
	 * @param server server
	 */
	public PacketGetPlayersServer(String server) {
		this.server = server;
		this.type = BungeePacketType.GETPLAYERSSERVER;
		this.shouldanswer = true;
	}
	
	/**
	 * Gets the server.
	 *
	 * @return server
	 */
	public String getServer() {
		return server;
	}
	
}
