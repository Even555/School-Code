<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书店|历史订单</title>
<style>
     @import url("${pageContext.request.contextPath}/css/default2.css");
	</style>
	<style>
		.orderUI{
			background-color: rgba(255,255,255,0.6);
			padding: 10px;
			margin: 0px;
			border-radius: 6px;
			height:90px;
		}
		.orderUI img{
			width: 60px;
			height:90px;
		}
		#small_title{
	    	font-weight:bold;
	    }
	    .caritem{
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		    overflow: hidden;
		    font-size: 16px;
		}
		.caritem li{
			display:block;
		    float: left;
		    margin-right:30px;
		    width:120px;
		}
		input[name*=_num]{
			width:20px;
		}
		input[name=xiaoji]{
			width:50px;
		}
		.msg{
			color:red;
		}
		.msg2{
			color:green;
		}
		.msg3{
			color:gray;
		}
	</style>
<!-- 加载目录 -->
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
 
	</script>
	<script type="text/javascript" src="/CoreResource/JS/json2.min.js">
	</script>
	<script>
		$(document).ready(function(){
			//用户退单
			$('.sendOrder').bind("click",function(e){
				var temp=e.target.id;
				var oid=temp.replace("_send","");
				$.post(
					"Order?method=setOrder",{"method":"setOrder","orderstate":1,"oid":oid},
					function(){
						alert("发货成功");
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
			<p id="small_title">网上书店-订单管理</p>
			<c:forEach items="${orderlist }" var="ps">
			<hr>
			<p><b>
			订单号:${ps.oid } 下单时间:${ps.ordertime }</b>
			<c:if test="${ps.isvip==1 }">
				<label class="msg"> 该用户使用了会员优惠</label>
			</c:if>
			</p>
				<c:forEach items="${ps.list }" var="p">
					<div class="orderUI">
					<ul class="caritem">
					<li>
					<img src="${pageContext.request.contextPath }/${p.pimage}"></li>
					<li>《${p.pname }》</li>
					<li>单价:${p.price }元</li>
					<li>小计:${p.xiaoji }元</li>
					<li>${p.num }本</li>
					</ul>
					</div>
				</c:forEach>
				总金额:${ps.sumprice}元<br>
				收货信息:${ps.address }
				<c:if test="${ps.orderstate==0 }">
					<label class="msg">(未发货)</label>
					<button class="sendOrder" id="${ps.oid }_send">发货</button>
				</c:if>
				<c:if test="${ps.orderstate==1 }">
					<label class="msg">(等待收货)</label>
				</c:if>
				<c:if test="${ps.orderstate==2 }">
					<label class="msg2">(用户已收货)</label>
				</c:if>
				<c:if test="${ps.orderstate==3 }">
					<label class="msg3">(已退单)</label>
				</c:if>
				<c:if test="${ps.orderstate==4 }">
					<label class="msg2">(用户已收货)</label>
				</c:if>
			</c:forEach>
		</div>
	</div>

	<div class="footer">
		2020 山东大学数据库课设
	</div>
</body>
</html>