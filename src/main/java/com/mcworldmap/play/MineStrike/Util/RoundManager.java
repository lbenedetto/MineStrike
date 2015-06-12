package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class RoundManager
{
	public static int round = 1;

	public static void newRound(String winner)
	{
		round += 1;
		int maxrounds = MineStrike.config.getInt("maxrounds");
		if (round > maxrounds || (MineStrike.team.CTscore >= maxrounds / 2 && MineStrike.team.Tscore >= maxrounds / 2))
		{
			String winMessage = "";
			if (MineStrike.team.CTscore == MineStrike.team.Tscore)
				winMessage = "Tie";
			if (MineStrike.team.Tscore == 1 + (maxrounds / 2))
				winMessage = ChatColor.GOLD + "Terrorists Win";
			if (MineStrike.team.CTscore == 1 + (maxrounds / 2))
				winMessage = ChatColor.DARK_BLUE + "Counter-Terrorists Win";
			for (Person p : MineStrike.team.getT())
			{
				MineStrike.getNetwork().updatePlayerScore(p, winner.equalsIgnoreCase("T"));
				Util.sendTitle(p.getPlayer(), 20, 100, 20, "" + winMessage, "MVP: " + MineStrike.team.getTMVP().getPlayer().getDisplayName() + " for highest score");
				p.getPlayer().performCommand("scoreboard");
			}
			for (Person p : MineStrike.team.getCT())
			{
				MineStrike.getNetwork().updatePlayerScore(p, winner.equalsIgnoreCase("CT"));
				Util.sendTitle(p.getPlayer(), 20, 100, 20, "" + winMessage, "MVP: " + MineStrike.team.getCTMVP().getPlayer().getDisplayName() + " for highest score");
				p.getPlayer().performCommand("scoreboard");
			}
			MineStrike.team.reset();
		} else if (winner.equals("CT"))
		{
			for (Person p : MineStrike.team.getT())
			{
				Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.DARK_BLUE + "Counter-Terrorists Win", "MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + " for most eliminations");
				p.getPlayer().performCommand("scoreboard");
			}
			for (Person p : MineStrike.team.getCT())
			{
				Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.DARK_BLUE + "Counter-Terrorists Win", "MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + " for most eliminations");
				p.getPlayer().performCommand("scoreboard");
			}
			MineStrike.team.rewardCT(3250);
			MineStrike.team.rewardT(1400);
			MineStrike.team.CTscore += 1;
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(round), 200);
		} else
		{
			for (Person p : MineStrike.team.getT())
			{
				Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.GOLD + "Terrorists Win", "MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + " for most eliminations");
				p.getPlayer().performCommand("scoreboard");
			}
			for (Person p : MineStrike.team.getCT())
			{
				Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.GOLD + "Terrorists Win", "MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + " for most eliminations");
				p.getPlayer().performCommand("scoreboard");
			}
			MineStrike.team.rewardCT(1400);
			MineStrike.team.rewardT(3250);
			MineStrike.team.Tscore += 1;
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(round), 200);
		}
	}

	public static String stringify()
	{
		String out;
		out = ChatColor.GOLD + "" + MineStrike.team.Tscore;
		out += ChatColor.WHITE + " | ";
		out += ChatColor.DARK_BLUE + "" + MineStrike.team.CTscore;
		return out;
	}
}
