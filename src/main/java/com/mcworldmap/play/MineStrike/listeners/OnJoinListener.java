package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinListener implements Listener {
    /**
     * Create data in the database for the player on login.
     *
     * @param event
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        MineStrike.getNetwork().createPlayerData(event.getPlayer());
    }
}
