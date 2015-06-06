package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.*;

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
		Location l = event.getEntity().getLocation();
		World w = l.getWorld();
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

	@EventHandler
	public void onNadeImpact(PotionSplashEvent event)
	{
		Bukkit.getLogger().info("Nade detected");
		ThrownPotion nade = event.getPotion();
		Location loc = nade.getLocation();
		World w = nade.getWorld();
		w.playSound(loc, Sound.ANVIL_BREAK, 10, 1);
	}
}
