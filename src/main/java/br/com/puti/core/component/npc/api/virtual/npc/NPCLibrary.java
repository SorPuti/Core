package br.com.puti.core.component.npc.api.virtual.npc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.entity.Entity;

public class NPCLibrary {

	private static List<NPC> npcs = new ArrayList<>();

	public static NPC createNPC(String name) {
		return createNPC(UUID.nameUUIDFromBytes(("NPC:" + name).getBytes()), name);
	}

	public static NPC createNPC(UUID uuid, String name) {
		NPC npc = new NPC(uuid, name);
		npcs.add(npc);
		return npc;
	}

	public static List<NPC> listNPCs() {
		return npcs;
	}

	public static NPC getNPC(Entity entity) {
		return isNPC(entity) ? ((NPCHolder) entity).getNPC() : null;
	}

	public static NPC getNPC(String skinName) {
		npcs.forEach( npc -> {
		});
		return null;
	}

	public static boolean isNPC(Entity entity) {
		return entity != null && entity instanceof NPCHolder;
	}
}
