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
    <!-- 加载目录 -->
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
	</script>
	<script>
		$(document).ready(function(){
			$("#addCate").click(function(){
				var newCate=$("#newCate").val();
				if(newCate){
					alert("添加成功");
					$.post("${pageContext.request.contextPath}/Category",
							{"method":"addCate","cname":newCate},
		    				function(){
		    					window.location.reload();
		    				});
				}
			});
			$(".delete").click(function(e){
				var temp=e.target.id;
				var cid=temp.replace("_del","");
				
				$.post("${pageContext.request.contextPath}/Category",
						{"method":"delCate","cid":cid},
	    				function(){
	    					window.location.reload();
	    				});
			});
		});
	</script>
    <style>
    	table{
    		margin:auto;
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
		th{
			width:30%;
		}
		input{
			border:0;
		}
		.gray{
			color:gray;
		}
    </style>
</head>
<body>
    <div class="firstmenu">
        <a style="font:bold 40px Times New Roman ;color:white;padding: 10px;" href="${pageContext.request.contextPath}/admin/admin_index.jsp">网上书店后台管理</a>
        <!-- 静态包含 -->
    </div>
	<div class="row">
		<div class="main sidemenu">
			<%@include file="../WEB-INF/sidemenu2.jsp" %>
		</div>
		<div class="main content">
			<table border="0">
			  <tr>
			    <th>分类名称</th>
			    <th>操作</th>
			  </tr>
			  <c:forEach items="${catelist}" var="p">
				  <tr>
				    <td id="${p.cid }">${p.cname }</td>
				     <td>
				     	<c:if test="${p.psize==1 }">
				     	<label class="gray">请先移除书籍</label>
				     	</c:if>
				     	<c:if test="${p.psize==0 }">
				     	<button class="delete" id="${p.cid }_del">×</button>
				     	</c:if>
				     </td>
				  </tr>
			  </c:forEach>
			  <tr>
				    <td class="new" id="${p.cid }">
				    <input id="newCate" placeholder="输入新分类">
				    </td>
				     <td><button id="addCate">添加</button></td>
				  </tr>
			</table>
		</div>
	</div>

	<div class="footer">
		2020 山东大学数据库课设
	</div>
</body>
</html>