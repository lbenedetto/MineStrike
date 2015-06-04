package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdJoin implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
	{
		if (!(sender instanceof Player))
			sender.sendMessage("This command can only be run by a player.");
		if (sender.hasPermission("MineStrike.join"))
		{
			if (args.length > 1)
				sender.sendMessage("Too many arguments!");
			else if (args.length < 1)
				sender.sendMessage("Not enough arguments!");
			else
			{
				if (args[0].equalsIgnoreCase("t") && MineStrike.ts < 5)
				{
					Player player = (Player) sender;
					MineStrike.team.getT()[MineStrike.ts] = new Person(player);
					MineStrike.ts += 1;
				} else if (args[0].equalsIgnoreCase("ct") && MineStrike.cts < 5)
				{
					Player player = (Player) sender;
					MineStrike.team.getCT()[MineStrike.cts] = new Person(player);
					MineStrike.cts += 1;
				} else
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
