package br.com.puti.core.component.nms.protocol.discord.actions.bot;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;

@SuppressWarnings("all")
public interface BotEvents {

	public static final int serialID = 5;

	void onReactAdd(MessageReactionAddEvent event);

	void onReactRemove(MessageReactionRemoveEvent event);

	void onMessageReceived(MessageReceivedEvent event);

	/*
	void onGuildMemberJoin(GuildMemberJoinEvent event);

	void onGuildMemberQuit(GuildMemberLeaveEvent event);
	*/

}
