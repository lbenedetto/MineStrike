package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.entity.Player;

public class FireRate implements Runnable
{

	Player p;

	public FireRate(Player p)
	{
		this.p = p;
	}

	@Override
	public void run()
	{
		MineStrike.coolDown.remove(p);
	}
}
