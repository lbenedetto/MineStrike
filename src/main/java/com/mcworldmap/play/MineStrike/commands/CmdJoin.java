package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.Util;
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
			Player player = (Player) sender;
			if (MineStrike.teams.findPerson(player) == null)
			{
				if (args[0].equalsIgnoreCase("t") && MineStrike.ts < p.getConfig().getInt("teamsize"))
				{

					MineStrike.teams.getT()[MineStrike.ts] = new Person(player);
					MineStrike.teams.getT()[MineStrike.ts].respawnT();
					MineStrike.teams.getT()[MineStrike.ts].setMoney(16000);
					Util.sendTitle(player, 20, 50, 20, "Warmup Round", "Please wait for the teams to fill up");
					MineStrike.ts += 1;
                    MineStrike.canBuy = true;
                    sender.sendMessage("Joined Terrorist Team");
				} else if (args[0].equalsIgnoreCase("ct") && MineStrike.cts < p.getConfig().getInt("teamsize"))
				{
					MineStrike.teams.getCT()[MineStrike.cts] = new Person(player);
					MineStrike.teams.getCT()[MineStrike.cts].respawnCT();
					MineStrike.teams.getCT()[MineStrike.cts].setMoney(16000);
					Util.sendTitle(player, 20, 50, 20, "Warmup Round", "Please wait for the teams to fill up");
                    MineStrike.canBuy = true;
					MineStrike.cts += 1;
					sender.sendMessage("Joined Counter-Terrorist Team");
				} else
				{
					sender.sendMessage("The teams you tried to join is either full, or does not exist");
					return false;
				}
				if (MineStrike.cts == p.getConfig().getInt("teamsize") && MineStrike.ts == p.getConfig().getInt("teamsize"))
				{
					MineStrike.teams.resetInventory();
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "start competitive");
					MineStrike.isGameActive = true;
				}
				return true;
			} else
			{
				sender.sendMessage("You are already on a teams");
			}
		}
		return false;
	}
}
