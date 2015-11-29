package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Tasks.*;
import com.mcworldmap.play.MineStrike.Util.NadeKillCreditor;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unused")
public class onNadeImpact implements Listener {


    /**
     * This function controls the various effects of grenades, that trigger when the PotionSplashEvent is fired.
     *
     * @param event
     */
    @EventHandler
    public void nadeImpact(PotionSplashEvent event) {
        ThrownPotion pot = event.getPotion();
        Location loc = pot.getLocation();
        World w = pot.getWorld();
        Collection<LivingEntity> affected = event.getAffectedEntities();
        Player thrower = (Player) pot.getShooter();
        ArrayList<Location> arrayList = new ArrayList<>();
        for (PotionEffect effect : pot.getEffects()) {
            //Moltov
            if (effect.getType().equals(PotionEffectType.FIRE_RESISTANCE)) {
                w.playEffect(loc, Effect.SMOKE, 10);
                pot.setBounce(true);
                List<Entity> nearbyEntities = pot.getNearbyEntities(20, 20, 20);
                nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e ->
                        ((Player) e).playSound(loc, Sound.FIRE, 2, 1));
                nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e ->
                        ((Player) e).playSound(loc, Sound.FIRE_IGNITE, 2, 1));
                int spread;

                //loop through a 4x4 set of blocks and set them to fire.
                double y = loc.getY();
                for (int x = -2; x <= 2; x++) {
                    for (int z = -2; z <= 2; z++) {
                        double newX = loc.getX() + x;
                        double newZ = loc.getZ() + z;
                        if (loc.getWorld().getBlockAt((int) newX, (int) y, (int) newZ).getType().equals(Material.AIR)) {
                            spread = new Random().nextInt(100);
                            if (spread < (100 - (Math.abs(z) + Math.abs(x) * 20))) {
                                Block block = loc.getWorld().getBlockAt((int) loc.getX() + x, (int) y, (int) loc.getZ() + z);
                                //create a 4x4 square that is set to fire.
                                block.setType(Material.FIRE);
                                // create a task that will remove the fire block after a random amount of time, to give an effect of the fire extinguishing.
                                Util.newTask(new FireExtinguish(block), (new Random().nextInt(10) + 10) * 20);
                                //Add the location of the fire to the location arraylist for nadekillcreditor
                                arrayList.add(new Location(w, newX, y, newZ));
                            }
                        }
                    }
                }
                MineStrike.killers.add(new NadeKillCreditor(thrower, arrayList));
            }
            //HE Grenade
            if (effect.getType().equals(PotionEffectType.HARM)) {
                //make it go boom after a certain amount of time
                Util.newTask(new DelayedBoom(w, loc, (Player) pot.getShooter()), 20);
            }
            //Flashbang
            if (effect.getType().equals(PotionEffectType.NIGHT_VISION)) {
                Bukkit.getLogger().info("Flashbang Detected");
                //get all nearby entities
                List<Entity> nearbyEntities = pot.getNearbyEntities(50, 50, 50);
                //delay the flash bang by x amount
                Util.newTask(new DelayedFlash(nearbyEntities, pot), 20);
            }
            //Decoy grenade, all it does is play sounds.
            if (effect.getType().equals(PotionEffectType.JUMP)) {
                Bukkit.getLogger().info("Decoy Detected");
                List<Entity> nearbyEntities = pot.getNearbyEntities(20, 20, 20);
                nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, Sound.ANVIL_LAND, 1, 1));
                //random bow shoot sounds in the vicinity of where the grenade landed for about 30ish seconds
                for (int t = 20; t < 600; t += ThreadLocalRandom.current().nextInt(10, 30 + 1)) {
                    Util.newTask(new DelayedSound(w, loc, pot, Sound.SHOOT_ARROW), t);
                    Util.newTask(new DelayedSound(w, loc, pot, Sound.ARROW_HIT), t + ThreadLocalRandom.current().nextInt(10, 40 + 1));
                }
            }
            //Smoke Grenade
            if (effect.getType().equals(PotionEffectType.SLOW)) {
                Bukkit.getLogger().info("Smoke Detected");
                int x = (int) loc.getX();
                int y = (int) loc.getY();
                int z = (int) loc.getZ();
                for (int ix = -5; ix < 5; ix++)
                    for (int iz = -5; iz < 5; iz++)
                        if (Math.abs(iz) + Math.abs(ix) <= 6)
                            for (int iy = 0; iy < 4; iy++) {
                                int checkX = x + ix;
                                int checkZ = z + iz;
                                int checkY = y + iy;
                                Block b = w.getBlockAt(checkX, checkY, checkZ);
                                if (b.getType().equals(Material.FIRE)) {
                                    b.setType(Material.AIR);
                                }
                            }
                for (int t = 20; t < 380; t += 10)
                    Util.newTask(new DelayedSmoke(w, loc), t);
            }
        }
    }
}
