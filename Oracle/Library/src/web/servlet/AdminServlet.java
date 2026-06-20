package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import utils.DataSourceUtils;
import web.servlet.base.BaseServlet;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/Admin")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 跳转添加新book页面
	 */
	public String newBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		System.out.println("进入newbook");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/admin_addbook.jsp";
	}
	/*
	 * 展示修改完的书本
	 */
	public String showChanged(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		System.out.println("进入到showChanged");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String pname=request.getParameter("pname");
		String pimage=request.getParameter("pimage");
		String pdesc=request.getParameter("pdesc");
		Double price=Double.parseDouble(request.getParameter("price"));
		Double vip_price=Double.parseDouble(request.getParameter("vip_price"));
		String pdate=request.getParameter("pdate");
		String cname = request.getParameter("cname");
		//通过cname获取cid
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql1 = "select cid from category where cname='"+cname+"'";
		System.out.println(sql1);
		String cid=((String) qr.query(sql1, new ScalarHandler()));
		Product newbook=(Product) request.getSession().getAttribute("newbook");
		String pid=newbook.getPid();
		String sql2="update Products set pname='"+pname+"' , pimage='"+pimage+"' , pdesc='"+pdesc+"', price="+price+" , vip_price="+vip_price+" , pdate='"+pdate+"' , cid='"+cid+"' where pid='"+pid+"'";
		System.out.println(sql2);
		qr.update(sql2);
		response.sendRedirect(request.getContextPath()+"/admin/admin_index2.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 创建新书本
	 */
	public String addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("进入到addBook");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String pname=request.getParameter("pname");
			String pimage=request.getParameter("pimage");
			String pdesc=request.getParameter("pdesc");
			Double price=Double.parseDouble(request.getParameter("price"));
			Double vip_price=Double.parseDouble(request.getParameter("vip_price"));
			String pdate=request.getParameter("pdate");
			String cname = request.getParameter("cname");
			//通过cname获取cid
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql1 = "select cid from category where cname='"+cname+"'";
			System.out.println(sql1);
			String cid=((String) qr.query(sql1, new ScalarHandler()));
			//先获取pid
			String sql="select pid from products order by pid+0 DESC limit 1";
			Map<String,Object> map = qr.query(sql,new MapHandler());
			int new_pid=Integer.parseInt((String) map.get("pid"))+1;
			String sql2="insert into products values('"+new_pid+"','"+pname+"','"+pimage+"',"+price+","+vip_price+",'"+pdate+"',0,0,'"+pdesc+"',0,'"+cid+"')";
			System.out.println(sql2);
			qr.update(sql2);
			response.sendRedirect("admin/admin_index2.jsp");
		//newbook.setCname(cname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String showUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入showUser方法");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String sql1="select* from users";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			List<User> list=qr.query(sql1, new BeanListHandler<>(User.class));
			request.getSession().setAttribute("userlist", list);
		} catch (SQLException e) {
		}
		return "admin/admin_user.jsp";
	}
	/*
	 * 管理员查看所有订单
	 * */
	public String showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("---管理员查询订单---");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			List<Order> orderlist=new ArrayList<Order>();
			//先获取所有的orderid
			int userID=Integer.parseInt(request.getParameter("userID"));
			String sql="";
			if(userID==0){
				sql="select*from orders order by ordertime desc";
			}else{
				sql="select*from orders where userID="+userID+" order by ordertime desc";
			}
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			orderlist=qr.query(sql, new BeanListHandler<>(Order.class));
			for(int i=0;i<orderlist.size();i++){
				String oid=orderlist.get(i).getOid();
				String sql2="select*from orderitem where oid='"+oid+"'";
				List<OrderItem> items=new ArrayList<OrderItem>();
				items=qr.query(sql2, new BeanListHandler<>(OrderItem.class));
				//为每个item添加image和name
				for(int j=0;j<items.size();j++){
					String pid=items.get(j).getPid();
					String sql3="select*from products where pid='"+pid+"'";
					Map<String,Object> map=qr.query(sql3, new MapHandler());
					items.get(j).setPname((String) map.get("pname"));
					items.get(j).setPimage((String) map.get("pimage"));
				}
				orderlist.get(i).setList(items);
			}
			request.getSession().setAttribute("orderlist", orderlist);
			//request.getSession().setAttribute("orderlist",orderlist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---over---");
		return "admin/admin_order.jsp";
	}
	/*
	 * 关于会员
	 */
	public String setVip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("---管理员管理会员---");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int sysRole=Integer.parseInt(request.getParameter("sysRole"));
			int userID=Integer.parseInt(request.getParameter("userID"));
			String sql="update users set sysRole="+sysRole+" where userID="+userID;
			List<User> list=(List<User>)request.getSession().getAttribute("userlist");
			for(int i=0;i<list.size();i++){
				if(list.get(i).getUserID()==userID){
					list.get(i).setSysrole(sysRole);
					System.out.println("设置userID="+userID+"的用户sysRole="+sysRole);
				}
			}
			request.getSession().setAttribute("userlist", list);
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---over---");
		return null;
	}
	/*
	 * 管理员退出登录
	 */
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/index2.jsp");
		return null;
	};
}
