package com.mcworldmap.play.CounterCraft.commands;

import com.mcworldmap.play.CounterCraft.CounterCraft;
import com.mcworldmap.play.CounterCraft.PlayerData.Person;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.w3c.dom.css.Counter;

public class CmdBuy implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
	{
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
				if (args[0].equalsIgnoreCase("helmet"))
					if (p.getMoney() >= 350)
						p.setMoney(p.getMoney() - 350);
					else
						sender.sendMessage("You do not have enough money");
				if (args[0].equalsIgnoreCase("kevlar"))
					if (p.getMoney() >= 650)
						p.setMoney(p.getMoney() - 650);
					else
						sender.sendMessage("You do not have enough money");
			}
		}
		return false;
	}
}
