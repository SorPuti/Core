package br.com.puti.core.component.action.callbacks;

import org.bukkit.command.CommandSender;

public interface CommandAction<E extends CommandSender> {
	void execute(E sender, String[] args);
}
