package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.Util.RoundManager;

class EndRound implements Runnable {
    public void run(){
        RoundManager.newRound("CT", "running down the clock");
    }
}
