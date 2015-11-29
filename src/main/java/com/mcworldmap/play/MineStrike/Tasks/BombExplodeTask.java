package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BombExplodeTask implements Runnable {

    Person planter;
    Block bomb;

    public BombExplodeTask(Person planter, Block bomb) {
        this.planter = planter;
        this.bomb = bomb;
    }

    @Override
    public void run() {
        RoundManager.newRound(planter.getTeam(), "planting the bomb");
        bomb.setType(Material.AIR);
    }
}
