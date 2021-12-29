package br.com.puti.core.component.npc.api.virtual.npc;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;

import br.com.puti.core.Core;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityTrackerEntry;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class PlayerlistTracker extends EntityTrackerEntry {

  public PlayerlistTracker(Entity entity, int i, int j, boolean flag) {
    super(entity, i, j, flag);
  }

  public PlayerlistTracker(EntityTrackerEntry entry) {
    this(entry.tracker, entry.b, entry.c, getU(entry));
  }

  @Override
  public void updatePlayer(EntityPlayer entityplayer) {
    if (entityplayer instanceof EntityNPCPlayer) {
      return;
    }

    if (entityplayer != tracker && c(entityplayer)) {
      if (!trackedPlayers.contains(entityplayer)
          && (entityplayer.u().getPlayerChunkMap().a(entityplayer, tracker.ae, tracker.ag)
              || tracker.attachedToPlayer)) {
        if (Core.getPlugin(Core.class) == null || !Core.getPlugin(Core.class).isEnabled()) {
          return; // previnir o crash que acabou de acontecer
        }
        
        // Aqui iremos enviar a info do NPC.
        entityplayer.playerConnection.sendPacket(
            new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, (EntityPlayer) tracker));
        // agora iremos remover do Tab
        Bukkit.getScheduler().scheduleSyncDelayedTask(Core.getPlugin(Core.class), new Runnable() {

          @Override
          public void run() {
            entityplayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(
                EnumPlayerInfoAction.REMOVE_PLAYER, (EntityPlayer) tracker));
          }
        }, 1);
      }
    }

    super.updatePlayer(entityplayer);
  }

  static boolean getU(EntityTrackerEntry entry) {
    try {
      Field U = entry.getClass().getDeclaredField("u");
      U.setAccessible(true);
      return (boolean) U.get(entry);
    } catch (ReflectiveOperationException ex) {
      ex.printStackTrace();
      return false;
    }
  }
}
