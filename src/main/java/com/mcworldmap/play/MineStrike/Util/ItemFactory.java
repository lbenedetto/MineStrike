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
import java.util.List;

public class ItemFactory {

    public static ItemStack createItem(String name) {
        ItemStack i;
        ItemMeta im = null;
        ArrayList<String> loreList = new ArrayList<>();
        Potion p;
        for (Item item : Item.values()) {
            if (item.name().equalsIgnoreCase(name)) {
                switch (item) {
                    case KEVLAR:
                        i = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
                        im = i.getItemMeta();
                        im.setDisplayName(ChatColor.RED + name);
                        loreList = new ArrayList<>();
                        loreList.add(ChatColor.AQUA + "Gear");
                        loreList.add(ChatColor.DARK_AQUA + name);
                        im.setLore(loreList);
                        i.setItemMeta(im);
                        return i;
                    case HELMET:
                        i = new ItemStack(Material.DIAMOND_HELMET, 1);
                        im = i.getItemMeta();
                        im.setDisplayName(ChatColor.RED + name);
                        loreList = new ArrayList<>();
                        loreList.add(ChatColor.AQUA + "Gear");
                        loreList.add(ChatColor.DARK_AQUA + name);
                        im.setLore(loreList);
                        i.setItemMeta(im);
                        return i;
                    case KIT:
                        i = new ItemStack(Material.SHEARS, 1);
                        im = i.getItemMeta();
                        im.setDisplayName(ChatColor.RED + name);
                        loreList = new ArrayList<>();
                        loreList.add(ChatColor.AQUA + "Gear");
                        loreList.add(ChatColor.DARK_AQUA + "Defuse Kit");
                        im.setLore(loreList);
                        i.setItemMeta(im);
                        return i;
                    case ZEUS:
                        i = new ItemStack(Material.BOW, 1);
                        im = i.getItemMeta();
                        im.setDisplayName(ChatColor.RED + name);
                        loreList = new ArrayList<>();
                        loreList.add(ChatColor.AQUA + "Gear");
                        loreList.add(ChatColor.DARK_AQUA + name);
                        im.setLore(loreList);
                        i.setItemMeta(im);
                        i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        i.setDurability((short) (i.getType().getMaxStackSize() - 1));
                        return i;
                    case FRAG:
                        p = new Potion(PotionType.INSTANT_DAMAGE);
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
                    case MOLOTOV:
                        p = new Potion(PotionType.FIRE_RESISTANCE);
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
                    case DECOY:
                        i = new ItemStack(Material.EXP_BOTTLE, 1);
                        im = i.getItemMeta();
                        im.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
                        im.setDisplayName(ChatColor.RED + name);
                        loreList = new ArrayList<>();
                        loreList.add(ChatColor.AQUA + "Grenade");
                        loreList.add(ChatColor.DARK_AQUA + name);
                        im.setLore(loreList);
                        i.setItemMeta(im);
                        return i;
                    default:
                        i = new ItemStack(Material.BOW, 1);
                        im = i.getItemMeta();
                        im.setDisplayName(ChatColor.RED + name);
                        loreList = new ArrayList<>();
                        loreList.add(ChatColor.AQUA + "Gun");
                        loreList.add(ChatColor.DARK_AQUA + name);
                        im.setLore(loreList);
                        i.setItemMeta(im);
                        i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        i.setDurability((short) 320);
                        break;
                }
            }
        }
        return null;
    }
}
