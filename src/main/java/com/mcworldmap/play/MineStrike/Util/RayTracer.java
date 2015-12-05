package com.mcworldmap.play.MineStrike.Util;

import com.mcworldmap.play.MineStrike.MineStrike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import java.util.Iterator;

public class RayTracer {
    protected final Player player;

    protected RayTracer(Player player) {
        this.player = player;
    }

    /**
     * Check if a player is facing an entity, using cardinal directions
     *
     * @param flash The entity
     * @return True if facing entity
     */
    public boolean isFacing(Entity flash) {
        Location flashLoc = flash.getLocation();
        Location playerLoc = player.getLocation();
        String playerDir = RayTracer.getCardinalDirection(player);
        if (playerLoc.getZ() > flashLoc.getZ())
            if (playerDir.equals("North") || playerDir.equals("Northwest") || playerDir.equals("Northeast"))
                return true;
        if (playerLoc.getZ() < flashLoc.getZ())
            if (playerDir.equals("South") || playerDir.equals("Southeast") || playerDir.equals("Southwest"))
                return true;
        if (playerLoc.getX() > flashLoc.getX())
            if (playerDir.equals("West") || playerDir.equals("Northwest") || playerDir.equals("Southwest"))
                return true;
        if (playerLoc.getX() < flashLoc.getX())
            if (playerDir.equals("East") || playerDir.equals("Southeast") || playerDir.equals("Northeast"))
                return true;
        return false;
    }

    /**
     * Finds the cardinal direction a player is facing
     *
     * @param player The players who's direction is needed
     * @return The cardinal direction that they are facing as a String
     */
    public static String getCardinalDirection(Player player) {
        if (player == null) return "Swag";
        double rot = (player.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        if (0 <= rot && rot < 22.5) {
            return "West";
        } else if (22.5 <= rot && rot < 67.5) {
            return "Northwest";
        } else if (67.5 <= rot && rot < 112.5) {
            return "North";
        } else if (112.5 <= rot && rot < 157.5) {
            return "Northeast";
        } else if (157.5 <= rot && rot < 202.5) {
            return "East";
        } else if (202.5 <= rot && rot < 247.5) {
            return "Southeast";
        } else if (247.5 <= rot && rot < 292.5) {
            return "South";
        } else if (292.5 <= rot && rot < 337.5) {
            return "Southwest";
        } else if (337.5 <= rot && rot < 360.0) {
            return "West";
        } else {
            return "Swag";
        }
    }

    //    /**
//     * Checks if this person can see a given entity
//     * (Probably doesn't work)
//     *
//     * @param b Entity to check
//     * @return true if can see
//     */
//    public boolean canSee(Entity b) {
//        // Checking A - B is the same as B - A, so skip those
//        if (player.getWorld().equals(b.getWorld())) {
//            // Bounding box of the target
//            Location location = b.getLocation();
//            Material targetBlock = player.getWorld().getBlockAt(b.getLocation()).getType();
//            while (!targetBlock.equals(Material.AIR))
//                if (!targetBlock.equals(Material.AIR))
//                    location.setY(location.getY() + 1);
//            Location targetAA = b.getLocation().add(-0.5, 0, -0.5);
//            Location targetBB = b.getLocation().add(0.5, .5, 0.5);
//            int distance = (int) player.getLocation().distance(targetAA);
//            // No need to check this
//            if (distance > 120)
//                return false;
//            if (RayTracer.getTargetBlock(RayTracer.lookAt(player.getLocation(), targetAA), distance) == null ||
//                    RayTracer.getTargetBlock(RayTracer.lookAt(player.getLocation(), targetBB), distance) == null) {
//                // All air - we can probably see this player
//                Bukkit.getServer().getLogger().info(player.getDisplayName() + " can see " + b);
//                return true;
//            }
//        }
//        return true;
//    }

    public boolean canSee(Entity entity) {
        entity = player.getWorld().spawn(entity.getLocation(), Ghast.class);
        ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000, 30));
        boolean out = player.hasLineOfSight(entity);
        entity.remove();
        if (out) Bukkit.getLogger().info("Player can see it");
        else Bukkit.getLogger().info("Player can not see it");
        return out;
    }

    public static Block getTargetBlock(Location direction, int maxDistance) {
        for (Iterator<Block> it = new BlockIterator(direction, 0, maxDistance); it.hasNext(); ) {
            Block block = it.next();
            int id = block.getTypeId();

            // Determine if this is a non-air block
            if (MineStrike.transparent == null ? id != 0 : !MineStrike.transparent.contains(id)) {
                return block;
            }
        }
        // No target block found
        return null;
    }

    public static Location lookAt(Location loc, Location lookat) {
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
}
