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
        diffuser.addPoints(2);
        Bukkit.getScheduler().cancelTask(MineStrike.bombExplodeTaskID);
        Bukkit.getScheduler().cancelTask(MineStrike.bombTimerTaskID);
        Bukkit.getScheduler().cancelTask(MineStrike.bombDiffuseDisplayTaskID);
        //This gives us an easy way to check if the bomb is planted
        MineStrike.bombExplodeTaskID = 0;
        //Probably don't need to reset these, but I'll do it in the name of future proofing
        MineStrike.bombTimerTaskID =0;
        MineStrike.bombDiffuseDisplayTaskID = 0;
        MineStrike.bombTimer = 45;
        MineStrike.bombDiffusing = false;
        MineStrike.diffuser = null;
        bomb.setType(Material.AIR);
    }
}
