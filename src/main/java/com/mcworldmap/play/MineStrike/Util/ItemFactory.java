package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.PlayerData.Item;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class ItemFactory {

    /**
     * Create a ItemStack based on the name you provided.
     *
     * @param name the name of the item
     * @return a new ItemStack of the correct item
     */
    public static ItemStack createItem(String owner, String name) {
        for (Item item : Item.values()) {
            if (item.name().equalsIgnoreCase(name)) {
                name = ChatColor.stripColor(owner) + "'s " + name;
                switch (item) {
                    //TODO:Balance values
                    //region Gear
                    case CTKEVLAR:
                        return createCustomArmor(Material.LEATHER_CHESTPLATE, name, 40, false);
                    case CTHELMET:
                        return createCustomArmor(Material.LEATHER_HELMET, name, 25, false);
                    case TKEVLAR:
                        return createCustomArmor(Material.LEATHER_CHESTPLATE, name, 40, true);
                    case THELMET:
                        return createCustomArmor(Material.LEATHER_HELMET, name, 25, true);
                    case KIT:
                        return createCustomItem(Material.SHEARS, name, Material.SHEARS.getMaxDurability());
                    case ZEUS:
                        return createCustomGun(name, "Gear");
                    case C4:
                        return createCustomItem(Material.TNT, name, 0);
                    //endregion
                    //region Grenades
                    case FRAG:
                        return createCustomNade(PotionType.INSTANT_DAMAGE, name);
                    case MOLOTOV:
                        return createCustomNade(PotionType.FIRE_RESISTANCE, name);
                    case INCENDIARY:
                        return createCustomNade(PotionType.FIRE_RESISTANCE, name);
                    case FLASHBANG:
                        return createCustomNade(PotionType.NIGHT_VISION, name);
                    case DECOY:
                        return createCustomNade(PotionType.JUMP, name);
                    case SMOKE:
                        return createCustomNade(PotionType.SLOWNESS, name);
                    //endregion
                    //region Pistols
                    case GLOCK:
                        return createCustomGun(name, "Pistol");
                    case USP:
                        return createCustomGun(name, "Pistol");
                    //endregion
                    //region Heavy
                    case NEGEV:
                        return createCustomGun(name, "Heavy");
                    case M249:
                        return createCustomGun(name, "Heavy");
                    case NOVA:
                        return createCustomGun(name, "Heavy");
                    case XM1014:
                        return createCustomGun(name, "Heavy");
                    case SAWNOFF:
                        return createCustomGun(name, "Heavy");
                    case MAG7:
                        return createCustomGun(name, "Heavy");
                    //endregion
                    //region SMG
                    case MAC10:
                        return createCustomGun(name, "SMG");
                    case MP7:
                        return createCustomGun(name, "SMG");
                    case UMP:
                        return createCustomGun(name, "SMG");
                    case P90:
                        return createCustomGun(name, "SMG");
                    case BISON:
                        return createCustomGun(name, "SMG");
                    case MP9:
                        return createCustomGun(name, "SMG");
                    //endregion
                    //region Rifles
                    case FAMAS:
                        return createCustomGun(name, "Rifle");
                    case GALIL:
                        return createCustomGun(name, "Rifle");
                    case AK47:
                        return createCustomGun(name, "Rifle");
                    case M4A1S:
                        return createCustomGun(name, "Rifle");
                    case AUG:
                        return createCustomGun(name, "Rifle");
                    case SG:
                        return createCustomGun(name, "");
                    case SSG:
                        return createCustomGun(name, "Rifle");
                    case AWP:
                        return createCustomGun(name, "Rifle");
                    case G3SG1:
                        return createCustomGun(name, "Rifle");
                    case SCAR20:
                        return createCustomGun(name, "Rifle");
                    //endregion
                    default:
                        return createCustomGun(name, "Unknown");
                }
            }
        }
        return null;
    }

    /**
     * Creates a custom gun based on the Material, Name and Type of gun provided.
     *
     * @param name The name of the item
     * @param type The type of weapon that the item is.
     * @return a new ItemStack of the correct item
     */
    private static ItemStack createCustomGun(String name, String type) {
        //This is for guns
        ItemStack i;
        ItemMeta im;
        ArrayList<String> loreList;
        i = new ItemStack(Material.BOW, 1);
        im = i.getItemMeta();
        im.setDisplayName(ChatColor.RED + name);
        loreList = new ArrayList<>();
        loreList.add(ChatColor.AQUA + type);
        loreList.add(ChatColor.DARK_AQUA + name);
        im.setLore(loreList);
        i.setItemMeta(im);
        i.setDurability((short) (i.getType().getMaxDurability() - Item.getItem(name.split(" ")[1]).getAmmo()));
        return i;
    }

    /**
     * @param m          The Material that the armor is to be created from
     * @param name       The name of the item
     * @param durability The durability of the item
     * @param isT        Is the item for Terrorists or Counter Terrorists.
     * @return The ItemStack that was created
     */
    private static ItemStack createCustomArmor(Material m, String name, int durability, boolean isT) {
        //This is for armor
        ItemStack i;
        LeatherArmorMeta im;
        ArrayList<String> loreList;
        i = new ItemStack(m, 1);
        im = (LeatherArmorMeta) i.getItemMeta();
        im.setDisplayName(ChatColor.RED + name);
        loreList = new ArrayList<>();
        loreList.add(ChatColor.AQUA + "Gear");
        loreList.add(ChatColor.DARK_AQUA + name);
        im.setLore(loreList);
        if (isT)
            im.setColor(Color.ORANGE);
        else
            im.setColor(Color.BLUE);
        i.setItemMeta(im);
        i.setDurability((short) durability);
        return i;
    }

    /**
     * @param m          The material of the item
     * @param name       The name of the item
     * @param durability The amount of durability it is used to be
     * @return the ItemStack that was created
     */
    private static ItemStack createCustomItem(Material m, String name, int durability) {
        // this is for items..
        ItemStack i;
        ItemMeta im;
        ArrayList<String> loreList;
        i = new ItemStack(m, 1);
        im = i.getItemMeta();
        im.setDisplayName(ChatColor.RED + name);
        loreList = new ArrayList<>();
        loreList.add(ChatColor.AQUA + "Gear");
        loreList.add(ChatColor.DARK_AQUA + name);
        im.setLore(loreList);
        i.setItemMeta(im);
        i.setDurability((short) durability);
        return i;
    }

    /**
     * @param m    The type of potion
     * @param name The name of the potion(Grenade)
     * @return a new ItemStack of the correct 'Nade
     */
    private static ItemStack createCustomNade(PotionType m, String name) {
        //This is for nades
        ItemStack i;
        ItemMeta im;
        ArrayList<String> loreList;
        Potion p;
        p = new Potion(m);
        p.setSplash(true);
        i = p.toItemStack(1);
        im = i.getItemMeta();
        im.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
        im.setDisplayName(ChatColor.RED + name);
        loreList = new ArrayList<>();
        loreList.add(ChatColor.AQUA + "Grenade");
        loreList.add(ChatColor.DARK_AQUA + name);
        im.setLore(loreList);
        i.setItemMeta(im);
        return i;
    }
}
