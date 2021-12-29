package br.com.puti.core.task;

import br.com.puti.core.loader.CoreLoader;
import br.com.puti.core.services.database.DataBaseServices;
import org.bukkit.scheduler.BukkitRunnable;

public class CoreTask extends BukkitRunnable {

    private CoreLoader plugin;
    private DataBaseServices services;


    public CoreTask(CoreLoader plugin) {
        this.plugin = plugin;
        this.services = plugin.getDataBaseLoader().getDataBaseServices();
    }


    @Override
    public void run() {
        services.getAll().forEach(db -> {
            if (!db.isConnected()) {
                System.out.println("Recuperando conexão perdida("+db.getID()+")....");
                db.getConnection();
            }
        });
    }
}
