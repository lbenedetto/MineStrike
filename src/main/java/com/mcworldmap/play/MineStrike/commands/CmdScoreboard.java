package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdScoreboard implements CommandExecutor {
    /**
     * Displays a text version of the scoreboard to the sender using private chat
     *
     * @return true if ran successfully
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage("State | Name | Money | Kills | Deaths | Score");
        sender.sendMessage("Terrorists:");
        for (Person p : MineStrike.teams.allPlayers)
            if (p != null && p.getTeam().equals("T")) sender.sendMessage(p.toString());
        sender.sendMessage("Counter-Terrorists:");
        for (Person p : MineStrike.teams.allPlayers)
            if (p != null && p.getTeam().equals("T")) sender.sendMessage(p.toString());
        return true;
    }
}
