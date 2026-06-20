package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Modify {
	public void change(Connection con) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		String sql = "update student set name = ? where name = ?";
		PreparedStatement statement = null;
		statement = con.prepareStatement(sql);
		System.out.print("请要输入修改的姓名：");
		statement.setString(2, sc.next());
		System.out.print("请输入修改后的姓名：");
		statement.setString(1, sc.next());
		statement.executeUpdate();
		sc.close();
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
