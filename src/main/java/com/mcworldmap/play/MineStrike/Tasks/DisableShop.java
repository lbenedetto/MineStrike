package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.MineStrike;

public class DisableShop implements Runnable
{
	@Override
	public void run(){
		MineStrike.canBuy = false;
	}
}
