package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdJoin implements CommandExecutor
{
	Plugin p;

	public CmdJoin(Plugin p)
	{
		this.p = p;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
	{

		if (!(sender instanceof Player))
			sender.sendMessage("This command can only be run by a player.");
		if (args.length > 1)
			sender.sendMessage("Too many arguments!");
		else if (args.length < 1)
			sender.sendMessage("Not enough arguments!");
		else
		{
			//TODO: Add check to make sure you are joining the team twice
			//Don't do this yet, we need to be able to start the game with less players for testing
			if (args[0].equalsIgnoreCase("t") && MineStrike.ts < p.getConfig().getInt("teamsize"))
			{
				Player player = (Player) sender;
				MineStrike.team.getT()[MineStrike.ts] = new Person(player);
				MineStrike.team.getT()[MineStrike.ts].respawnT();
				MineStrike.ts += 1;
				sender.sendMessage("Joined Terrorist Team");
			} else if (args[0].equalsIgnoreCase("ct") && MineStrike.cts < p.getConfig().getInt("teamsize"))
			{
				Player player = (Player) sender;
				MineStrike.team.getCT()[MineStrike.cts] = new Person(player);
				MineStrike.team.getCT()[MineStrike.cts].respawnCT();
				MineStrike.cts += 1;
				sender.sendMessage("Joined Counter-Terrorist Team");
			} else
			{
				sender.sendMessage("The team you tried to join is either full, or does not exist");
				return false;
			}
			if (MineStrike.cts == p.getConfig().getInt("teamsize") && MineStrike.ts == p.getConfig().getInt("teamsize")){
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "start competitive");
			}
			return true;
		}
		return false;
	}
}
