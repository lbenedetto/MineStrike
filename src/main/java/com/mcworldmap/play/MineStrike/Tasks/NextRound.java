package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;

public class NextRound implements Runnable {
    int rounds;

    public NextRound(int rounds) {
        this.rounds = rounds;
    }

    /**
     * Starts the next round
     */
    @Override
    public void run() {
        //If halftime, switch teams
        if (rounds == 1 + (MineStrike.config.getInt("maxrounds") / 2)) {
            MineStrike.teams.switchTeams();
        }
        MineStrike.teams.respawnTeams();
        MineStrike.canBuy = true;
        //Display the round information to every player
        for (Person p : MineStrike.teams.getCT()) {
            Util.sendTitle(p.getPlayer(), 20, 50, 20, "Round " + rounds, RoundManager.stringify());
            MineStrike.frozenPlayers.add(p.getPlayer());
        }
        for (Person p : MineStrike.teams.getT()) {
            Util.sendTitle(p.getPlayer(), 20, 50, 20, "Round " + rounds, RoundManager.stringify());
            MineStrike.frozenPlayers.add(p.getPlayer());
        }
        //Schedule a task to unfreeze players and disable the shop
        Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new UnfreezePlayers(), 10 * 20);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DisableShop(), 20 * 20);
    }
}
