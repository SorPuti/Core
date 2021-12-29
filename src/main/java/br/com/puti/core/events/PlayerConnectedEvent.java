package br.com.puti.core.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import br.com.puti.core.component.model.CorePlayer;

public class PlayerConnectedEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private CorePlayer corePlayer;
	private boolean isCancelled;
	private String server,playerName;
	private String message = "";
	private PlayerConnectedType connectedType;

	public PlayerConnectedEvent(String name, String server,PlayerConnectedType connectedType) {
		this.corePlayer = CorePlayer.get(name);
		this.isCancelled = false;
		this.server = server;
		this.connectedType = connectedType;
	}

	public CorePlayer getPlayer() {
		return this.corePlayer;
	}
	
	public PlayerConnectedType getConnectionType() {
		return this.connectedType;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public String getServer() {
		return server;
	}
	
	@Override
	public boolean isCancelled() {
		return this.isCancelled;
	}

	public void setMessage(String arg0) {
		this.message = arg0;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	@Override
	public void setCancelled(boolean arg0) {
		this.isCancelled = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}