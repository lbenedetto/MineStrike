package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

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
		for (Entity e : nearbyEntities)
		{
			if (e instanceof Player)
			{
				if (MineStrike.team.findPerson((Player) e).canSee(pot))
				{
					//Flash Player
					((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 3, 10));
				}
			}
		}
		//My Code, might still need this
//		Location eLoc = null;
//		Location flashLoc = null;
//		String eDir = "";
//		String flashDir = "";
		/**For Loop begins */
//		eLoc = e.getLocation();
//		flashLoc = pot.getLocation();
//		eDir = Util.getCardinalDirection((Player) e);
//		if(eLoc.getZ() > flashLoc.getZ()) {
//			if(eDir.equals("North") || eDir.equals("Northwest") || eDir.equals("Northeast")){
//				//Flash Player
//			}
//		}
//		else if(eLoc.getX() > flashLoc.getX()) {
//			if(eDir.equals("West") || eDir.equals("Northwest") || eDir.equals("Southwest")){
//				//Flash Player
//			}
//		}
//		else if(eLoc.getZ() < flashLoc.getZ()) {
//			if(eDir.equals("South") || eDir.equals("Southeast") || eDir.equals("Southwest")){
//				//Flash Player
//			}
//		}
//		else if(eLoc.getZ() < flashLoc.getZ()) {
//			if(eDir.equals("East") || eDir.equals("Southeast") || eDir.equals("Northeast")){
//				//Flash Player
//			}
//		}
	}
}
