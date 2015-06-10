package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.Tasks.DelayedBoom;
import com.mcworldmap.play.MineStrike.Tasks.DelayedFlash;
import com.mcworldmap.play.MineStrike.Tasks.FireExtinguish;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class onNadeImpact implements Listener
{
	@EventHandler
	public void nadeImpact(PotionSplashEvent event)
	{
		ThrownPotion pot = event.getPotion();
		Location loc = pot.getLocation();
		World w = pot.getWorld();
		Collection<LivingEntity> affected = event.getAffectedEntities();
		for (PotionEffect effect : pot.getEffects())
		{
			//Moltov
			if (effect.getType().equals(PotionEffectType.FIRE_RESISTANCE))
			{
				w.playEffect(loc, Effect.SMOKE, 10);
				pot.setBounce(true);
				List<Entity> nearbyEntities = pot.getNearbyEntities(20, 20, 20);
				nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, Sound.FIRE, 2, 1));
				nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, Sound.FIRE_IGNITE, 2, 1));
				int spread;
				for (int x = -2; x <= 2; x++)
				{
					for (int z = -2; z <= 2; z++)
					{
						if (loc.getWorld().getBlockAt((int) loc.getX() + x, (int) loc.getY(), (int) loc.getZ() + z).getType().equals(Material.AIR))
						{
							spread = new Random().nextInt(100);
							if (spread < (100 - (Math.abs(z) + Math.abs(x) * 20)))
							{
								Block block = loc.getWorld().getBlockAt((int) loc.getX() + x, (int) loc.getY(), (int) loc.getZ() + z);
								block.setType(Material.FIRE);
								Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new FireExtinguish(block), (new Random().nextInt(10) + 10) * 20);
							}
						}
					}
				}
			}
			//HE Grenade
			if (effect.getType().equals(PotionEffectType.HARM))
			{
				Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DelayedBoom(w, loc, (Player)pot.getShooter()), 20);
			}
			//Flashbang
			if (effect.getType().equals(PotionEffectType.NIGHT_VISION))
			{
				Bukkit.getLogger().info("Flashbang Detected");
				List<Entity> nearbyEntities = pot.getNearbyEntities(50, 50, 50);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DelayedFlash(nearbyEntities, pot), 20);
			}
			if(effect.getType().equals(PotionEffectType.SLOW)){

			}
		}
	}
}
