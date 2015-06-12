package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;

public class NextRound implements Runnable
{
	int rounds;

	public NextRound(int rounds)
	{
		this.rounds = rounds;
	}

	@Override
	public void run()
	{
		if (rounds == 1+(MineStrike.config.getInt("maxrounds")/2))
		{
			MineStrike.team.switchTeams();
		}
		MineStrike.team.respawnCTTeam();
		MineStrike.team.respawnTTeam();
		MineStrike.canBuy = true;
		for (Person p : MineStrike.team.getCT())
		{
			Util.sendTitle(p.getPlayer(), 20, 50, 20, "Round " + rounds, RoundManager.stringify());
			MineStrike.frozenPlayers.add(p.getPlayer());
		}
		for (Person p : MineStrike.team.getT())
		{
			Util.sendTitle(p.getPlayer(), 20, 50, 20, "Round " + rounds, RoundManager.stringify());
			MineStrike.frozenPlayers.add(p.getPlayer());
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new UnfreezePlayers(), 10 * 20);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DisableShop(), 20 * 20);
	}
}
