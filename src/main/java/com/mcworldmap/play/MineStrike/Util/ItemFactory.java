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
    public static ItemStack createItem(String name) {
        for (Item item : Item.values()) {
            if (item.name().equalsIgnoreCase(name)) {
                switch (item) {
                    //TODO:Balance values
                    //region Gear
                    case CTKEVLAR:
                        return createCustomArmor(Material.LEATHER_CHESTPLATE, name, "Gear", 320, false);
                    case CTHELMET:
                        return createCustomArmor(Material.LEATHER_HELMET, name, "Gear", 320, false);
                    case TKEVLAR:
                        return createCustomArmor(Material.LEATHER_CHESTPLATE, name, "Gear", 320, true);
                    case THELMET:
                        return createCustomArmor(Material.LEATHER_HELMET, name, "Gear", 320, true);
                    case KIT:
                        return createCustomItem(Material.SHEARS, name, "Gear", Material.SHEARS.getMaxDurability());
                    case ZEUS:
                        return createCustomGun(Material.BOW, name, "Gear");
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
                        return createCustomGun(Material.BOW, name, "Pistol");
                    case USP:
                        return createCustomGun(Material.BOW, name, "Pistol");
                    //endregion
                    //region Heavy
                    case NEGEV:
                        return createCustomGun(Material.BOW, name, "Heavy");
                    case M249:
                        return createCustomGun(Material.BOW, name, "Heavy");
                    case NOVA:
                        return createCustomGun(Material.BOW, name, "Heavy");
                    case XM1014:
                        return createCustomGun(Material.BOW, name, "Heavy");
                    case SAWNOFF:
                        return createCustomGun(Material.BOW, name, "Heavy");
                    case MAG7:
                        return createCustomGun(Material.BOW, name, "Heavy");
                    //endregion
                    //region SMG
                    case MAC10:
                        return createCustomGun(Material.BOW, name, "SMG");
                    case MP7:
                        return createCustomGun(Material.BOW, name, "SMG");
                    case UMP:
                        return createCustomGun(Material.BOW, name, "SMG");
                    case P90:
                        return createCustomGun(Material.BOW, name, "SMG");
                    case BISON:
                        return createCustomGun(Material.BOW, name, "SMG");
                    case MP9:
                        return createCustomGun(Material.BOW, name, "SMG");
                    //endregion
                    //region Rifles
                    case FAMAS:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case GALIL:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case AK47:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case M4A1S:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case AUG:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case SG:
                        return createCustomGun(Material.BOW, name, "");
                    case SSG:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case AWP:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case G3SG1:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    case SCAR20:
                        return createCustomGun(Material.BOW, name, "Rifle");
                    //endregion
                    default:
                        return createCustomGun(Material.BOW, name, "Unknown");
                }
            }
        }
        return null;
    }

    /**
     * Creates a custom gun based on the Material, Name and Type of gun provided.
     *
     * @param m    Material that is to be used for the item
     * @param name The name of the item
     * @param type The type of weapon that the item is.
     * @return a new ItemStack of the correct item
     */
    public static ItemStack createCustomGun(Material m, String name, String type) {
        //This is for guns
        ItemStack i;
        ItemMeta im;
        ArrayList<String> loreList;
        i = new ItemStack(m, 1);
        im = i.getItemMeta();
        im.setDisplayName(ChatColor.RED + name);
        loreList = new ArrayList<>();
        loreList.add(ChatColor.AQUA + type);
        loreList.add(ChatColor.DARK_AQUA + name);
        im.setLore(loreList);
        i.setItemMeta(im);
        i.setDurability((short) (i.getType().getMaxDurability() - Item.getItem(name).getAmmo()));
        return i;
    }

    /**
     *
     * @param m The Material that the armor is to be created from
     * @param name The name of the item
     * @param type The type of the item
     * @param durability The durability of the item
     * @param isT Is the item for Terrorists or Counter Terrorists.
     * @return
     */
    public static ItemStack createCustomArmor(Material m, String name, String type, int durability, boolean isT) {
        //This is for armor
        ItemStack i;
        LeatherArmorMeta im;
        ArrayList<String> loreList;
        i = new ItemStack(m, 1);
        im = (LeatherArmorMeta) i.getItemMeta();
        im.setDisplayName(ChatColor.RED + name);
        loreList = new ArrayList<>();
        loreList.add(ChatColor.AQUA + type);
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
     * @param type       The type of the item
     * @param durability The amount of durability it is used to be
     * @return the itemstack that was created
     */
    public static ItemStack createCustomItem(Material m, String name, String type, int durability) {
        // this is for items..
        ItemStack i;
        ItemMeta im;
        ArrayList<String> loreList;
        i = new ItemStack(m, 1);
        im = i.getItemMeta();
        im.setDisplayName(ChatColor.RED + name);
        loreList = new ArrayList<>();
        loreList.add(ChatColor.AQUA + type);
        loreList.add(ChatColor.DARK_AQUA + name);
        im.setLore(loreList);
        i.setItemMeta(im);
        i.setDurability((short) durability);
        return i;
    }

    /**
     *
     * @param m The type of potion
     * @param name The name of the potion(Grenade)
     * @return a new ItemStack of the correct 'Nade
     */
    public static ItemStack createCustomNade(PotionType m, String name) {
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
