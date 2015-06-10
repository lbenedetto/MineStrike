package com.mcworldmap.play.MineStrike.listeners;
import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Item;
import com.mcworldmap.play.MineStrike.Tasks.DelayArrowRemove;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
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
        if(event.getEntity() instanceof Arrow)
        {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DelayArrowRemove((Arrow)event.getEntity()), 200);
        }
	}

    @EventHandler
    public void onDamageByPlayer(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player && !MineStrike.isGameActive)
        {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void customDamage(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof  Arrow)
        {
            Arrow a = (Arrow)event.getDamager();
            Player shooter = (Player)a.getShooter();

            ItemStack itemInHand = shooter.getItemInHand();
            Item item = Item.getItem(itemInHand.getItemMeta().getDisplayName());
            event.setCancelled(true);
            ((Player) event.getEntity()).damage(item.getDamage(), shooter);
        }
    }

    @EventHandler
    public void onExtinguish(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        final Block block = event.getClickedBlock();
        final BlockFace blockFace = event.getBlockFace();
        final Block relativeBlock = block.getRelative(blockFace);
        final Material fireMaterial = Material.FIRE;
        if (relativeBlock.getType() == fireMaterial) {
            event.setCancelled(true);
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
		//Players can move on Y, but its a feature, not a bug
		if(MineStrike.frozenPlayers.contains(event.getPlayer()))
		{
			if((int)before.getX() == (int)after.getX() && (int)before.getZ() == (int)after.getZ())
				return;
			event.getPlayer().teleport(before);
		}
	}


	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		event.getDrops().clear();
	}

//	@EventHandler
//	//public void onDrop(PlayerDropItemEvent event)
//	{
//		event.setCancelled(true);
//	}
//
	@EventHandler
    public void onPickup(PlayerPickupItemEvent event)
    {
        if(event.getItem().getItemStack().getType().equals(Material.ARROW))
    	    event.setCancelled(true);
    }

	@EventHandler
	public void onHunger(FoodLevelChangeEvent event)
	{
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
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || event.getCause() == EntityDamageEvent.DamageCause.FIRE);
            ((Player) event.getEntity()).damage(2);
            event.getEntity().setFireTicks(0);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        event.setCancelled(true);
    }

	@EventHandler
	public void onFireSpread(BlockIgniteEvent event) {event.setCancelled(true);	}

}
