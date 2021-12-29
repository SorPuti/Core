package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to check if a server is online/responds.
 * 
 * Returned: boolean result
 */
@SuppressWarnings("serial")
public class PacketIsServerOnline extends BungeePacket implements Serializable {
	
	private String bungeename;
	
	/**
	 * Instantiates a new PacketIsServerOnline.
	 *
	 * @param bungeename bungeename
	 */
	public PacketIsServerOnline(String bungeename) {
		this.bungeename = bungeename;
		this.type = BungeePacketType.ISSERVERONLINE;
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
