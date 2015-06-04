package com.mcworldmap.play.CounterCraft.listeners;

import com.mcworldmap.play.CounterCraft.CounterCraft;
import com.mcworldmap.play.CounterCraft.PlayerData.Person;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventListener implements Listener
{
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		Player prey = (Player) event.getEntity();
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

		for (Person p : CounterCraft.team.getCT())
		{
			if (p.getPlayer() == prey)
			{
				p.setDeaths(p.getDeaths() + 1);
				p.setAlive(false);
			}
			if (p.getPlayer() == predator)
			{
				p.setKills(p.getKills() + 1);
			}
		}
		for (Person p : CounterCraft.team.getT())
		{
			if (p.getPlayer() == prey)
			{
				p.setDeaths(p.getDeaths() + 1);
				p.setAlive(false);
			}
			if (p.getPlayer() == predator)
			{
				p.setKills(p.getKills() + 1);
			}
		}
	}
}
