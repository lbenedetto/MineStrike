package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;

import java.util.List;

public class DelayedSound implements Runnable {
    World world;
    Location loc;
    ThrownPotion pot;
    Sound sound;

    public DelayedSound(World world, Location loc, ThrownPotion pot, Sound sound) {
        this.world = world;
        this.pot = pot;
        this.loc = loc;
        this.sound = sound;
    }

    public void run() {
        List<Entity> nearbyEntities = pot.getNearbyEntities(20, 20, 20);
        nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, sound, 1, 1));
    }
}
