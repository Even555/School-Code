package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public Connection getcon() throws SQLException
	{
		Connection connect = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jdbc";
			String user = "root";
			String password = "55555";
			connect = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}
	public void close(Connection con) {
		try {
			if(con!=null)
			{
				con.close();
				con = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
