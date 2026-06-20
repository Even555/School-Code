package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc {
	public static void main(String[] args) throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		Connect conn = new Connect();
		Connection con = conn.getcon();
		//Add ad = new Add();
		//ad.add(con);
		//Delete de = new Delete();
		//de.del(con);
		//Modify mo = new Modify();
		//mo.change(con);
		//Find fi = new Find();
		//fi.search(con);
		//Addcl adcl = new Addcl();
		//adcl.add(con);
		Updatecl up = new Updatecl();
		up.change(con);
		A a  = new A();
		a.a(con);
		sc.close();
		conn.close(con);
	}
}
