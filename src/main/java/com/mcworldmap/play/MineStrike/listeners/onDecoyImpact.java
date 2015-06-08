package com.mcworldmap.play.MineStrike.listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

import java.util.List;
@SuppressWarnings("unused")
public class onDecoyImpact implements Listener
{
	@EventHandler
	public void decoyImpact(ExpBottleEvent event)
	{
		Bukkit.getLogger().info("Deocoy Nade detected");
		Location loc = event.getEntity().getLocation();
		World w = loc.getWorld();
		ThrownExpBottle nade = event.getEntity();
		List<Entity> nearbyEntities = nade.getNearbyEntities(20, 20, 20);
		nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, Sound.ANVIL_LAND, 1, 1));
		w.playEffect(loc, Effect.FOOTSTEP, 1);
		w.playSound(loc, Sound.ARROW_HIT, 1, 1);
		w.playSound(loc, Sound.FALL_BIG, 1, 1);
		w.playEffect(loc, Effect.FOOTSTEP, 1);
		w.playSound(loc, Sound.ARROW_HIT, 1, 1);
		w.playSound(loc, Sound.FALL_BIG, 1, 1);
		w.playEffect(loc, Effect.FOOTSTEP, 1);
		w.playSound(loc, Sound.ARROW_HIT, 1, 1);
		w.playSound(loc, Sound.FALL_BIG, 1, 1);

	}
}
