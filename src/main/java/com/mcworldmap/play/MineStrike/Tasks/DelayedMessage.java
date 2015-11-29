package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.entity.Player;

public class DelayedMessage implements Runnable {
    Player player;
    String title;
    String subtitle;

    public DelayedMessage(Player player, String message, String subtitle) {
        this.player = player;
        this.title = message;
        this.subtitle = subtitle;
    }

    public void run() {
        Util.sendTitle(player.getPlayer(), 20, 100, 20, title, subtitle);
    }
}
