package br.com.puti.core.services.message;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import br.com.puti.core.events.PlayerConnectedEvent;
import br.com.puti.core.events.PlayerConnectedType;

public class MessageServicesEvent implements PluginMessageListener {

	public MessageServices service;

	public MessageServicesEvent(MessageServices service) {
		this.service = service;
		service.loader.getPluginLoader().log("&e[BungeeCord] &fMessageEvent registrado.");
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equalsIgnoreCase("Core")) {
			return;
		}

		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subChannel = in.readUTF();

		if (subChannel.equalsIgnoreCase("PlayerConnected")) {
//			String server = service.send("ServerOfPlayer", in.readUTF());
			service.send("ServerOfPlayer", in.readUTF(), (data,author) -> {
				PlayerConnectedEvent event = new PlayerConnectedEvent(player.getName(), data.readUTF(),
						PlayerConnectedType.BUNGEECORD);
				Bukkit.getPluginManager().callEvent(event);	
			});
			
			return;
		}
		
		for (String key : service.aks.keySet())
			if (key.equalsIgnoreCase(subChannel))
				service.aks.get(key).callBack(in, service);
	}
}
