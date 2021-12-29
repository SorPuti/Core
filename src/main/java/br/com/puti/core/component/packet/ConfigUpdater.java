package br.com.puti.core.component.packet;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUpdater {
	
	public static void update() {
		final int CURRENTVERSION = BungeeBridgeC.getVersion();
		BungeeBridgeC.config = YamlConfiguration.loadConfiguration(BungeeBridgeC.configfile);
		if(BungeeBridgeC.config.get("configversion") == null) {
			//Update Config from 1.3.0 or lower to 1.4.0 or higher
		} else {
			BungeeBridgeC.CONFIGVERSION = BungeeBridgeC.config.getInt("configversion");
			if(BungeeBridgeC.CONFIGVERSION < CURRENTVERSION) {
				ConsolePrinter.print("Your config is outdated! Running updater...");
				ConsolePrinter.print("Your config is outdated! Running updater...");
				/*
				 * if(BungeeBridgeS.CONFIGVERSION == ???) {
				 * 		//Update...
				 * }
				*/
				ConsolePrinter.print("Done!");
			}
		}
		BungeeBridgeC.config.set("configversion", BungeeBridgeC.getVersion());
		try {
			BungeeBridgeC.config.save(BungeeBridgeC.configfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
