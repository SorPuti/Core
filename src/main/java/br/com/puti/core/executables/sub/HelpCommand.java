package br.com.puti.core.executables.sub;

import org.bukkit.entity.Player;

import br.com.puti.core.component.command.Command;
import br.com.puti.core.executables.CoreCommnad;

public class HelpCommand extends Command {

	public HelpCommand(CoreCommnad plugin) {
		setCommand("ajuda");
		setAliases(new String[] { "help" });
		setPermission("core.admin");
		setDescription("Veja todos os comandos disponíveis.");

		setCommand((sender, args) -> {
			if (!(sender instanceof Player))
				return;

			sender.sendMessage("§fComandos: ");
			for(Command cmd: plugin.commands)
				if (cmd.hasPermissions(sender))
					sender.sendMessage(" §f* §7/core "+cmd.getCommand()+" - "+cmd.getDescription());
			sender.sendMessage("");
		});
	}
}
