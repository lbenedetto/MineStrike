package com.mcworldmap.play.MineStrike.Tasks;


import org.bukkit.entity.Arrow;

public class DelayArrowRemove implements Runnable {

    private final Arrow arrow;

    public DelayArrowRemove(Arrow arrow) {
        this.arrow = arrow;
    }

    /**
     * remove the arrow
     */
    @Override
    public void run() {
        arrow.remove();
    }
}
