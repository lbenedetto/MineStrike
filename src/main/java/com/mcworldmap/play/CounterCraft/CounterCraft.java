package com.mcworldmap.play.CounterCraft;

import com.mcworldmap.play.CounterCraft.PlayerData.Team;
import com.mcworldmap.play.CounterCraft.commands.CmdBuy;
import com.mcworldmap.play.CounterCraft.commands.CmdJoin;
import com.mcworldmap.play.CounterCraft.commands.CmdStart;
import com.mcworldmap.play.CounterCraft.listeners.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CounterCraft extends JavaPlugin
{
	public static String gamemode;
    public static Team team = new Team();
    public static int ts = 0, cts = 0;

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getLogger().info("CounterCraft Enabled");

        getCommand("buy").setExecutor(new CmdBuy());
        getCommand("startmatch").setExecutor(new CmdStart());
        getCommand("join").setExecutor(new CmdJoin());

    }

	@Override
	public void onDisable()
	{
	}
}
