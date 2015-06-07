package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
				if (MineStrike.team.findPerson((Player) e).canSee(pot))
				{
					eLoc = e.getLocation();
					flashLoc = pot.getLocation();
					eDir = Util.getCardinalDirection((Player) e);
					if(eLoc.getZ() > flashLoc.getZ()) {
						if(eDir.equals("North") || eDir.equals("Northwest") || eDir.equals("Northeast")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 10));
						}
					}
					else if(eLoc.getX() > flashLoc.getX()) {
						if(eDir.equals("West") || eDir.equals("Northwest") || eDir.equals("Southwest")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 10));
						}
					}
					else if(eLoc.getZ() < flashLoc.getZ()) {
						if(eDir.equals("South") || eDir.equals("Southeast") || eDir.equals("Southwest")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 10));
						}
					}
					else if(eLoc.getZ() < flashLoc.getZ()) {
						if(eDir.equals("East") || eDir.equals("Southeast") || eDir.equals("Northeast")){
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 10));
						}
					}
				}
			}
		}
	}
}
