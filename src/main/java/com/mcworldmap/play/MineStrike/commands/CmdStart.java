package com.mcworldmap.play.MineStrike.commands;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.PlayerData.Person;
import com.mcworldmap.play.MineStrike.Tasks.NextRound;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class CmdStart implements CommandExecutor {
    /**
     * Starts the game
     *
     * @return true if ran successfully
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        for(Person p : MineStrike.teams.allPlayers){
            Bukkit.getLogger().info("Tried to start game with partially filled teams");
            if(p==null)return false;
        }
        if (args[0].equalsIgnoreCase("deathmatch")) {
            MineStrike.gamemode = "deathmatch";
            return true;
        } else if (args[0].equalsIgnoreCase("competitive")) {
            MineStrike.gamemode = "competitive";
            //Sets up the ScoreBoard
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Team T = board.registerNewTeam("T");
            Team CT = board.registerNewTeam("CT");
            //Sets up nametags
            T.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
            CT.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
            Objective objective = board.registerNewObjective("showhealth", "health");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            objective.setDisplayName("/ 20");
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.setScoreboard(board);
                online.setHealth(online.getHealth());
            }
            //Resets money and schedules next round
            MineStrike.teams.resetMoney();
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineStrike"), new NextRound(1), 1);
            return true;
        }
        return false;
    }
}
