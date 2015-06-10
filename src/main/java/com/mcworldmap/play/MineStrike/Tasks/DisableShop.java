package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Bukkit;

public class DisableShop implements Runnable
{
	@Override
	public void run(){
		Bukkit.getServer().getLogger().info("Shop disabled");
		MineStrike.canBuy = false;
	}
}
