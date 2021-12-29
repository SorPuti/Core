package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to connect a player to a server.
 * 
 * Returned: ConnectResult result
 */
@SuppressWarnings("serial")
public class PacketConnectPlayer extends BungeePacket implements Serializable {
	
	private UUID uuid;
	private String server;
	
	/**
	 * Instantiates a new PacketConnectPlayer.
	 *
	 * @param uuid the player's uuid.
	 * @param server server to connect.
	 */
	public PacketConnectPlayer(UUID uuid, String server) {
		this.uuid = uuid;
		this.server = server;
		this.type = BungeePacketType.CONNECTPLAYER;
		this.shouldanswer = true;
	}
	
	/**
	 * Gets the uuid.
	 *
	 * @return uuid
	 */
	public UUID getUUID() {
		return uuid;
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
