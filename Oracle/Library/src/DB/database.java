package DB;
import java.sql.*;
public class database {
	private static Connection connection=null;
	private static Statement statement=null;
	private static PreparedStatement prestatement=null;
	private static ResultSet resultset=null;
	public static void connect(){
		try{
			System.out.println("开始连接数据库");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String ip="jdbc:mysql://localhost:3306/library?serverTimezone=UTC";
			connection=DriverManager.getConnection(ip, "root", "root");
			System.out.println("连接数据库成功");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//判断是否连接
	public static boolean isNon(){
		return connection==null;
	}
	public static void setst(){
		try {
			statement=connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Statement getst() {
		return statement;
	}
	public static PreparedStatement getpst(String sql) {
		try {
			prestatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestatement;
	}
	public static void close_st(){
		if(statement!=null)
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void close_pst(){
		if(prestatement!=null)
			try {
				prestatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static void disconnect(){
		try {
			if(statement!=null)
				statement.close();
			if(prestatement!=null)
				prestatement.close();
			if(resultset!=null)
				resultset.close();
			if(connection!=null)
				connection.close();
			System.out.println("断开数据库");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
