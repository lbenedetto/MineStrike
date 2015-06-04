package com.mcworldmap.play.CounterCraft;

public class Team
{
	private Person[] T;
	private Person[] CT;

	public Team(){
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
}
