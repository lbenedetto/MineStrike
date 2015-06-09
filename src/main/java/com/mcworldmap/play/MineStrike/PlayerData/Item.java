package com.mcworldmap.play.MineStrike.PlayerData;

public enum Item
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

	private Item(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}


	public static Item getItem(final String name){
		for(Item item : Item.values())
			if(item.name().equalsIgnoreCase(name))
				return item;
		return null;
	}
//TODO:Maybe load prices from config instead?

}
