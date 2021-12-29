package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to get count of players on a server.
 * 
 * Returned: int onlinecount
 */
@SuppressWarnings("serial")
public class PacketGetOnlineCountServer extends BungeePacket implements Serializable {
	
	private String server;
	
	/**
	 * Instantiates a new PacketGetOnlineCountServer.
	 *
	 * @param server server to get playercount from
	 */
	public PacketGetOnlineCountServer(String server) {
		this.server = server;
		this.type = BungeePacketType.GETONLINECOUNTSERVER;
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
