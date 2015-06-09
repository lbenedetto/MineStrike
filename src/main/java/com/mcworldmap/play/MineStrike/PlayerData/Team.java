package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Team
{
	private Person[] T;
	private Person[] CT;
	public int CTscore;
	public int Tscore;
	Plugin p;
	public Team(Plugin p)
	{
		this.p = p;
		T = new Person[p.getConfig().getInt("teamsize")];
		CT = new Person[p.getConfig().getInt("teamsize")];
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

	public Person findTMVP()
	{
		int mostKills = 0;
		Person MVP = null;
		for (Person p : T)
		{
			if (p.getKills() > mostKills)
			{
				MVP = p;
			}
		}
		return MVP;
	}

	public Person findCTMVP()
	{
		int mostKills = 0;
		Person MVP = null;
		for (Person p : CT)
		{
			if (p.getKills() > mostKills)
			{
				MVP = p;
			}
		}
		return MVP;
	}
	public String getTeam(Player player){
		for (Person p : MineStrike.team.getCT())
		{
			if (p.getPlayer().equals(player))
			{
				return "CT";
			}
		}
		for (Person p : MineStrike.team.getT())
		{
			if (p.getPlayer().equals(player))
			{
				return "T";
			}
		}
		return "";
	}
	public Person findPerson(Player player)
	{
		for (Person p : MineStrike.team.getCT())
		{
			if (p.getPlayer().equals(player))
			{
				return p;
			}
		}
		for (Person p : MineStrike.team.getT())
		{
			if (p.getPlayer().equals(player))
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
		boolean out = true;
		for (Person p : T){
			if(p.isAlive()){
				out = false;
			}
		}
		return out;
	}

	public boolean isCTTeamDead()
	{
		boolean out = true;
		for (Person p : CT){
			if(p.isAlive()){
				out = false;
			}
		}
		return out;
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
