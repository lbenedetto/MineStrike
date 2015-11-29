package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
    /**
     * Disallow use of the opposing teams shop.
     *
     * @param event
     */
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (!MineStrike.isGameActive)
            return;
        if (event.getMessage().equalsIgnoreCase("/shopct") && MineStrike.teams.getTeam(event.getPlayer()).equalsIgnoreCase("t")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You can't open CT's shop as T!");
            return;
        }
        if (event.getMessage().equalsIgnoreCase("/shopt") && MineStrike.teams.getTeam(event.getPlayer()).equalsIgnoreCase("ct")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You can't open T's shop as CT!");
            return;
        }
    }
}
