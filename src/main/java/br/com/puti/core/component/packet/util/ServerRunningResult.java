package br.com.puti.core.component.packet.util;

import java.io.Serializable;

/**
 * Represents the result of a PacketServerRunning. It contains important informations for the server.
 */
@SuppressWarnings("serial")
public class ServerRunningResult implements Serializable {
	
	private String bungeename;
	private int version;
	private long time;
	
	/**
	 * Instantiates a new ServerRunningResult.
	 *
	 * @param bungeename bungeename
	 * @param version version
	 * @param time time
	 */
	public ServerRunningResult(String bungeename, int version, long time) {
		this.bungeename = bungeename;
		this.version = version;
		this.time = time;
	}
	
	/**
	 * Gets the bungeename.
	 *
	 * @return bungeename
	 */
	public String getBungeename() {
		return  bungeename;
	}
	
	/**
	 * Gets the version.
	 *
	 * @return version
	 */
	public int getVersion() {
		return version;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return time
	 */
	public long getTime() {
		return time;
	}

}
