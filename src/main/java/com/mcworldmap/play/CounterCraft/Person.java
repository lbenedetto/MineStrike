package com.mcworldmap.play.CounterCraft;

import org.bukkit.entity.Player;

public class Person
{
	private int score, kills, deaths, money;
	private Player player;
	private boolean alive;

	public Person(Player player)
	{
		this.player = player;
		this.score = 0;
		this.kills = 0;
		this.deaths = 0;
		this.money = 800;
		this.alive = true;
	}

	public String toString()
	{
		if (this.alive)
			return "Alive | " + this.player.getDisplayName() + " | " + this.money + " | " + this.kills + " | " + this.deaths + " | " + this.score;
		else
			return "Dead | " + this.player.getDisplayName() + " | " + this.money + " | " + this.kills + " | " + this.deaths + " | " + this.score;

	}

	public boolean isAlive()
	{
		return this.alive;
	}

	public void setAlive(boolean b)
	{
		this.alive = b;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getKills()
	{
		return kills;
	}

	public void setKills(int kills)
	{
		this.kills = kills;
	}

	public int getDeaths()
	{
		return deaths;
	}

	public void setDeaths(int deaths)
	{
		this.deaths = deaths;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public Player getPlayer()
	{
		return player;
	}
}
