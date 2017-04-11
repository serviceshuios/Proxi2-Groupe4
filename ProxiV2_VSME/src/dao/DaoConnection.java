package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnection {

	private static final String PILOTE = "com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost:3306/bddclients";
	private static final String USER="root";
	private static final String mdp="";
	private static Connection conn=null;
	
	public static Connection getConnection(){
		if(conn==null)
		{
		
				try {
					Class.forName(PILOTE);
					conn = DriverManager.getConnection(URL,USER,mdp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else
		{
			return conn;
		}
		return conn;
	}
	
	
	public static void closeConnection()
	{
		if(conn!=null)
		{
		try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
