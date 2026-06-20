package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.Category;
import domain.Product;
import utils.DataSourceUtils;
import web.servlet.base.BaseServlet;

/**
 * 首页
 */
@WebServlet("/Index")
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入IndexServlet的Index方法");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//查询最新商品和热门商品
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="select*from products where pflag = 0 order by salenum desc limit 6";
			String sql2="select*from products where pflag = 0 order by pdate desc limit 6";
			List<Product> hotList=qr.query(sql, new BeanListHandler<>(Product.class));
			List<Product> newList=qr.query(sql2, new BeanListHandler<>(Product.class));
			//将两个list都放入request域中
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
			
		} catch (SQLException e) {
		}
		return "user_store.jsp";
	}
	/*
	 * 展示种类
	 */
	public String showCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("进入servlet的findAll方法");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String sql="select*from category order by cid+0 asc";
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			List<Category> list = qr.query(sql, new BeanListHandler<>(Category.class));
			for(int i=0;i<list.size();i++){
				Category cate=list.get(i);
				String cid=cate.getCid();
				String sql2="select pid from products where cid='"+cid+"'";
				System.out.println(sql2);
				if(!(qr.query(sql2, new ScalarHandler())==null)){
					list.get(i).setPsize(1);
				}
			}
			request.getSession().setAttribute("catelist", list);
		} catch (SQLException e) {
			
		}
		return "/admin/admin_cate.jsp";
	}
}
