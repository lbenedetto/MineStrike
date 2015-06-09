package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Location;
import org.bukkit.World;

public class DelayedBoom implements Runnable
{
	World world;
	Location loc;

	public DelayedBoom(World world, Location loc)
	{
		this.world = world;
		this.loc = loc;
	}

	public void run()
	{
		world.createExplosion(loc.getX(), loc.getY(), loc.getZ(), 5, false, false);
	}
}
