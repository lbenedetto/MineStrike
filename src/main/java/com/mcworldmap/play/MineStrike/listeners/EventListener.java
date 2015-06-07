package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.FireExtinguish;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;
import java.util.Random;

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
		if (!(e0 instanceof Arrow) || !(e0 instanceof Player))
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
			predatorPerson.addMoney(700);
		} else
		{
			predatorPerson.setScore(predatorPerson.getScore() - 1);
		}
		if (MineStrike.team.isTTeamDead())
		{
			Bukkit.getServer().broadcastMessage("Counter-Terrorists Win");
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

				for (LivingEntity ent : affected)
				{
					if (ent instanceof Player)
					{
						event.setIntensity(ent, 0);
					}
				}
				Bukkit.getLogger().info("HE Grenade detected");
				event.getEntity().getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 5, false, false);
				pot.setBounce(true);
			}
			break;
		}
	}

	@EventHandler
	public void onFireSpread(BlockIgniteEvent event)
	{
		event.setCancelled(true);
	}

	//I hope this works.
//	@EventHandler
//	public void onMoltovImpact(PotionSplashEvent event)
//	{
//		ThrownPotion pot = event.getPotion();
//
//	}
}
