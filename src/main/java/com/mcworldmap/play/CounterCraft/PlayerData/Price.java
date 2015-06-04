package com.mcworldmap.play.CounterCraft.PlayerData;

import java.util.HashMap;

public class Price
{
	public HashMap<String, Integer> price = new HashMap<>();

	public Price()
	{
		//TODO:Finish adding prices
		//Gear
		price.put("kevlar", 650);
		price.put("helmet", 350);
		price.put("kit", 400);
		price.put("zeus", 400);
		//Nades
		price.put("frag", 300);
		price.put("decoy", 50);
		price.put("moltov", 400);
		price.put("flash", 200);
		price.put("smoke", 300);
		price.put("incendiary", 600);
		//Pistols
		price.put("glock", 200);
		price.put("USP", 500);
		price.put("P2000", 200);
		price.put("berettas", 500);
		price.put("P250", 300);
		price.put("rek9", 500);
		price.put("Deagle", 700);
		//Shotguns
		price.put("Nova", 1200);
		price.put("XM1014", 2000);
		price.put("Sawnoff", 1200);
		price.put("M249", 5200);
		price.put("Negev", 5700);
		price.put("Mag7", 1800);
		//SMG (So Many Guns)
		price.put("Mac10", 1050);
		price.put("MP7", 1700);
		price.put("UMP", 1200);
		price.put("Pro90", 2350);
		price.put("PeePee", 1400);
	}
}
