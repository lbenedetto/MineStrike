package com.mcworldmap.play.MineStrike.Util;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.BlockIterator;

import com.google.common.collect.Sets;

public class VisibilityDetection extends JavaPlugin implements Listener {
	private static final int TICKS_PER_SECOND = 20;

	private Set<Integer> transparent = Sets.newHashSet();

	@Override
	public void onEnable() {
		// Determine transparency
		for (Material material : Material.values()) {
			if (material.isTransparent()) {
				transparent.add(material.getId());
			}
		}

		getServer().getScheduler().runTaskTimer(this, new Runnable(	) {
			@Override
			public void run() {

				checkVisibility();
			}
		}, 1, 5 * TICKS_PER_SECOND);
	}

	// Note that this is O(n^2)
	private void checkVisibility() {
		// Check visibility for all players
		//Fix this and we're golden
		Player[] players = getServer().getOnlinePlayers();

		// Checking A - B is the same as B - A, so skip those
		for (int i = 0; i < players.length; i++) {
			for (int j = i + 1; j < players.length; j++) {
				Player a = players[i];
				Player b = players[j];

				// Must be in the same world
				if (a.getWorld().equals(b.getWorld())) {
					// Bounding box of the target
					Location targetAA = b.getLocation().add(-0.5, 0, -0.5);
					Location targetBB = b.getLocation().add(0.5, 1.67, 0.5);
					int distance = (int) a.getLocation().distance(targetAA);

					// No need to check this
					if (distance > 120)
						continue;

					if (getTargetBlock(lookAt(a.getLocation(), targetAA), transparent, distance) == null ||
							getTargetBlock(lookAt(a.getLocation(), targetBB), transparent, distance) == null) {
						// All air - we can probably see this player
						System.out.println(a + " can see " + b + ", and vice versa.");
					}
				}
			}
		}


	}

	private Location lookAt(Location loc, Location lookat) {
		double dx = lookat.getX() - loc.getX();
		double dy = lookat.getY() - loc.getY();
		double dz = lookat.getZ() - loc.getZ();

		double dxz = Math.sqrt(dx * dx + dz * dz);
		double pitch = Math.atan(dy / dxz);
		double yaw = 0;

		if (dx != 0) {
			if (dx < 0) {
				yaw = 1.5 * Math.PI;
			} else {
				yaw = 0.5 * Math.PI;
			}
			yaw -= Math.atan(dz / dx);
		} else if (dz < 0) {
			yaw = Math.PI;
		}

		loc.setYaw((float) Math.toDegrees(-yaw));
		loc.setPitch((float) Math.toDegrees(-pitch));
		return loc;
	}

	private Block getTargetBlock(Location direction, Set<Integer> transparent, int maxDistance) {
		for (Iterator<Block> it = new BlockIterator(direction, 0, maxDistance); it.hasNext(); ) {
			Block block = it.next();
			int id = block.getTypeId();

			// Determine if this is a non-air block
			if (transparent == null ? id != 0 : !transparent.contains(id)) {
				return block;
			}
		}
		// No target block found
		return null;
	}
}
