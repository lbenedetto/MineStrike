package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class DelayedSmoke implements Runnable {
    World world;
    Location loc;

    /**
     * @param world The world of which the smoke is to occur in
     * @param loc   The Location of the smoke
     */
    public DelayedSmoke(World world, Location loc) {
        this.world = world;
        this.loc = loc;
    }

    /**
     * Create a particle cloud at specified location
     */
    public void run() {
        // /particle <name> <x> <y> <z> <xd> <yd> <zd> <speed> [count = 1] [mode = normal]
        // Spawn a cloud of 10000 particles at x,y,z with radius of 2,2,2 and speed. Force all players to see them
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "particle hugeexplosion " + x + " " + y + " " + z + " 1 1 1 0 10 force");
    }
}
