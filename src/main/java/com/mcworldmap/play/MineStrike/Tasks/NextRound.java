package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.entity.Entity;

import java.util.List;

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
		MineStrike.team.respawnCT();
		MineStrike.team.respawnT();
		for(Person p : MineStrike.team.getCT())
			Util.sendTitle(p.getPlayer(), 20, 50, 20, "Round " + rounds, "");
		for(Person p : MineStrike.team.getT())
			Util.sendTitle(p.getPlayer(), 20 , 50, 20, "Round " + rounds, "");
		//Unfreeze player
		//Open buy menu
	}
}
