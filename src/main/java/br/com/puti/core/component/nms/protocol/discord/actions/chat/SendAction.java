package br.com.puti.core.component.nms.protocol.discord.actions.chat;


import net.dv8tion.jda.api.entities.Message;

public interface SendAction {
	public static final int serialID = 1;

	void action(Message message);
}
