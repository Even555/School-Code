package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.Cart;
import domain.Category;
import domain.Comment;
import domain.PageBean;
import domain.Product;
import domain.User;
import utils.DataSourceUtils;
import web.servlet.base.BaseServlet;

/**
 * 前台商品详情模块
 */
@WebServlet("/Product")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("---进入书本详情---");
			//获取pid
			String pid=request.getParameter("pid");
			//查询product放入request域中 
			String sql="select*from products where pid='"+pid+"'";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			//单个叫bean 多个叫list
			Product pd;
			pd = qr.query(sql, new BeanHandler<>(Product.class));
			request.setAttribute("bean", pd);
			//获取评价列表
			String sql2="select*from comments where pid='"+pid+"'";
			List<Comment> list=qr.query(sql2, new BeanListHandler<>(Comment.class));
			request.setAttribute("comments", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//请求转发
		System.out.println("over");
		return "product_info.jsp";
	}
	/*
	 * 获取pagebean转发给product_list页面
	 * */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入ProductServlet的findByPage方法");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//获取当前页数和分页种类
			int pageNumber=1;
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize=9;
			int cid=Integer.parseInt(request.getParameter("cid"));
			PageBean<Product> pb=new PageBean<>();
			pb.setPageNumber(pageNumber);
			pb.setPageSize(pageSize);
			pb.setCid(cid);
			int startIndex=pb.getstartIndex();
			//获取排序方式
			int order=Integer.parseInt(request.getParameter("order"));
			String orderStr="pdate";
			switch(order){
			case 0:
				orderStr="pdate";
				break;
			case 1:
				orderStr="salenum";
				break;
			case 2:
				orderStr="price";
				break;
			}
			//根据cid和pb区查询productlist
			String sql="select*from products where cid='"+cid+"' and pflag=0 order by "+orderStr+" desc limit ?,? ";
			String sql1="select count(*) from products where cid='"+cid+"' and pflag=0";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			List<Product> data=qr.query(sql, new BeanListHandler<>(Product.class),startIndex,pageSize);
			int totalRecord=((Long) qr.query(sql1, new ScalarHandler())).intValue();
			//
			pb.setData(data);
			pb.setTotalRecord(totalRecord);
			int totalPage=(int) Math.ceil((double)totalRecord/pageSize);
			System.out.println("总共有"+totalRecord+","+pageSize+","+totalPage+"页");
			//System.out.println("用户"+request.getSession().getAttribute("username"));
			pb.setTotalPage(totalPage);
			//
			request.getSession().setAttribute("pb", pb);
			
		} catch (SQLException e) {
		}
		return "product_list.jsp";
	}
	/*
	 * 点击加入购物车
	 */
	public String addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//获取参数
			String pid=request.getParameter("pid");
			int num=Integer.parseInt(request.getParameter("num"));
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			//
			String sql="insert into cart values("+userID+",'"+pid+"',"+num+")";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
			System.out.println(sql);
		} catch (SQLException e) {
		}
		return null;
	}
	/*
	 * 跳转购物车界面
	 */
	public String showCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			Cart<Product> cart=new Cart<Product>();
			cart.setUserID(userID);
			String sql="select*from products natural join cart where userID="+userID;
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			List<Product>list=qr.query(sql, new BeanListHandler<>(Product.class));
			cart.setData(list);
			request.getSession().setAttribute("cart",cart);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user_cart.jsp";
	}
	/*
	 * 减少购物车数目
	 */
	public String minusOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String pid=request.getParameter("pid");
			int num=Integer.parseInt(request.getParameter("num"));
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			String sql="UPDATE CART set num="+num+" where pid='"+pid+"' and userID="+userID;
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
		} catch (Exception e) {
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
		return null;
	}
	/*
	 * 添加购物车数目
	 */
	public String plusOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String pid=request.getParameter("pid");
			int num=Integer.parseInt(request.getParameter("num"));
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			String sql="UPDATE CART set num="+num+" where pid='"+pid+"' and userID="+userID;
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
		} catch (Exception e) {
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
		return null;
	}
	/*
	 * 删除购物车项目
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String pid=request.getParameter("pid");
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			String sql="delete from cart where userID="+userID+" and pid='"+pid+"'";
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
			response.getWriter().println();
		} catch (Exception e) {
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
		return null;
	}
	/*
	 * 清空购物车
	 */
	public String clearAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			User user=(User) request.getSession().getAttribute("user");
			int userID=user.getUserID();
			String sql="delete from cart where userID="+userID;
			System.out.println(sql);
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			qr.update(sql);
			response.getWriter().println();
		} catch (Exception e) {
			request.setAttribute("msg", "出现异常!"+e);
			return "msg.jsp";
		}
		return null;
	}
	/*
	 * 管理员获取书列表
	 * */
	public String showBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入showBook方法");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			int order=Integer.parseInt(request.getParameter("order"));
			String orderStr="pdate";
			switch(order){
			case 0:
				orderStr="order by price desc";
				break;
			case 1:
				orderStr="order by salenum desc";
				break;
			case 2:
				orderStr="order by pdate desc";
				break;
			}
			//获取到cid个list
			//先获取cid的list
			List<Category> catelist=(List<Category>) request.getSession().getAttribute("catelist");
			for(int i=0;i<catelist.size();i++){
				String cid=catelist.get(i).getCid();
				String sql1="select* from products where cid='"+cid+"'"+orderStr;
				QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
				List<Product> data=qr.query(sql1, new BeanListHandler<>(Product.class));
				catelist.get(i).setList(data);
			}
			//
			request.getSession().setAttribute("catelist", catelist);
			
		} catch (SQLException e) {
		}
		return "admin/admin_book.jsp";
	}
	/*
	 * 下架书本
	 */
	public String outBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String pid=request.getParameter("pid");
		String pflag=request.getParameter("pflag");
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql2="update products set pflag="+pflag+" where pid='"+pid+"'";
		System.out.println(sql2);
		qr.update(sql2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 管理员修改书本内容
	 */
	public String changeBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String pid=request.getParameter("pid");
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select*from products where pid='"+pid+"'";
		System.out.println(sql);
		Product newbook=qr.query(sql, new BeanHandler<>(Product.class));
		String cid=newbook.getCid();
		String sql1="select cname from category where cid='"+cid+"'";
		String cname=(String) qr.query(sql1,new ScalarHandler());
		newbook.setCname(cname);
		request.getSession().setAttribute("newbook", newbook);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin/book_change.jsp";
	}
	/*
	 * 查询书籍list
	 */
	public String findBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String name=request.getParameter("name");
			String sql="select*from products where pname like '%"+name+"%'";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			List<Product> list=qr.query(sql, new BeanListHandler<>(Product.class));
			request.getSession().setAttribute("searchList", list);
			request.getSession().setAttribute("searchMsg", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
