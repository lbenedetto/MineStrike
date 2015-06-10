package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.PlayerData.Item;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ArrowFire implements Listener {

    @EventHandler
    public void onArrowFire(PlayerInteractEvent event) {
        Action a = event.getAction();
        if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = event.getPlayer().getItemInHand();
            if (event.getPlayer().getItemInHand().getType().equals(Material.BOW)) {
                if (item.getDurability() >= item.getType().getMaxDurability()) {
                    event.setCancelled(true);
                    return;
                }
                Arrow e = (Arrow) event.getPlayer().launchProjectile(Arrow.class);
                Vector currentVel = e.getVelocity();


                for (Item i : Item.values()) {
                    if (i.name().equalsIgnoreCase(ChatColor.stripColor(item.getItemMeta().getDisplayName()))) {
                        switch (i) {
                            case ZEUS:
                                e.setVelocity(currentVel.multiply(.5));
                                break;
                            default:
                                break;
                        }
                    }
                }

                item.setDurability((short) (item.getDurability() + 1));

                event.setCancelled(true);
            }
        }
    }
}
