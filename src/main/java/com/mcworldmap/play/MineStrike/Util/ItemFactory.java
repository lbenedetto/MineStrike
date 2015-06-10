package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.PlayerData.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class ItemFactory
{

	public static ItemStack createItem(String name)
	{
		for (Item item : Item.values())
		{
			if (item.name().equalsIgnoreCase(name))
			{
				switch (item)
				{
					//TODO:Balance values
					//region Gear
					case KEVLAR:
						return createCustomArmor(Material.DIAMOND_CHESTPLATE, name, "Gear", 320);
					case HELMET:
						return createCustomArmor(Material.DIAMOND_HELMET, name, "Gear", 320);
					case KIT:
						return createCustomArmor(Material.SHEARS, name, "Gear", Material.SHEARS.getMaxDurability());
					case ZEUS:
						return createCustomGun(Material.BOW, name, "Gear", 1, 1, .5);
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
						//Don't think about it, just accept it, and move on
						return createCustomArmor(Material.EXP_BOTTLE, name, "Grenade", Material.EXP_BOTTLE.getMaxDurability());
					//endregion
					//region Pistols
					case GLOCK:
						return createCustomGun(Material.BOW, name, "Pistol", 140, .15, 1);
					case USP:
						return createCustomGun(Material.BOW, name, "Pistol", 36, .17, 1);
					//endregion
					//region Heavy
					case NEGEV:
						return createCustomGun(Material.BOW, name, "Heavy", 350, .1, .8);
					case M249:
						return createCustomGun(Material.BOW, name, "Heavy", 300, .1, .8);
					case NOVA:
						return createCustomGun(Material.BOW, name, "Heavy", 40, 2, .7);
					case XM1014:
						return createCustomGun(Material.BOW, name, "Heavy", 39, 1, .7);
					case SAWNOFF:
						return createCustomGun(Material.BOW, name, "Heavy", 39, 1, .7);
					case MAG7:
						return createCustomGun(Material.BOW, name, "Heavy", 37, 1, .7);
					//endregion
					//region SMG
					case MAC10:
						return createCustomGun(Material.BOW, name, "SMG", 130, .1, .9);
					case MP7:
						return createCustomGun(Material.BOW, name, "SMG", 150, .1, .9);
					case UMP:
						return createCustomGun(Material.BOW, name, "SMG", 125, .1, .9);
					case P90:
						return createCustomGun(Material.BOW, name, "SMG", 150, .1, .9);
					case BISON:
						return createCustomGun(Material.BOW, name, "SMG", 184, .1, .9);
					case MP9:
						return createCustomGun(Material.BOW, name, "SMG", 150, .1, .9);
					//endregion
					//region Rifles
					case FAMAS:
						return createCustomGun(Material.BOW, name, "Rifle", 0, .1, 1.5);
					case GALIL:
						return createCustomGun(Material.BOW, name, "Rifle", 0, .1, 1.5);
					case AK47:
						return createCustomGun(Material.BOW, name, "Rifle", 0, .1, 1.5);
					case M4A1S:
						return createCustomGun(Material.BOW, name, "Rifle", 0, .1, 1.5);
					case AUG:
						return createCustomGun(Material.BOW, name, "Rifle", 0, .1, 1.5);
					case SG:
						return createCustomGun(Material.BOW, name, "", 0, .1, 1.5);
					case SSG:
						return createCustomGun(Material.BOW, name, "Rifle", 100, 2, 10);
					case AWP:
						return createCustomGun(Material.BOW, name, "Rifle", 40, 2, 10);
					case G3SG1:
						return createCustomGun(Material.BOW, name, "Rifle", 110, .25, 10);
					case SCAR20:
						return createCustomGun(Material.BOW, name, "Rifle", 110, .25, 10);
					//endregion
					default:
						return createCustomGun(Material.BOW, name, "Unknown", 0, 1, 1);
				}
			}
		}
		return null;
	}

	public static ItemStack createCustomGun(Material m, String name, String type, int ammo, double firerate, double rangeMultiplier)
	{
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
		loreList.add(ChatColor.BLACK + "" + firerate);
		loreList.add(ChatColor.BLACK + "" + rangeMultiplier);
		im.setLore(loreList);
		i.setItemMeta(im);
		i.setDurability((short) (i.getType().getMaxDurability() - ammo));
		return i;
	}

	public static ItemStack createCustomArmor(Material m, String name, String type, int durability)
	{
		//This is for armor
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

	public static ItemStack createCustomNade(PotionType m, String name)
	{
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
