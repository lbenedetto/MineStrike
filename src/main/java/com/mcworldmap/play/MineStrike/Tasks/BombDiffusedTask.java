package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.Bukkit;

/**
 * Created by Apathetic on 11/28/2015.
 */
public class BombDiffusedTask implements Runnable{
    Person diffuser;

    public BombDiffusedTask(Person diffuser){
        this.diffuser = diffuser;
    }

    @Override
    public void run() {
        RoundManager.newRound(diffuser.getTeam());
    }
}
