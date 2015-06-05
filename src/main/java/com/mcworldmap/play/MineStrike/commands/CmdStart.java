package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdStart implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
	{
		Player player = (Player) sender;
		if (player.hasPermission("MineStrike.start"))
		{
			if (MineStrike.ts == 5 && MineStrike.cts == 5)
			{
				//TODO: Add stuff that starts the match
				if (args[0].equalsIgnoreCase("armsrace"))
				{
					MineStrike.gamemode = "armsrace";
				} else if (args[0].equalsIgnoreCase("competitive"))
				{
					MineStrike.gamemode = "competitive";
				}
				if(MineStrike.gamemode.equalsIgnoreCase("")){
					return false;
				}
				//Teleport players to their spawns
				if (MineStrike.gamemode.equalsIgnoreCase("competitive"))
				{
					for (Person p : MineStrike.team.getCT())
					{
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "scoreboard teams add nametag");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "scoreboard teams join nametag @a");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "summon ArmorStand ~ ~ ~ {CustomName:\"Counter-Terrorist\",CustomNameVisible:1,Invisible:1,NoGravity:1}");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tp @e[name=\"Counter-Terrorist\"] @e[name=@]");
						p.respawnCT();
					}
					for (Person p : MineStrike.team.getT())
					{
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "sdfs");
						p.respawnT();
					}
				}
				return true;
			} else
			{
				sender.sendMessage("Not enough players");
			}
		} else
		{
			sender.sendMessage("No permission");
			return false;
		}
		return false;
	}
}
