package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Created by Apathetic on 11/28/2015.
 */
public class BombTimer  implements Runnable{

    @Override
    public void run() {
        if(MineStrike.bombTimer % 5 == 0){
            String message = ChatColor.RED + "" + MineStrike.bombTimer + " second(s) until the bomb explodes.";

            Bukkit.broadcastMessage(message);
        }
        MineStrike.bombTimer--;
    }
}
