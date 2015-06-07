package com.mcworldmap.play.MineStrike.PlayerData;

import java.util.HashMap;

public enum Price
{
	KEVLAR(65), HELMET(350),
	KIT(400), ZEUS(400),

	FRAG(300), DECOY(50), MOLOTOV(400),
	FLASH(200), SMOKE(300), INCENDIARY(600),

	GLOCK(200), USP(500), P2000(200),
	BERETTAS(500), P250(300), REK9(500),
	DEAGLE(700),

	NOVA(1200), XM1014(2000), SAWNOFF(1200),
	M249(5200), NEGEV(5700), MAG7(1800),

	MAC10(1050), MP7(1700), UMP(1200),
	PRO90(2350), BISON(1400), MP9(1250),
	FAMAS(2250), GALIL(2000), AK(2700),
	M4A1S(2700), SSG(1700), AUG(3300),
	SG(3000), AWP(2750), AUTOSNIPER(5000);

	private int value;

	private Price(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}


	public static Price getPrice(final String name){
		for(Price price : Price.values())
			if(price.name().equalsIgnoreCase(name))
				return price;
		return null;
	}
	public static <T extends Enum<T>> T findEnumValue(Class<T> type, String name) {
		if (name == null)
			return null;
		try {
			return Enum.valueOf(type, name.toUpperCase());
		} catch (IllegalArgumentException iae) {
			return null;
		}
	}

	//public HashMap<String, Integer> price = new HashMap<>();

//	public Price()
//	{
//		//TODO:Maybe load prices from config instead?
//		//Gear
//		price.put("kevlar", 650);
//		price.put("helmet", 350);
//		price.put("kit", 400);
//		price.put("zeus", 400);
//		//Nades
//		price.put("frag", 300);
//		price.put("decoy", 50);
//		price.put("moltov", 400);
//		price.put("flash", 200);
//		price.put("smoke", 300);
//		price.put("incendiary", 600);
//		//Pistols
//		price.put("glock", 200);
//		price.put("USP", 500);
//		price.put("P2000", 200);
//		price.put("berettas", 500);
//		price.put("P250", 300);
//		price.put("rek9", 500);
//		price.put("Deagle", 700);
//		//Shotguns
//		price.put("Nova", 1200);
//		price.put("XM1014", 2000);
//		price.put("Sawnoff", 1200);
//		price.put("M249", 5200);
//		price.put("Negev", 5700);
//		price.put("Mag7", 1800);
//		//SMG (So Many Guns)
//		price.put("Mac10", 1050);
//		price.put("MP7", 1700);
//		price.put("UMP", 1200);
//		price.put("Pro90", 2350);
//		price.put("Bison", 1400);
//		price.put("MP9", 1250);
//		//Rifles
//		price.put("Famas", 2250);
//		price.put("Galil", 2000);
//		price.put("AK", 2700);
//		price.put("M4A1S", 3200);
//		price.put("SSG", 1700);
//		price.put("AUG", 3300);
//		price.put("SG", 3000);
//		price.put("AWP", 4750);
//		price.put("autosniper", 5000);
//		price.put("AWP", 4750);
//	}
}
