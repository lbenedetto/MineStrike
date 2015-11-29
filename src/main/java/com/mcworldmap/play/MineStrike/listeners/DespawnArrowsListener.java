package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.Tasks.DelayArrowRemove;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DespawnArrowsListener implements Listener {
    /**
     * Schedules a task to remove an arrow as soon as it hits something
     */
    @SuppressWarnings("unused")
    @EventHandler
    public void projectileHitEvent(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DelayArrowRemove((Arrow) event.getEntity()), 200);
        }
    }
}
