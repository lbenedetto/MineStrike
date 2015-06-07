package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

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
				if (args[0].equalsIgnoreCase("deathmatch"))
				{
					MineStrike.gamemode = "deathmatch";
				} else if (args[0].equalsIgnoreCase("competitive"))
				{
					MineStrike.gamemode = "competitive";
				}
				if (!(MineStrike.gamemode.equalsIgnoreCase("competitive") || MineStrike.gamemode.equalsIgnoreCase("deathmatch")))
				{
					return false;
				}
				//Teleport players to their spawns
				if (MineStrike.gamemode.equalsIgnoreCase("competitive"))
				{
					ScoreboardManager manager = Bukkit.getScoreboardManager();
					Scoreboard board = manager.getNewScoreboard();
					Team T = board.registerNewTeam("T");
					Team CT = board.registerNewTeam("CT");
					for(Team team : board.getTeams())
					{
						team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					}
					for (Person p : MineStrike.team.getCT())
					{
						board.getTeam("T").addPlayer(p.getPlayer());
						p.respawnCT();
					}
					for (Person p : MineStrike.team.getT())
					{
						board.getTeam("CT").addPlayer(p.getPlayer());
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
