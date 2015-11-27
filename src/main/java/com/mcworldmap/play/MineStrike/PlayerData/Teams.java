package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.entity.Player;

public class Teams {
    private Person[] T;
    private Person[] CT;
    public int CTscore;
    public int Tscore;

    public Teams() {
        T = new Person[MineStrike.config.getInt("teamsize")];
        CT = new Person[MineStrike.config.getInt("teamsize")];
        CTscore = 0;
        Tscore = 0;
    }

    /**
     * Resets all data to starting values
     * Teleports players to the pregame spawn location
     */
    public void reset() {
        for (Person p : T) {
            p.getPlayer().teleport(MineStrike.config.getLocation("pregameSpawn"));
            p.setTeamKills(0);
        }
        for (Person p : CT) {
            p.getPlayer().teleport(MineStrike.config.getLocation("pregameSpawn"));
            p.setTeamKills(0);
        }
        T = new Person[MineStrike.config.getInt("teamsize")];
        CT = new Person[MineStrike.config.getInt("teamsize")];
        CTscore = 0;
        Tscore = 0;
        RoundManager.round = 1;
        MineStrike.cts = 0;
        MineStrike.ts = 0;
    }

    /**
     * @return Person[] Array of Terrorist Person's
     */
    public Person[] getT() {
        return T;
    }

    /**
     * @return Person[] Array of Counter-Terrorist Person's
     */
    public Person[] getCT() {
        return CT;
    }

    /**
     * Finds person with most round kills on T side
     * Based on RoundKills
     *
     * @return Person
     */
    public Person getRoundTMVP() {
        int mostKills = 0;
        Person MVP = null;
        for (Person p : T) {
            if (p.getRoundKills() >= mostKills) {
                MVP = p;
                mostKills = p.getRoundKills();
            }
        }
        return MVP;
    }

    /**
     * Finds person with most round kills on T side
     * Based on total score
     *
     * @return Person
     */
    public Person getGameTMVP() {
        int highScore = 0;
        Person MVP = null;
        for (Person p : T)
            if (p.getScore() >= highScore) {
                MVP = p;
                highScore = p.getScore();
            }
        return MVP;
    }

    /**
     * Finds person with most round kills on CT side
     * Based on RoundKills
     *
     * @return Person
     */
    public Person getRoundCTMVP() {
        int mostKills = 0;
        Person MVP = null;
        for (Person p : CT)
            if (p.getRoundKills() >= mostKills) {
                MVP = p;
                mostKills = p.getRoundKills();
            }
        return MVP;
    }

    /**
     * Finds person with most round kills on CT side
     * Based on total score
     *
     * @return Person
     */
    public Person getGameCTMVP() {
        int highScore = 0;
        Person MVP = null;
        for (Person p : CT)
            if (p.getScore() >= highScore) {
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
        for (Person p : MineStrike.teams.getCT())
            if (p != null && p.getPlayer().equals(player))
                return "CT";
        for (Person p : MineStrike.teams.getT())
            if (p != null && p.getPlayer().equals(player))
                return "T";
        return "";
    }

    /**
     * Given a player Object, find the corresponding Person object
     *
     * @param player
     * @return Person
     */
    public Person findPerson(Player player) {
        for (Person p : MineStrike.teams.getCT())
            if (p != null && p.getPlayer().equals(player))
                return p;
        for (Person p : MineStrike.teams.getT())
            if (p != null && p.getPlayer().equals(player))
                return p;
        return null;
    }

    /**
     * Switches players and resets other things for halftime
     */
    public void switchTeams() {
        //Swap teams
        Person[] temp = T;
        T = CT;
        CT = temp;
        //Swap scores
        int tem = Tscore;
        Tscore = CTscore;
        CTscore = tem;
        resetMoney();
        resetInventory();
    }

    /**
     * resets the inventory of both teams
     */
    public void resetInventory() {
        for (Person p : T) p.getPlayer().getInventory().clear();
        for (Person p : CT) p.getPlayer().getInventory().clear();
    }

    /**
     * resets everyones money to starting value
     */
    public void resetMoney() {
        for (Person p : CT)
            p.setMoney(800);
        for (Person p : T)
            p.setMoney(800);
    }

    /**
     * Checks if everyone on T side is dead
     *
     * @return boolean true if everyone is dead
     */
    public boolean isTTeamDead() {
        boolean out = true;
        for (Person p : T)
            if (p.isAlive())
                out = false;
        return out;
    }

    /**
     * Checks if everyone on CT side is dead
     * @return boolean true if everyone is dead
     */
    public boolean isCTTeamDead() {
        boolean out = true;
        for (Person p : CT)
            if (p.isAlive())
                out = false;
        return out;
    }

    /**
     *  Respawns everyone on T side
     */
    public void respawnTTeam() {
        for (Person p : T) {
            p.setRoundKills(0);
            p.setAlive(true);
            p.respawnT();
        }
    }

    /**
     * Respawns everyone on CT side
     */
    public void respawnCTTeam() {
        for (Person p : CT) {
            p.setRoundKills(0);
            p.setAlive(true);
            p.respawnCT();
        }
    }

    /**
     * Rewards T side with money
     * @param reward int, how much to give
     */
    public void rewardT(int reward) {
        for (Person p : T)
            p.addMoney(reward);
    }

    /**
     * Rewards CT side with money
     * @param reward int, how much to give
     */
    public void rewardCT(int reward) {
        for (Person p : CT)
            p.addMoney(reward);
    }
}
