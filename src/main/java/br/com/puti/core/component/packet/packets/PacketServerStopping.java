package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to indicate that a server is stopping.
 * ONLY USE THIS PACKET IF YOU KNOW WHAT YOU DO!
 * (Not mentioned on the page)
 */
@SuppressWarnings("serial")
public class PacketServerStopping extends BungeePacket implements Serializable {

	private String bungeename;
	
	/**
	 * Instantiates a new PacketServerStopping.
	 */
	public PacketServerStopping(String bungeename) {
		this.bungeename = bungeename;
		this.type = BungeePacketType.SERVERSTOPPING;
		this.shouldanswer = false;
	}
	
	/**
	 * Gets the bungeename.
	 *
	 * @return bungeename
	 */
	public String getBungeename() {
		return bungeename;
	}

}
