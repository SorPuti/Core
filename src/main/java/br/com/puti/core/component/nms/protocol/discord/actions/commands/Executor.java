package br.com.puti.core.component.nms.protocol.discord.actions.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Executor {

	private String name;
	private Permission permission;
	private String[] aliases;

	public Executor(String name, Permission permission) {
		this.name = name;
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public Permission getPermission() {
		return permission;
	}

	public String[] getAliases() {
		return aliases;
	}

	public Executor(String name) {
		this.name = name;
		this.permission = Permission.UNKNOWN;
	}

	public boolean has(String arg0) {
		if (arg0.equalsIgnoreCase(name))
			return true;

		if (aliases != null && aliases.length > 0)
			for (String line : aliases)
				if (line.equalsIgnoreCase(arg0))
					return true;

		return false;
	}

	public abstract void onCommand(Member author, Message message, String[] args, MessageReceivedEvent event);
}
