package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Find {
	public void search(Connection con) throws SQLException
	{
		Statement statement = null;
		ResultSet rs = null;
		statement = con.createStatement();
		String sql = "select * from student where sno=2";
		rs = statement.executeQuery(sql);
		System.out.println("sno | name | sex | age | address | tel | class |");
		while(rs.next())
		{
			int sno = rs.getInt("sno");
			String name = rs.getString("name");
			String sex = rs.getString("sex");
			String age = rs.getString("age");
			String address = rs.getString("address");
			String tel = rs.getString("tel");
			int cla = rs.getInt("class");
			System.out.println(sno+" | "+name+" | "+sex+" | "+age+" | "+address+" | "+tel+" | "+cla+" | ");
		}
		try {
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
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
