package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

class UnfreezePlayers implements Runnable {
    /**
     * Unfreezes all players
     */
    @Override
    public void run() {
        MineStrike.frozenPlayers.clear();
        for(Person p : MineStrike.teams.allPlayers){
            Player player = p.getPlayer();
            if (player != null) {
                player.removePotionEffect(PotionEffectType.JUMP);
            }
        }
    }
}
