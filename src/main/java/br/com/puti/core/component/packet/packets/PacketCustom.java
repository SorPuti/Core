package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;


/**
 * Packet used to send custom Subjects & Data.
 * 
 * Returns: Answer of your Bungeecord-Plugin
 */
@SuppressWarnings("serial")
public class PacketCustom extends BungeePacket implements Serializable {
	
	private String channel;
	private Object subject;
	
	/**
	 * Instantiates a new PacketCustomCommand.
	 *
	 * @param channel channel
	 * @param subject subject
	 */
	public PacketCustom(String channel, Object subject) {
		this.channel = channel;
		this.subject = subject;
		this.type = BungeePacketType.CUSTOM;
		this.shouldanswer = true;
	}
	
	/**
	 * Gets the channel.
	 *
	 * @return channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * Gets the subject.
	 *
	 * @return subject
	 */
	public Object getSubject() {
		return subject;
	}
}
