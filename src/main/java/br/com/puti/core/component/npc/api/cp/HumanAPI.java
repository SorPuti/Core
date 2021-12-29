
package br.com.puti.core.component.npc.api.cp;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import br.com.puti.core.component.npc.NPCAction;
import br.com.puti.core.component.npc.api.utilities.SkinFetcher;
import br.com.puti.core.component.npc.api.virtual.npc.NPC;
import br.com.puti.core.component.npc.api.virtual.npc.NPCLibrary;

public class HumanAPI {

    private String id;
    private Location location;
    private String value, signature;
    public NPCAction<NPC, Player> action = null;
    private NPC thisNPC = null;

    public HumanAPI(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    public String getID() {
        return this.id;
    }

    public HumanAPI setSkin(String name) {
        String[] args = SkinFetcher.getFromName(name);

        this.value = args[0];
        this.signature = args[1];
        return this;
    }

    public HumanAPI setSkin(String value, String signature) {
        this.value = value;
        this.signature = signature;
        return this;
    }

	public NPC getNPC() {
		return thisNPC;
	}

	public void spawn() {
		if (thisNPC != null)
			return;

		thisNPC = NPCLibrary.createNPC(this.id);
        thisNPC.setExtends(this);

        thisNPC.setSkin(value, signature);

        thisNPC.spawn(location);
    }

    public void spawn(String headName) {
		if (thisNPC != null)
			return;

        thisNPC = NPCLibrary.createNPC(headName);
        thisNPC.setExtends(this);

        thisNPC.setSkin(value, signature);

        thisNPC.spawn(location);
    }

    public void spawn(UUID uuid, String headName) {
        NPC npc = NPCLibrary.createNPC(uuid, headName);
        npc.setExtends(this);

        npc.setSkin(value, signature);

        npc.spawn(location);
    }

    public <T> HumanAPI onClick(NPCAction<NPC, Player> action) {
        this.action = action;
        return this;
    }

    public Location getSpawnLocation() {
        return this.location;
    }

    public static NPCLibrary getManager() {
        return new NPCLibrary();
    }
}
