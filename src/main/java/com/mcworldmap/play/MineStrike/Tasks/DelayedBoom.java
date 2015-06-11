package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class DelayedBoom implements Runnable
{
	World world;
	Location loc;
    Entity damager;

	public DelayedBoom(World world, Location loc, Entity damager)
	{
		this.world = world;
		this.loc = loc;
        this.damager = damager;
	}

	public void run()
	{
		world.createExplosion(loc, 0.0f);
        Collection<Entity> nearExplosion = world.getNearbyEntities(loc, 3, 3, 3);

        for(Entity e : nearExplosion)
        {
            if(e instanceof  Player)
            {
                Player p = (Player)e;

                p.damage(18, damager);
            }
        }
	}
}
