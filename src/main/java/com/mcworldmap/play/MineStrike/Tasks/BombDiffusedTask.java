package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BombDiffusedTask implements Runnable {
    Person diffuser;
    Block bomb;

    public BombDiffusedTask(Person diffuser, Block bomb) {
        this.diffuser = diffuser;
        this.bomb = bomb;
    }

    @Override
    public void run() {
        RoundManager.newRound(diffuser.getTeam(), "defusing the bomb");
        Bukkit.getScheduler().cancelTask(MineStrike.bombExplodeTaskID);
        MineStrike.bombDiffusing = false;
        MineStrike.diffuser = null;
        bomb.setType(Material.AIR);
    }
}
