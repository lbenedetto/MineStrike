package com.mcworldmap.play.CounterCraft.commands;

import com.mcworldmap.play.CounterCraft.CounterCraft;
import com.mcworldmap.play.CounterCraft.PlayerData.Person;
import com.mcworldmap.play.CounterCraft.PlayerData.Price;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBuy implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
	{
		Price item = new Price();
		if (!(sender instanceof Player))
			sender.sendMessage("This command can only be run by a player.");
		if (sender.hasPermission("CounterCraft.start"))
		{
			if (args.length > 1)
				sender.sendMessage("Too many arguments!");
			else if (args.length < 1)
				sender.sendMessage("Not enough arguments!");
			else
			{
				Person p = CounterCraft.team.findPerson((Player) sender);
				sender.sendMessage("You have $" + p.getMoney());
				if (p.getMoney() >= item.price.get(args[0])){
					p.setMoney(p.getMoney() - item.price.get(args[0]));
				}

			}
		}
		return false;
	}
}
