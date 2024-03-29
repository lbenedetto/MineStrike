package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import sun.rmi.server.MarshalInputStream;

import java.util.Collection;

public class BombExplodeTask implements Runnable {

    private final Person planter;
    private final Block bomb;

    public BombExplodeTask(Person planter, Block bomb) {
        this.planter = planter;
        this.bomb = bomb;
    }

    @Override
    public void run() {
        RoundManager.newRound(planter.getTeam(), "planting the bomb");
        planter.addPoints(2);
        bomb.setType(Material.AIR);
        Bukkit.getScheduler().cancelTask(MineStrike.bombTimerTaskID);
        Bukkit.getScheduler().cancelTask(MineStrike.bombDiffuseDisplayTaskID);
        MineStrike.bombTimer = 45;
        Location loc = bomb.getLocation();
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "particle hugeexplosion " + x + " " + y + " " + z + " 30 30 30 0 50 force");
        World world = bomb.getWorld();
        world.createExplosion(loc, 0.0f);
        Collection<Entity> nearExplosion = world.getNearbyEntities(loc, 13, 13, 13);
        for (Entity e : nearExplosion) {
            if (e instanceof Player) {
                Player p = (Player) e;
                //RIP Player p
                p.damage(99, planter.getPlayer());
            }
        }
        nearExplosion = world.getNearbyEntities(loc, 20, 20, 20);
        for (Entity e : nearExplosion) {
            if (e instanceof Player) {
                Player p = (Player) e;
                p.damage(19, planter.getPlayer());
            }
        }
        nearExplosion = world.getNearbyEntities(loc, 30, 30, 30);
        for (Entity e : nearExplosion) {
            if (e instanceof Player) {
                Player p = (Player) e;
                p.damage(10, planter.getPlayer());
            }
        }
    }
}
