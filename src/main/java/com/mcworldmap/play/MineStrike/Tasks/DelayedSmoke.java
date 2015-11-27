package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Location;
import org.bukkit.World;

public class DelayedSmoke implements Runnable{
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

    }
}
