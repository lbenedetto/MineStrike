package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Bukkit;
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
			if (p.getRoundKills() >= mostKills)
			{
				MVP = p;
				mostKills = p.getRoundKills();
				Bukkit.getServer().getLogger().info("Player has" + p.getRoundKills());
			}
		}
		Bukkit.getServer().getLogger().info("mostKills =" + mostKills);
		return MVP;
	}

	public Person findCTMVP()
	{
		int mostKills = 0;
		Person MVP = null;
		for (Person p : CT)
			if (p.getRoundKills() >= mostKills)
			{
				MVP = p;
				mostKills = p.getRoundKills();
			}
		return MVP;
	}

	public String getTeam(Player player)
	{
		for (Person p : MineStrike.team.getCT())
			if (p != null && p.getPlayer().equals(player))
				return "CT";
		for (Person p : MineStrike.team.getT())
			if (p != null && p.getPlayer().equals(player))
				return "T";
		return "";
	}

	public Person findPerson(Player player)
	{
		for (Person p : MineStrike.team.getCT())
			if (p != null && p.getPlayer().equals(player))
				return p;
		for (Person p : MineStrike.team.getT())
			if (p != null && p.getPlayer().equals(player))
				return p;
		return null;
	}

	public void switchTeams()
	{
		//Swap teams
		Person[] temp = T;
		T = CT;
		CT = temp;
		//Swap scores
		int tem = Tscore;
		Tscore = CTscore;
		CTscore = tem;

	}

	public boolean isTTeamDead()
	{
		boolean out = true;
		for (Person p : T)
			if (p.isAlive())
				out = false;
		return out;
	}

	public boolean isCTTeamDead()
	{
		boolean out = true;
		for (Person p : CT)
			if (p.isAlive())
				out = false;
		return out;
	}

	public void respawnTTeam()
	{
		for (Person p : T)
		{
			p.setRoundKills(0);
			p.setAlive(true);
			p.respawnT();
		}
	}

	public void respawnCTTeam()
	{
		for (Person p : CT)
		{
			p.setRoundKills(0);
			p.setAlive(true);
			p.respawnCT();
		}
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
