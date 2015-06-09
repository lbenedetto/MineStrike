package com.mcworldmap.play.MineStrike.listeners;
import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
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
	//Make potions not do stuff
	@EventHandler(priority = EventPriority.HIGHEST)
	public void potionSplashEvent(PotionSplashEvent event)
	{
		event.getAffectedEntities().stream().filter(ent -> ent instanceof Player).forEach(ent -> event.setIntensity(ent, 0));

	}

	@EventHandler
	public void onMove(PlayerMoveEvent event)
	{
		Location before = event.getFrom();
		Location after = event.getTo();

		if(MineStrike.frozenPlayers.contains(event.getPlayer()))
		{
			if((int)before.getX() == (int)after.getX())
				return;
			event.getPlayer().teleport(before);
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		event.getDrops().clear();
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event)
	{
		event.setCancelled(true);
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event)
	{
		event.setCancelled(true);
	}


	@EventHandler
	public void onArrowFire(PlayerInteractEvent event)
	{
		Action a = event.getAction();
		if(a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK))
		{
			if(event.getPlayer().getItemInHand().getType().equals(Material.BOW))
			{
				ItemStack item = event.getPlayer().getItemInHand();
				event.getPlayer().launchProjectile(Arrow.class);
				item.setDurability((short)(item.getDurability() + 1));
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onHunger(FoodLevelChangeEvent event)
	{
		event.setCancelled(true);
	}

	@EventHandler
	public void onHealthRegen(EntityRegainHealthEvent event)
	{
		if(event.getEntity() instanceof Player && event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.REGEN))
		{
			event.setCancelled(true);
		}
	}


	@EventHandler
	public void onFireSpread(BlockIgniteEvent event) {event.setCancelled(true);	}

}
