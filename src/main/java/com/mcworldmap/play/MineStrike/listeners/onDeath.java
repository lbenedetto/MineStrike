package com.mcworldmap.play.MineStrike.listeners;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.DelayedFlash;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import com.mcworldmap.play.MineStrike.Util.RoundManager;
import com.mcworldmap.play.MineStrike.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class onDeath implements Listener
{
	@EventHandler
	public void death(PlayerDeathEvent event)
	{
		String tv = Bukkit.getVersion();
		int index = tv.indexOf("(MC: ")+5;
		int end = tv.indexOf(")", index);
		String version = tv.substring(index, end);
		try
		{
			Class packet = Class.forName("net.minecraft.server.v"+version.replace(".", "_") +".Packet205ClientCommand");
			Object name = packet.getConstructor(new Class[0]).newInstance(new Object[0]);
			Field a = packet.getDeclaredField("a");
			a.setAccessible(true);
			a.set(name, 1);
			Object nmsPlayer = Class.forName("org.bukkit.craftbukkit.v"+version.replace(".", "_")+".entity.CraftPlayer").getMethod("getHandle", new Class[0])
					.invoke(event.getEntity(), new Object[0]);
			Field con = Class.forName("net.minecraft.server.v"+version.replace(".", "_") +".EntityPlayer").getDeclaredField("playerConnection");
			con.setAccessible(true);
			Object handle = con.get(nmsPlayer);
			packet.getDeclaredMethod("handle", Class.forName("net.minecraft.server.v"+version.replace(".", "_") +".Connection"))
					.invoke(name, handle);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
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
		if (MineStrike.team.getTeam(prey.getPlayer()).equals("T"))
			prey.getPlayer().teleport(MineStrike.spawnpoint.getTBox());
		else
			prey.getPlayer().teleport(MineStrike.spawnpoint.getCTBox());
		if (MineStrike.team.isTTeamDead())
		{
			RoundManager.newRound("CT");
		}
		if (MineStrike.team.isCTTeamDead())
		{
			RoundManager.newRound("T");
		}
	}
}
