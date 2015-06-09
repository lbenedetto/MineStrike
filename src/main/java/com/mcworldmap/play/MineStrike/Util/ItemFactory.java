package com.mcworldmap.play.MineStrike.Util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory
{

	public static ItemStack createItem(String name)
	{
		ItemStack i = null;
		if(name.equalsIgnoreCase("kevlar"))
		{
			i = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(ChatColor.RED + name);
			List<String> loreList = new ArrayList<String>();
			loreList.add(ChatColor.AQUA + "Armor");
			loreList.add(ChatColor.DARK_AQUA + name);
			im.setLore(loreList);
			i.setItemMeta(im);
			return i;
		}
		if(name.equalsIgnoreCase("helmet"))
		{
			i = new ItemStack(Material.DIAMOND_HELMET, 1);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(ChatColor.RED + name);
			List<String> loreList = new ArrayList<String>();
			loreList.add(ChatColor.AQUA + "Armor");
			loreList.add(ChatColor.DARK_AQUA + name);
			im.setLore(loreList);
			i.setItemMeta(im);
			return i;
		}
		if(name.equalsIgnoreCase("frag"))
		{
			Potion p = new Potion(PotionType.INSTANT_DAMAGE);
			p.setSplash(true);
			i = p.toItemStack(1);

			ItemMeta im = i.getItemMeta();
			im.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
			im.setDisplayName(ChatColor.RED + name);
			List<String> loreList = new ArrayList<String>();
			loreList.add(ChatColor.AQUA + "Grenade");
			loreList.add(ChatColor.DARK_AQUA + name);
			im.setLore(loreList);
			i.setItemMeta(im);
			return i;
		}

		if(name.equalsIgnoreCase("molotov"))
		{
			Potion p = new Potion(PotionType.FIRE_RESISTANCE);
			p.setSplash(true);
			i = p.toItemStack(1);

			ItemMeta im = i.getItemMeta();
			im.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
			im.setDisplayName(ChatColor.RED + name);
			List<String> loreList = new ArrayList<String>();
			loreList.add(ChatColor.AQUA + "Grenade");
			loreList.add(ChatColor.DARK_AQUA + name);
			im.setLore(loreList);
			i.setItemMeta(im);
			return i;
		}

		if(name.equalsIgnoreCase("decoy"))
		{
			i = new ItemStack(Material.EXP_BOTTLE, 1);

			ItemMeta im = i.getItemMeta();
			im.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
			im.setDisplayName(ChatColor.RED + name);
			List<String> loreList = new ArrayList<String>();
			loreList.add(ChatColor.AQUA + "Grenade");
			loreList.add(ChatColor.DARK_AQUA + name);
			im.setLore(loreList);
			i.setItemMeta(im);
			return i;
		}

		i = new ItemStack(Material.BOW, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + name);
		List<String> loreList = new ArrayList<String>();
		loreList.add(ChatColor.AQUA + "Armor");
		loreList.add(ChatColor.DARK_AQUA + name);
		im.setLore(loreList);
		i.setItemMeta(im);
		i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		i.setDurability((short)320);


		return i;
	}
}
