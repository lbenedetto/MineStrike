package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.Util.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class DelayedMessage implements Runnable {
    private final Player player;
    private final ArrayList<String> progressBar;
    private int diffuseTime;

    public DelayedMessage(Player player, ArrayList<String> message, int diffuseTime) {
        this.player = player;
        this.progressBar = message;
        this.diffuseTime = diffuseTime;
    }

    public void run() {
        Utils.sendTitle(player.getPlayer(), 20, 100, 20, Utils.arrayToString(progressBar), diffuseTime + " second(s) remaining");
        diffuseTime--;
        progressBar.remove(progressBar.size() - 1);
    }
}
