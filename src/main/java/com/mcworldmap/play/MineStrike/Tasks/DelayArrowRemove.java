package com.mcworldmap.play.MineStrike.Tasks;


import org.bukkit.entity.Arrow;

public class DelayArrowRemove implements Runnable {

    Arrow arrow;

    public DelayArrowRemove(Arrow arrow) {
        this.arrow = arrow;
    }

    @Override
    public void run() {
        arrow.remove();
    }
}
