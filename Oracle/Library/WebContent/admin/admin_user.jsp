<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>书店|用户管理</title>
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
		.green{
			color:green;
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
		.buttona{
			display:block;
			border:1;
			padding:0 5px;
			color:gray;
		}
    </style>
    <!-- 加载目录 -->
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
	</script>
	<script>
		$(document).ready(function(){	
				//设为会员
				$('.setVip').bind("click",function(e){
					var temp=e.target.id;
					var userID=temp.replace("_set","");
					$.post(
						"Admin?method=setVip",{"method":"setVip","sysRole":1,"userID":userID},
								function(){
							alert("设置成功");
							window.location.reload();	
						}
					);
			    });
				$('.delVip').bind("click",function(e){
					var temp=e.target.id;
					var userID=temp.replace("_del","");
					$.post(
						"Admin?method=setVip",{"method":"setVip","sysRole":0,"userID":userID},
								function(){
							alert("设置成功");
							window.location.reload();	
						}
					);
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
			<table border="0">
			  <tr>
			    <th>用户id</th>
			    <th>用户名</th>
			    <th>真实姓名</th>
			    <th>电话号</th>
			    <th>会员状态</th>
			    <th>操作</th>
			    <th>历史订单</th>
			  </tr>
			  <c:forEach items="${userlist}" var="p">
				  <tr>
				  	<td>${p.userID }</td>
				  	<td>${p.username }</td>
				  	<td>${p.realname }</td>
				  	<td>${p.telephone }</td>
				  	
				  	<c:if test="${p.sysrole==0 }">
				  	<td>普通用户</td>
				  	<td><button class="setVip" id="${p.userID }_set">设为会员</button></td>
				  	</c:if>
				  	<c:if test="${p.sysrole==1 }">
				  	<td class="green">会员</td>
				  	<td><button class="delVip" id="${p.userID }_del">取消会员</button></td>
				  	</c:if>
				  	<c:if test="${p.sysrole==2 }">
				  	<td class="red">申请中</td>
				  	<td><button class="setVip" id="${p.userID }_set">同意</button>
				  		<button class="delVip" id="${p.userID }_del">拒绝</button></td>
				  	</c:if>
				  	<td><a class="buttona" href="Admin?method=showOrders&userID=${p.userID }">查询订单</a></td>
				  </tr>
			  </c:forEach>
			</table>
		</div>
	</div>

	<div class="footer">
		2020 山东大学数据库课设
	</div>
</body>
</html>