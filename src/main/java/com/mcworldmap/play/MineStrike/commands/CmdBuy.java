package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Item;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
		else if (!MineStrike.canBuy)
		{
			sender.sendMessage("Buy period has expired");
			return true;
		} else
		{
			Person p = MineStrike.team.findPerson((Player) sender);
			sender.sendMessage("You have $" + p.getMoney());
			if (Item.getItem(args[0].toUpperCase()) != null)
			{
				int price = Item.getItem(args[0].toUpperCase()).getPrice();
				if (p.getMoney() >= price)
				{
                    for(ItemStack i : p.getPlayer().getInventory())
                    {
                        if(i == null)
                            continue;
                        String type = Item.getItem(i.getItemMeta().getDisplayName()).getType();
                        String invType = ChatColor.stripColor(i.getItemMeta().getLore().get(1));
                        if((type != null && invType != null) && type.equalsIgnoreCase(invType))
                        {
                            sender.sendMessage("You can't buy an item of the same class!");
                            return true;
                        }
                    }
					p.setMoney(p.getMoney() - price);
					sender.sendMessage(ChatColor.RED + "-$" + price);
					sender.sendMessage(ChatColor.GREEN + "+" + args[0]);
					sender.sendMessage("$" + p.getMoney() + " remaining");

					p.creditItem(args[0]);
				} else
				{
					sender.sendMessage("You do not have enough money");
				}
			}
			return true;
		}
		return false;
	}
}
