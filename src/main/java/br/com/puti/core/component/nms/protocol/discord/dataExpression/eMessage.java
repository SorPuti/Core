package br.com.puti.core.component.nms.protocol.discord.dataExpression;

import br.com.puti.core.component.nms.protocol.discord.Bot;
import br.com.puti.core.component.nms.protocol.discord.actions.chat.SendAction;
import br.com.puti.core.component.nms.protocol.discord.actions.chat.ReactAction;
import net.dv8tion.jda.api.entities.Message;

public class eMessage {

	private Message message;
	private String messageId;
	private final Bot bot;
	private ReactAction action;

	public eMessage(Bot bot){
		this.bot = bot;
	}

	public Bot getBot() {
		return bot;
	}

	public Message getMessage() {
		return message;
	}

	public ReactAction getAction() {
		return action;
	}

	public String getMessageId() {
		return messageId;
	}

	public eMessage onReact(ReactAction action) {
		this.action = action;
		return this;
	}

	public eMessage complete() {
		if (!bot.getAks().containsKey(messageId))
			bot.getAks().put(messageId, this);
		return this;
	}

	public eMessage query(SendAction action) {
		if (!bot.getAks().containsKey(messageId))
			bot.getAks().put(messageId, this);

		action.action(message);
		return this;
	}

	public void close() {
		if (this.bot.getAks().containsKey(messageId))
			this.bot.getAks().remove(messageId);
	}

	public eMessage query(Message message) {
		if (message == null)
			return null;

		this.message = message;
		this.messageId = message.getId();
		return this;
	}

}
