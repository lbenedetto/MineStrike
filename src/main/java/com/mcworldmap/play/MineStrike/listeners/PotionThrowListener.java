package com.mcworldmap.play.MineStrike.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

public class PotionThrowListener implements Listener {
    /**
     * Modify the speed of which potions are thrown.
     */
    @SuppressWarnings("unused")
    @EventHandler
    public void potionThrowEvent(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Firework) {
            event.getEntity().setVelocity(new Vector(0, 0, 0));
        }
        if (event.getEntity() instanceof ThrownPotion) {
            Entity e = event.getEntity();
            ThrownPotion thrownPotion = (ThrownPotion) e;
            thrownPotion.setBounce(true);
            e.setVelocity(e.getVelocity().multiply(2));
        }
    }
}
