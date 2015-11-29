package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.BombDiffusedTask;
import com.mcworldmap.play.MineStrike.Tasks.BombExplodeTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class BombListener implements Listener {


    Plugin p;

    public BombListener(Plugin p) {
        this.p = p;
    }

    /**
     * This listener checks if a player right clicks the bomb and starts a timer to keep checking if they are.
     *
     * @param event
     */
    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Person person = MineStrike.teams.findPerson(player);
        if (MineStrike.isGameActive) {
            if (person.getTeam().equals("T")) {
                if (event.getBlockPlaced().getType().equals(Material.TNT)) {
                    MineStrike.bombExplodeTaskID = Bukkit.getScheduler().scheduleSyncDelayedTask(p, new BombExplodeTask(person, event.getBlockPlaced()), 20 * p.getConfig().getInt("bombtimer"));
                    Bukkit.broadcastMessage(ChatColor.RED + "The bomb has been planted.");
                }
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        Person person = MineStrike.teams.findPerson(player);
        if (MineStrike.isGameActive)
            if (!MineStrike.bombDiffusing) {
                if (person.getTeam().equals("CT"))
                    if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                        Block block = event.getClickedBlock();
                        if (block.getType().equals(Material.TNT)) {
                            MineStrike.bombDiffusing = true;
                            int bombDiffusedTaskID;

                            if (event.getPlayer().getItemInHand().getType().equals(Material.SHEARS)) {
                                bombDiffusedTaskID = Bukkit.getScheduler()
                                        .scheduleSyncDelayedTask(p, new BombDiffusedTask(person, event.getClickedBlock()), 100);
                                player.sendMessage(ChatColor.GREEN + "Using diffusal kit, 5 seconds until bomb is diffused.");

                            } else {
                                bombDiffusedTaskID = Bukkit.getScheduler()
                                        .scheduleSyncDelayedTask(p, new BombDiffusedTask(person, event.getClickedBlock()), 200);
                                player.sendMessage(ChatColor.GREEN + "No diffusal kit, 10 seconds until bomb is diffused.");
                            }
                            MineStrike.bombDiffusedTaskID = bombDiffusedTaskID;
                            MineStrike.diffuser = person;
                        }
                    }
            } else {
                if (person.equals(MineStrike.diffuser) && MineStrike.bombDiffusing) {
                    MineStrike.bombDiffusing = false;
                    Bukkit.getScheduler().cancelTask(MineStrike.bombDiffusedTaskID);
                    MineStrike.diffuser = null;
                    Bukkit.broadcastMessage(ChatColor.RED + "Bomb diffusal cancelled.");
                }
            }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
//        Location to = event.getTo();
//        Location from = event.getFrom();
//        if (to.equals(from)) {
//            return;
//        }
        if (event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockZ() == event.getTo().getBlockZ() && event.getFrom().getBlockY() == event.getTo().getBlockY())
            return;
        if (MineStrike.isGameActive && MineStrike.bombDiffusing) {
            Person person = MineStrike.teams.findPerson(event.getPlayer());

            if (person != null && MineStrike.diffuser != null && person.equals(MineStrike.diffuser)) {
                MineStrike.bombDiffusing = false;
                MineStrike.diffuser = null;
                Bukkit.getScheduler().cancelTask(MineStrike.bombDiffusedTaskID);
                Bukkit.broadcastMessage(ChatColor.RED + "Bomb diffusal cancelled.");
            }
        }
    }
}
