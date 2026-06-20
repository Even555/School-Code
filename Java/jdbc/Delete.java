package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
	public void del(Connection con) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		PreparedStatement statement = null;
		String sql = "delete from student where name=?";
		statement = con.prepareStatement(sql);
		System.out.print("请输入姓名:");
		String st = sc.next();
		statement.setString(1, st);
		String sq2 = "select * from student where name=?";
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		stmt1 = con.prepareStatement(sq2);
		stmt1.setString(1,st);
		rs = stmt1.executeQuery();
		int b = 0;
		while(rs.next())
		{
			b = rs.getInt("class");
		}
		Updatecl up1 = new Updatecl();
		up1.auto(con,b,0);
		statement.executeUpdate();
		System.out.print("删除成功");
		try {
			if(statement!=null)
			{
				statement.close();
				statement = null;
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
