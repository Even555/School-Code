package web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import DB.database;
import domain.User;
import utils.DataSourceUtils;
import web.servlet.base.BaseServlet;

/**
 * 用户模块
 */
@WebServlet("/User")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 跳转到注册页面
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "regist.jsp";
	}
	/*
	 * 跳转到登陆页面
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "index1.jsp";
	}
	/*
	 * 登陆判定
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入servlet的login方法");
		database.connect();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sql="select*from users where userName ='"+username+"'and password='"+password+"'";
		
		System.out.println(sql);
		
		try {
			database.setst();
			ResultSet rs=database.getst().executeQuery(sql);
			//登陆成功
			if(rs.next()){
				System.out.println("登陆成功");
				String realname=rs.getString("realname");
				int userID=rs.getInt("userID");
				int sisrole=rs.getInt("sysRole");
				String telephone=rs.getString("telephone");
				//创建新用户
				User user =new User();
				BeanUtils.populate(user, request.getParameterMap());
				user.setRealname(realname);
				user.setUserID(userID);
				user.setSysrole(sisrole);
				user.setTelephone(telephone);
				//保存用户登录状态！！！
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("error", "no");
				//重定向到首页
				response.sendRedirect(request.getContextPath());
				database.close_st();
				return null;
			}
			//登陆失败
			else{
				System.out.println("登陆失败");
				request.getSession().setAttribute("error", "yes");
				database.getst().close();
				return("index1.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
	}
	/*
	 * 退出登录
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//清空会话内容
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
		return null;
	}
	/*
	 * 管理员登陆判定
	 */
	public String login2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入servlet的login方法");
		database.connect();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sql="select*from admin where userName ='"+username+"'and password='"+password+"'";
		
		System.out.println(sql);
		
		try {
			database.setst();
			ResultSet rs=database.getst().executeQuery(sql);
			//登陆成功
			if(rs.next()){
				System.out.println("登陆成功");
				//保存用户登录状态！！！
				request.getSession().setAttribute("admin", "ok");
				request.getSession().setAttribute("error", "no");
				//重定向到首页
				response.sendRedirect(request.getContextPath()+"/admin/admin_index.jsp");
				database.disconnect();
				return null;
			}
			//登陆失败
			else{
				System.out.println("登陆失败");
				request.getSession().setAttribute("error", "yes");
				database.disconnect();
				return("index2.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
	}
	public String changeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入changeInfo方法");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String realname=request.getParameter("name");
			String telephone=request.getParameter("tel");
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			user.setRealname(realname);
			user.setTelephone(telephone);
			String sql="update users set realname='"+realname+"', telephone='"+telephone+"'where userID="+userID;
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
			response.sendRedirect("user_info.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
		return null;
	}
	/*
	 * 提出会员申请
	 */
	public String askVip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入askVip方法");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			user.setSysrole(2);
			String sql="update users set sysRole=2 where userID="+userID;
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
			response.getWriter().println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
		return null;
	}
}
