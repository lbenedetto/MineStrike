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
     *
     * @param winner Who won?
     */
    public static void newRound(String winner) {
        round += 1;
        int maxrounds = MineStrike.config.getInt("maxrounds");
        if (winner.equals("CT"))
            MineStrike.teams.CTscore += 1;
        if (winner.equals("T"))
            MineStrike.teams.Tscore += 1;
        boolean didSomeoneWin = false;
        if (MineStrike.teams.Tscore == 1 + (maxrounds / 2)) didSomeoneWin = true;
        if (MineStrike.teams.CTscore == 1 + (maxrounds / 2)) didSomeoneWin = true;
        boolean isTie = MineStrike.teams.CTscore >= maxrounds / 2 && MineStrike.teams.Tscore >= maxrounds / 2;
        //If the game is over
        if (round > maxrounds || isTie || didSomeoneWin) {
            Bukkit.getLogger().info("GG detected");
            //Build the components of the GG message
            String winMessage = "";
            if (MineStrike.teams.CTscore == MineStrike.teams.Tscore)
                winMessage = ChatColor.WHITE + "Tie";
            if (MineStrike.teams.Tscore == 1 + (maxrounds / 2))
                winMessage = ChatColor.GOLD + "Terrorists Win";
            if (MineStrike.teams.CTscore == 1 + (maxrounds / 2))
                winMessage = ChatColor.DARK_BLUE + "Counter-Terrorists Win";
            //Send out the GG message
            for (Person p : MineStrike.teams.allPlayers) {
                //if (p.getTeam().equals("T"))
                    //MineStrike.getNetwork().updatePlayerScore(p, booleanify(winner));
                //if (p.getTeam().equals("CT"))
                    //MineStrike.getNetwork().updatePlayerScore(p, !booleanify(winner));
                Util.sendTitle(p.getPlayer(), 20, 100, 20, "" + winMessage, "MVP: " + MineStrike.teams.getGameMVP(winner).getPlayer().getDisplayName() + " for highest score");
                p.getPlayer().performCommand("scoreboard");
            }
            MineStrike.teams.reset();
        } else {
            if (winner.equals("CT")) {
                //Game isn't over and the CT's won the round
                for (Person p : MineStrike.teams.allPlayers) {
                    Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.DARK_BLUE + "Counter-Terrorists Win", "MVP: " + MineStrike.teams.getRoundMVP(winner).getPlayer().getName() + " for most eliminations");
                    p.getPlayer().performCommand("scoreboard");
                }
                MineStrike.teams.reward(3250, "CT");
                MineStrike.teams.reward(1400, "T");
            } else {
                //Game isn't over and the T's won the round
                for (Person p : MineStrike.teams.allPlayers) {
                    Util.sendTitle(p.getPlayer(), 20, 100, 20, ChatColor.GOLD + "Terrorists Win", "MVP: " + MineStrike.teams.getRoundMVP(winner).getPlayer().getName() + " for most eliminations");
                    p.getPlayer().performCommand("scoreboard");
                }
                MineStrike.teams.reward(1400, "CT");
                MineStrike.teams.reward(3250, "T");
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(round), 200);
        }
    }

    /**
     * If the terrorists won, return true
     *
     * @param s String of the team name
     * @return Boolean true if T's won, false if CT's won, null if something else
     */
    public static boolean booleanify(String s) {
        if (s.equalsIgnoreCase("CT")) return false;
        return true;
    }

    /**
     * Creates a colored string of the current scores
     *
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
