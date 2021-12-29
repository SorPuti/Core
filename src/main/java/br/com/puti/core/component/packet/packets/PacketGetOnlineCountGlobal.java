package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to get count of players on the network.
 * 
 * Returned: int onlinecount
 */
@SuppressWarnings("serial")
public class PacketGetOnlineCountGlobal extends BungeePacket implements Serializable {
	
	/**
	 * Instantiates a new PacketGetOnlineCountGlobal.
	 */
	public PacketGetOnlineCountGlobal() {
		this.type = BungeePacketType.GETONLINECOUNTGLOBAL;
		this.shouldanswer = true;
	}
	
}
