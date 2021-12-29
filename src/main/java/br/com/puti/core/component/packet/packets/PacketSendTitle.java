package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;
import br.com.puti.core.component.packet.PackedTitle;

import java.io.Serializable;
import java.util.UUID;


/**
 * Packet used to send a title to a player.
 */
@SuppressWarnings("serial")
public class PacketSendTitle extends BungeePacket implements Serializable {

	private UUID uuid;
	private PackedTitle title;
	
	/**
	 * Instantiates a new PacketSendTitle.
	 *
	 * @param uuid uuid
	 * @param title title
	 */
	public PacketSendTitle(UUID uuid, PackedTitle title) {
		this.uuid = uuid;
		this.title = title;
		this.type = BungeePacketType.SENDTITLE;
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
	 * Gets the title.
	 *
	 * @return title
	 */
	public PackedTitle getTitle() {
		return title;
	}
}
