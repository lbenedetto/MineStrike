package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.util.Iterator;
import java.util.Set;

public class Person
{
	private int score, kills, deaths, money;
	private Player player;
	private boolean alive;

	public Person(Player player)
	{
		this.player = player;
		this.score = 0;
		this.kills = 0;
		this.deaths = 0;
		this.money = 800;
		this.alive = true;
	}

	public String toString()
	{
		if (this.alive)
			return "Alive | " + this.player.getDisplayName() + " | $" + this.money + " | " + this.kills + " | " + this.deaths + " | " + this.score;
		else
			return "Dead | " + this.player.getDisplayName() + " | $" + this.money + " | " + this.kills + " | " + this.deaths + " | " + this.score;

	}

	public void respawnT()
	{
		World world = player.getWorld();
		Location location = MineStrike.spawnpoint.getRandTSpawn();
		player.teleport(location);
		player.setHealth(player.getMaxHealth());
	}

	public void respawnCT()
	{
		World world = player.getWorld();
		Location location = MineStrike.spawnpoint.getRandCTSpawn();
		player.teleport(location);
		player.setHealth(player.getMaxHealth());
	}

	public boolean isAlive()
	{
		return this.alive;
	}

	public boolean isDead()
	{
		return !this.alive;
	}

	public void setAlive(boolean b)
	{
		this.alive = b;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getKills()
	{
		return kills;
	}

	public void setKills(int kills)
	{
		this.kills = kills;
	}

	public int getDeaths()
	{
		return deaths;
	}

	public void setDeaths(int deaths)
	{
		this.deaths = deaths;
	}

	public int getMoney()
	{
		return money;
	}

	public void creditItem(Item item)
	{
		//TODO: Give the player the specified item.
	}


	public void setMoney(int money)
	{
		this.money = money;
		if(this.money > 16000)
			this.money = 16000;
	}
	public void addMoney(int money){
		this.money = this.money + money;
		if(this.money > 16000)
			this.money = 16000;
	}

	public Player getPlayer()
	{
		return player;
	}

	public boolean canSee(Entity b)
	{
		// Checking A - B is the same as B - A, so skip those
		if (this.player.getWorld().equals(b.getWorld()))
		{
			// Bounding box of the target
			Location targetAA = b.getLocation().add(-0.5, 0, -0.5);
			Location targetBB = b.getLocation().add(0.5, .5, 0.5);
			int distance = (int) this.player.getLocation().distance(targetAA);

			// No need to check this
			if (distance > 120)
				return false;

			if (getTargetBlock(lookAt(this.player.getLocation(), targetAA), MineStrike.transparent, distance) == null ||
					getTargetBlock(lookAt(this.player.getLocation(), targetBB), MineStrike.transparent, distance) == null)
			{
				// All air - we can probably see this player
				Bukkit.getServer().getLogger().info(this.player.getDisplayName() + " can see " + b);
				return true;
			}
		}
		return true;
	}
	private Location lookAt(Location loc, Location lookat)
	{
		double dx = lookat.getX() - loc.getX();
		double dy = lookat.getY() - loc.getY();
		double dz = lookat.getZ() - loc.getZ();

		double dxz = Math.sqrt(dx * dx + dz * dz);
		double pitch = Math.atan(dy / dxz);
		double yaw = 0;

		if (dx != 0)
		{
			if (dx < 0)
			{
				yaw = 1.5 * Math.PI;
			} else
			{
				yaw = 0.5 * Math.PI;
			}
			yaw -= Math.atan(dz / dx);
		} else if (dz < 0)
		{
			yaw = Math.PI;
		}

		loc.setYaw((float) Math.toDegrees(-yaw));
		loc.setPitch((float) Math.toDegrees(-pitch));
		return loc;
	}

	private Block getTargetBlock(Location direction, Set<Integer> transparent, int maxDistance)
	{
		for (Iterator<Block> it = new BlockIterator(direction, 0, maxDistance); it.hasNext(); )
		{
			Block block = it.next();
			int id = block.getTypeId();

			// Determine if this is a non-air block
			if (transparent == null ? id != 0 : !transparent.contains(id))
			{
				return block;
			}
		}
		// No target block found
		return null;
	}
}
