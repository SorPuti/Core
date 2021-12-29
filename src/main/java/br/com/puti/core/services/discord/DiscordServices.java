package br.com.puti.core.services.discord;

import br.com.puti.core.component.nms.protocol.discord.Bot;
import br.com.puti.core.loader.CoreLoader;

import java.util.HashMap;

public class DiscordServices {
	protected CoreLoader loader;

	HashMap<String, Bot> bots = new HashMap<String, Bot>();

	public DiscordServices(CoreLoader loader) {
		if (loader.getMessageServices() == null)
			this.loader = loader;
		else
			throw new NullPointerException("Esta classe nao pode ser instanciada.(DiscordServices.class ?:?)");

	}
	
	public Bot get(String arg0) {
		return this.bots.get(arg0);
	}
	
	public HashMap<String, Bot> getAll(){
		return this.bots;
	}
	
	public void add(Bot bot) {
		this.bots.put(bot.getId(), bot);
	}
	
	public void remove(Bot bot) {
		this.bots.remove(bot.getId(), bot);
	}
	
	public void unregisterAll() {
		for(Bot bot : bots.values())
			bot.disconnect();
		
		this.bots.clear();
	}

}
