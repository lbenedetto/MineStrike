package com.mcworldmap.play.MineStrike.commands;
import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
public class CmdScoreboard implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
	{
		sender.sendMessage("State | Name | Money | Kills | Deaths | Score");
		sender.sendMessage("Terrorists:");
		for (Person p : MineStrike.team.getT())
			if(p != null) sender.sendMessage(p.toString());
		sender.sendMessage("Counter-Terrorists:");
		for (Person p : MineStrike.team.getCT())
			if(p != null) sender.sendMessage(p.toString());
		return true;
	}
}
