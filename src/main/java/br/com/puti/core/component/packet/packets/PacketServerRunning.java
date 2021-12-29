package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to indicate that a server started up. Is only sent on Server start/reload.
 * ONLY USE THIS PACKET IF YOU KNOW WHAT YOU DO!
 * (Not mentioned on the page)
 */
@SuppressWarnings("serial")
public class PacketServerRunning extends BungeePacket implements Serializable {

	private String name;
	private String motd;
	private int port;
	private int updateintervall;
	private int version;
	
	/**
	 * Instantiates a new PacketServerRunning.
	 */
	public PacketServerRunning(String name, String motd, int port, int updateintervall, int version) {
		this.name = name;
		this.motd = motd;
		this.port = port;
		this.updateintervall = updateintervall;
		this.version = version;
		this.type = BungeePacketType.SERVERRUNNING;
		this.shouldanswer = true;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the MOTD.
	 *
	 * @return motd
	 */
	public String getMOTD() {
		return motd;
	}
	
	/**
	 * Gets the port.
	 *
	 * @return port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Gets the UpdateIntervall.
	 *
	 * @return updateintervall
	 */
	public int getUpdateIntervall() {
		return updateintervall;
	}
	
	/**
	 * Gets the version.
	 *
	 * @return version
	 */
	public int getVersion() {
		return version;
	}

}
