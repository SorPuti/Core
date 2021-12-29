package br.com.puti.core.component.packet.packets;

import br.com.puti.core.component.packet.BungeePacket;
import br.com.puti.core.component.packet.BungeePacketType;

import java.io.Serializable;
import java.util.logging.Level;


/**
 * Packet used to write a message to the console.
 */
@SuppressWarnings("serial")
public class PacketWriteConsole extends BungeePacket implements Serializable {
	
	private String message;
	private Level level;
	
	/**
	 * Instantiates a new PacketWriteConsole.
	 *
	 * @param message message
	 */
	public PacketWriteConsole(String message) {
		this.message = message;
		this.level = Level.INFO;
		this.type = BungeePacketType.WRITECONSOLE;
		this.shouldanswer = false;
	}
	
	/**
	 * Instantiates a new PacketWriteConsole.
	 *
	 * @param message message
	 * @param level level
	 */
	public PacketWriteConsole(String message, Level level) {
		this.message = message;
		this.level = level;
		this.type = BungeePacketType.WRITECONSOLE;
		this.shouldanswer = false;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return level
	 */
	public Level getLevel() {
		return level;
	}
}
