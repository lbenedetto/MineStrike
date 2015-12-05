package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class DelayedFlash implements Runnable {
    private final List<Entity> nearbyEntities;
    private final Entity flash;

    public DelayedFlash(List<Entity> nearbyEntities, Entity flash) {
        this.nearbyEntities = nearbyEntities;
        this.flash = flash;
    }

    @Override
    public void run() {
        for (Entity entity : nearbyEntities)
            if (entity instanceof Player) {
                Player player = (Player) entity;
                Person person = MineStrike.teams.findPerson(player);
                if(person==null)continue;
                //If its possible for the player to see the flashbang
                if (person.isFacing(flash))
                    if (person.canSee(flash))
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 30));
            }
    }
}
