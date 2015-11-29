package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Item;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("unused")
public class EventListener implements Listener {
    @EventHandler
    /**
     * Handles people abandoning the game
     * @param event
     */
    public void onDisconnect(PlayerQuitEvent event) {
        String losingTeam = MineStrike.teams.findPerson(event.getPlayer()).getTeam();
        String winningTeam;
        if (losingTeam.equals("T"))
            winningTeam = "CT";
        else {
            winningTeam = "T";
        }
        RoundManager.gameOverLogic(winningTeam, losingTeam + " forfeits the game");
    }

    /**
     * Disable PVP if the game isnt running.
     *
     * @param event
     */
    @EventHandler
    public void onDamageByPlayer(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player && !MineStrike.isGameActive) {
            event.setCancelled(true);
        }

    }

    /**
     * Deal custom arrow damage based on the gun that shot it.
     *
     * @param event
     */
    @EventHandler
    public void customDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Arrow && MineStrike.isGameActive) {
            event.setCancelled(true);

            try {
                Item item = MineStrike.launchedProjectiles.get(event.getDamager());
                Arrow arrow = (Arrow) event.getDamager();
                if (item != null) {
                    ((Player) event.getEntity()).damage(item.getDamage(), (Entity) arrow.getShooter());
                    MineStrike.launchedProjectiles.remove(arrow);
                }
            } catch (NullPointerException e) {
                Bukkit.getLogger().info("Null pointer: Player switched weapons before getting kill");
            }
        }
    }

    /**
     * @param event
     */
    @EventHandler
    public void projectileLaunchEvent(ProjectileLaunchEvent event) {
        Projectile proj = event.getEntity();
        if (proj instanceof Arrow && proj.getShooter() instanceof Player) {
            Player player = (Player) proj.getShooter();
            MineStrike.launchedProjectiles.put((Arrow) proj, Item.getItem(player.getItemInHand().getItemMeta().getDisplayName()));
        }
    }


    /**
     * disable the extinguishing of fire.
     *
     * @param event
     */
    @EventHandler
    public void onExtinguish(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_BLOCK)
            return;

        final Player player = event.getPlayer();

        final Block block = event.getClickedBlock();
        final BlockFace blockFace = event.getBlockFace();
        final Block relativeBlock = block.getRelative(blockFace);
        final Material fireMaterial = Material.FIRE;
        if (relativeBlock != null)
            if (relativeBlock.getType() == fireMaterial) {
                event.setCancelled(true);
            }
    }

    /**
     * Make potions not do stuff
     *
     * @param event
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void potionSplashEvent(PotionSplashEvent event) {
        event.getAffectedEntities().stream().filter(ent -> ent instanceof Player).forEach(ent -> event.setIntensity(ent, 0));

    }

    /**
     * disable players being able to move.
     *
     * @param event
     */
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Location before = event.getFrom();
        Location after = event.getTo();
        //Players can move on Y, but its a feature, not a bug
        if (MineStrike.frozenPlayers.contains(event.getPlayer())) {
            //TODO: Maybe remove the cast to int?
            boolean differentX = !Util.almostEqual(before.getX(), after.getX(), .01);
            boolean differentZ = !Util.almostEqual(before.getZ(), after.getZ(), .01);
            if (differentX || differentZ)
                event.getPlayer().teleport(before);
        }
    }

    /**
     * Disable the picking up of items if its an arrow.
     *
     * @param event
     */
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType().equals(Material.ARROW))
            event.setCancelled(true);
    }

    /**
     * Players don't lose hunger.
     *
     * @param event
     */
    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    /**
     * disable lasting fire damage
     *
     * @param event
     */
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!MineStrike.isGameActive)
            return;
        if (event.getEntity() instanceof Player && (event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK || event.getCause() == EntityDamageEvent.DamageCause.FIRE)) {
            event.setCancelled(true);
            ((Player) event.getEntity()).damage(2);
            event.getEntity().setFireTicks(0);
        }
    }

    /**
     * Allow the player to break blocks if they are in creative, otherwise disallow it
     *
     * @param event
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            return;
        event.setCancelled(true);
    }

    /**
     * Disable the ignition of blocks. AKA fire spread
     *
     * @param event
     */
    @EventHandler
    public void onFireSpread(BlockIgniteEvent event) {
        event.setCancelled(true);
    }

}
