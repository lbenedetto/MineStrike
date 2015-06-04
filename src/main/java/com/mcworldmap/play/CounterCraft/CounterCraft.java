package com.mcworldmap.play.CounterCraft;

import com.mcworldmap.play.CounterCraft.PlayerData.Person;
import com.mcworldmap.play.CounterCraft.PlayerData.Team;
import com.mcworldmap.play.CounterCraft.listeners.EventListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CounterCraft extends JavaPlugin
{
    public static Team team = new Team();
    public static int ts = 0, cts = 0;

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getLogger().info("CounterCraft Enabled");
	}

	@Override
	public void onDisable()
	{
		getLogger().info("CounterCraft Disabled");
	}



	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("buy")){

		}
		if (cmd.getName().equalsIgnoreCase("startMatch"))
		{
			Player player = (Player) sender;
			if (player.hasPermission("some.pointless.permission")) {
				if (ts == 5 && cts == 5){
					//TODO: Add stuff that starts the match
					//Teleport players to their spawns
				}else{
					sender.sendMessage("Not enough players");
				}
			} else {
				return false;
			}
		}
		if (cmd.getName().equalsIgnoreCase("join"))
		{
			if (!(sender instanceof Player))
				sender.sendMessage("This command can only be run by a player.");
			else if (args.length > 1)
				sender.sendMessage("Too many arguments!");
			else if (args.length < 1)
				sender.sendMessage("Not enough arguments!");
			else
			{
				if (args[0].equalsIgnoreCase("t") && ts < 5)
				{
					Player player = (Player) sender;
					team.getT()[ts] = new Person(player);
					ts += 1;
				}
				else if (args[0].equalsIgnoreCase("ct") && cts < 5)
				{
					Player player = (Player) sender;
					team.getCT()[cts] = new Person(player);
					cts += 1;
				}
				else
				{
					sender.sendMessage("Must enter either T or CT");
					return false;
				}
				return true;
			}
		}
		return false;
	}
}
