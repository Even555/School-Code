package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Deletecl {
	public void del(Connection con) throws SQLException
	{
		Statement statement = null;
		statement = con.createStatement();
		String sql = "delete from class where cno=1";
		statement.execute(sql);
		try {
			if(statement!=null)
			{
				statement.close();
				statement = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
