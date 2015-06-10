package com.mcworldmap.play.MineStrike.PlayerData;

public enum Item
{
	//Gear
	KEVLAR(650), HELMET(350),
	KIT(400), ZEUS(400),
	//Grenades
	FRAG(300), DECOY(50), MOLOTOV(400),
	FLASHBANG(200), SMOKE(300), INCENDIARY(600),
	//Pistols
	GLOCK(200, 140, .15, 1d, 2), USP(500, 36, .17, 1, 2), P2000(200),
	BERETTAS(500), P250(300), TEK9(500),
	DEAGLE(700),
	//Heavy
	NOVA(1200, 40, 2, .7, 2), XM1014(2000, 39, 1, .7, 2), SAWNOFF(1200, 39, 1, .7, 2),
	M249(5200, 300, .1, .8, 2), NEGEV(5700, 350, .1, .8, 2), MAG7(1800, 37, 1, .7, 2),
	//SMG's
	MAC10(1050, 130, .1, .9, 2), MP7(1700, 150, .1, .9, 2), UMP(1200, 125, .1, .9, 2),
	P90(2350, 150, .1, .9, 2), BISON(1400, 184, .1, .9, 2), MP9(1250, 184, .1, .9, 2),
	//Rifles
	FAMAS(2250, 0, .1, 1.5, 2), GALIL(2000, 0, .1, 1.5, 2), AK47(2700, 0, .1, 1.5, 2),
	M4A1S(2700, 0, .1, 1.5, 2), SSG(1700), AUG(3300, 0, .1, 1.5, 2),
	SG(3000, 0, .1, 1.5, 2), AWP(2750, 40, 2, 10, 20), G3SG1(5000, 110, .25, 10, 2), SCAR20(5000, 110, .25, 10, 2);

	private int price;
    private int damage;


    private double range;
    private int ammo;
    private double fireRate;

	private Item(int price, int ammo, double fireRate, double range, int damage)
	{

        this.price = price;
        this.damage = damage;
        this.range = range;
        this.ammo = ammo;
        this.fireRate = fireRate;
	}

    private Item(int price)
    {
        this.price = price;
    }

	public int getPrice()
	{
		return price;
	}
    public int getDamage() {return damage;}
    public double getRange() { return range; }
    public int getAmmo() { return ammo; }
    public double getFireRate() { return fireRate; }

	public static Item getItem(final String name){
		for(Item item : Item.values())
			if(item.name().equalsIgnoreCase(name))
				return item;
		return null;
	}
//TODO:Maybe load prices from config instead?

}
