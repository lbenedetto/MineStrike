package com.mcworldmap.play.MineStrike.PlayerData;

public enum Item
{
	//Gear
	KEVLAR(650), HELMET(350),
	KIT(400), ZEUS(400, 1, 1, .5, 20, "Kit"),
	//Grenades
	FRAG(300), DECOY(50), MOLOTOV(400),
	FLASHBANG(200), SMOKE(300), INCENDIARY(600),
	//Pistols
	GLOCK(200, 140, .15, 1d, 5, "Pistol"), USP(500, 36, .17, 1d, 6, "Pistol"), P2000(200, 65, .17, 1d, 6, "Pistol"),
	BERETTAS(500, 150, .12, 1d, 7, "Pistol"), P250(300, 39, .15, 1d, 6, "Pistol"), TEC9(500, 144, .12, 1d, 5, "Pistol"),
	DEAGLE(700, 42, .5, 1d, 15, "Pistol"),
	//Heavy
	NOVA(1200, 40, .8, .7, 5, "Shotgun"), XM1014(2000, 39, .35, .7, 6, "Shotgun"), SAWNOFF(1200, 39, .84, .7, 6, "Shotgun"),
	M249(5200, 300, .06, .8, 2, "Heavy"), NEGEV(5700, 350, .05, .8, 7, "Heavy"), MAG7(1800, 37, .84, .7, 6, "Shotgun"),
	//SMG's
	MAC10(1050, 130, .1, .9, 2, "SMG"), MP7(1700, 150, .1, .9, 2, "SMG"), UMP(1200, 125, .1, .9, 2, "SMG"),
	P90(2350, 150, .1, .9, 2, "SMG"), BISON(1400, 184, .1, .9, 2, "SMG"), MP9(1250, 184, .1, .9, 2, "SMG"),
	//Rifles
	FAMAS(2250, 0, .1, 1.5, 2, "Rifle"), GALIL(2000, 0, .1, 1.5, 2, "Rifle"), AK47(2700, 0, .1, 1.5, 2, "Rifle"),
	M4A1S(2700, 0, .1, 1.5, 2, "Rifle"), SSG(1700), AUG(3300, 0, .1, 1.5, 2, "Rifle"),
	SG(3000, 0, .1, 1.5, 2, "Sniper-Rifle"), AWP(2750, 40, 2, 10, 20, "Sniper-Rifle"),
	G3SG1(5000, 110, .25, 10, 2, "Sniper-Rifle"), SCAR20(5000, 110, .25, 10, 2, "Sniper-Rifle");

	private int price;
    private int damage;


    private double range;
    private int ammo;
    private double fireRate;
    private String type;

	Item(int price, int ammo, double fireRate, double range, int damage, String type)
	{

        this.price = price;
        this.damage = damage;
        this.range = range;
        this.ammo = ammo;
        this.fireRate = fireRate;
        this.type = type;
	}

    Item(int price)
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
    public String getType() { return type; }

	public static Item getItem(final String name){
		for(Item item : Item.values())
			if(item.name().equalsIgnoreCase(name))
				return item;
		return null;
	}
//TODO:Maybe load prices from config instead?

}
