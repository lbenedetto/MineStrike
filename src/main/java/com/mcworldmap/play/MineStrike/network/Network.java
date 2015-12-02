package com.mcworldmap.play.MineStrike.network;

import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.entity.Player;

import java.sql.*;

public class Network {
    private Connection c;
    final String ip, database, password, username, url;

    /**
     * get an instance of this class
     *
     * @return returns this class
     */
    public Network getInstance() {
        return this;
    }

    /**
     * This is the constructor for the Network class, which initializes the class with
     * the appropriate data to start a MySQL Connection
     *
     * @param ip       the ip of the database
     * @param database the database in which the tables/data is to be stored
     * @param username the username of the user that has access to the database
     * @param password the password of the user that has access to the database
     */
    public Network(String ip, String database, String username, String password) {
        this.ip = ip;
        this.database = database;
        this.password = password;
        this.username = username;
        this.url = "jdbc:mysql://" + ip + ":3306/" + database;
    }

    /**
     * Create a table in the MySQL DB for storage if it doesn't exist
     */
    public void init() {
        String query = "CREATE TABLE IF NOT EXISTS playerdata (username VARCHAR(256), kills VARCHAR(256), deaths VARCHAR(256), wins VARCHAR(256), losses VARCHAR(256))";

        try {
            Statement s = c.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * connect to the db and store the connection the variable 'c'
     */
    public void connect() {
        try {
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return connection
     */
    public Connection getConnection() {
        return c;
    }


    /**
     * Updates a players score based on the data you provided.
     * Takes Person, and a boolean detailing if they won.
     *
     * @param p     Takes a Person
     * @param isWin Did the Person win?
     * @return a boolean based on if it could update the player score, false means it couldn't
     */
    public boolean updatePlayerScore(Person p, Boolean isWin) {
        String query = "UPDATE playerdata SET kills = ?, deaths = ?, wins = ?, losses = ? WHERE username = ?";

        int kills = getTotalKills(p) + p.getKills();
        int deaths = getTotalDeaths(p) + p.getDeaths();
        int wins = getWins(p);
        int losses = getLosses(p);

        if (isWin == null)
            wins += 1;
        if (isWin)
            wins += 1;
        else
            losses += 1;

        PreparedStatement ps = getPreparedStatement(query);
        try {
            ps.setInt(1, kills);
            ps.setInt(2, deaths);
            ps.setInt(3, wins);
            ps.setInt(4, losses);
            ps.setString(5, p.getPlayer().getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get the wins of a provided person from the MySQL DB
     *
     * @param p Takes a Person
     * @return 0 if there are no wins, or if it cannot find the person in the db.
     */
    private int getWins(Person p) {
        String query = "SELECT * FROM playerdata WHERE username = ?";

        try {
            PreparedStatement ps = getPreparedStatement(query);
            ps.setString(1, p.getPlayer().getName());
            ResultSet res = ps.executeQuery();

            if (res.next())
                return res.getInt("wins");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get the total kills of a Person from the MySQL DB
     *
     * @param p Takes a Person
     * @return 0 if there are no kills, or if it cannot find the person in the db.
     */
    private int getTotalKills(Person p) {
        String query = "SELECT * FROM playerdata WHERE username = ?";

        try {
            PreparedStatement ps = getPreparedStatement(query);
            ps.setString(1, p.getPlayer().getName());
            ResultSet res = ps.executeQuery();

            if (res.next())
                return res.getInt("kills");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get the total deaths of a Person from the MySQL DB
     *
     * @param p Takes a Person
     * @return 0 if there are no deaths, or if it cannot find the person in the db.
     */
    private int getTotalDeaths(Person p) {
        String query = "SELECT * FROM playerdata WHERE username = ?";

        try {
            PreparedStatement ps = getPreparedStatement(query);
            ps.setString(1, p.getPlayer().getName());
            ResultSet res = ps.executeQuery();

            if (res.next())
                return res.getInt("deaths");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get the total losses of a Person from the MySQL DB
     *
     * @param p Takes a Person
     * @return 0 if there are no losses, or if it cannot find the person in the db.
     */
    private int getLosses(Person p) {
        String query = "SELECT * FROM playerdata WHERE username = ?";

        try {
            PreparedStatement ps = getPreparedStatement(query);
            ps.setString(1, p.getPlayer().getName());
            ResultSet res = ps.executeQuery();

            if (res.next())
                return res.getInt("losses");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @return a MySQL statement created from the connection
     */
    public Statement getStatement() {
        try {
            return c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param query pass in a Query to be executed.
     * @return the prepared statement which is sanitized of all malicious characters
     */
    private PreparedStatement getPreparedStatement(String query) {
        try {
            return c.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param playername This is the name of the player.
     * @return returns a boolean value based on if the player exists in the database
     */
    private boolean doesExist(String playername) {
        try {
            PreparedStatement ps = c
                    .prepareStatement("SELECT * FROM playerdata WHERE username = ?");
            ps.setString(1, playername);
            ResultSet res = ps.executeQuery();
            return res.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param player Create player data for the given player
     */
    public void createPlayerData(Player player) {
        if (!doesExist(player.getName())) {
            String username = player.getName();
            String query = "INSERT IGNORE INTO playerdata values (?,?,?,?,?)";
            try {
                PreparedStatement ps = getPreparedStatement(query);
                ps.setString(1, username);
                ps.setInt(2, 0);
                ps.setInt(3, 0);
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
