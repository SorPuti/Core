package br.com.puti.core.component.npc.api.virtual.holograms;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import com.google.common.collect.ImmutableList;
import net.minecraft.server.v1_8_R3.EntityTypes;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HologramLibrary {

  
  private static List<Hologram> holograms = new ArrayList<>();
  
  static {
    try {
      Field classToString = EntityTypes.class.getDeclaredField("d");
      classToString.setAccessible(true);
      Map map = (Map) classToString.get(null);
      map.put(EntityHologramStand.class, "ArmorStand");
      
      Field classToId = EntityTypes.class.getDeclaredField("f");
      classToId.setAccessible(true);
      map = (Map) classToId.get(null);
      map.put(EntityHologramStand.class, 30);
    } catch (ReflectiveOperationException e) {
      e.printStackTrace();
    }
  }
  
  public static Hologram createHologram(Location location, String... lines) {
    Hologram hologram = new Hologram(location, lines);
    holograms.add(hologram);
    return hologram;
  }
  
  public static void removeHologram(Hologram hologram) {
    holograms.remove(hologram);
    hologram.despawn();
  }
  
  public static List<Hologram> listHolograms() {
    return ImmutableList.copyOf(holograms);
  }
}
