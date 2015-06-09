package com.mcworldmap.play.MineStrike;

import com.google.common.collect.Sets;
import com.mcworldmap.play.MineStrike.PlayerData.Spawnpoint;
import com.mcworldmap.play.MineStrike.PlayerData.Team;
import com.mcworldmap.play.MineStrike.commands.*;
import com.mcworldmap.play.MineStrike.listeners.EventListener;
import com.mcworldmap.play.MineStrike.listeners.onDeath;
import com.mcworldmap.play.MineStrike.listeners.onDecoyImpact;
import com.mcworldmap.play.MineStrike.listeners.onNadeImpact;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.Set;

public class MineStrike extends JavaPlugin
{

	public static String gamemode = "";
	public FileConfiguration config = getConfig();
	public static int teamsize = config.getInt("teamsize");
	public static Team team = new Team();
	public static int ts = 0, cts = 0;
	public static Spawnpoint spawnpoint;
	public static Set<Integer> transparent = Sets.newHashSet();
	public static ArrayList<Player> frozenPlayers = new ArrayList<>();

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
		getCommand("givemoney").setExecutor(new CmdGiveMoney());
		saveDefaultConfig();
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
		getLogger().warning("Teamsize" + teamsize);
	}

	@Override
	public void onDisable()
	{
	}
}
