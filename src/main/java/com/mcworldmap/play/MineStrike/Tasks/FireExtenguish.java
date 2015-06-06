package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class FireExtenguish implements Runnable
{
	//Location loc;
	Block block;
	//int extTime;

	public FireExtenguish(Block block)
	{
		//this.loc = loc;
		this.block = block;
		//this.extTime = extTime;
	}

	@Override
	public void run()
	{
		block.setType(Material.AIR);
	}
}
