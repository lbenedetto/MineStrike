package com.mcworldmap.play.CounterCraft.listeners;

import com.mcworldmap.play.CounterCraft.CounterCraft;
import com.mcworldmap.play.CounterCraft.PlayerData.Person;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;

public class EventListener implements Listener
{
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		Player prey = event.getEntity();
		EntityDamageEvent e0 = prey.getLastDamageCause();
		Player predator = null;
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
		Person preyPerson = CounterCraft.team.findPerson(prey);
		Person predatorPerson = CounterCraft.team.findPerson(predator);
		preyPerson.setDeaths(preyPerson.getDeaths() + 1);
		preyPerson.setAlive(false);
		predatorPerson.setKills(predatorPerson.getKills() + 1);
	}
	//TODO:Finish adding grenades
	public void onDecoyImpact(ThrownExpBottle event){
		Location l = event.getLocation();
		World w = event.getWorld();
		w.playEffect(l, Effect.FOOTSTEP, 1);
		w.playSound(l, Sound.ARROW_HIT, 10, 1);
		w.playSound(l, Sound.FALL_BIG, 10, 1);
		w.playEffect(l, Effect.FOOTSTEP, 1);
		w.playSound(l, Sound.ARROW_HIT, 10, 1);
		w.playSound(l, Sound.FALL_BIG, 10, 1);
		w.playEffect(l, Effect.FOOTSTEP, 1);
		w.playSound(l, Sound.ARROW_HIT, 10, 1);
		w.playSound(l, Sound.FALL_BIG, 10, 1);

	}
	public void onNadeImpact(PotionSplashEvent event){
		ThrownPotion nade = event.getPotion();
		Location l = nade.getLocation();

	}
}
