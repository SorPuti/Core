package br.com.puti.core.component.nms.protocol.discord.actions.chat;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public interface MessageAction {
	public static final int serialID = 2;

	void action(Message message, User author, ChannelType channel);

}
