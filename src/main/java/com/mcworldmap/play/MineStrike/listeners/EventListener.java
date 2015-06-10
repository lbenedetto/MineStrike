package com.mcworldmap.play.MineStrike.listeners;
import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Tasks.DelayArrowRemove;
import org.bukkit.Bukkit;
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
    //Despawn arrows faster.
    @EventHandler
	public void projectileHitEvent(ProjectileHitEvent event)
	{
        if(event.getEntity() instanceof  Arrow)
        {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DelayArrowRemove((Arrow)event.getEntity()), 200);
        }
	}

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
	public void onHunger(FoodLevelChangeEvent event)
	{
		event.setCancelled(true);
	}

    @EventHandler
    public void regenHealth(EntityRegainHealthEvent event)
    {
        if(event.getEntity() instanceof  Player)
            event.setCancelled(true);
    }

//    @EventHandler
//    public void onDamage(EntityDamageByBlockEvent event)
//    {
//        Block b = event.getDamager();
//        if(b.getType().equals(Material.FIRE) && event.getEntity() instanceof Player)
//        {
//            ((Player) event.getEntity()).damage(2);
//            ((Player) event.getEntity()).getActivePotionEffects().clear();
//            event.setCancelled(true);
//        }
//    }

    @EventHandler
    public void onCombustBlock(EntityCombustByBlockEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            ((Player) event.getEntity()).damage(2);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCombust(EntityCombustEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            ((Player) event.getEntity()).damage(2);
            event.setCancelled(true);
        }
    }


	@EventHandler
	public void onFireSpread(BlockIgniteEvent event) {event.setCancelled(true);	}

}
