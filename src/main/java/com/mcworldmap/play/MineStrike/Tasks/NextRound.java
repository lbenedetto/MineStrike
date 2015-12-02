package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import com.mcworldmap.play.MineStrike.Util.Utils;
import org.bukkit.potion.PotionEffectType;

public class NextRound implements Runnable {
    private final int rounds;

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
        for (Person p : MineStrike.teams.allPlayers) {
            Utils.sendTitle(p.getPlayer(), 20, 50, 20, "Round " + rounds, RoundManager.stringify());
            MineStrike.frozenPlayers.add(p.getPlayer());
            p.getPlayer().addPotionEffect(PotionEffectType.JUMP.createEffect(999,-10), true);
        }
        //Schedule a task to unfreeze players and disable the shop
        Utils.newTask(new UnfreezePlayers(), 10 * 20);
        Utils.newTask(new DisableShop(), 20 * 20);
        //Schedule a task to end the round if none of the other win conditions have been met
        MineStrike.roundEndTaskID = Utils.newTask(new EndRound(), MineStrike.config.getInt("roundtimer")*20);
    }
}
