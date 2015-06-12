package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGiveMoney implements CommandExecutor
{


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(!(sender instanceof Player))
			return false;

		Person p = MineStrike.team.findPerson(Bukkit.getPlayer(args[0]));
		p.setMoney(p.getMoney() + Integer.parseInt(args[1]));

		return true;
	}
}
