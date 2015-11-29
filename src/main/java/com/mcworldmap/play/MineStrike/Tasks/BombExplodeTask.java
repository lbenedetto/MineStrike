package com.mcworldmap.play.MineStrike.Tasks;

import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class BombExplodeTask implements Runnable {

    Person planter;
    Block bomb;

    public BombExplodeTask(Person planter, Block bomb) {
        this.planter = planter;
        this.bomb = bomb;
    }

    @Override
    public void run() {
        RoundManager.newRound(planter.getTeam(), "planting the bomb");
        bomb.setType(Material.AIR);
        Location loc = bomb.getLocation();
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                "particle hugeexplosion " + x + " " + y + " " + z + " 10 10 10 0 50 force");
        World world = bomb.getWorld();
        world.createExplosion(loc, 0.0f);
        Collection<Entity> nearExplosion = world.getNearbyEntities(loc, 10, 10, 10);

        nearExplosion.stream().filter(e -> e instanceof Player).forEach(e -> {
            Player p = (Player) e;
            p.damage(20, planter.getPlayer());
        });
    }
}
