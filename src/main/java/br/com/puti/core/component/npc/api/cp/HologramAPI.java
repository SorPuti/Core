package br.com.puti.core.component.npc.api.cp;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import br.com.puti.core.component.npc.api.virtual.holograms.Hologram;
import br.com.puti.core.component.npc.api.virtual.holograms.HologramLibrary;

public class HologramAPI {
	
	private static List<Hologram> holograms = new ArrayList<Hologram>(); 

	public static Hologram makeHologram(Location location, String... lines) {
		final Hologram hologram = HologramLibrary.createHologram(location, lines);
		
		hologram.spawn();
		
		holograms.add(hologram);
		return hologram;
	}

	
	public static void clearAll() {
		for (Hologram h : holograms) {
			try {
				h.despawn();
			} catch (Exception Ignored) {}
		}
	}
	
	public static Hologram get(Location location) {
		for (Hologram h : holograms) {
			if (h.getInitLocation().distance(location) == 0) return h;
		}
		return null;
	}
}
