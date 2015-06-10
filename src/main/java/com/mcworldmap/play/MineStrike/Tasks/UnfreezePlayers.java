package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;

public class UnfreezePlayers implements Runnable
{
	@Override
	public void run(){
		MineStrike.frozenPlayers.clear();
	}
}
