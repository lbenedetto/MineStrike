package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Item;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBuy implements CommandExecutor
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
			Person p = MineStrike.team.findPerson((Player) sender);
			sender.sendMessage("You have $" + p.getMoney());
			if(Item.getItem(args[0].toUpperCase()) != null)
			{
				int price = Item.getItem(args[0].toUpperCase()).getValue();
				if (p.getMoney() >= price)
				{
					p.setMoney(p.getMoney() - price);
					sender.sendMessage("You bought a " + args[0] + ", you now have " + p.getMoney());
					p.creditItem(Item.getItem(args[0]));
				}
			}
		}
		return false;
	}
}
