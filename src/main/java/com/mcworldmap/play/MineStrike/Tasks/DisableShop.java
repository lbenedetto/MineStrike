package com.mcworldmap.play.MineStrike.Tasks;


import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Bukkit;

class DisableShop implements Runnable {
    /**
     * Disables the shop
     */
    @Override
    public void run() {
        Bukkit.getServer().getLogger().info("Shop disabled");
        MineStrike.canBuy = false;
    }
}
