package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.entity.Player;

public class FireRate implements Runnable {

    private final Player p;

    public FireRate(Player p) {
        this.p = p;
    }

    /**
     * Removes a player from cooldown
     */
    @Override
    public void run() {
        MineStrike.coolDown.remove(p);
    }
}
