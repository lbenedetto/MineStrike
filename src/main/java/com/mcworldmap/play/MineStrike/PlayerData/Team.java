package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.entity.Player;

public class Team
{
	private Person[] T;
	private Person[] CT;
	public int CTscore;
	public int Tscore;

	public Team()
	{
		T = new Person[5];
		CT = new Person[5];
		CTscore = 0;
		Tscore = 0;
	}

	public Person[] getT()
	{
		return T;
	}

	public Person[] getCT()
	{
		return CT;
	}

	public Person findTMVP(){
		int mostKills = 0;
		Person MVP = null;
		for (Person p : T){
			if (p.getKills() > mostKills){
				MVP = p;
			}
		}
		return MVP;
	}

	public Person findCTMVP(){
		int mostKills = 0;
		Person MVP = null;
		for (Person p : CT){
			if (p.getKills() > mostKills){
				MVP = p;
			}
		}
		return MVP;
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

	public boolean isTTeamDead()
	{
		return (T[0].isDead() && T[1].isDead() && T[2].isDead() && T[3].isDead() && T[4].isDead());
	}

	public boolean isCTTeamDead()
	{
		return (T[0].isDead() && T[1].isDead() && T[2].isDead() && T[3].isDead() && T[4].isDead());
	}

	public void respawnT()
	{
		for (Person p : T)
			p.respawnCT();
	}

	public void respawnCT()
	{
		for (Person p : CT)
			p.respawnCT();
	}

	public void rewardT(int reward)
	{
		for (Person p : T)
			p.addMoney(reward);
	}

	public void rewardCT(int reward)
	{
		for (Person p : T)
			p.addMoney(reward);
	}
}
