package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Util.NadeKillCreditor;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class FireExtinguish implements Runnable {
    //Location loc;
    private final Block block;
    //int extTime;

    public FireExtinguish(Block block) {
        //this.loc = loc;
        this.block = block;
        //this.extTime = extTime;
    }

    /**
     * Extinguishes flames at the scheduled block
     */
    @Override
    public void run() {
        block.setType(Material.AIR);
        for (NadeKillCreditor kills : MineStrike.killers) {
            //Loop through all locations in the getLocations() array list in the kills variable.
            kills.getLocations().stream().filter(location -> location.equals(block.getLocation())).forEach(location ->
                    kills.getLocations().remove(block.getLocation()));
        }
    }
}
