package br.com.puti.core.component.test;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PluginTask extends BukkitRunnable {

    private List<Plank> planks = new ArrayList<>();
    private long value;

    public void waitTemp(long args0) {
        this.value = args0;
    }

    @Override
    public void run() {
        try {
            this.planks.forEach((plank) -> {
                int rando = new Random().nextInt();
                if (rando < 0.60 && value == 0) {
                    plank.update();
                } else {
                    value--;
                }
                plank.updateHolograms();
            });
        } catch (Exception ex) {
            cancel();
        }

    }
}
