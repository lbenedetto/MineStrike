package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.playerdata.Item;
import com.mcworldmap.play.MineStrike.tasks.FireRate;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
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
        if ((a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().getItemInHand().getType().equals(Material.BOW)) {
            ItemStack item = event.getPlayer().getItemInHand();
            Item gun = Item.getItem(ChatColor.stripColor(item.getItemMeta().getDisplayName()));
            event.setCancelled(true);
            double fireRate = gun.getFireRate();
            double velChange = gun.getRange();

                if (item.getDurability() >= item.getType().getMaxDurability()) {
                    event.setCancelled(true);
                    return;
                }
                if(!MineStrike.coolDown.contains(event.getPlayer())) {
                    //Shotgun spread.
                    if(gun.getType().equalsIgnoreCase("shotgun"))
                    {
                        for (int i = 0; i < 5; i++)
                        {
                            Arrow e = (Arrow) event.getPlayer().launchProjectile(Arrow.class);

                            Vector velocity = e.getVelocity();
                            double speed = velocity.length();
                            Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
                            double spray = 3.5D;

                            e.setVelocity(new Vector(direction.getX() + (Math.random() - 0.5) / spray, direction.getY() + (Math.random() - 0.5) / spray, direction.getZ() + (Math.random() - 0.5) / spray).normalize().multiply(speed));
                        }
                    }else
                    {
                        Arrow e = (Arrow) event.getPlayer().launchProjectile(Arrow.class);
                        e.setVelocity(e.getVelocity().multiply(velChange));
                    }

                    item.setDurability((short) (item.getDurability() + 1));

                    MineStrike.coolDown.add(event.getPlayer());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new FireRate(event.getPlayer()), Math.round(fireRate) * 20);
                }
        }
    }
}
