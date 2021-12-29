package br.com.puti.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.puti.core.Core;
import br.com.puti.core.component.model.CorePlayer;
import br.com.puti.core.events.PlayerConnectedEvent;
import br.com.puti.core.events.PlayerConnectedType;
import br.com.puti.core.services.database.DataBaseServices;

public class PlayerListeners implements Listener {

    @EventHandler
    void e(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player player = e.getPlayer();

        CorePlayer corePlayer = CorePlayer.get(player.getName());
        if (corePlayer != null)
            corePlayer = new CorePlayer(player);

        DataBaseServices baseServices = Core.getManager().getDataBaseLoader().getDataBaseServices();
        baseServices.regenConnections();
        baseServices.checkAndCreate(player.getName());

        try {
            PlayerConnectedEvent event = new PlayerConnectedEvent(player.getName(), corePlayer.getCurrentServer(),
                    PlayerConnectedType.LOCAL);
            Bukkit.getPluginManager().callEvent(event);
        } catch (Exception e2) {
        }
    }

}
