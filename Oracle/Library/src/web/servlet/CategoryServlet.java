package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
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

import DB.database;
import web.servlet.base.BaseServlet;
import domain.Category;
import redis.clients.jedis.Jedis;
import utils.DataSourceUtils;
import utils.JSONUtil;
import utils.JedisUtil;
/**
 * 前台分类模块
 */
@WebServlet("/Category")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * 查询所有分类
	 */
	@SuppressWarnings("unused")
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("---sidemenu获取cate---");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//查询所有分类
//			//先从redis获取
//			JedisUtil.setJedisPool();
//			Jedis jedis=JedisUtil.getJedisPool().getResource();
			String value=null;
			//若redis获取不到
//			if(value==null){
				String sql="select*from category order by cid+0 asc";
				QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
				List<Category> list = qr.query(sql, new BeanListHandler<>(Category.class));
				//返回json字符串
				if(list!=null&&list.size()>0){
					value=JSONUtil.toJSONString(list);
					response.getWriter().println(value);
				}
				else{
					System.out.println("无法获取json");
				}
//			}
//			else{
//				System.out.println("从redis获取");
//				response.getWriter().println(value);
//			}
		} catch (SQLException e) {
		}
		System.out.println("---over---");
		return null;
	}
	/*
	 * 添加Cate
	 */
	public String addCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cname=request.getParameter("cname");
		//获取最后一行的cid
		String sql="select cid from category order by cid+0 DESC limit 1";
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		Map<String,Object> map = qr.query(sql,new MapHandler());
		int new_cid=Integer.parseInt((String) map.get("cid"))+1;
		String sql2="insert into category values('"+new_cid+"','"+cname+"')";
		System.out.println(sql2);
		qr.update(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * delCate
	 */
	public String delCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cid=request.getParameter("cid");
		System.out.println(cid);
		//获取最后一行的cid
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql2="delete from category where cid='"+cid+"'";
		System.out.println(sql2);
		qr.update(sql2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
