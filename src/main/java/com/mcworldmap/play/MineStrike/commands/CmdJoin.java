package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.CounterCraft;
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
				if (args[0].equalsIgnoreCase("t") && CounterCraft.ts < 5)
				{
					Player player = (Player) sender;
					CounterCraft.team.getT()[CounterCraft.ts] = new Person(player);
					CounterCraft.ts += 1;
				} else if (args[0].equalsIgnoreCase("ct") && CounterCraft.cts < 5)
				{
					Player player = (Player) sender;
					CounterCraft.team.getCT()[CounterCraft.cts] = new Person(player);
					CounterCraft.cts += 1;
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
