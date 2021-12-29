package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to send a actionbar to a player.
 */
@SuppressWarnings("serial")
public class PacketSendActionbar extends BungeePacket implements Serializable {

	private UUID uuid;
	private String actionbar;
	
	/**
	 * Instantiates a new PacketSendActionbar.
	 *
	 * @param uuid uuid
	 * @param actionbar actionbar
	 */
	public PacketSendActionbar(UUID uuid, String actionbar) {
		this.uuid = uuid;
		this.actionbar = actionbar;
		this.type = BungeePacketType.SENDACTIONBAR;
		this.shouldanswer = false;
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
	 * Gets the actionbar.
	 *
	 * @return actionbar
	 */
	public String getActionbar() {
		return actionbar;
	}
}
