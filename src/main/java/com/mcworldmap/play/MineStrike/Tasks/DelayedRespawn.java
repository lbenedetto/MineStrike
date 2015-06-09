package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.entity.Player;

public class DelayedRespawn implements Runnable
{
	Player p;

	public DelayedRespawn(Player p)
	{
		this.p = p;
	}

	@Override
	public void run()
	{
		try
		{
			Util.autoRespawnPlayer(p);
		}
		catch (ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
	}
}
