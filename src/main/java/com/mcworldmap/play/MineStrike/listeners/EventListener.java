package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.DelayedFlash;
import com.mcworldmap.play.MineStrike.Tasks.FireExtinguish;
import com.mcworldmap.play.MineStrike.Util.Util;
import net.minecraft.server.v1_8_R3.Explosion;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EventListener implements Listener
{
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		Bukkit.getLogger().info("Death detected");
		Player prey = event.getEntity();
		Player predator = null;

		Entity e1 = event.getEntity().getKiller();
		if (e1 instanceof Arrow)
		{
			Arrow a = (Arrow) e1;
			if (a.getShooter() instanceof Player)
			{
				predator = (Player) a.getShooter();
			}
		}
		if (e1 instanceof Player)
		{
			predator = (Player) e1;
		}
		Person preyPerson = MineStrike.team.findPerson(prey);
		Person predatorPerson = MineStrike.team.findPerson(predator);
		assert predator != null;
		Bukkit.getServer().broadcastMessage(prey.getDisplayName() + " was killed by " + predator.getDisplayName());
		if (predator.getDisplayName().equals(prey.getDisplayName()))
		{
			predatorPerson.setScore(predatorPerson.getScore() - 1);
		} else
		{
			preyPerson.setDeaths(preyPerson.getDeaths() + 1);
			preyPerson.setAlive(false);
			predatorPerson.setKills(predatorPerson.getKills() + 1);
			predatorPerson.setScore(predatorPerson.getScore() + 2);
			predatorPerson.addMoney(700);

		}

		if (MineStrike.team.isTTeamDead())
		{
			for (Person p : MineStrike.team.getT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Counter-Terrorists Win", "? MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + "for most eliminations");
			for (Person p : MineStrike.team.getCT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Counter-Terrorists Win", "? MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + "for most eliminations");
			MineStrike.team.respawnCT();
			MineStrike.team.respawnT();
			MineStrike.team.rewardCT(3250);
			MineStrike.team.rewardT(1400);
			MineStrike.team.CTscore += 1;
		}
		if (MineStrike.team.isCTTeamDead())
		{
			for (Person p : MineStrike.team.getT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Terrorists Win", "? MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + "for most eliminations");
			for (Person p : MineStrike.team.getCT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Terrorists Win", "? MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + "for most eliminations");
			MineStrike.team.respawnCT();
			MineStrike.team.respawnT();
			MineStrike.team.rewardCT(1400);
			MineStrike.team.rewardT(3250);
			MineStrike.team.Tscore += 1;
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
	public void potionThrowEvent(ProjectileLaunchEvent event)
	{
		if (event.getEntity() instanceof ThrownPotion)
		{
			Entity e = event.getEntity();
			ThrownPotion thrownPotion = (ThrownPotion) e;
			thrownPotion.setBounce(true);
			e.setVelocity(e.getVelocity().multiply(2));
		}
	}

	@EventHandler
	public void onNadeImpact(PotionSplashEvent event)
	{

		//Changed some stuff. let's see if it works.

		ThrownPotion pot = event.getPotion();
		Location loc = pot.getLocation();
		World w = pot.getWorld();
		Collection<LivingEntity> affected = event.getAffectedEntities();
		for (PotionEffect effect : pot.getEffects())
		{
			//Moltov
			if (effect.getType().equals(PotionEffectType.FIRE_RESISTANCE))
			{

				for (LivingEntity ent : affected)
				{
					if (ent instanceof Player)
					{
						event.setIntensity(ent, 0);
					}
				}
				Bukkit.getLogger().info("Moltov Detected");
				w.playEffect(loc, Effect.SMOKE, 10);
				pot.setBounce(true);

				List<Entity> nearbyEntities = pot.getNearbyEntities(20, 20, 20);
				nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, Sound.FIRE, 2, 1));
				nearbyEntities.stream().filter(e -> e instanceof Player).forEach(e -> ((Player) e).playSound(loc, Sound.FIRE_IGNITE, 2, 1));
				int spread;
				for (int x = -2; x <= 2; x++)
				{
					for (int z = -2; z <= 2; z++)
					{
						if (loc.getWorld().getBlockAt((int) loc.getX() + x, (int) loc.getY(), (int) loc.getZ() + z).getType().equals(Material.AIR))
						{
							spread = new Random().nextInt(100);
							if (spread < (100 - (Math.abs(z) + Math.abs(x) * 20)))
							{
								Block block = loc.getWorld().getBlockAt((int) loc.getX() + x, (int) loc.getY(), (int) loc.getZ() + z);
								block.setType(Material.FIRE);
								Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new FireExtinguish(block), (new Random().nextInt(10) + 10) * 20);
							}
						}
					}
				}
			}
			//'Nade
			if (effect.getType().equals(PotionEffectType.HARM))
			{
				affected.stream().filter(ent -> ent instanceof Player).forEach(ent -> {
					event.setIntensity(ent, 0);
				});
				Bukkit.getLogger().info("HE Grenade detected");
				event.getEntity().getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 5, false, false);
				pot.setBounce(true);
			}
			//Flashbang
			//A rudimentary way to see if the player is facing away from the flashbang
			//We may later implement a way to see if the grenade is obscure, but it would be difficult
			if (effect.getType().equals(PotionEffectType.NIGHT_VISION))
			{
				Bukkit.getLogger().info("Flashbang Detected");
				List<Entity> nearbyEntities = pot.getNearbyEntities(20, 20, 20);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new DelayedFlash(nearbyEntities, pot), (new Random().nextInt(10) + 10) * 20);
			}
			break;
		}
	}


	public void entityDamage(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Explosion)
		{

		}
	}

	public void projectileHitEvent(ProjectileHitEvent event)
	{

	}
	@EventHandler
	public void onFireSpread(BlockIgniteEvent event)
	{
		event.setCancelled(true);
	}

}
