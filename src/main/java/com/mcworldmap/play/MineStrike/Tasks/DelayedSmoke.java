package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class DelayedSmoke implements Runnable {
    private final Location loc;

    /**
     * @param loc   The Location of the smoke
     */
    public DelayedSmoke(Location loc) {
        this.loc = loc;
    }

    /**
     * Create a particle cloud at specified location
     *
     * @see "http://gaming.stackexchange.com/questions/212694/how-do-i-use-the-particle-command-in-vanilla-minecraft"
     */
    public void run() {
        // /particle <name> <x> <y> <z> <xd> <yd> <zd> <speed> [count = 1] [mode = normal]
        // Spawn a cloud of 10000 particles at x,y,z with radius of 1,1,1 and speed 0. Force all players to see them
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "particle hugeexplosion " + x + " " + y + " " + z + " 1 1 1 0 10 force");

    }
}
