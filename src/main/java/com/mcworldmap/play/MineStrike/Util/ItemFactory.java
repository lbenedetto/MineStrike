package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.PlayerData.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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
					//region Gear
					case KEVLAR:
						return createCustomItem(Material.DIAMOND_CHESTPLATE, name, "Gear", 320);
					case HELMET:
						return createCustomItem(Material.DIAMOND_HELMET, name, "Gear", 320);
					case KIT:
						return createCustomItem(Material.SHEARS, name, "Gear");
					case ZEUS:
						return createCustomItem(Material.BOW, name, "Gear", Material.BOW.getMaxDurability() - 1);
					//endregion
					//region Grenades
					case FRAG:
						return createCustomPotion(PotionType.INSTANT_DAMAGE, name);
					case MOLOTOV:
						return createCustomPotion(PotionType.FIRE_RESISTANCE, name);
					case INCENDIARY:
						return createCustomPotion(PotionType.FIRE_RESISTANCE, name);
					case FLASH:
						return createCustomPotion(PotionType.NIGHT_VISION, name);
					case DECOY:
						return createCustomItem(Material.EXP_BOTTLE, name, "Grenade");
					//endregion
					//region Pistols
					case GLOCK:
						return createCustomItem(Material.BOW, name, "Pistol", 320, .15);
					case USP:
						return createCustomItem(Material.BOW, name, "Pistol", 320, .17);
					//endregion
					//region Heavy
					//endregion
					default:
						return createCustomItem(Material.BOW, name, "Unknown", 320, 1);
				}
			}
		}
		return null;
	}

	public static ItemStack createCustomItem(Material m, String name, String type, int durability, double firerate)
	{
		ItemStack i;
		ItemMeta im;
		ArrayList<String> loreList;
		i = new ItemStack(m, 1);
		im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + name);
		loreList = new ArrayList<>();
		loreList.add(ChatColor.AQUA + type);
		loreList.add(ChatColor.DARK_AQUA + name);
		loreList.add("" + firerate);
		im.setLore(loreList);
		i.setItemMeta(im);
		i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		i.setDurability((short) durability);
		return i;
	}

	public static ItemStack createCustomItem(Material m, String name, String type, int durability)
	{
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
		i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		i.setDurability((short) durability);
		return i;
	}

	public static ItemStack createCustomPotion(PotionType m, String name)
	{
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

	public static ItemStack createCustomItem(Material m, String name, String type)
	{
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
		return i;
	}
}
