<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>书店|管理员</title>
    <style>
        @import url("${pageContext.request.contextPath}/css/default2.css");
    </style>
    <style>
    	table{
    		margin:auto;
    	}
    	table img{
    		width:50px;
    		height:70px;
    	}
    	tr{
			background-color: rgba(255,255,255,0.6);
			padding: 10px;
			margin: 15px;
			border-radius: 6px;
			
			height:25%;
			text-align: center;
			line-height:30px;
		}
		td{
			padding:0px 5px;
		}
		th{
		}
		input{
			border:0;
		}
		.pname{
			width:170px;
		}
		.img{
			width:60px;
			height:80px;
		}
		.date{
			width:100px;
		}
		.red{
			color:red;
		}
		.change{
			background-color:lightgray;
			border:1;
			padding:2px 5px;
		}
		.aaa{
			display:inline-block;
			background-color:white;
			padding:5px 10px;
			margin:5px;
		}
    </style>
    <!-- 加载目录 -->
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
	</script>
	<script>
	//下架
		$(document).ready(function(){
			$(".saleout").click(function(e){
				var temp=e.target.id;
				var pid=temp.replace("_out","");
				$.post("${pageContext.request.contextPath}/Product",
						{"method":"outBook","pid":pid,"pflag":1},
	    				function(){
	    					window.location.reload();
	    				});
			});
			$(".salein").click(function(e){
				var temp=e.target.id;
				var pid=temp.replace("_in","");
				$.post("${pageContext.request.contextPath}/Product",
						{"method":"outBook","pid":pid,"pflag":0},
	    				function(){
	    					window.location.reload();
	    				});
			});
		});
	</script>
</head>
<body>
    <div class="firstmenu">
        <a style="font:bold 40px Times New Roman ;color:white;padding: 10px;" href="${pageContext.request.contextPath}/admin/admin_index.jsp">网上书店后台管理</a>
    </div>
	<div class="row">
		<div class="main sidemenu">
			<%@include file="../WEB-INF/sidemenu2.jsp" %>
		</div>
		<div class="main content">
		<a class="aaa" href="${pageContext.request.contextPath }/Product?method=showBook&order=0">价格↓</a>
		<a class="aaa" href="${pageContext.request.contextPath }/Product?method=showBook&order=1">销量↓</a>
		<a class="aaa" href="${pageContext.request.contextPath }/Product?method=showBook&order=2">时间↓</a>
			<table border="0">
			  <tr>
			    <th>书籍名称</th>
			    <th>图片</th>
			    <th>简介</th>
			    <th>市场价</th>
			    <th>会员价</th>
			    <th>销量</th>
			    <th>上市时间</th>
			    <th>分类</th>
			    <th>操作</th>
			    <th>修改</th>
			  </tr>
			  <c:forEach items="${catelist}" var="p">
			  <tr><td colspan="10"><b>${p.cname }类书籍</b></td></tr>
				  
				  <c:forEach items="${p.list }" var="ps">
				  <tr>
				  	<td class="pname">《${ps.pname }》</td>
				  	<td class="img"><img src="${pageContext.request.contextPath }/${ps.pimage }" alt="图片未定义"></td>
				  	<td>${fn:substring(ps.pdesc,0,19)}...</td>
				  	<td>${ps.price }</td>
				  	<td>${ps.vip_price }</td>
				  	<td>${ps.salenum }</td>
				  	<td class="date">${ps.pdate }</td>
				  	<td>${p.cname }</td>
				  	<c:if test="${ps.pflag==0 }">
				  	<td><button class="saleout" id="${ps.pid }_out">下架</button></td>
				  	</c:if>
				  	<c:if test="${ps.pflag==1 }" >
				  	<td><button class="salein red" id="${ps.pid }_in">上市</button></td>
				  	</c:if>
				  	<td><a class="change" href="${pageContext.request.contextPath }/Product?method=changeBook&pid=${ps.pid }">修改</a></td>
				  	</tr>
				  </c:forEach>
			  </c:forEach>
			</table>
			<a class="aaa" id="addCate" href="${pageContext.request.contextPath }/Admin?method=newBook">添加新书籍</a>
		</div>
	</div>

	<div class="footer">
		2020 山东大学数据库课设
	</div>
</body>
</html>