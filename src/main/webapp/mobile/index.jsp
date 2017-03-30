<%@page import="com.xidu.redis.RedisUtil"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xidu.util.MD5Util"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.xidu.entity.Customer"%>
<%@ page import="com.xidu.entity.Robot"%>
<%@ page import="com.xidu.dao.CustomerDao"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.xidu.util.IPUtil"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.xidu.service.DoLoginService"%>
<%
            DoLoginService loginService = new DoLoginService();
            String c_json = ""; 
            String roomNo = "1";
			String code=request.getParameter("code");
			Long time = System.currentTimeMillis(); 
           String[] param = {code,roomNo,String.valueOf(time)};
           Customer customerResult =new Customer();
           try{
             customerResult = loginService.doLogin(param);
             if(customerResult != null){
                c_json = customerResult.toJson().toString();
             }else{
          	   //response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe4f3c376dac42a79&redirect_uri=http%3A%2F%2F8000dd.08op.com%2FredirectUrl.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
             }
           }catch(Exception e){
        	   e.printStackTrace();
        	   //response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe4f3c376dac42a79&redirect_uri=http%3A%2F%2F8000dd.08op.com%2FredirectUrl.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
           }
           
            HttpSession ss=request.getSession(true);
            String sessionId = ss.getId();
            ss.setAttribute("customer",customerResult);
            ss.setAttribute("time",time);
            
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0 user-scalable=no"/>
	<meta name="format-detection" content="telephone=no,email=no" />
	<title>嘀嘀V云商 会员投资课堂</title>
	<link rel="stylesheet" href="css/style.css?time=20161201" />
	<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css"/><!--滚动-->
	<link rel="stylesheet" href="css/jquery.sinaEmotion.css"/><!--表情-->
	<script type="text/javascript" src="js/html5shiv.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/defaultParam.js"></script>
</head>
<body>
<div class="bg">
	<div id="main">
		<div class="video"  id="myplayer">	
			 <iframe id="videoFrame" src="video.html" frameborder="0" scrolling="no" width="100%" height="100%"></iframe>
		</div>
		<div id="topic">
			<div class="topicMain">
				<div class="topiccontent mCustomScrollbar _mCS_1">
					<div class="mCustomScrollBox mCS-light" id="mCSB_1">
						<div class="mCSB_container">
							<div id="topicbox"> 
							</div>
						</div>
						<div class="mCSB_scrollTools" style="position: absolute; display: block; opacity: 0;">
							<a class="mCSB_buttonUp" oncontextmenu="return false;"></a>
							<div class="mCSB_draggerContainer">
								<div class="mCSB_dragger" style="position: absolute; top: 268px; height: 33px;" oncontextmenu="return false;">
									<div class="mCSB_dragger_bar" style="position: relative; line-height: 33px;"></div>
								</div>
								<div class="mCSB_draggerRail"></div>
							</div>
							<a class="mCSB_buttonDown" oncontextmenu="return false;"></a>
						</div>
					</div>
				</div>
				<div id="topicinput">
					<div class="input_area">
						<div class="tool_bar">
							<a href="javascript:;" id="bt_face" class="bar_2 bar"><img src="images/bar.jpg"></a>
						</div>
						<div class="inputBox">
							<div id="sendMsgInput"  contenteditable="true"></div>
						</div>
						<a  id="postMessage" href="javascript:;" class="sub_btn">发送</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="pop"></div>
<div class="btnBox">
	<table>
		<tr>
			<td  width="16%"><img src="" class="headImg" id="headImg"></td>
			<td width="45%" style="font-size:1rem; text-align:left" id="nickName"></td>
			<!-- <td width="18%" style="padding-top:0.3rem;background:#0f3e6f;border-right:#063664 0.3rem solid"><a href="javascript:;" style=" font-weight: bold"><img src="images/cust.png"><br />金牌客服</a></td> -->
			<td width="18%" style="padding-top:0.3rem;"><a href="javascript:;" style=" font-weight: bold; color:#fefefe "><img src="images/screen.png" id="clearAll"><br />清屏</a></td>
		</tr>
	</table>
</div>
<div class="popPsd" >
	<form action="javascript:;" method="post">
		<p><label>密码</label><input id="lockPwd" type="password"></p>
		<input type="submit" class="passSub">
	</form>
</div>
<script>
    sessionId = "<%=sessionId%>";
    time = "<%=time%>";
    var json = <%=c_json%>;  //将后台传来的json格式字符串转换为object
    level = json.level;  //获取用户等级
    customer=json;
    var islock="<%=RedisUtil.getString("islock")%>";
</script>

<script src="js/jquery.mobile-1.3.2.min.js"></script>
<script src="js/drag.js"></script>
<script type="text/javascript" src="js/jquery.mCustomScrollbar.js"></script><!--滚动-->
<script type="text/javascript" src="js/jquery.sinaEmotion.js"></script><!--表情彩条-->
<script type="text/javascript" src="js/scrollTo.js"></script>
<script type="text/javascript" src="js/resize.js"></script>
<script type="text/javascript" src="js/jquery.JPlaceholder.js"></script>
<script type="text/javascript" src="js/tinybox.js"></script>
<script type="text/javascript" src="js/script.js?time=20161201"></script>

<script type="text/javascript" src="js/indexDo.js?time=20161201"></script>

</body>
</html>