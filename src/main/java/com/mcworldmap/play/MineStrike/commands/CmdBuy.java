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

public class CmdBuy implements CommandExecutor {
    /**
     * Gives the command sender the item requested and subtracts the appropriate amount of money
     *
     * @return true if ran successfully
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player))
            sender.sendMessage("This command can only be run by a player.");
        if (args.length > 1)
            sender.sendMessage("Too many arguments!");
        else if (args.length < 1)
            sender.sendMessage("Not enough arguments!");
        else if (!MineStrike.canBuy) {
            sender.sendMessage("Buy period has expired");
            return true;
        } else {
            //Get the Person class and store it in p, and send them the amount of money they have.
            Person p = MineStrike.teams.findPerson((Player) sender);
            sender.sendMessage("You have $" + p.getMoney());
            //only run this if the item exists that they want to buy
            if (Item.getItem(args[0].toUpperCase()) != null) {
                //get the price of the item
                int price = Item.getItem(args[0].toUpperCase()).getPrice();
                //if the have enough money to buy it...
                if (p.getMoney() >= price) {
                    //loop through their items in their inventory
                    for (ItemStack i : p.getPlayer().getInventory().getContents()) {
                        if (i == null)
                            continue;
                        //get the class of weapon the item is.
                        String type = Item.getItem(i.getItemMeta().getDisplayName()).getType();
                        String invType = ChatColor.stripColor(i.getItemMeta().getLore().get(1));
                        //if they have a weapon already in that class, dont let them buy it
                        if ((type != null && invType != null) && type.equalsIgnoreCase(invType)) {
                            sender.sendMessage("You can't buy an item of the same class!");
                            return true;
                        }
                    }
                    //deduct the price of the weapon from their money
                    p.setMoney(p.getMoney() - price);
                    sender.sendMessage(ChatColor.RED + "-$" + price);
                    sender.sendMessage(ChatColor.GREEN + "+" + args[0]);
                    sender.sendMessage("$" + p.getMoney() + " remaining");

                    //give them the item
                    p.creditItem(p.getPlayer().getDisplayName(), args[0]);
                } else {
                    sender.sendMessage("You do not have enough money");
                }
            }
            return true;
        }
        return false;
    }
}
