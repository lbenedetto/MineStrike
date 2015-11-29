package com.mcworldmap.play.MineStrike.PlayerData;

import net.md_5.bungee.api.ChatColor;

public enum Item {
    //Gear
    CTKEVLAR(650), CTHELMET(350),
    TKEVLAR(650), THELMET(350),
    KIT(400), ZEUS(400, 1, 1, .5, 100, "Kit"),
    C4(0),
    //Grenades
    FRAG(300), DECOY(50), MOLOTOV(400),
    FLASHBANG(200), SMOKE(300), INCENDIARY(600),
    //Format(Price, ammo, fireRate, range, damage, type)
    //Pistols
    GLOCK(200, 140, .15, 1d, 5, "Pistol"), USP(500, 36, .17, 1d, 6, "Pistol"), P2000(200, 65, .17, 1d, 6, "Pistol"),
    BERETTAS(500, 150, .12, 1d, 7, "Pistol"), P250(300, 39, .15, 1d, 6, "Pistol"), TEC9(500, 144, .12, 1d, 5, "Pistol"),
    DEAGLE(700, 42, .5, 1d, 15, "Pistol"),
    //Heavy
    NOVA(1200, 40, .8, .7, 5, "Shotgun"), XM1014(2000, 39, .6, .7, 7, "Shotgun"), SAWNOFF(1200, 39, .84, .7, 6, "Shotgun"),
    M249(5200, 300, .08, .8, 6, "Heavy"), NEGEV(5700, 350, .05, .8, 7, "Heavy"), MAG7(1800, 37, .84, .7, 6, "Shotgun"),
    //SMG's
    MAC10(1050, 130, .1, .9, 2, "SMG"), MP7(1700, 150, .1, .9, 2, "SMG"), UMP(1200, 125, .1, .9, 2, "SMG"),
    P90(2350, 150, .1, .9, 2, "SMG"), BISON(1400, 184, .1, .9, 2, "SMG"), MP9(1250, 184, .1, .9, 2, "SMG"),
    //Rifles
    FAMAS(2250, 115, .1, 1.5, 2, "Rifle"), GALIL(2000, 125, .1, 1.5, 2, "Rifle"), AK47(2700, 120, .1, 1.5, 2, "Rifle"),
    M4A1S(2700, 60, .1, 1.5, 2, "Rifle"), SSG(1700, 100, 2, 2, 17, "Sniper-Rifle"), AUG(3300, 120, .1, 1.5, 2, "Rifle"),
    SG(3000, 120, .1, 1.5, 2, "Sniper-Rifle"), AWP(4750, 40, 2, 10, 25, "Sniper-Rifle"),
    G3SG1(5000, 110, .25, 10, 18, "Sniper-Rifle"), SCAR20(5000, 110, .25, 10, 18, "Sniper-Rifle");
    //TODO:Maybe load stats from config instead?
    private int price;
    private int damage;
    private double range;
    private int ammo;
    private double fireRate;
    private String type;

    /**
     * @param price    the price of the gun
     * @param ammo     the amount of ammo the gun should have
     * @param fireRate the firerate of the gun
     * @param range    the range of the gun
     * @param damage   the damage the gun deals
     * @param type     the type of gun
     */
    Item(int price, int ammo, double fireRate, double range, int damage, String type) {

        this.price = price;
        this.damage = damage;
        this.range = range;
        this.ammo = ammo;
        this.fireRate = fireRate;
        this.type = type;
    }

    Item(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public double getRange() {
        return range;
    }

    public int getAmmo() {
        return ammo;
    }

    public double getFireRate() {
        return fireRate;
    }

    public String getType() {
        return type;
    }

    public static Item getItem(String name) {
        if (name == null)
            return null;
        try {
            name = name.split(" ")[1];
        }catch(ArrayIndexOutOfBoundsException e){

        }
        for (Item item : Item.values())
            if (item.name().equalsIgnoreCase(ChatColor.stripColor(name)))
                return item;
        return null;
    }
}
