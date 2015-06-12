package com.mcworldmap.play.MineStrike.network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
		String query = "CREATE TABLE IF NOT EXISTS playerdata (username VARCHAR(256), kills VARCHAR(256), deaths VARCHAR(256), wins VARCHAR(256), losses VARCHAR(256)";

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
}
