package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class RoundManager {
    public static int round = 1;

    /**
     * Create a new round, or not depending on the scores
     * Notifies players of the scores
     * @param winner Who won?
     */
    public static void newRound(String winner) {
        round += 1;
        int maxrounds = MineStrike.config.getInt("maxrounds");
        if (winner.equals("CT"))
            MineStrike.teams.CTscore += 1;
        if (winner.equals("T"))
            MineStrike.teams.Tscore += 1;
        //If the game is over
        if (round > maxrounds || (MineStrike.teams.CTscore >= maxrounds / 2 && MineStrike.teams.Tscore >= maxrounds / 2)) {
            //Build the components of the GG message
            String winMessage = "";
            if (MineStrike.teams.CTscore == MineStrike.teams.Tscore)
                winMessage = ChatColor.WHITE + "Tie";
            if (MineStrike.teams.Tscore == 1 + (maxrounds / 2))
                winMessage = ChatColor.GOLD + "Terrorists Win";
            if (MineStrike.teams.CTscore == 1 + (maxrounds / 2))
                winMessage = ChatColor.DARK_BLUE + "Counter-Terrorists Win";
            //Send out the GG message
            for (Person p : MineStrike.teams.getAllPlayers()) {
                MineStrike.getNetwork().updatePlayerScore(p, booleanifyT(ChatColor.stripColor(winMessage)));
                Util.sendTitle(p.getPlayer(), 20, 100, 20, "" + winMessage, "MVP: " + MineStrike.teams.getGameTMVP().getPlayer().getDisplayName() + " for highest score");
                p.getPlayer().performCommand("scoreboard");
            }
            MineStrike.teams.reset();
        } else if (winner.equals("CT")) {
            //If the game isn't over and the CT's won
            for (Person p : MineStrike.teams.getAllPlayers()) {
                Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.DARK_BLUE + "Counter-Terrorists Win", "MVP: " + MineStrike.teams.getRoundCTMVP().getPlayer().getName() + " for most eliminations");
                p.getPlayer().performCommand("scoreboard");
            }
            MineStrike.teams.rewardCT(3250);
            MineStrike.teams.rewardT(1400);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(round), 200);

        } else {
            //If the game isn't over and the CT's won
            for (Person p : MineStrike.teams.getAllPlayers()) {
                Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.GOLD + "Terrorists Win", "MVP: " + MineStrike.teams.getRoundTMVP().getPlayer().getName() + " for most eliminations");
                p.getPlayer().performCommand("scoreboard");
            }
            MineStrike.teams.rewardCT(1400);
            MineStrike.teams.rewardT(3250);
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(round), 200);
        }
    }

    /**
     * If the terrorists won, return true
     *
     * @param s String of the team name
     * @return Boolean true if T's won, false if CT's won, null if something else
     */
    public static Boolean booleanifyT(String s) {
        if (s.equalsIgnoreCase("Counter-Terrorists Win")) return false;
        if (s.equalsIgnoreCase("Terrorists Win")) return true;
        return null;
    }

    /**
     * If the terrorists won, return true
     *
     * @param s String of the team name
     * @return Boolean true if T's won, false if CT's won, null if something else
     */
    public static Boolean booleanifyCT(String s) {
        if (s.equalsIgnoreCase("Counter-Terrorists Win")) return true;
        if (s.equalsIgnoreCase("Terrorists Win")) return false;
        return null;
    }

    /**
     * Creates a colored string of the current scores
     * @return String
     */
    public static String stringify() {
        String out;
        out = ChatColor.GOLD + "" + MineStrike.teams.Tscore;
        out += ChatColor.WHITE + " | ";
        out += ChatColor.DARK_BLUE + "" + MineStrike.teams.CTscore;
        return out;
    }
}
