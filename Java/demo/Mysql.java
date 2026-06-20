package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql 

{
	public static void main(String[] args) throws SQLException
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jdbc";
			String user = "root";
			String password = "55555";
			connect = DriverManager.getConnection(url, user, password);
			statement = connect.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally
		{
			if(connect!=null)
			{
				connect.close();
				connect = null;
			}
			if(statement!=null)
			{
				statement.close();
				statement = null;
			}
			if(rs!=null)
			{
				rs.close();
				rs = null;
			}
		}
	}
}
