package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Teams {
    public Person[] allPlayers;
    public int CTscore;
    public int Tscore;

    public Teams() {
        allPlayers = new Person[MineStrike.config.getInt("teamsize") * 2];
        CTscore = 0;
        Tscore = 0;
    }

    /**
     * Resets all data to starting values
     * Teleports players to the pregame spawn location
     */
    public void reset() {
        for (Person p : allPlayers) {
            p.getPlayer().teleport(MineStrike.config.getLocation("pregameSpawn"));
            p.setTeamKills(0);
        }
        allPlayers = new Person[MineStrike.config.getInt("teamsize") * 2];
        CTscore = 0;
        Tscore = 0;
        RoundManager.round = 1;
        MineStrike.cts = 0;
        MineStrike.ts = 0;
    }

    /**
     * Finds MVP on winning side based on round kills
     *
     * @param winningTeam String of which team won. T or CT.
     * @return Person
     */
    public Person getRoundMVP(String winningTeam) {
        int mostKills = 0;
        Person MVP = null;
        for (Person p : allPlayers) {
            if (p.getRoundKills() >= mostKills && p.getTeam().equals(winningTeam)) {
                MVP = p;
                mostKills = p.getRoundKills();
            }
        }
        return MVP;
    }

    /**
     * Finds MVP on winning side based on total score
     *
     * @param winningTeam String of which team won. T or CT.
     * @return Person
     */
    public Person getGameMVP(String winningTeam) {
        int highScore = 0;
        Person MVP = null;
        for (Person p : allPlayers)
            if (p.getScore() >= highScore && p.getTeam().equals(winningTeam)) {
                MVP = p;
                highScore = p.getScore();
            }
        return MVP;
    }

    /**
     * Given a player Object, find the team their Person Object is on
     *
     * @param player Player object to find team of
     * @return String representing which team given player is on
     */
    public String getTeam(Player player) {
        for (Person p : allPlayers) {
            if (p != null && p.getPlayer().equals(player)) {
                return p.getTeam();
            }
        }
        return "";
    }

    /**
     * Given a player Object, find the corresponding Person object
     *
     * @param player
     * @return Person
     */
    public Person findPerson(Player player) {
        for (Person p : allPlayers)
            if (p != null && p.getPlayer().equals(player))
                return p;
        return null;
    }

    /**
     * Switches players and resets other things for halftime
     */
    public void switchTeams() {
        //Swap teams
        for (Person p : allPlayers)
            if (p.getTeam().equals("T"))
                p.setTeam("CT");
            else
                p.setTeam("T");
        //Swap scores
        int tem = Tscore;
        Tscore = CTscore;
        CTscore = tem;
        resetMoney();
        resetInventory();
    }

    /**
     * Resets the inventory of both teams
     */
    public void resetInventory() {
        for (Person p : allPlayers) p.getPlayer().getInventory().clear();
    }

    /**
     * Resets everyones money to starting value
     */
    public void resetMoney() {
        for (Person p : allPlayers)
            p.setMoney(800);
    }

    /**
     * Checks if everyone on T side is dead
     *
     * @param team Team to check if dead CT or T
     * @return boolean true if everyone is dead
     */
    public boolean isTeamDead(String team) {
        boolean out = true;
        for (Person p : allPlayers)
            if (p.isAlive() && p.getTeam().equals(team))
                out = false;
        return out;
    }

    /**
     * Respawns everyone
     */
    public void respawnTeams() {
        for (Person p : allPlayers) {
            p.setRoundKills(0);
            p.setAlive(true);
            p.respawnPlayer();
        }
    }

    /**
     * Rewards a team with money
     *
     * @param reward Amount to award
     * @param team   Team to award money to. T or CT.
     */
    public void reward(int reward, String team) {
        for (Person p : allPlayers)
            if (p.getTeam().equals(team))
                p.addMoney(reward);
    }
}
