package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DelayedMessage implements Runnable {
    Player player;
    ArrayList<String> progressBar;
    int diffuseTime;

    public DelayedMessage(Player player, ArrayList<String> message, int diffuseTime) {
        this.player = player;
        this.progressBar = message;
        this.diffuseTime = diffuseTime;
    }

    public void run() {
        Util.sendTitle(player.getPlayer(), 20, 100, 20, Util.arrayToString(progressBar), diffuseTime + " second(s) remaining");
        diffuseTime--;
        progressBar.remove(progressBar.size() - 1);
    }
}
