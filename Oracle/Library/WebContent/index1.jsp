<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上书店管理系统-登录</title>
    <style>
        @import url("css/login.css");
    </style>
    <script>
		var errori ='<%=session.getAttribute("error")%>';
		if(errori=='yes'){
		 	alert("用户名和密码不匹配!");
		}
		function f_isEmpty(){
			if(form1.name1.value.length<=0){
				alert("用户名不能为空");
				return false;	
			}
			if(form1.psw1.value.length<=0){
				alert("密码不能为空");
				return false;	
			}
		}
	</script>
</head>
<body>
	<div class="firstmenu">
		网上书店管理系统</div>
	<div class="row">
		<div class="main">
			<h2>用户登陆</h2>
			<form name="form1" action="User?method=login" method="post" onsubmit="return f_isEmpty()">
				<div id="text">
                <input type="text" name="username" placeholder="用户名">
				<input type="password" name="password" placeholder="密码">
				<input type="submit" name="login" value="账号登陆">
				</div>
            </form>
            <a href="index2.jsp" id="admin">切换管理员</a>
            <a href="User?method=registUI" id="register">注册账号</a>
		</div>
	</div>
	<div class="footer">
		2020 山东大学数据库课设
    </div>
</body>
</html>