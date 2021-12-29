package br.com.puti.core.component.packet;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Spigot-Server on BungeeBridgeS.
 */
public class BungeeServer {

	private String name;
	private String motd;
	private String bungeename;
	private int port;
	private int updateintervall;
	private InetSocketAddress address;
	private static List<BungeeServer> servers = new ArrayList<BungeeServer>();
	
	/**
	 * Instantiates a new BungeeServer.
	 *
	 * @param name name
	 * @param motd motd
	 * @param bungeename bungeename
	 * @param port port
	 * @param updateintervall updateintervall
	 * @param address address
	 */
	public BungeeServer(String name, String motd, String bungeename, int port, int updateintervall, InetSocketAddress address) {
		this.name = name;
		this.motd = motd;
		this.bungeename = bungeename;
		this.port = port;
		this.updateintervall = updateintervall;
		this.address = address;
		servers.add(this);
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
	 * Gets the motd.
	 *
	 * @return motd
	 */
	public String getMOTD() {
		return motd;
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
	 * Gets the port.
	 *
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Gets the updateintervall.
	 *
	 * @return updateintervall
	 */
	public int getUpdateIntervall() {
		return updateintervall;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return address
	 */
	public InetSocketAddress getAddress() {
		return address;
	}	
	
	/**
	 * Sets the motd.
	 *
	 * @param motd
	 */
	public void setMOTD(String motd) {
		this.motd = motd;
	}

	/**
	 * Sets the updateintervall.
	 *
	 * @param updateintervall
	 */
	public void setUpdateIntervall(int updateintervall) {
		this.updateintervall = updateintervall;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the port.
	 *
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 */
	public void setAddress(InetSocketAddress address) {
		this.address = address;
	}
	
	/**
	 * Gets a BungeeServer by it's bungeename.
	 *
	 * @return server; null if no server is found
	 */
	public static BungeeServer getByBungeename(String bungeename) {
		BungeeServer result = null;
		for(BungeeServer server : servers) {
			if(server.getBungeename().equals(bungeename)) result = server;
		}
		return result;
	}
	
	/**
	 * Gets a BungeeServer by it's address.
	 *
	 * @return server; null if no server is found
	 */
	public static BungeeServer getByAddress(InetSocketAddress inputaddress) {
		BungeeServer result = null;
		if(!servers.isEmpty()) {
			for(BungeeServer server : servers) {
				if(server.getAddress().equals(inputaddress)) result = server;
			}
		}
		return result;
	}
	

}
