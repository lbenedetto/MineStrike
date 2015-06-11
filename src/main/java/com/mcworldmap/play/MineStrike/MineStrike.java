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
	public static boolean canBuy = false;

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
		populateConfig();
		saveDefaultConfig();
		config = new Config(cfg);
		team = new Team();
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

	public void populateConfig(){
		getConfig().addDefault("maxTeamKills", 3);
		getConfig().addDefault("world", "de_dust2");
		getConfig().addDefault("pregameSpawn.x", -33);
		getConfig().addDefault("pregameSpawn.y", 42);
		getConfig().addDefault("pregameSpawn.z", -506);
		getConfig().addDefault("competitive.de_dust2.spawnbox.T.x", -46);
		getConfig().addDefault("competitive.de_dust2.spawnbox.T.y", 42);
		getConfig().addDefault("competitive.de_dust2.spawnbox.T.z", -481);
		getConfig().addDefault("competitive.de_dust2.spawnbox.CT.x", -56);
		getConfig().addDefault("competitive.de_dust2.spawnbox.CT.y", 42);
		getConfig().addDefault("competitive.de_dust2.spawnbox.CT.z", -481);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.one.x", -3);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.one.y", 46);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.one.z", -551);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.two.x", -9);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.two.y", 46);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.two.z", -551);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.three.x", -9);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.three.y", 46);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.three.z", -554);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.four.x", -13);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.four.y", 46);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.four.z", -554);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.five.x", -1);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.five.y", 46);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.T.five.z", -557);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.one.x", -31);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.one.y", 40);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.one.z", -469);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.two.x", -35);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.two.y", 40);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.two.z", -469);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.three.x", -39);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.three.y", 40);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.three.z", -469);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.four.x", -37);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.four.y", 40);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.four.z", -471);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.five.x", -33);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.five.y", 40);
		getConfig().addDefault("competitive.de_dust2.spawnpoints.CT.five.z", -471);
	}
	@Override
	public void onDisable()
	{
	}
}
