package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.NadeKillCreditor;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

@SuppressWarnings("unused")
public class onDeath implements Listener {
    /**
     * Code runs whenever a player dies.
     */
    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (!MineStrike.isGameActive)
            return;
        Bukkit.getLogger().info("Death detected");
        Player prey = event.getEntity();
        Player predator = null;
        Entity e1 = event.getEntity().getKiller();
        assert e1 != null;
        //If the killer is an arrow, find who shot the arrow
        if (e1 instanceof Arrow) {
            Arrow a = (Arrow) e1;
            if (a.getShooter() instanceof Player)
                predator = (Player) a.getShooter();
        } else {
            //Else, they are a player
            predator = (Player) e1;
        }
        Person preyPerson = MineStrike.teams.findPerson(prey);
        Person predatorPerson = MineStrike.teams.findPerson(predator);
        event.getDrops().clear();
        Bukkit.getServer().broadcastMessage(prey.getDisplayName() + " was killed by " + predator.getDisplayName());
        if (predator.getDisplayName().equals(prey.getDisplayName()))
            //If the kill was a suicide, dock their score
            predatorPerson.setScore(predatorPerson.getScore() - 1);
        else if (MineStrike.teams.getTeam(predator).equals(MineStrike.teams.getTeam(prey))) {
            //If the kill was a team kill, dock their score, increment their teamkill counter
            //If it exceeds the size of their team, put them in ?jail?
            predatorPerson.setScore(predatorPerson.getScore() - 1);
            predatorPerson.incrementTeamKills();
            if (predatorPerson.getTeamKills() >= MineStrike.config.getInt("teamsize")) {
                //Put player in jail
            }
        } else if (prey.getLastDamageCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
            //if the player dies by fire, and it was by a players molotov.
            //Get the location of the prey
            Location playerLocation = prey.getLocation();
            //Get the block that the prey is standing on
            Block playerBlock = playerLocation.getBlock();
            //Loop through all NadeKillCreditor classes in the killers arraylist
            for (NadeKillCreditor kills : MineStrike.killers) {
                //Loop through all locations in the getLocations() array list in the kills variable.
                //If they were standing on a block of fire created by another player, then update the kills
                kills.getLocations().stream().filter(location -> location.getBlock().equals(playerBlock)).forEach(location -> {
                    //If they were standing on a block of fire created by another player, then update the kills
                    updatePlayerVariables(preyPerson, MineStrike.teams.findPerson(kills.getPlayer()));
                });
            }
        } else
            //Else it was a normal PvP kill
            //Update the appropriate variables
            updatePlayerVariables(preyPerson, predatorPerson);
        //Teleport the killed player to their teams spawnbox
        prey.setHealth(20.0D);
        if (MineStrike.teams.getTeam(prey.getPlayer()).equals("T"))
            prey.getPlayer().teleport(MineStrike.config.getLocation("TBox"));
        else
            prey.getPlayer().teleport(MineStrike.config.getLocation("CTBox"));
        //Remove any dropped XP
        event.setDroppedExp(0);
        //Check if one of the teams is dead, if so, start a new round
        if (MineStrike.teams.isTeamDead("T")) {
            RoundManager.newRound("CT");
            return;
        }
        if (MineStrike.teams.isTeamDead("CT")) {
            RoundManager.newRound("T");
            return;
        }
    }

    public void updatePlayerVariables(Person preyPerson, Person predatorPerson) {
        preyPerson.setDeaths(preyPerson.getDeaths() + 1);
        preyPerson.setAlive(false);
        predatorPerson.setKills(predatorPerson.getKills() + 1);
        predatorPerson.setRoundKills(predatorPerson.getRoundKills() + 1);
        predatorPerson.setScore(predatorPerson.getScore() + 2);
        predatorPerson.addMoney(700);
    }
}
