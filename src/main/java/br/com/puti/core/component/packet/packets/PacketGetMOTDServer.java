package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to get the MOTD of a server.
 * 
 * Returned: String MOTD
 */
@SuppressWarnings("serial")
public class PacketGetMOTDServer extends BungeePacket implements Serializable {
	
	private String bungeename;
	
	/**
	 * Instantiates a new PacketGetMOTDServer.
	 *
	 * @param bungeename
	 */
	public PacketGetMOTDServer(String bungeename) {
		this.bungeename = bungeename;
		this.type = BungeePacketType.GETMOTDSERVER;
		this.shouldanswer = true;
	}
	
	/**
	 * Gets the server.
	 *
	 * @return server
	 */
	public String getBungeename() {
		return bungeename;
	}
	
}
