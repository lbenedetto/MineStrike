package com.mcworldmap.play.CounterCraft.PlayerData;

import com.mcworldmap.play.CounterCraft.CounterCraft;
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
		for (Person p : CounterCraft.team.getCT())
		{
			if (p.getPlayer() == player)
			{
				return p;
			}
		}
		for (Person p : CounterCraft.team.getT())
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
