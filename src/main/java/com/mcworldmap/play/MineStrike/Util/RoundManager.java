package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RoundManager
{
	public static String[] rounds = new String[30];
	public static int round = 1;

	public static void newRound(String winner)
	{
		rounds[round - 1] = winner;
		round += 1;
		if (winner.equals("CT"))
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
		out += ChatColor.DARK_BLUE + ""  + MineStrike.team.CTscore;
		return out;
	}
}
