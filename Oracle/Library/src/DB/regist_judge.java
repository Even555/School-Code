package DB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class regist_judge
 */
@WebServlet("/regist_judge")
public class regist_judge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regist_judge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入到servlet的注册判定");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String realName=request.getParameter("realname");
		String userName=request.getParameter("username");
		//先判断是否被注册过
		String sql1="select*from users where userName='"+userName+"'";
		database.connect();
		try {
			ResultSet rs=database.getpst(sql1).executeQuery();
			//
			if(rs.next()){
				System.out.println("用户名已被注册");
				database.close_pst();
				PrintWriter out=response.getWriter();
				out.println("<script>alert('用户名已被注册');window.history.go(-1)</script>");
			}
			//
			else{
				String password=request.getParameter("password1");
				String telephone=request.getParameter("telephone");
				String sql2="insert into users (realName,userName,password,telephone,sysRole)values('"+realName+"','"+userName+"','"+password+"','"+telephone+"',0);";
				System.out.println(sql2);
				database.getpst(sql2).executeUpdate();
				database.close_pst();
				System.out.println("注册成功");
				PrintWriter out=response.getWriter();
				out.println("<script>alert('注册成功!');location='index1.jsp';</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
