package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Updatecl {
	public void change(Connection con) throws SQLException
	{
		Statement statement = null;
		statement = con.createStatement();
		String sql = "update class set cname ='2°à'where cno=2";
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
	public void auto(Connection con,int a,int j) throws SQLException
	{
		String sql = "update class set sum = ? where cno=?";
		String sq2 = "select * from class where cno=?";
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		stmt = con.prepareStatement(sql);
		stmt1 = con.prepareStatement(sq2);
		stmt1.setInt(1,a);
		rs = stmt1.executeQuery();
		int b = 0;
		while(rs.next())
		{
			b = rs.getInt("sum");
		}
		if(j==1)
		{
			b=b+1;
			stmt.setInt(1, b);
		}
		else
		{	
			b=b-1;
			stmt.setInt(1, b);
		}
		stmt.setInt(2, a);
		stmt.executeUpdate();
		try {
			if(stmt!=null)
			{
				stmt.close();
				stmt = null;
			}
			if(stmt1!=null)
			{
				stmt1.close();
				stmt1 = null;
			}
			if(rs!=null)
			{
				rs.close();
				rs = null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
