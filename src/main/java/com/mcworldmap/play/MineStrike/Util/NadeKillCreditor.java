package com.mcworldmap.play.MineStrike.Util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class NadeKillCreditor {
    private Player player;
    private ArrayList<Location> locations;

    public NadeKillCreditor(Player player, ArrayList<Location> locations) {
        this.player = player;
        this.locations = locations;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}

