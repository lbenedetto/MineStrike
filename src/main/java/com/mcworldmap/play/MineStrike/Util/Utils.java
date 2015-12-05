package com.mcworldmap.play.MineStrike.Util;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Utils {

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

        PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
        connection.sendPacket(packetPlayOutTimes);

        if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket(packetPlayOutSubTitle);
        }

        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('&', title);
            IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket(packetPlayOutTitle);
        }
    }

    public static void sendTabTitle(Player player, String header, String footer) {
        if (header == null) header = "";
        header = ChatColor.translateAlternateColorCodes('&', header);

        if (footer == null) footer = "";
        footer = ChatColor.translateAlternateColorCodes('&', footer);

        header = header.replaceAll("%player%", player.getDisplayName());
        footer = footer.replaceAll("%player%", player.getDisplayName());

        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);

        try {
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.sendPacket(headerPacket);
        }
    }

    /**
     * (
     * A string of 1's and 0's with guaranteed at least one 1.
     *
     * @return A string 5 chars long
     */
    public static String randomIntGuaranteed(double odds) {
        String out = "";
        for (int x = 0; x < 5; x++) {
            out += randomInt(odds);
        }
        if (out.contains("1")) return out;
        return randomIntGuaranteed(odds);
    }

    public static int randomInt(double odds) {
        if (Math.random() < odds) {
            return 1;
        }
        return 0;
    }

    /**
     * Saves typing when scheduling tasks
     *
     * @param task  The Runnable task to run
     * @param delay The delay before running the task
     * @return The task ID
     */
    public static int newTask(Runnable task, int delay) {
        return Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), task, delay);
    }

    /**
     * Saves typing  when scheduling repeating tasks
     *
     * @param task      The Runnable task to run
     * @param delay     The delay before running the task the first time
     * @param loopdelay The delay between each run of the task
     * @return The task ID
     */
    public static int newRepeatingTask(Runnable task, int delay, int loopdelay) {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MineStrike"), task, delay, loopdelay);

    }

    /**
     * Converts an ArrayList of strings to a string
     *
     * @param array The ArrayList to convert
     * @return The string
     */
    public static String arrayToString(ArrayList<String> array) {
        String s = "";
        for (String s1 : array) {
            s += s1;
        }
        return s;
    }

    public static Location getNearestAirBlock(double x, double y, double z){
        Location loc;
        World world = Bukkit.getWorld("de_dust2");

        Block block1 = world.getBlockAt((int)x + 1,(int)y + 1,(int)z);
        Block block2 = world.getBlockAt((int)x - 1,(int)y + 1,(int)z);
        Block block3 = world.getBlockAt((int)x,    (int)y + 1,(int)z + 1);
        Block block4 = world.getBlockAt((int)x,    (int)y + 1,(int)z - 1);

        if(block1.getType().equals(Material.AIR))
            loc = block1.getLocation();
        else if(block2.getType().equals(Material.AIR))
            loc = block2.getLocation();
        else if(block3.getType().equals(Material.AIR))
            loc = block3.getLocation();
        else if(block4.getType().equals(Material.AIR))
            loc = block4.getLocation();
        else
            loc = null;

        return loc;
    }

    /**
     * Returns true if two doubles are almost equal to eachother
     *
     * @param a   double one
     * @param b   double two
     * @param eps maximum allowable distance
     * @return true if they are not almost equal
     */
    public static boolean notAlmostEqual(double a, double b, double eps) {
        return Math.abs(a - b) >= eps;
    }
}
