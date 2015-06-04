package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
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
				if (args[0].equalsIgnoreCase("armsrace")) {
					MineStrike.gamemode = "armsrace";
				} else if (args[0].equalsIgnoreCase("competitive")) {
					MineStrike.gamemode = "competitive";
				}
				//TODO: Add stuff that starts the match
				//Teleport players to their spawns
				for (Person p : MineStrike.team.getCT())
					p.respawnCT();
				for (Person p : MineStrike.team.getT())
					p.respawnT();
			} else
			{
				sender.sendMessage("Not enough players");
			}
		} else
		{
			return false;
		}
		return false;
	}
}
