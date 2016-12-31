package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.javafx.collections.SetListenerHelper;

public class DBConnector {
	
	private static DBConnector instance;
	
	private Connection c;
	private Statement statement;
	
	private DBConnector(){
		
	}
	
	public boolean connect(String driver, String url){
		
		if(statement != null){
			try {
				statement.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(url);
			statement = c.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c != null ? true : false;
	}
	
	public int excuteUpdate(String update){
		
		if(c == null) return -1;
		
		try {
			return statement.executeUpdate(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return -1;
		}
		
	}
	
	public ResultSet excuteQuery(String query){
		
		if(c == null) return null;
		
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	public static DBConnector getInstance(){
		if(instance == null) instance = new DBConnector();
		return instance;
	}
	
}
