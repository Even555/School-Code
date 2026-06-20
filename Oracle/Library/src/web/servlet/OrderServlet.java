package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import domain.Cart;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.DataSourceUtils;
import utils.JSONUtil;
import utils.UUIDUtils;
import web.servlet.base.BaseServlet;

/**
 * 订单模块
 */
@WebServlet("/Order")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 提交订单
	 */
	public String submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("----进入orderservlet提交订单----");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			Order order=(Order) request.getSession().getAttribute("neworder");
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			int sysrole=user.getSysrole();
			Double sumprice=order.getSumprice();
			String oid=order.getOid();
			String ordertime=order.getOrdertime();
			
			String name=request.getParameter("name1");
			String telephone1=request.getParameter("telephone1");
			String province=request.getParameter("province");
			String city=request.getParameter("city");
			String detail=request.getParameter("address_detail");
			String address=""+name+" "+telephone1+" "+province+city+detail;
			String sql1="insert into orders (oid,userID,sumprice,ordertime,address,isvip,orderstate) values('"+oid+"',"+userID+","+sumprice+",'"+ordertime+"','"+address+"',"+sysrole+","+0+")";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql1);
			System.out.println(sql1);
			List<OrderItem> list =order.getList();
			for(int i=0;i<list.size();i++){
				String pid=list.get(i).getPid();
				int num=list.get(i).getNum();
				Double xiaoji=list.get(i).getXiaoji();
				Double price=list.get(i).getPrice();
				String sql2="insert into orderitem values('"+oid+"','"+pid+"',"+num+","+xiaoji+","+price+")";
				String sql3="delete from cart where userID="+userID+" and pid='"+pid+"'";
				System.out.println(sql2);
				qr.update(sql2);
				qr.update(sql3);
				//增加销量
				String sql4="update products set salenum=(select salenum where pid='"+pid+"')+"+num+" where pid='"+pid+"'";
				System.out.println(sql4);
				qr.update(sql4);
			}
			//跳转到所有订单
			response.sendRedirect("order_index.jsp");
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("----over----");
		return null;
	}
	/*
	 * 生成订单跳转输入收货地址页面
	 */
	public String newOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入orderservlet生成新订单");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//获取参数 物品 总价
			String itemsStr=request.getParameter("items");
			JSONArray jsonArray = JSONArray.fromObject(itemsStr);
			Double sumprice=Double.parseDouble(request.getParameter("sumprice"));
			//生成订单号
			String oid=new UUIDUtils().getUUID();
			System.out.println("订单号为"+oid);
			List<OrderItem> list=new ArrayList();
			Order order=new Order();
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			//获取数据生成orderitem
			for(int i=0;i<jsonArray.size();i++){
				JSONObject   jsonObject  =  jsonArray.getJSONObject(i);
				String pid=jsonObject.getString("pid");
				Double price=jsonObject.getDouble("price");
				Double xiaoji=jsonObject.getDouble("xiaoji");
				int num=jsonObject.getInt("num");
				//赋值
				OrderItem orderitem=new OrderItem();
				orderitem.setPid(pid);
				orderitem.setPrice(price);
				orderitem.setXiaoji(xiaoji);
				orderitem.setOid(oid);
				orderitem.setNum(num);
				String sql4="select*from products where pid='"+pid+"'";
				Map<String,Object> map=qr.query(sql4, new MapHandler());
				orderitem.setPname((String) map.get("pname"));
				orderitem.setPimage((String) map.get("pimage"));
				
				//添加到list中
				list.add(orderitem);
				//插入数据库中
				//String sql1="insert into orderitem values('"+oid+"','"+pid+"',"+num+","+xiaoji+","+price+")";
				//String sql3="delete from cart where userID="+userID+" and pid='"+pid+"'";
				//System.out.println(sql1);
				//qr.update(sql1);
				//qr.update(sql3);
			}
			Date date =new Date();			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = format.format(date);
			order.setOid(oid);
			order.setList(list);
			order.setOrderstate(0);
			order.setSumprice(sumprice);
			order.setOrdertime(dateString);
			int sysrole=user.getSysrole();
			order.setIsvip(sysrole);
			//插入数据库中
			//String sql2="insert into orders (oid,userID,sumprice,ordertime,isvip,orderstate) values('"+oid+"',"+userID+","+sumprice+",'"+dateString+"',"+sysrole+","+0+")";
			//qr.update(sql2);
			//System.out.println(sql2);
			request.getSession().setAttribute("neworder",order);
			response.getWriter().println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 展示订单页面
	 */
	public String showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入showOrder");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			List<Order> orderlist=new ArrayList<Order>();
			//先获取所有的orderid
			String sql="select*from orders where userID="+userID+" order by ordertime desc";
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
		return "user_order.jsp";
	}
	/*
	 * 用户修改订单状态
	 */
	public String setOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("---设置订单状态---");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//获取参数
			int orderstate=Integer.parseInt(request.getParameter("orderstate"));
			String oid=request.getParameter("oid");
			String sql="update orders set orderstate="+orderstate+" where oid='"+oid+"'";
			
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----over-----");
		return null;
	}
	/*
	 * setCom
	 */
	public String setComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("---评论---");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String oid=request.getParameter("oid");
			String sql="select*from orderitem where oid='"+oid+"'";
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			List<OrderItem> items=qr.query(sql, new BeanListHandler<>(OrderItem.class));
			for(int j=0;j<items.size();j++){
				String pid=items.get(j).getPid();
				String sql3="select*from products where pid='"+pid+"'";
				Map<String,Object> map=qr.query(sql3, new MapHandler());
				items.get(j).setPname((String) map.get("pname"));
				items.get(j).setPimage((String) map.get("pimage"));
			}
			request.getSession().setAttribute("comlist", items);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---over---");
		return null;
	}
	/*
	 * 写入评论
	 */
	public String submitCom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("--进入submitCom---");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//获取参数 物品 总价
			String itemsStr=request.getParameter("items");
			JSONArray jsonArray = JSONArray.fromObject(itemsStr);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			//获取数据生成orderitem
			Date date =new Date();			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = format.format(date);
			List<OrderItem> items=(List<OrderItem>)request.getSession().getAttribute("comlist");
			String oid=items.get(0).getOid();
			for(int i=0;i<jsonArray.size();i++){
				JSONObject   jsonObject  =  jsonArray.getJSONObject(i);
				String pid=jsonObject.getString("pid");
				String content =jsonObject.getString("content");
				String sql="insert into comments (pid,userID,content,time) values('"+pid+"',"+userID+",'"+content+"','"+dateString+"')";
				System.out.println(sql);
				qr.update(sql);
			}
			String sql1="update orders set orderstate=4 where oid='"+oid+"'";
			System.out.println(sql1);
			qr.update(sql1);
			response.getWriter().println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---over---");
		return null;
	}
}
