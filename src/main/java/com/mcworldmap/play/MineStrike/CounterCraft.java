package com.mcworldmap.play.MineStrike;

import com.mcworldmap.play.MineStrike.PlayerData.Team;
import com.mcworldmap.play.MineStrike.commands.CmdBuy;
import com.mcworldmap.play.MineStrike.commands.CmdJoin;
import com.mcworldmap.play.MineStrike.commands.CmdStart;
import com.mcworldmap.play.MineStrike.listeners.EventListener;
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
		getLogger().info("MineStrike Enabled");

        getCommand("buy").setExecutor(new CmdBuy());
        getCommand("startmatch").setExecutor(new CmdStart());
        getCommand("join").setExecutor(new CmdJoin());

    }

	@Override
	public void onDisable()
	{
	}
}
