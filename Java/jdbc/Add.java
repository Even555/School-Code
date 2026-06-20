package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Add 
{
	public void add(Connection con) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		String sql = "insert into student(sno,name,sex,age,address,tel,class)values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		System.out.print("请输入学号：");
		stmt.setInt(1,sc.nextInt());
		System.out.print("请输入姓名：");
		stmt.setString(2, sc.next());
		System.out.print("请输入性别：");
		stmt.setString(3, sc.next());
		System.out.print("请输入年龄：");
		stmt.setInt(4, sc.nextInt());
		System.out.print("请输入地址：");
		stmt.setString(5, sc.next());
		System.out.print("请输入电话号码：");
		stmt.setInt(6, sc.nextInt());
		System.out.print("请输入班级：");
		int cla = sc.nextInt();
		stmt.setInt(7, cla);
		Updatecl up1 = new Updatecl();
		up1.auto(con,cla,1);
		stmt.executeUpdate();
		System.out.println("添加成功");
	}
}
