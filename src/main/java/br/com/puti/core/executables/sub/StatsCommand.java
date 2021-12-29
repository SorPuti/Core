package br.com.puti.core.executables.sub;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.base.Strings;

import br.com.puti.core.component.command.Command;
import br.com.puti.core.loader.CoreLoader;

public class StatsCommand extends Command {

	protected CoreLoader plugin;

	public StatsCommand(CoreLoader plugin) {
		this.plugin = plugin;
		setCommand("status");
		setAliases(new String[] { "stats" });
		setPermission("core.admin");
		setDescription("Veja os estado de alguns serviços em tempo real.");

		setCommand((sender, args) -> {
			if (!(sender instanceof Player))
				return;

			Player player = (Player) sender;
			Runtime r = Runtime.getRuntime();
			int memUsed = (int) ((r.totalMemory() - r.freeMemory()) / 1048576);
			int dataBasesStabilized = plugin.getDataBaseLoader().getDataBaseServices().size();
			int accountsStabilized = plugin.getAccountServices().size();
			int dataBasesUnstable = plugin.getDataBaseLoader().getDataBaseServices().getDeconnected();

			int percent = memUsed * 100 / 100;
			double b = Math.round(percent * 10.0) / 10.0;

			player.sendMessage("                               §f[Core Status]                                    ");
			player.sendMessage("");
			player.sendMessage("§fContas Sincronizadas: §7" + accountsStabilized);
			player.sendMessage("§fConexões Estáveis: §7" + dataBasesStabilized);
			player.sendMessage("§fConexões Instáveis: §7" + dataBasesUnstable);
			player.sendMessage("");
			player.sendMessage("" + getProgressBar(memUsed, 100, 40, '‖', ChatColor.GREEN, ChatColor.RED));
			player.sendMessage("§7[" + b + "%]");
		});
	}

	public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
			ChatColor notCompletedColor) {
		float percent = (float) current / max;
		int progressBars = (int) (totalBars * percent);
		int nprogress = totalBars - progressBars;
		if (nprogress < 0)
			nprogress = Math.abs(nprogress);

		return Strings.repeat("" + completedColor + symbol, progressBars)
				+ Strings.repeat("" + notCompletedColor + symbol, nprogress);
	}
}
