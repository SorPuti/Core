package br.com.puti.core.executables;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.puti.core.executables.sub.HelpCommand;
import br.com.puti.core.executables.sub.StatsCommand;
import br.com.puti.core.loader.CoreLoader;

public class CoreCommnad implements CommandExecutor {

	CoreLoader plugin;

	public  List<br.com.puti.core.component.command.Command> commands = new ArrayList<br.com.puti.core.component.command.Command>();

	public CoreCommnad(CoreLoader plugin) {
		this.plugin = plugin;
		this.commands.add(new StatsCommand(plugin));
		this.commands.add(new HelpCommand(this));
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;

		if (args.length == 0) 
			return true;
		
		for(br.com.puti.core.component.command.Command cmd : this.commands)
			if (cmd.has(args[0]))
				if (cmd.hasPermissions(sender)) {
					cmd.a(sender, args);
					return true;
				}else {
					sender.sendMessage("§cVocê não possui permissão para utilizar este comando.");
					return true;
				}
		
		sender.sendMessage("§cSub-Comando não encontrado.");
		return true;
	}

}