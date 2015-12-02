package com.mcworldmap.play.MineStrike.PlayerData;

import com.mcworldmap.play.MineStrike.MineStrike;
import com.mcworldmap.play.MineStrike.Util.ItemFactory;
import com.mcworldmap.play.MineStrike.Util.RayTracer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Person extends RayTracer {
    private int score;
    private int roundKills;
    private int kills;
    private int deaths;
    private int money;
    private int teamKills;
    private String team;
    private boolean alive;

    public Person(Player player, String team) {
        super(player);
        this.team = team;
        score = 0;
        kills = 0;
        roundKills = 0;
        deaths = 0;
        money = 800;
        teamKills = 0;
        alive = true;
    }

    public String toString() {
        if (this.alive)
            return "Alive | " + player.getDisplayName() + " | $" + money + " | " + kills + " | " + deaths + " | " + score;
        else
            return "Dead | " + player.getDisplayName() + " | $" + money + " | " + kills + " | " + deaths + " | " + score;

    }

    /**
     * Resets players for new round
     * Gives pistol if they don't have one
     */
    public void respawnPlayer(boolean giveBomb) {
        String team = MineStrike.teams.getTeam(getPlayer());
        Location location = MineStrike.config.getRandCTSpawn();
        if (team.equalsIgnoreCase("T")) {
            location = MineStrike.config.getRandTSpawn();
        }
        //Teleports them to their teams spawn, resets their health and hunger
        player.teleport(location);
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(4);
        boolean givePistol = true;
        //Resets ammo, checks  if they have a pistol
        for (ItemStack item : player.getInventory()) {
            if (item == null) continue;
            if (!item.getType().equals(Material.BOW)) continue;
            Item gun = Item.getItem(item.getItemMeta().getDisplayName());
            if (gun == null) continue;
            item.setDurability((short) (item.getType().getMaxDurability() - gun.getAmmo()));
            if (ChatColor.stripColor(item.getItemMeta().getLore().get(0)).equalsIgnoreCase("Pistol"))
                givePistol = false;
        }
        //Gives them a pistol if they don't have one
        if (givePistol) {
            if (team.equals("CT")) creditItem(player.getDisplayName(), "USP");
            if (team.equals("T")) creditItem(player.getDisplayName(), "Glock");
        }
        if (giveBomb) {
            creditItem(player.getDisplayName(), "C4");
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean isAlive) {
        alive = isAlive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getMoney() {
        return money;
    }

    public void creditItem(String owner, String itemName) {
        ItemStack item = ItemFactory.createItem(owner, itemName);
        player.getInventory().addItem(item);
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setMoney(int money) {
        this.money = money;
        if (this.money > 16000)
            this.money = 16000;
        updateExpBar();
    }

    public void addMoney(int money) {
        this.money = this.money + money;
        if (this.money > 16000)
            this.money = 16000;
        player.sendMessage(ChatColor.GREEN + "+$" + money);
        player.sendMessage(ChatColor.WHITE + "You now have $" + money);
        updateExpBar();
    }

    public void addPoints(int points) {
        score += points;
    }

    public Player getPlayer() {
        return player;
    }

    private void updateExpBar() {
        player.setLevel(getMoney());
    }

    public int getRoundKills() {
        return roundKills;
    }

    public void setRoundKills(int roundKills) {
        this.roundKills = roundKills;
    }

    public void incrementTeamKills() {
        this.teamKills += 1;
    }

    public int getTeamKills() {
        return teamKills;
    }

    public void resetTeamKills() {
        this.teamKills = 0;
    }
}
