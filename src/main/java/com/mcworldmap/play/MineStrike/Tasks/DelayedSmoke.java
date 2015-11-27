package com.mcworldmap.play.MineStrike.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

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
        for (int ix = -5; ix < 5; ix++)
            for (int iz = -5; iz < 5; iz++)
                if (Math.abs(iz) + Math.abs(ix) <= 5)
                    for (int iy = 0; iy < 4; iy++) {
                        int checkX = x + ix;
                        int checkZ = z + iz;
                        int checkY = y + iy;
                        Block b = world.getBlockAt(checkX, checkY, checkZ);
                        if (b.getType().equals(Material.FIRE)) {
                            b.setType(Material.AIR);
                        }
                    }
    }
}
