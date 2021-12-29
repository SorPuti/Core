package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to get the IP of a server.
 * 
 * Returned: InetSocketAddress address
 */
@SuppressWarnings("serial")
public class PacketGetServerIP extends BungeePacket implements Serializable {
	
	private String server;
	
	/**
	 * Instantiates a PacketGetServerIP.
	 *
	 * @param server server
	 */
	public PacketGetServerIP(String server) {
		this.server = server;
		this.type = BungeePacketType.GETSERVERIP;
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
