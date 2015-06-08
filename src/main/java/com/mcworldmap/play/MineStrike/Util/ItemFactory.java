package com.mcworldmap.play.MineStrike.Util;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory
{

	public static ItemStack createItem(String name, ItemStack item)
	{
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.RED + "displayName");
		List<String> loreList = new ArrayList<String>();
		loreList.add(ChatColor.AQUA + "Gun");
		loreList.add(ChatColor.DARK_AQUA + name);
		im.setLore(loreList);
		item.setItemMeta(im);
		return null;
	}
}
