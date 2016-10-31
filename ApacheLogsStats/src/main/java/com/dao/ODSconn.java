package com.dao;

import java.util.*;
import java.sql.*;

public class ODSconn 
{
	private Properties props;
	private Connection dbconn;
	
	public ODSconn()
	{
		props = new Properties();
		dbconn = null;
	}
	
	public void connect2DB()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			String userName = props.getProperty("db.oracle.connect.username");
			String passWord =  props.getProperty("db.oracle.connect.password");
			String url		=  props.getProperty("db.oracle.connect.url");
			
			dbconn =DriverManager.getConnection(url, userName,passWord);  
			
			}
			catch (ClassNotFoundException e) 
			{
				System.out.println("Unable to locate OracleDriver");
				e.printStackTrace();
				return;
		
			}catch (SQLException e){
				System.out.println("SQL Exception123");
				e.printStackTrace();
			return;
			}

			
			System.out.println("Successfully connected to data base");
	}
}

