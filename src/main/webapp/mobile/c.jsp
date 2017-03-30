<%@page import="com.xidu.redis.RedisUtil"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0 user-scalable=no"/>
	<meta name="format-detection" content="telephone=no,email=no" />
	<title>嘀嘀V交易 会员投资课堂</title>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/c.js"></script>
	<style>
		*{ margin: 0; padding: 0}
		table{ vertical-align: baseline;border-collapse:collapse;border-spacing:0;}
		body{ padding: 20px; font-family: "微软雅黑"}
		input{ border: 0; outline: none; font-family: "微软雅黑"}
		td{ padding-right: 20px; height: 40px}
		input[type="button"]{ height: 30px; line-height: 30px; padding: 0 10px; margin-right: 10px; background-color: #1457a0; color: #fff; border: #114b8a 1px solid; cursor:pointer;}
		input[type="text"]{ height: 30px; border: #bcbcbc 1px solid; margin: 0 10px}
		select{ margin: 0 10px}
		div{display:block;float:left;width: 400px;}
	</style>
</head>
<body>
	<input type="hidden" id="userid" value="<%=request.getParameter("uid") %>" />
	<div>
		<span id="total">总人数：</span>
		<table id="userTable">
			
		</table>
	</div>
	<div>
		聊天审核:
		<table id="chatTable">
			
		</table>
	</div>
	<div>
		<%
			String islock=RedisUtil.getString("islock");
			String pwd=RedisUtil.getString("lockPwd");
		%>
		<span>
			房间锁
			<select id="room_lock">
				<option value="lock"
					<%
						if("lock".equals(islock)){
							out.print("selected='selected'");
						}
					%>
				>开启</option>
				<option value="unlock"
					<%
						if("unlock".equals(islock)){
							out.print("selected='selected'");
						}
					%>
				>关闭</option>
			</select>
			密码
			<input value="<%=pwd %>" id="lock_pwd" type="text"/>
			<input value="确认" id="lock_sub" type="button" />
		</span>
	</div>
</body>
</html>