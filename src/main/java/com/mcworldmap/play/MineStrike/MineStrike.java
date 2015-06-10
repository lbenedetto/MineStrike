package com.mcworldmap.play.MineStrike;

import com.google.common.collect.Sets;
import com.mcworldmap.play.MineStrike.PlayerData.Config;
import com.mcworldmap.play.MineStrike.PlayerData.Team;
import com.mcworldmap.play.MineStrike.commands.*;
import com.mcworldmap.play.MineStrike.listeners.*;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.Set;

public class MineStrike extends JavaPlugin
{

	public static String gamemode = "";
	public FileConfiguration cfg = getConfig();
	public static Team team;
	public static int ts = 0, cts = 0;
	public static Config config;
	public static Set<Integer> transparent = Sets.newHashSet();
	public static ArrayList<Player> frozenPlayers = new ArrayList<>();
    public static ArrayList<Player> coolDown = new ArrayList<>();
    public static boolean isGameActive = false;

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new onDeath(), this);
		getServer().getPluginManager().registerEvents(new onNadeImpact(), this);
        getServer().getPluginManager().registerEvents(new ArrowFire(), this);

		getCommand("buy").setExecutor(new CmdBuy());
		getCommand("start").setExecutor(new CmdStart());
		getCommand("join").setExecutor(new CmdJoin(this));
		getCommand("scoreboard").setExecutor(new CmdScoreboard());
		getCommand("givemoney").setExecutor(new CmdGiveMoney());
		saveDefaultConfig();
		config = new Config(cfg);
		team = new Team(this);
		// Determine transparency
		for (Material material : Material.values())
		{
			if (material.isTransparent())
			{
				transparent.add(material.getId());
			}
		}
		getLogger().info("MineStrike Enabled");
		getLogger().warning("Teamsize" + getConfig().getInt("teamsize"));
	}

	@Override
	public void onDisable()
	{
	}
}
