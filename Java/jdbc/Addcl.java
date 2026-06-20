package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Addcl {
	public void add(Connection con) throws SQLException
	{
		Statement stmt = null;
		stmt = con.createStatement();
		String sql = "insert into class(cno,cname)values('5','5')";
		stmt.execute(sql);
		try {
			if(stmt!=null)
			{
				stmt.close();
				stmt = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
