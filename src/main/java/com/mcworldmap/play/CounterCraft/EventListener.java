package com.mcworldmap.play.CounterCraft;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventListener implements Listener
{
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		for (Person p : Constants.team.getCT())
			if (p.getPlayer() == event.getEntity())
			{
				p.setDeaths(p.getDeaths() + 1);
				p.setAlive(false);
			}
		for (Person p : Constants.team.getT())
			if (p.getPlayer() == event.getEntity())
			{
				p.setDeaths(p.getDeaths() + 1);
				p.setAlive(false);
			}
	}
}
