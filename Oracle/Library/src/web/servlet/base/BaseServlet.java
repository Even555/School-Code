package web.servlet.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的servlet方法
 */
@WebServlet("/Base")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//获取方法名
			String mName=request.getParameter("method");
			//如果方法名为空！！！
			if(mName==null||mName.trim().length()==0){
				mName="index";
			}
			//获取到对应的方法 如registUI
			Method method=this.getClass().getMethod(mName, HttpServletRequest.class,HttpServletResponse.class);
			//获取方法return的路径参数
			String path=(String)method.invoke(this,request,response);
			if(path!=null){
				request.getRequestDispatcher(path).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	//啥都不写执行默认方法
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("没有写跳转页面");
		return null;
	}
		

}
