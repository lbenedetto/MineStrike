package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Created by Apathetic on 11/28/2015.
 */
public class BombDiffusedTask implements Runnable {
    Person diffuser;
    Block bomb;

    public BombDiffusedTask(Person diffuser, Block bomb) {
        this.diffuser = diffuser;
        this.bomb = bomb;
    }

    @Override
    public void run() {
        RoundManager.newRound(diffuser.getTeam());
        bomb.setType(Material.AIR);
    }
}
