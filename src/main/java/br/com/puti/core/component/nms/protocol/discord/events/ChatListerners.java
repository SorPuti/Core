package br.com.puti.core.component.nms.protocol.discord.events;

import br.com.puti.core.loader.CoreLoader;
import br.com.puti.core.services.discord.DiscordServices;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings("all")
public class ChatListerners extends ListenerAdapter {

	private DiscordServices service;

	public ChatListerners(CoreLoader loader) {
		this.service = loader.getDiscordServices();
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		service.getAll().forEach((id, bot) -> {
			bot.onMessageReceived(event);
		});
	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		service.getAll().forEach((id, bot) -> {
			bot.onReactAdd(event);
		});
	}
	

	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
		service.getAll().forEach((id, bot) -> {
			bot.onReactRemove(event);
		});
	}
	
	/*
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		service.getAll().forEach((id, bot) -> {
			bot.onGuildMemberJoin(event);
		});
	}
	
	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		service.getAll().forEach((id, bot) -> {
			bot.onGuildMemberQuit(event);
		});
	}*/
}
