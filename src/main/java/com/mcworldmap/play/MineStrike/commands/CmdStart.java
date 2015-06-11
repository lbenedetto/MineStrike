package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
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
			MineStrike.team.resetMoney();
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(1), 1);
			return true;
		}
		return false;
	}
}
