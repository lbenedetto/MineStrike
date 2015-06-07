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
		if (args.length > 1)
			sender.sendMessage("Too many arguments!");
		else if (args.length < 1)
			sender.sendMessage("Not enough arguments!");
		else
		{
			//TODO: Add check to make sure you are joining the team twice
			//Don't do this yet, we need to be able to start the game with less players for testing
			if (args[0].equalsIgnoreCase("t") && MineStrike.ts < 5)
			{
				Player player = (Player) sender;
				MineStrike.team.getT()[MineStrike.ts] = new Person(player);
				MineStrike.ts += 1;
				sender.sendMessage("Joined Terrorist Team");
			} else if (args[0].equalsIgnoreCase("ct") && MineStrike.cts < 5)
			{
				Player player = (Player) sender;
				MineStrike.team.getCT()[MineStrike.cts] = new Person(player);
				MineStrike.cts += 1;
				sender.sendMessage("Joined Counter-Terrorist Team");
			} else
			{
				sender.sendMessage("The team you tried to join is either full, or does not exist");
				return false;
			}
			return true;
		}
		return false;
	}
}
