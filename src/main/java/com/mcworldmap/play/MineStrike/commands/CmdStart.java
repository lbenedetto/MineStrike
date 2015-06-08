package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class CmdStart implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
	{
		if (args[0].equalsIgnoreCase("deathmatch"))
		{
			MineStrike.gamemode = "deathmatch";
			return true;
		} else if (args[0].equalsIgnoreCase("competitive"))
		{
			MineStrike.gamemode = "competitive";
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();
			Team T = board.registerNewTeam("T");
			Team CT = board.registerNewTeam("CT");
			T.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
			CT.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
			Objective objective = board.registerNewObjective("showhealth", "health");
			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			objective.setDisplayName("/ 20");
			for (Player online : Bukkit.getOnlinePlayers())
			{
				online.setScoreboard(board);
				online.setHealth(online.getHealth());
			}
			for (Person p : MineStrike.team.getCT())
			{
				T.addPlayer(p.getPlayer());
				Util.sendTitle(p.getPlayer(), 20, 50, 20, "Match Started", "Round 1");
				p.respawnCT();
			}
			for (Person p : MineStrike.team.getT())
			{
				CT.addPlayer(p.getPlayer());
				Util.sendTitle(p.getPlayer(), 20, 50, 20, "Match Started", "Round 1");
				p.respawnT();
			}
			return true;
		}
		return false;
	}
}
