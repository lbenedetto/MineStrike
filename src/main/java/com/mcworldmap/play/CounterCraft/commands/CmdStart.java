package com.mcworldmap.play.CounterCraft.commands;

import com.mcworldmap.play.CounterCraft.CounterCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdStart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("some.pointless.permission")) {
            if (CounterCraft.ts == 5 && CounterCraft.cts == 5){
                //TODO: Add stuff that starts the match
                //Teleport players to their spawns
            }else{
                sender.sendMessage("Not enough players");
            }
        } else {
            return false;
        }
        return false;
    }
}
