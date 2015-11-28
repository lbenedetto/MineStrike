package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.BombDiffusedTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by Apathetic on 11/28/2015.
 */
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
    public void onRightClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        Person person = MineStrike.teams.findPerson(player);
        if (!MineStrike.bombDiffusing) {
            if (person.getTeam().equals("CT"))
                if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    Block block = event.getClickedBlock();
                    if (block.getType().equals(Material.TNT)) {
                        MineStrike.bombDiffusing = true;
                        int bombDiffusedTaskID = Bukkit.getScheduler()
                                .scheduleSyncDelayedTask(p, new BombDiffusedTask(person), 100);
                        MineStrike.bombDiffusedTaskID = bombDiffusedTaskID;
                        MineStrike.diffuser = person;
                    }
                }
        } else {
            if(person.equals(MineStrike.diffuser)){
                MineStrike.bombDiffusing = false;
                Bukkit.getScheduler().cancelTask(MineStrike.bombDiffusedTaskID);
                Bukkit.broadcastMessage(ChatColor.RED + "Bomb diffusal cancelled.");
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Location to = event.getTo();
        Location from = event.getFrom();
        if(to.equals(from)){
            return;
        }
        Person person = MineStrike.teams.findPerson(event.getPlayer());

        if(person.equals(MineStrike.diffuser)){
            MineStrike.bombDiffusing = false;
            Bukkit.getScheduler().cancelTask(MineStrike.bombDiffusedTaskID);
            Bukkit.broadcastMessage(ChatColor.RED + "Bomb diffusal cancelled.");
        }
    }
}
