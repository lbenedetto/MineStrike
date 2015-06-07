package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.logging.Level;

public class DelayedFlash implements Runnable
{
	List<Entity> nearbyEntities;
	Entity pot;

	public DelayedFlash(List<Entity> nearbyEntities, Entity pot)
	{
		this.nearbyEntities = nearbyEntities;
		this.pot = pot;
	}

	@Override
	public void run()
	{
		//Flash Player
		Location eLoc = null;
		Location flashLoc = null;
		String eDir = "";
		String flashDir = "";
		for (Entity e : nearbyEntities)
		{
			if (e instanceof Player)
			{
				Location loc = pot.getLocation();
				Firework firework = pot.getWorld().spawn(loc, Firework.class);
				FireworkMeta data = (FireworkMeta) firework.getFireworkMeta();
				data.addEffects(FireworkEffect.builder().withColor(Color.WHITE).with(FireworkEffect.Type.BALL_LARGE).build());
				data.setPower(2);
				firework.setFireworkMeta(data);
				firework.setVelocity(new Vector(0,0,0));
				if (MineStrike.team.findPerson((Player) e).canSee(pot))
				{
					eLoc = e.getLocation();
					flashLoc = pot.getLocation();
					eDir = Util.getCardinalDirection((Player) e);
					if(eLoc.getZ() > flashLoc.getZ()) {
						if(eDir.equals("North") || eDir.equals("Northwest") || eDir.equals("Northeast")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 30));
						}
					}
					else if(eLoc.getX() > flashLoc.getX()) {
						if(eDir.equals("West") || eDir.equals("Northwest") || eDir.equals("Southwest")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 30));
						}
					}
					else if(eLoc.getZ() < flashLoc.getZ()) {
						if(eDir.equals("South") || eDir.equals("Southeast") || eDir.equals("Southwest")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 30));
						}
					}
					else if(eLoc.getZ() < flashLoc.getZ()) {
						if(eDir.equals("East") || eDir.equals("Southeast") || eDir.equals("Northeast")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 30));
						}
					}
				}
			}
		}
	}
}
