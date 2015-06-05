package com.mcworldmap.play.MineStrike;

import com.mcworldmap.play.MineStrike.PlayerData.Spawnpoint;
import com.mcworldmap.play.MineStrike.PlayerData.Team;
import com.mcworldmap.play.MineStrike.commands.CmdBuy;
import com.mcworldmap.play.MineStrike.commands.CmdJoin;
import com.mcworldmap.play.MineStrike.commands.CmdStart;
import com.mcworldmap.play.MineStrike.listeners.EventListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MineStrike extends JavaPlugin
{

	public static String gamemode = "";
	public static Team team = new Team();
	public static int ts = 0, cts = 0;
	public static Spawnpoint spawnpoint;

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getCommand("buy").setExecutor(new CmdBuy());
		getCommand("start").setExecutor(new CmdStart());
		getCommand("join").setExecutor(new CmdJoin());
		saveDefaultConfig();
		FileConfiguration config = getConfig();
		spawnpoint = new Spawnpoint(config);
		getLogger().info("MineStrike Enabled");
	}

	@Override
	public void onDisable()
	{
	}
}
