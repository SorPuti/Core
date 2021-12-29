package br.com.puti.core.component.command;

import org.bukkit.command.CommandSender;

import br.com.puti.core.component.action.callbacks.CommandAction;

public abstract class Command {

	private String command, description, permission;
	private String[] aliases;
	private CommandAction<CommandSender> action;
	private String usage = "";

	public Command() {
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(CommandAction<CommandSender> action) {
		this.action = action;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String[] getAliases() {
		return aliases;
	}

	public boolean has(String arg0) {
		if (aliases != null)
			for (String aliase : aliases)
				if (arg0.equalsIgnoreCase(aliase))
					return true;

		return command.equalsIgnoreCase(arg0);
	}

	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}

	public boolean hasPermissions(CommandSender sender) {
		if (permission == null)
			return true;

		return sender.hasPermission(permission);
	}

	public void execute(CommandSender sender, String[] args) {}

	public void a(CommandSender sender, String[] args) {
		if (this.action != null)
			this.action.execute(sender, args);
	}

	/**
	 * @return the usage
	 */
	public String getUsage() {
		if (usage == null || usage.isEmpty())
			return "";
		return " "+usage;
	}

	/**
	 * @param usage the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}
}
