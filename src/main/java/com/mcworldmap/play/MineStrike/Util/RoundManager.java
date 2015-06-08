package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import org.bukkit.Bukkit;

public class RoundManager
{
	public static String[] rounds = new String[30];
	public static int round = 1;
	public static void newRound(String winner){
		rounds[round-1] = winner;
		round += 1;
		if(winner.equals("CT"))
		{
			for (Person p : MineStrike.team.getT()){
				Util.sendTitle(p.getPlayer(), 20, 100, 20, "Counter-Terrorists Win", "? MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + "for most eliminations");
				p.getPlayer().performCommand("scoreboard");
				p.getPlayer().performCommand("scoreboard");
			}
			for (Person p : MineStrike.team.getCT())
			{
				Util.sendTitle(p.getPlayer(), 20, 100, 20, "Counter-Terrorists Win", "? MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + "for most eliminations");
			}
			MineStrike.team.rewardCT(3250);
			MineStrike.team.rewardT(1400);
			MineStrike.team.CTscore += 1;
			//Freeze player, then
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(round), 200);
		}
		else{
			for (Person p : MineStrike.team.getT())
			{
				Util.sendTitle(p.getPlayer(), 20, 100, 20, "Terrorists Win", "? MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + "for most eliminations");
				p.getPlayer().performCommand("scoreboard");
			}
			for (Person p : MineStrike.team.getCT())
			{
				Util.sendTitle(p.getPlayer(), 02, 100, 20, "Terrorists Win", "? MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + "for most eliminations");
				p.getPlayer().performCommand("scoreboard");
			}
			MineStrike.team.rewardCT(1400);
			MineStrike.team.rewardT(3250);
			MineStrike.team.Tscore += 1;
			//Freeze player, then
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(round), 200);
		}
	}
	public String toString(){
		String out = "";
		for (String s : rounds){
			out += s + " ";
		}
		return out;
	}
}
