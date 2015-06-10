package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Item;
import com.mcworldmap.play.MineStrike.Tasks.FireRate;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArrowFire implements Listener {

    @EventHandler
    public void onArrowFire(PlayerInteractEvent event) {
        Action a = event.getAction();
        if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().getItemInHand().getType().equals(Material.BOW)) {
            ItemStack item = event.getPlayer().getItemInHand();
            Item gun = Item.getItem(item.getItemMeta().getDisplayName());

            double fireRate = gun.getFireRate();
            double velChange = gun.getRange();

            if (event.getPlayer().getItemInHand().getType().equals(Material.BOW)) {
                if (item.getDurability() >= item.getType().getMaxDurability()) {
                    event.setCancelled(true);
                    return;
                }
                if(!MineStrike.coolDown.contains(event.getPlayer())) {
                    Arrow e = (Arrow) event.getPlayer().launchProjectile(Arrow.class);
                    e.setVelocity(e.getVelocity().multiply(velChange));


                    item.setDurability((short) (item.getDurability() + 1));

                    event.setCancelled(true);
                    MineStrike.coolDown.add(event.getPlayer());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new FireRate(event.getPlayer()), Math.round(fireRate) * 20);
                }
            }
        }
    }
}
