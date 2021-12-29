package br.com.puti.core.component.packet;

import br.com.puti.core.component.packet.packets.PacketKeepAlive;
import br.com.puti.core.component.packet.packets.PacketServerRunning;
import br.com.puti.core.component.packet.packets.PacketServerStopping;
import br.com.puti.core.component.packet.util.ServerRunningResult;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Core class of BungeeBridgeC(Spigot).
 * Keep in mind you always have to use the same version of BungeeBridgeS(Bungeecord) and BungeeBridgeC(Spigot)!
 * http://www.spigotmc.org/resources/bungeebridge.5820/
 */
public class BungeeBridgeC extends JavaPlugin {		
	
	/** The static instance of BungeeBridgeC. */
	public static JavaPlugin instance = null;
	
	/** Displays the unique bungeename of this server. Obtained by sending PacketServerRunning. */
	protected static String BUNGEENAME = "";

	/** The version of the config. */
	protected static int CONFIGVERSION;
	
	/** The host to send packets to. Obtained from the config. */
	protected static String HOST;	
	
	/** The port to send packets to. Obtained from the config. */
	protected static int PORT;
	
	/** Displays the SecurityMode of the Server. .Obtained from the config. */
	protected static SecurityMode SECMODE;
	
	/** The password used to secure packets. Obtained from the config. */
	protected static String PASS;
	
	/** The timeintervall to send PacketKeepAlive(s). */
	protected static int UPDATEINTERVALL;
	
	/** The File of the config. */
	protected static File configfile;
	
	/** The FileConfiguration of the config. */
	protected static FileConfiguration config;

	@Override
	public void onEnable() {
		BungeeBridgeC.instance = this;
		BungeeBridgeC.enable();
		ConsolePrinter.print("Starting BungeeBridgeC... Keep in mind you always have to use the same version of BungeeBridgeS(Bungeecord) and BungeeBridgeC(Spigot)!");
		ConsolePrinter.print("Port: " + PORT);
		ConsolePrinter.print("SecurityMode: " + SECMODE);
		
		long sended = System.currentTimeMillis();
		PacketServerRunning startpacket = new PacketServerRunning(Bukkit.getServerName(), Bukkit.getMotd(), Bukkit.getPort(), UPDATEINTERVALL, getVersion());
		ServerRunningResult result = (ServerRunningResult) startpacket.send();
		
		if(result.getVersion() != getVersion()) {
			ConsolePrinter.err("Your version of BungeeBridgeS(Bungeecord) is incompatible to your version of BungeeBridgeC(Spigot)!\nYou have to update immediately!");
			Bukkit.getPluginManager().disablePlugin(instance);
		} else {
			BUNGEENAME = result.getBungeename();
			ConsolePrinter.print("Connected! Server ---[" + (result.getTime() - sended) + "ms]---> Bungee ---[" + (System.currentTimeMillis() - result.getTime()) + "ms]---> Server");
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					new PacketKeepAlive(BUNGEENAME, true, Bukkit.getMotd()).send();
				}		
			}, UPDATEINTERVALL * 20L, UPDATEINTERVALL * 20L);
		}
	}
	
	@Override
	public void onDisable() {
		PacketServerStopping stoppacket = new PacketServerStopping(BUNGEENAME);
		stoppacket.send();
		BungeeBridgeC.instance = null;		
	}
	
	private static void enable() {
		if(!BungeeBridgeC.instance.getDataFolder().exists()) {
			BungeeBridgeC.instance.getDataFolder().mkdir();
		}
		configfile = new File(BungeeBridgeC.instance.getDataFolder().getPath() + File.separator + "config.yml");
		if(configfile.exists()) {
			ConfigUpdater.update();
		} else {
			ConfigManager.createConfig();
		}
		ConfigManager.loadConfig();
	}
	
	public static void sendKeepAlive() {
		new PacketKeepAlive(BUNGEENAME, false, Bukkit.getMotd());
	}
	
	public static int getVersion() {
		return Integer.valueOf(instance.getDescription().getVersion().replace(".", ""));
	}

}
