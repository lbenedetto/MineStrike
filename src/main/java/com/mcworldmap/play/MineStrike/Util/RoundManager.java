package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class RoundManager {
    public static int round = 1;

    /**
     * Create a new round, or not depending on the scores
     * Notifies players of the scores
     *
     * @param winner Who won?
     * @param reason How did they win?
     */
    public static void newRound(String winner, String reason) {
        Bukkit.getScheduler().cancelTask(MineStrike.roundEndTaskID);
        for (Entity e : Bukkit.getWorld("de_dust2").getEntities()) {
            if (e instanceof Player)
                continue;
            e.remove();
        }
        round += 1;
        int maxrounds = MineStrike.config.getInt("maxrounds");
        if (winner.equals("CT"))
            MineStrike.teams.CTscore += 1;
        if (winner.equals("T"))
            MineStrike.teams.Tscore += 1;
        boolean someoneWon = false;
        if (MineStrike.teams.Tscore == 1 + (maxrounds / 2)) someoneWon = true;
        if (MineStrike.teams.CTscore == 1 + (maxrounds / 2)) someoneWon = true;
        boolean isTie = MineStrike.teams.CTscore >= maxrounds / 2 && MineStrike.teams.Tscore >= maxrounds / 2;
        if (isTie) winner = "tie";
        //If the game is over
        if (round > maxrounds || isTie || someoneWon) {
            gameOverLogic(winner, "");
        } else {
            if (winner.equals("CT")) {
                CTWinLogic(reason, winner);
            } else {
                TWinLogic(reason, winner);
            }
            Utils.newTask(new NextRound(round), 200);
        }
    }

    public static void TWinLogic(String reason, String winner) {
        String title = "";
        String subtitle = "";
        if (reason.equals("planting the bomb")) {
            title = ChatColor.DARK_BLUE + "Terrorists Win";
            subtitle = "MVP: " + MineStrike.teams.getRoundMVP(winner).getPlayer().getName() + " for planting the bomb";
        } else if (reason.equals("eliminating the enemy team")) {
            title = ChatColor.DARK_BLUE + "Counter-Terrorists Win";
            subtitle = "MVP: " + MineStrike.teams.getRoundMVP(winner).getPlayer().getName() + " for most eliminations";
        }
        for (Person p : MineStrike.teams.allPlayers) {
            Player player = p.getPlayer();
            if (player == null) continue;
            Utils.sendTitle(player, 20, 100, 20, title, subtitle);
            p.getPlayer().performCommand("scoreboard");
        }
        MineStrike.teams.reward(1400, "CT");
        MineStrike.teams.reward(3250, "T");
    }

    /**
     * Logic for if the CT's won the round
     *
     * @param reason How did they win?
     * @param winner Who won?
     */
    public static void CTWinLogic(String reason, String winner) {
        String title = "";
        String subtitle = "";
        switch (reason) {
            case "defusing the bomb":
                title = ChatColor.DARK_BLUE + "Counter-Terrorists Win";
                subtitle = "MVP: " + MineStrike.teams.getRoundMVP(winner).getPlayer().getName() + " for defusing the bomb";
                MineStrike.teams.reward(1400, "T");
                break;
            case "eliminating the enemy team":
                title = ChatColor.DARK_BLUE + "Counter-Terrorists Win";
                subtitle = "MVP: " + MineStrike.teams.getRoundMVP(winner).getPlayer().getName() + " for most eliminations";
                MineStrike.teams.reward(1400, "T");
                break;
            case "running down the clock":
                for (Person p : MineStrike.teams.allPlayers) {
                    Player player = p.getPlayer();
                    if (player == null) continue;
                    //Don't give money to surviving terrorists if the clock runs out
                    if (p.isDead()) p.addMoney(1400);
                }
                break;
        }
        for (Person p : MineStrike.teams.allPlayers) {
            Player player = p.getPlayer();
            if (player == null) continue;
            Utils.sendTitle(player, 20, 100, 20, title, subtitle);
            p.getPlayer().performCommand("scoreboard");
        }
        MineStrike.teams.reward(3250, "CT");
    }

    /**
     * Logic for if the game is over
     *
     * @param winner Who won?
     * @param reason How did they win?
     */
    public static void gameOverLogic(String winner, String reason) {
        Bukkit.getLogger().info("GG detected");
        MineStrike.isGameActive = false;
        //Build the components of the GG message
        String winMessage = "";
        String subtitle = "";
        if (MineStrike.teams.CTscore == MineStrike.teams.Tscore)
            winMessage = ChatColor.WHITE + "Tie";
        if (MineStrike.teams.Tscore > MineStrike.teams.CTscore)
            winMessage = ChatColor.GOLD + "Terrorists Win";
        if (MineStrike.teams.CTscore > MineStrike.teams.Tscore)
            winMessage = ChatColor.DARK_BLUE + "Counter-Terrorists Win";
        if (reason.equals(""))
            subtitle = "MVP: " + MineStrike.teams.getGameMVP(winner).getPlayer().getDisplayName() + " for highest score";
        if (reason.contains("forfeit")) {
            if (winner.equals("T"))
                winMessage = "Terrorists Win";
            if (winner.equals("CT"))
                winMessage = "Counter-Terrorists Win";
            subtitle = reason;
        }
        //Send out the GG message
        for (Person p : MineStrike.teams.allPlayers) {
            Player player = p.getPlayer();
            if (player == null) continue;
            if (p.getTeam().equals("T"))
                MineStrike.getNetwork().updatePlayerScore(p, p.getTeam().equals(winner));
            if (p.getTeam().equals("CT"))
                MineStrike.getNetwork().updatePlayerScore(p, p.getTeam().equals(winner));
            Utils.sendTitle(player, 20, 100, 20, winMessage, subtitle);
            p.getPlayer().performCommand("scoreboard");
        }
        MineStrike.teams.reset();
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
