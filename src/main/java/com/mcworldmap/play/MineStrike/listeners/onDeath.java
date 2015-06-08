package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
@SuppressWarnings("unused")
public class onDeath implements Listener
{
	@EventHandler
	public void death(PlayerDeathEvent event)
	{
		Bukkit.getLogger().info("Death detected");
		Player prey = event.getEntity();
		Player predator = null;

		Entity e1 = event.getEntity().getKiller();
		assert e1 != null;
		if (e1 instanceof Arrow)
		{
			Arrow a = (Arrow) e1;
			if (a.getShooter() instanceof Player)
			{
				predator = (Player) a.getShooter();
			}
		}
		if (e1 instanceof Player)
		{
			predator = (Player) e1;
		}
		Person preyPerson = MineStrike.team.findPerson(prey);
		Person predatorPerson = MineStrike.team.findPerson(predator);
		assert predator != null;
		Bukkit.getServer().broadcastMessage(prey.getDisplayName() + " was killed by " + predator.getDisplayName());
		if (predator.getDisplayName().equals(prey.getDisplayName()))
		{
			predatorPerson.setScore(predatorPerson.getScore() - 1);
		} else
		{
			preyPerson.setDeaths(preyPerson.getDeaths() + 1);
			preyPerson.setAlive(false);
			predatorPerson.setKills(predatorPerson.getKills() + 1);
			predatorPerson.setScore(predatorPerson.getScore() + 2);
			predatorPerson.addMoney(700);

		}

		if (MineStrike.team.isTTeamDead())
		{
			for (Person p : MineStrike.team.getT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Counter-Terrorists Win", "? MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + "for most eliminations");
			for (Person p : MineStrike.team.getCT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Counter-Terrorists Win", "? MVP: " + MineStrike.team.findCTMVP().getPlayer().getName() + "for most eliminations");
			MineStrike.team.respawnCT();
			MineStrike.team.respawnT();
			MineStrike.team.rewardCT(3250);
			MineStrike.team.rewardT(1400);
			MineStrike.team.CTscore += 1;
		}
		if (MineStrike.team.isCTTeamDead())
		{
			for (Person p : MineStrike.team.getT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Terrorists Win", "? MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + "for most eliminations");
			for (Person p : MineStrike.team.getCT())
				Util.sendTitle(p.getPlayer(), 1, 5, 1, "Terrorists Win", "? MVP: " + MineStrike.team.findTMVP().getPlayer().getName() + "for most eliminations");
			MineStrike.team.respawnCT();
			MineStrike.team.respawnT();
			MineStrike.team.rewardCT(1400);
			MineStrike.team.rewardT(3250);
			MineStrike.team.Tscore += 1;
		}
	}
}
