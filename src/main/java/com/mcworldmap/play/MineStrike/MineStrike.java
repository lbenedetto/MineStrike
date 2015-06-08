package com.mcworldmap.play.MineStrike;

import com.google.common.collect.Sets;
import com.mcworldmap.play.MineStrike.PlayerData.Spawnpoint;
import com.mcworldmap.play.MineStrike.PlayerData.Team;
import com.mcworldmap.play.MineStrike.commands.CmdBuy;
import com.mcworldmap.play.MineStrike.commands.CmdJoin;
import com.mcworldmap.play.MineStrike.commands.CmdScoreboard;
import com.mcworldmap.play.MineStrike.commands.CmdStart;
import com.mcworldmap.play.MineStrike.listeners.EventListener;
import com.mcworldmap.play.MineStrike.listeners.onDeath;
import com.mcworldmap.play.MineStrike.listeners.onDecoyImpact;
import com.mcworldmap.play.MineStrike.listeners.onNadeImpact;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class MineStrike extends JavaPlugin
{

	public static String gamemode = "";
	public static Team team = new Team();
	public static int ts = 0, cts = 0;
	public static Spawnpoint spawnpoint;
	public static Set<Integer> transparent = Sets.newHashSet();

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new onDeath(), this);
		getServer().getPluginManager().registerEvents(new onDecoyImpact(), this);
		getServer().getPluginManager().registerEvents(new onNadeImpact(), this);
		getCommand("buy").setExecutor(new CmdBuy());
		getCommand("start").setExecutor(new CmdStart());
		getCommand("join").setExecutor(new CmdJoin());
		getCommand("scoreboard").setExecutor(new CmdScoreboard());
		saveDefaultConfig();
		FileConfiguration config = getConfig();
		spawnpoint = new Spawnpoint(config);
		// Determine transparency
		for (Material material : Material.values())
		{
			if (material.isTransparent())
			{
				transparent.add(material.getId());
			}
		}
		getLogger().info("MineStrike Enabled");
	}

	@Override
	public void onDisable()
	{
	}
}
