package br.com.puti.core.component.npc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import br.com.puti.core.component.npc.api.virtual.npc.NPC;
import br.com.puti.core.component.npc.api.virtual.npc.NPCLibrary;
import org.jetbrains.annotations.NotNull;

public class NPCListeners implements Listener {

	@EventHandler
	void e(@NotNull PlayerInteractEntityEvent e) {
		if (NPCLibrary.isNPC(e.getRightClicked())) {
			NPC npc = NPCLibrary.getNPC(e.getRightClicked());
			if (npc.getHuman() != null && npc.getHuman().action != null)
				npc.getHuman().action.interact(npc, e.getPlayer());
		}
	}

}
