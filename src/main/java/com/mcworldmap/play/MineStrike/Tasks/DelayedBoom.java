package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class DelayedBoom implements Runnable {
    World world;
    Location loc;
    Entity damager;

    /**
     * @param world   The world of which the explosion is to occur in
     * @param loc     The Location fo the explosion
     * @param damager The person that triggered this explosion
     */
    public DelayedBoom(World world, Location loc, Entity damager) {
        this.world = world;
        this.loc = loc;
        this.damager = damager;
    }

    /**
     * damage the player if they are in the range of the explosion.
     */
    public void run() {
        world.createExplosion(loc, 0.0f);
        Collection<Entity> nearExplosion = world.getNearbyEntities(loc, 3, 3, 3);

        nearExplosion.stream().filter(e -> e instanceof Player).forEach(e -> {
            Player p = (Player) e;
            p.damage(18, damager);
        });
    }
}
