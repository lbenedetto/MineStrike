package com.mcworldmap.play.MineStrike.listeners;

import net.minecraft.server.v1_8_R3.Explosion;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
import org.bukkit.util.Vector;
@SuppressWarnings("unused")
public class EventListener implements Listener
{
	@EventHandler
	public void potionThrowEvent(ProjectileLaunchEvent event)
	{
		if(event.getEntity() instanceof Firework)
		{
			event.getEntity().setVelocity(new Vector(0,0,0));
		}
		if (event.getEntity() instanceof ThrownPotion)
		{
			Entity e = event.getEntity();
			ThrownPotion thrownPotion = (ThrownPotion) e;
			thrownPotion.setBounce(true);
			e.setVelocity(e.getVelocity().multiply(2));
		}
	}
//	public void entityDamage(EntityDamageByEntityEvent event)
//	{
//		if(event.getEntity() instanceof Player && event.getDamager() instanceof Explosion)
//		{
//
//		}
//	}
//	public void projectileHitEvent(ProjectileHitEvent event)
//	{
//
//	}
	@EventHandler
	public void onFireSpread(BlockIgniteEvent event)
	{
		event.setCancelled(true);
	}

}
