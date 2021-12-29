package br.com.puti.core.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import br.com.puti.core.component.model.CorePlayer;

public class AccountCreatedEvent extends Event implements Cancellable {

private static final HandlerList handlers = new HandlerList();
	
	private CorePlayer corePlayer;
	private boolean isCancelled;
	
	public AccountCreatedEvent(CorePlayer corePlayer) {
		this.corePlayer = corePlayer;
	    this.isCancelled = false;

	}
	
	public CorePlayer getPlayer() {
	    return this.corePlayer;
	}
	
	@Override
	public boolean isCancelled() {
		return this.isCancelled;
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