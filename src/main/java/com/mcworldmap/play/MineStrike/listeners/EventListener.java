package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Item;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.DelayArrowRemove;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public class EventListener implements Listener
{
	@EventHandler
	public void potionThrowEvent(ProjectileLaunchEvent event)
	{
		if (event.getEntity() instanceof Firework)
		{
			event.getEntity().setVelocity(new Vector(0, 0, 0));
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
		if (event.getEntity() instanceof Arrow)
		{
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DelayArrowRemove((Arrow) event.getEntity()), 200);
		}
	}

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        MineStrike.getNetwork().createPlayerData(event.getPlayer());
    }


	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		if (!MineStrike.isGameActive)
			return;
		if (event.getMessage().equalsIgnoreCase("/shopct") && MineStrike.team.getTeam(event.getPlayer()).equalsIgnoreCase("t"))
		{
			event.setCancelled(true);
			event.getPlayer().sendMessage("You can't open CT's shop as T!");
			return;
		}
		if (event.getMessage().equalsIgnoreCase("/shopt") && MineStrike.team.getTeam(event.getPlayer()).equalsIgnoreCase("ct"))
		{
			event.setCancelled(true);
			event.getPlayer().sendMessage("You can't open T's shop as CT!");
			return;
		}
	}

	@EventHandler
	public void onDamageByPlayer(EntityDamageByEntityEvent event)
	{
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player && !MineStrike.isGameActive)
		{
			event.setCancelled(true);
		}

	}


	@EventHandler
	public void customDamage(EntityDamageByEntityEvent event)
	{
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Arrow && MineStrike.isGameActive)
		{
			event.setCancelled(true);
			Arrow a = (Arrow) event.getDamager();
			Player shooter = (Player) a.getShooter();

			ItemStack itemInHand = shooter.getItemInHand();
			try
			{
				Item item = Item.getItem(itemInHand.getItemMeta().getDisplayName());
				((Player) event.getEntity()).damage(item.getDamage(), shooter);
			}catch(NullPointerException e){
				Bukkit.getLogger().info("Null pointer: Player switched weapons before getting kill");
			}

		}
	}

	@EventHandler
	public void onExtinguish(PlayerInteractEvent event)
	{

		if (event.getAction() != Action.LEFT_CLICK_BLOCK)
			return;

		final Player player = event.getPlayer();

		final Block block = event.getClickedBlock();
		final BlockFace blockFace = event.getBlockFace();
		final Block relativeBlock = block.getRelative(blockFace);
		final Material fireMaterial = Material.FIRE;
		if (relativeBlock != null)
			if (relativeBlock.getType() == fireMaterial)
			{
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
		if (MineStrike.frozenPlayers.contains(event.getPlayer()))
		{
			if ((int) before.getX() == (int) after.getX() && (int) before.getZ() == (int) after.getZ())
				return;
			event.getPlayer().teleport(before);
		}
	}


	@EventHandler
	public void onPickup(PlayerPickupItemEvent event)
	{
		if (event.getItem().getItemStack().getType().equals(Material.ARROW))
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
	public void onEntityDamage(EntityDamageEvent event)
	{
		if (!MineStrike.isGameActive)
			return;
		if (event.getEntity() instanceof Player && (event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || event.getCause() == EntityDamageEvent.DamageCause.FIRE))
		{
			event.setCancelled(true);
			((Player) event.getEntity()).damage(2);
			event.getEntity().setFireTicks(0);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
			return;
		event.setCancelled(true);
	}

	@EventHandler
	public void onFireSpread(BlockIgniteEvent event)
	{
		event.setCancelled(true);
	}

}
