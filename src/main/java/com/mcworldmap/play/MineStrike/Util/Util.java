package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.PlayerData.Item;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Util {
    @Deprecated
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, message, null);
    }

    @Deprecated
    public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, null, message);
    }

    @Deprecated
    public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

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

    public static String getCardinalDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        return getDirection(rot);
    }

    /**
     * @param rot The players rotation
     * @return The cardinal direction that they are facing as a String
     */
    private static String getDirection(double rot) {
        if (0 <= rot && rot < 22.5) {
            return "North";
        } else if (22.5 <= rot && rot < 67.5) {
            return "Northeast";
        } else if (67.5 <= rot && rot < 112.5) {
            return "East";
        } else if (112.5 <= rot && rot < 157.5) {
            return "Southeast";
        } else if (157.5 <= rot && rot < 202.5) {
            return "South";
        } else if (202.5 <= rot && rot < 247.5) {
            return "Southwest";
        } else if (247.5 <= rot && rot < 292.5) {
            return "West";
        } else if (292.5 <= rot && rot < 337.5) {
            return "Northwest";
        } else if (337.5 <= rot && rot < 360.0) {
            return "North";
        } else {
            return null;
        }
    }


    public static int getWeaponDamage(Item weapon) {
        return weapon.getDamage();
    }

    public static int randomInt(double odds) {
        if (Math.random() < odds) {
            return 1;
        }
        return 0;
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

    public static int newTask(Runnable task, int delay) {
        return Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), task, delay);
    }
    public static int newRepeatingTask(Runnable task, int delay, int loopdelay){
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("MineStrike"), task, delay, loopdelay);

    }

    public static String arrayToString(ArrayList<String> array) {
        String s = "";
        for(String s1 : array)
        {
            s += s1;
        }
        return s;
    }
    public static boolean almostEqual(double a, double b, double eps){
        return Math.abs(a-b)<eps;
    }
}
