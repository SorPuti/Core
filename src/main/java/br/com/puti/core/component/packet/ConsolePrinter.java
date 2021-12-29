package br.com.puti.core.component.packet;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

public class ConsolePrinter {
	
	/** Specifies the prefix. */
	private static final String prefix = "[Core] ";
	
	/** Logger used to send messages to the console. */
	private static Logger logger = Bukkit.getLogger();
	
	public static void log(Level lvl, String msg) {
		logger.log(lvl, prefix + msg);
	}
	
	public static void print(String msg) {
		log(Level.INFO, msg);
	}
	
	public static void err(String msg) {
		log(Level.SEVERE, msg);
	}
	
	public static void warn(String msg) {
		log(Level.WARNING, msg);
	}
	
	
	
	

}
