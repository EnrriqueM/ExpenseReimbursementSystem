package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
	private static ConnectionUtility cu = null;

	//AWS Credentials
	private String url;
	private String username;
	private String password;
	
	/**
	 * This method should read in the "database.properties" file and load
	 * the values into the Properties variable
	 */
	private ConnectionUtility() {
		//Reference for .properties file
		ClassLoader classLoader = getClass().getClassLoader();

		//Pass file into input stream
		InputStream is = classLoader.getResourceAsStream("resources/database.properties");

		//Used for loading the properties
		Properties p = new Properties();

		try
		{
			p.load(is);
			this.url = (String) p.getProperty("url");
			this.username = (String) p.getProperty("usr");
			this.password = (String) p.getProperty("pswd");
		}
		catch (IOException e)
		{
			System.out.println("Failed to load properties file");
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtility getConnectionUtil() {
		if(cu == null)
			cu = new ConnectionUtility();
		return cu;
	}
	
	/**
	 * This method should create and return a Connection object
	 * @return a Connection to the database
	 */
	public Connection getConnection() {
		// Hint: use the Properties variable to setup your Connection object
		Connection conn = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(this.url, this.username, this.password);
		}
		catch (SQLException e)
		{
			System.out.println("Failed to establish connection");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

}
