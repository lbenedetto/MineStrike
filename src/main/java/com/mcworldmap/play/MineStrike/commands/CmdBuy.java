package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.PlayerData.Price;
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
			//CONVERTED TO ENUMS YAY
//			for(Price price : Price.values())
//				if(price.toString().equalsIgnoreCase(args[0]))
//					if (p.getMoney() >= price.getValue())
//						p.setMoney(p.getMoney() - price.getValue());

			//OH GOD MORE METHODOLOGY.
			if(Price.getPrice(args[0].toUpperCase()) != null)
			{
				int price = Price.getPrice(args[0].toUpperCase()).getValue();
				if (p.getMoney() >= price)
					p.setMoney(p.getMoney() - price);
			}

//			//if this doesn't work, comment this out and use the above commented method.
//			if(Price.findEnumValue(Price, args[0].toUpperCase()))
//			{
//				int price = Price.valueOf(args[0].toUpperCase()).getValue();
//				if(p.getMoney() >= price)
//					p.setMoney(p.getMoney() - price);
//			}
		}
		return false;
	}
}
