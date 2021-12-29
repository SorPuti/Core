package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;

/**
 * Packet used to indicate a server is still alive & update some data.
 * ONLY USE THIS PACKET IF YOU KNOW WHAT YOU DO!
 * (Not mentioned on the page)
 */
@SuppressWarnings("serial")
public class PacketKeepAlive extends BungeePacket implements Serializable {
	
	private String bungeename;
	private boolean auto;
	private String motd;
	
	/**
	 * Instantiates a new PacketKeepAlive.
	 *
	 * @param bungeename bungeename
	 * @param auto 
	 * @param message message
	 */
	public PacketKeepAlive(String bungeename, boolean auto, String motd) {
		this.bungeename = bungeename;
		this.auto = auto;
		this.motd = motd;
		this.type = BungeePacketType.KEEPALIVE;
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
	
	/**
	 * Checks if is automatic.
	 *
	 * @return true, if is automatic
	 */
	public boolean isAutomatic() {
		return auto;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return message
	 */
	public String getMOTD() {
		return motd;
	}
	
}
