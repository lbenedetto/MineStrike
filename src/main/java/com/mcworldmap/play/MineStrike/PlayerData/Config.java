package com.mcworldmap.play.MineStrike.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Random;

public class Config
{
	public HashMap<String, Object> config = new HashMap<>();

	public Config(FileConfiguration config)
	{
		World w = Bukkit.getWorld(config.getString("world"));
		//T spawns
		this.config.put("T1", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.one.x"), config.getInt("competitive.de_dust2.spawnpoints.T.one.y"), config.getInt("competitive.de_dust2.spawnpoints.T.one.z")));
		this.config.put("T2", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.two.x"), config.getInt("competitive.de_dust2.spawnpoints.T.two.y"), config.getInt("competitive.de_dust2.spawnpoints.T.two.z")));
		this.config.put("T3", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.three.x"), config.getInt("competitive.de_dust2.spawnpoints.T.three.y"), config.getInt("competitive.de_dust2.spawnpoints.T.three.z")));
		this.config.put("T4", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.four.x"), config.getInt("competitive.de_dust2.spawnpoints.T.four.y"), config.getInt("competitive.de_dust2.spawnpoints.T.four.z")));
		this.config.put("T5", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.T.five.x"), config.getInt("competitive.de_dust2.spawnpoints.T.five.y"), config.getInt("competitive.de_dust2.spawnpoints.T.five.z")));
		//CT spawns
		this.config.put("CT1", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.one.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.one.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.one.z")));
		this.config.put("CT2", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.two.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.two.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.two.z")));
		this.config.put("CT3", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.three.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.three.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.three.z")));
		this.config.put("CT4", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.four.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.four.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.four.z")));
		this.config.put("CT5", new Location(w, config.getInt("competitive.de_dust2.spawnpoints.CT.five.x"), config.getInt("competitive.de_dust2.spawnpoints.CT.five.y"), config.getInt("competitive.de_dust2.spawnpoints.CT.five.z")));
		//Spawnbox
		this.config.put("TBox", new Location(w, config.getInt("competitive.de_dust2.spawnbox.T.x"), config.getInt("competitive.de_dust2.spawnbox.T.y"), config.getInt("competitive.de_dust2.spawnbox.T.z")));
		this.config.put("CTBox", new Location(w, config.getInt("competitive.de_dust2.spawnbox.CT.x"), config.getInt("competitive.de_dust2.spawnbox.CT.y"), config.getInt("competitive.de_dust2.spawnbox.CT.z")));
		//Pregame Spawn
		this.config.put("pregameSpawn", new Location(w, config.getInt("pregameSpawn.x"), config.getInt("pregameSpawn.y"), config.getInt("pregameSpawn.z")));
		//Teamsize
		this.config.put("teamsize", config.getInt("teamsize"));
		//Max Team Kills
		this.config.put("maxTeamKills", config.getInt("maxTeamKills"));
	}

	public int getInt(String s)
	{
		return (int) config.get(s);
	}

	public Location getLocation(String s)
	{
		return (Location) config.get(s);
	}

	public Location getRandTSpawn()
	{
		Random rg = new Random();
		int randInt = rg.nextInt(5);
		switch (randInt)
		{
			case 0:
				return (Location) config.get("T1");
			case 1:
				return (Location) config.get("T2");
			case 2:
				return (Location) config.get("T3");
			case 3:
				return (Location) config.get("T4");
			case 4:
				return (Location) config.get("T5");
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
				return (Location) config.get("CT1");
			case 1:
				return (Location) config.get("CT2");
			case 2:
				return (Location) config.get("CT3");
			case 3:
				return (Location) config.get("CT4");
			case 4:
				return (Location) config.get("CT5");
		}
		return null;
	}
}
