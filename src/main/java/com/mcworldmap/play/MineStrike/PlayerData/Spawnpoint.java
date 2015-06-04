package com.mcworldmap.play.MineStrike.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Random;

public class Spawnpoint
{
	public HashMap<String, Location> spawnpoint = new HashMap<>();

	public Spawnpoint(FileConfiguration config)
	{
		World w = Bukkit.getWorld(config.getString("world"));
		//T spawns
		spawnpoint.put("T1", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.one.x"), config.getInt("competitive.de_dust2.spawnpoints.T.one.y"), config.getInt("competitive.de_dust2.spawnpoints.T.one.z")));
		spawnpoint.put("T2", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.two.x"), config.getInt("competitive.de_dust2.spawnpoints.T.two.y"), config.getInt("competitive.de_dust2.spawnpoints.T.two.z")));
		spawnpoint.put("T3", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.three.x"), config.getInt("competitive.de_dust2.spawnpoints.T.three.y"), config.getInt("competitive.de_dust2.spawnpoints.T.three.z")));
		spawnpoint.put("T4", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.four.x"), config.getInt("competitive.de_dust2.spawnpoints.T.four.y"), config.getInt("competitive.de_dust2.spawnpoints.T.four.z")));
		spawnpoint.put("T5", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.five.x"), config.getInt("competitive.de_dust2.spawnpoints.T.five.y"), config.getInt("competitive.de_dust2.spawnpoints.T.five.z")));
		//CT spawns
		spawnpoint.put("CT1", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.one.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.one.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.one.z")));
		spawnpoint.put("CT2", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.two.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.two.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.two.z")));
		spawnpoint.put("CT3", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.three.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.three.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.three.z")));
		spawnpoint.put("CT4", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.four.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.four.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.four.z")));
		spawnpoint.put("CT5", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.five.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.five.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.five.z")));
	}

	public Location getRandTSpawn()
	{
		Random rg = new Random();
		int randInt = rg.nextInt(5);
		switch (randInt)
		{
			case 0:
				return spawnpoint.get("T1");
			case 1:
				return spawnpoint.get("T2");
			case 2:
				return spawnpoint.get("T3");
			case 3:
				return spawnpoint.get("T4");
			case 4:
				return spawnpoint.get("T5");
		}
		return null;
	}
	public Location getRandCTSpawn()
	{
		Random rg = new Random();
		int randInt = rg.nextInt(5);
		switch (randInt)
		{
			case 0:
				return spawnpoint.get("CT1");
			case 1:
				return spawnpoint.get("CT2");
			case 2:
				return spawnpoint.get("CT3");
			case 3:
				return spawnpoint.get("CT4");
			case 4:
				return spawnpoint.get("CT5");
		}
		return null;
	}
}
