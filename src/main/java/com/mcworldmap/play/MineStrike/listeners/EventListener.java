package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class EventListener implements Listener
{
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		Bukkit.getLogger().info("Death detected");
		Player prey = event.getEntity();
		EntityDamageEvent e0 = prey.getLastDamageCause();
		Player predator = null;
        /** This is here to make sure the thing that killed the player is in fact an Arrow or a player
            We do this here to save processing time, and to prevent(hopefully) an exception that occurs at runtime. **/
        if(!(e0 instanceof Arrow) || !(e0 instanceof Player))
            return;
		if (e0 instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent e1 = (EntityDamageByEntityEvent) e0;
			if (e1.getDamager() instanceof Arrow)
			{
				Arrow a = (Arrow) e1.getDamager();
				if (a.getShooter() instanceof Player)
				{
					predator = (Player) a.getShooter();
				}
			}
			if (e1.getDamager() instanceof Player)
			{
				predator = (Player) e1.getDamager();
			}
		}
		Person preyPerson = MineStrike.team.findPerson(prey);
		Person predatorPerson = MineStrike.team.findPerson(predator);
		if (!(predator == prey))
		{
			preyPerson.setDeaths(preyPerson.getDeaths() + 1);
			preyPerson.setAlive(false);
			predatorPerson.setKills(predatorPerson.getKills() + 1);
			predatorPerson.setScore(predatorPerson.getScore() + 2);
		} else
		{
			predatorPerson.setScore(predatorPerson.getScore() - 1);
		}
	}

	//TODO:Finish adding grenades
	@EventHandler
	public void onDecoyImpact(ExpBottleEvent event)
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

	@EventHandler
	public void onNadeImpact(PotionSplashEvent event)
	{

		//Changed some stuff. let's see if it works.

		ThrownPotion pot = event.getPotion();
		Location loc = pot.getLocation();
		World w = pot.getWorld();
		for(PotionEffect effect : pot.getEffects())
		{
			//Moltov
			if(effect.getType().equals(PotionEffectType.FIRE_RESISTANCE))
			{
				Bukkit.getLogger().info("Moltov Detected");
				pot.getLocation().getWorld().playEffect(loc, Effect.SMOKE, 10);
				pot.setBounce(true);
			}
			//'Nade
			if(effect.getType().equals(PotionEffectType.HARM))
			{
				Bukkit.getLogger().info("Nade detected");
				List<Entity> nearbyEntities = pot.getNearbyEntities(20, 20, 20);
				nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, Sound.ANVIL_LAND, 1, 1));
				pot.setBounce(true);
			}
			break;
		}
	}

	//I hope this works.
//	@EventHandler
//	public void onMoltovImpact(PotionSplashEvent event)
//	{
//		ThrownPotion pot = event.getPotion();
//
//	}
}
