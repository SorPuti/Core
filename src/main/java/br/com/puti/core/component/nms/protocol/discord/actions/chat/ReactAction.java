package br.com.puti.core.component.nms.protocol.discord.actions.chat;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;

public interface ReactAction {

	public static final int serialID = 0;

	void action(Member author, ReactionEmote emote, Message message, Type type);

	public enum Type {
		ADD, REMOVE,ADD_PRIVATE,REMOVE_PRIVATE
	}
}
