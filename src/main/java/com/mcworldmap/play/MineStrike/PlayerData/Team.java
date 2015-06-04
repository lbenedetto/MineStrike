package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.entity.Player;

public class Team
{
	private Person[] T;
	private Person[] CT;

	public Team()
	{
		T = new Person[5];
		CT = new Person[5];
	}

	public Person[] getT()
	{
		return T;
	}

	public Person[] getCT()
	{
		return CT;
	}

	public Person findPerson(Player player)
	{
		for (Person p : MineStrike.team.getCT())
		{
			if (p.getPlayer() == player)
			{
				return p;
			}
		}
		for (Person p : MineStrike.team.getT())
		{
			if (p.getPlayer() == player)
			{
				return p;
			}
		}
		return null;
	}

	public void switchTeams()
	{
		Person[] temp = T;
		T = CT;
		CT = temp;
	}
}
