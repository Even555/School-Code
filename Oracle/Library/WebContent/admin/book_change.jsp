<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
    <meta charset="UTF-8">
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
			//输入路径修改图片
			$(".changeImg").on('keydown', function(e){
				if( e.keyCode == 13){
				var path=this.value;
			    	$(".myImg").attr('src','${pageContext.request.contextPath}/'+path);
				}
				
			});
		});
		//取消回车自动提交历史
		document.onkeydown = function(event) {
            var target, code, tag;
            if (!event) {
                event = window.event; //针对ie浏览器
                target = event.srcElement;
                code = event.keyCode;
                if (code == 13) {
                    tag = target.tagName;
                    if (tag == "TEXTAREA") { return true; }
                    else { return false; }
                }
            }
            else {
                target = event.target; //针对遵循w3c标准的浏览器，如Firefox
                code = event.keyCode;
                if (code == 13) {
                    tag = target.tagName;
                    if (tag == "INPUT") { return false; }
                    else { return true; }
                }
            }
        };
	</script>
    <style>
    	table{
    		margin:auto;
    		width:400px;
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
		img{
			width:100px;
		}
		.img{
			padding:10px;
		}
    </style>
</head>
<body>
    <div class="firstmenu">
        <a style="font:bold 40px Times New Roman ;color:white;padding: 10px;" href="admin_index.jsp">网上书店后台管理</a>
        <!-- 静态包含 -->
    </div>
	<div class="row">
		<div class="main sidemenu">
			<%@include file="../WEB-INF/sidemenu2.jsp" %>
		</div>
		<div class="main content">
		<h4>书籍管理-修改书本内容-《${newbook.pname }》</h4>
			<form name="sadform" action="Admin?method=showChanged" method="post">
			<table>
			<tr>
				<td>书名：</td>
				<td><input name="pname" value="${newbook.pname }">
				</td>
			</tr>
			<tr>
				<td>图片路径：</td>
				<td><input name="pimage" class="changeImg" value="${newbook.pimage }"><br>
				</td>
			</tr>
			<tr>
				<td>图片：</td>
				<td class="img"><img class="myImg" src="${pageContext.request.contextPath}/${newbook.pimage}"><br>
				</td>
			</tr>
			<tr>
				<td>简介：</td>
				<td><textarea name="pdesc" rows="14" cols="29">${newbook.pdesc }</textarea>
				</td>
			</tr>
			<tr>
				<td>市场价：</td>
				<td><input name="price" type="text" oninput="value=value.replace(/[^\d.]/g,'')" value="${newbook.price }"><br>
				</td>
			</tr>
			<tr>
				<td>会员价：</td>
				<td><input name="vip_price" type="text" oninput="value=value.replace(/[^\d.]/g,'')" value="${newbook.vip_price }"><br>
				</td>
			</tr>
			<tr>
				<td>上市时间：</td>
				<td><input name="pdate" type="date" value="${newbook.pdate }"><br>
				</td>
			</tr>
			<tr>
				<td>分类：</td>
				<td>
					<select name=cname>
						<c:forEach items="${catelist}" var="p">
					  <option value ="${p.cname }">${p.cname }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"></td>
			</tr>
			</table>
			</form>
		</div>
	</div>

	<div class="footer">
		2020 山东大学数据库课设
	</div>
</body>
</html>