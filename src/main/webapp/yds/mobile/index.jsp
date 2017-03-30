<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.xidu.util.UtilForQQ"%>	
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.xidu.entity.Customer"%>
<%@ page import="com.xidu.entity.Robot"%>
<%@ page import="com.xidu.dao.CustomerDao"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.xidu.util.IPUtil"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="com.xidu.init.WebSocketPool"%>
<%@ page import="com.xidu.entity.KCB"%>
<%@ page import="com.xidu.init.SessionCounter"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.xidu.service.DoLoginService"%>
<%
       HttpSession ss=request.getSession(true);
       String sessionId = ss.getId();
	   Long time = System.currentTimeMillis();
	   ss.setAttribute("time",time);
	   
	   KCB kcb = new KCB(); //WebSocketPool.getInstance().getRoom("1").getKcb();
	   
 	  String c_json = ""; 
      DoLoginService loginService = new DoLoginService();
      String roomNo = "2";
	  String userName = "";
	  String pwd = "";
	  String ip = new IPUtil().getRemortIP(request);
      Cookie[] cookies = request.getCookies(); 
      if(cookies != null){
    	  for(int i=0;i<cookies.length;i++){
		       if(cookies[i].getName().equals("_Room"+roomNo+"_loginName")){  
		              userName = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
		       }
		       if(cookies[i].getName().equals("_Room"+roomNo+"_loginPwd")){  
		              pwd = cookies[i].getValue();
		       }
		   }
      }
	  
      String[] param = {userName,pwd,roomNo,String.valueOf(time),ip};
	  Customer customerResult = loginService.doLogin(param);
	  ss.setAttribute("customer",customerResult);
	  c_json = customerResult.toJson().toString();
   %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0 user-scalable=no"/>
	<meta name="format-detection" content="telephone=no,email=no" />
<title>银大师直播间_白银直播间_现货直播间_现货白银直播间_白银走势分析直播间</title>
<meta name="description" content="银大师直播间汇集顶尖专业分析师团队，特邀名师解盘，24小时不间断连播，为您提供最新最全面最专业的贵金属，白银投资，原油，石油，EIA,非农数据分析，行情分析操作建议走势分析等服务，完美狙击大行情" />
<meta name="keywords" content="白银走势分析直播间,现货白银直播间,现货黄金直播间,现货直播间,白银直播间,贵金属直播间,原油直播间，EIA行情分析,非农数据分析，现货白银直播室,现货白银直播间喊单,白银喊单，鑫西都,银大师直播间" />

	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/drag.css" />
	<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css"/><!--滚动-->
	<link rel="stylesheet" href="css/jquery.sinaEmotion.css"/><!--表情-->
	<script type="text/javascript" src="js/html5shiv.min.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/mobile.js"></script>
	<script charset="utf-8" src="http://wpa.b.qq.com/cgi/wpa.php"></script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?dd2140ffa55f581d8bd9e2177b442a40";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>


</head>
<body>
<div class="bg">
	<div id="main">
		<div class="video">
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
							<div id="sendMsgInput" contenteditable="true"></div>
						</div>
						<a href="javascript:return false;" class="sub_btn" id="sub_info_btn">发送</a>
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
			<td class="reg"><a href="javascript:;"><img src="images/reg.png"></a></td>
			<td class="log"><a href="javascript:;"><img src="images/log.png"></a></td>
			<td class="content"><a href="javascript:;" id="BizQQWPA" ><img src="images/content.png"></a></td>
		</tr>
	</table>
</div>
<div class="register hide">
	<h1>用户注册<span><i></i>设置帐号和密码，在线观看直播视频。</span></h1>
	<form action="#" method="post">
		<div>
			<p>
				<label class="lab1"></label>
				<input type="text" placeholder="用户名" required="required" id="userName">
			</p>
			<span>*</span>
		</div>
		<div>
			<p>
				<label class="lab2"></label>
				<input type="text" placeholder="昵称" required="required" id="nickName">
			</p>
			<span>*</span>
		</div>
		<div>
			<p>
				<label class="lab7"></label>
				<input type="text" placeholder="QQ号码" id="qq">
			</p>
		</div>
		<div>
			<p>
				<label class="lab3"></label>
				<input type="text" placeholder="密码" required="required" id="pwd">
			</p>
			<span>*</span>
		</div>
		<div>
			<p>
				<label class="lab3"></label>
				<input type="text" placeholder="确认密码" required="required" id="rePwd" onblur="checkPwd()">
			</p>
			<span>*</span>
		</div>
		<div>
			<p>
				<label class="lab4"></label>
				<input type="text" placeholder="手机号" required="required" id="mobile">
			</p>
			<span>*</span>
		</div>
		<div class="huakuai" id="huakuai"></div>
		<div>
			<p class="yan">
				<label class="lab6"></label>
				<input type="text" placeholder="手机短信验证码" required="required" id="mobileCode">
			</p>
			<span>*</span>
		</div>
		<h4><input type="button" value="完成注册" class="mit" id="register"></h4>
	</form>
	<a href="javascript:;" class="close"></a>
</div>
<div class="login hide">
	<h1>用户登录</h1>
	<form action="#" method="post">
		<p>
			<label class="lab2"></label>
			<input type="text" placeholder="昵称" required="required" id="userName_login">
		</p>
		<p>
			<label class="lab3"></label>
			<input type="text" placeholder="密码" required="required" id="pwd_login">
		</p>
		<input type="button" value="完成登录" class="mit" id="login">
		<span>还未注册帐户？赶紧注册一个<a href="javascript:;">GO</a></span>
	</form>
	<a href="javascript:;" class="close"></a>
</div>
<script src="js/jquery.mobile-1.3.2.min.js"></script>
<script src="js/drag.js"></script>
<script type="text/javascript" src="js/jquery.mCustomScrollbar.js"></script><!--滚动-->
<script type="text/javascript" src="js/jquery.sinaEmotion.js"></script><!--表情彩条-->
<script type="text/javascript" src="js/scrollTo.js"></script>
<script type="text/javascript" src="js/resize.js"></script>
<script type="text/javascript" src="js/jquery.JPlaceholder.js"></script>
<script type="text/javascript" src="js/tinybox.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript" src="js/script2.js"></script>
<script type="text/javascript" src="js/jquery.nanoscroller.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/defaultParam.js"></script>
<script type="text/javascript" src="js/indexRegister.js"></script>
<script type="text/javascript" src="js/indexDo.js"></script>
<script type="text/javascript">
    $('#huakuai').huakuai();
    sessionId = "<%=sessionId%>";
    time = "<%=time%>";
    is_chat="<%=customerResult.getCustomerType().getIs_chat()%>";
    flashVideo();
    var json = <%=c_json%>;  //将后台传来的json格式字符串转换为object
    level = json.level;  //获取用户等级
    customer=json;
    setCookie("_Room"+roomNo+"_loginName", customer.userName, 10);
    setCookie("_Room"+roomNo+"_loginPwd", customer.pwd, 10);
    initAction();
	BizQQWPA.addCustom({aty: '2', a: '3', nameAccount: 800037979, selector: 'BizQQWPA'});
</script>
<script type="text/javascript" src="js/track.js"></script>
</body>
</html>