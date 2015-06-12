package com.mcworldmap.play.MineStrike.network;

import com.mcworldmap.play.MineStrike.PlayerData.Person;
import org.bukkit.entity.Player;

import java.sql.*;

public class Network
{
	private Connection c;
	String ip, database, password, username;
	String url;

	public Network getInstance()
	{
		return this;
	}

	public Network(String ip, String database, String username, String password)
	{
		this.ip = ip;
		this.database = database;
		this.password = password;
		this.username = username;
		this.url = "jdbc:mysql://" + ip + ":3306/" + database;
	}

	public void init()
	{
		String query = "CREATE TABLE IF NOT EXISTS playerdata (username VARCHAR(256), kills VARCHAR(256), deaths VARCHAR(256), wins VARCHAR(256), losses VARCHAR(256))";

		try
		{
			Statement s = c.createStatement();
			s.executeUpdate(query);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void connect()
	{
		try
		{
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConnection()
	{
		return c;
	}

	public boolean updatePlayerScore(Person p, boolean isWin)
	{
		String query = "UPDATE playerdata SET kills = ?, deaths = ?, wins = ?, losses = ? WHERE username = ?";

		int kills = getTotalKills(p) + p.getKills();
		int deaths = getTotalDeaths(p) + p.getDeaths();
		int wins = getWins(p);
		int losses = getLosses(p);

		if(isWin)
			wins+=1;
		else
			losses+=1;

		PreparedStatement ps = getPreparedStatement(query);
		try
		{
			ps.setInt(0, kills);
			ps.setInt(1, deaths);
			ps.setInt(2, wins);
			ps.setInt(3, losses);
            ps.executeUpdate(query);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public int getWins(Person p)
	{
		String query = "SELECT * FROM playerdata WHERE username = " + p.getPlayer().getName();

		try
		{
			ResultSet res = getStatement().executeQuery(query);

			if(res.next())
				return res.getInt("wins");
			return 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public int getTotalKills(Person p)
	{
		String query = "SELECT * FROM playerdata WHERE username = " + p.getPlayer().getName();

		try
		{
			ResultSet res = getStatement().executeQuery(query);

			if(res.next())
				return res.getInt("kills");
			return 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public int getTotalDeaths(Person p)
	{
		String query = "SELECT * FROM playerdata WHERE username = " + p.getPlayer().getName();

		try
		{
			ResultSet res = getStatement().executeQuery(query);

			if(res.next())
				return res.getInt("deaths");
			return 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public int getLosses(Person p)
	{
		String query = "SELECT * FROM playerdata WHERE username = " + p.getPlayer().getName();

		try
		{
			ResultSet res = getStatement().executeQuery(query);

			if(res.next())
				return res.getInt("losses");
			return 0;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public Statement getStatement()
	{
		try
		{
			return c.createStatement();
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public PreparedStatement getPreparedStatement(String query)
	{
		try
		{
			return c.prepareStatement(query);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

    public void createPlayerData(Player player)
    {
        String username = player.getName();
        String query = "INSERT INTO playerdata VALUES(`" + username + "`, `0`, `0`, `0`, `0`)";
        try {
            getStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
