package br.com.puti.core.component.npc;

import org.bukkit.entity.Player;

import br.com.puti.core.component.npc.api.virtual.npc.NPC;

public interface NPCAction<E extends NPC, T extends Player> {
	void interact(E paramsE, T paramsT);
}
