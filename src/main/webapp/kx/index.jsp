<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.xidu.util.UtilForQQ"%>	
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.xidu.entity.Customer"%>
<%@ page import="com.xidu.dao.CustomerDao"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.xidu.util.IPUtil"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="com.xidu.init.WebSocketPool"%>
<%@ page import="com.xidu.entity.KCB"%>
<%@ page import="com.xidu.init.SessionCounter"%>
<%@ page import="com.xidu.service.DoLoginService"%>
	<%
	   UtilForQQ uq = new UtilForQQ();
       String QQ1 = uq.getQQ("room1.QQ1");
       String QQ2 = uq.getQQ("room1.QQ2");
       String QQ3 = uq.getQQ("room1.QQ3");
       String QQ4 = uq.getQQ("room1.QQ4");
       String QQ5 = uq.getQQ("room1.QQ5");
       String QQ6 = uq.getQQ("room1.QQ6");
       String name1 = uq.getQQ("room1.name1");
       String name2 = uq.getQQ("room1.name2");
       String name3 = uq.getQQ("room1.name3");
       String name4 = uq.getQQ("room1.name4");
       String name5 = uq.getQQ("room1.name5");
       String name6 = uq.getQQ("room1.name6");
       
       String sessionId = session.getId();
	   Long time = System.currentTimeMillis();
	   session.setAttribute("time",time);
	   
	   KCB kcb=WebSocketPool.getInstance().getRoom("1").getKcb();
	%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
	<title>凯旋直播间</title>
	<link rel="shortcut icon" type="image/x-icon" href="favicon/favicon.ico">
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css"/><!--滚动-->
	<link rel="stylesheet" href="css/jquery.sinaEmotion.css"/><!--表情-->
	<link rel="stylesheet" href="css/drag.css"/>
	<script type="text/javascript" src="js/html5shiv.min.js"></script>

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?b9b29e6fa781d94318b62d4f0d702fe5";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>
<body>
<span style="display:none;">
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259465169'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1259465169' type='text/javascript'%3E%3C/script%3E"));</script>
</span>

<!-- WPA Button Begin -->
<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzgwMDE4OTAyNl80MDQxNjFfODAwMTg5MDI2Xw"></script>
<!-- WPA Button END -->
<div class="bg">
	<img src="images/bg.jpg"/>
</div>
<header>
	<h1></h1>
	<a href="javascript:return false;" class="desktop" >保存到桌面</a>
	<nav class="sub">
		<a href="javascript:;" class="calHead">财经日历</a>
		<a href="javascript:return false;" class="courseHref">课程安排</a>
		<a href="javascript:;" class="eiaHref">EIA原油库存</a>
		<a href="javascript:;" class="unHref">非农数据</a>
	</nav>
	<div class="head_right">
		<a href="javascript:return false;" class="headBtn log">登  录</a>
		<a href="javascript:return false;" class="headBtn reg">注册会员</a>
		<a class="exit" href="javascript:;" style="display:none;" id="out">退出</a>
		
		<span>
			<img src="images/icon7.png">
			<a href="javascript:return false;" class="perName">甲乙丙丁</a>
		</span>
		<img src="images/unit.png" class="unit">
	</div>
</header>
<div id="main">
	<div id="topic" style="height: 464px;">
		<div class="notice">
			<b></b>
			<div class="marTit">
				<div class="marquee" id="marquee">
					<p style="font-size: 16px; font-weight: bold;"></p>                                  
				</div>
			</div>
		</div>
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
		<div class="msg" id="bottomChats">本周为超级数据周，用《决胜超级周》备战非农，EIA，学得快赚的快！！！快快点击下方小企鹅索要</div>
		<div class="qqbts">
			<span>在线客服：</span>
			<a href="tencent://message/?uin=<%=QQ1%>&Site=&menu=yes" target="_blank" class="qq_link_1"><%=name1%></a>
			<a href="tencent://message/?uin=<%=QQ2%>&Site=&menu=yes" target="_blank" class="qq_link_2"><%=name2%></a>
			<a href="tencent://message/?uin=<%=QQ3%>&Site=&menu=yes" target="_blank" class="qq_link_3"><%=name3%></a>
			<a href="tencent://message/?uin=<%=QQ4%>&Site=&menu=yes" target="_blank" class="qq_link_4"><%=name4%></a>
			<a href="tencent://message/?uin=<%=QQ5%>&Site=&menu=yes" target="_blank" class="qq_link_5"><%=name5%></a>
			<a href="tencent://message/?uin=<%=QQ6%>&Site=&menu=yes" target="_blank" class="qq_link_6"><%=name6%></a>
		</div> 
		<div id="topicinput">
			<div class="input_area">
				<div class="tool_bar">
					<a href="javascript:return false;" id="bt_face" class="bar_2 bar"></a>
					<a href="javascript:return false;" id="bt_caitiao" class="bar_3 bar" >
						<div id="caitiao">				
							<dl id="c_pt">
								<dd onclick="sendCaitiao('pt掌声');return false;">掌声</dd>
								<dd onclick="sendCaitiao('pt送鲜花');return false;">送鲜花</dd>
								<dd onclick="sendCaitiao('pt顶一个');return false;">顶一个</dd>
								<dd onclick="sendCaitiao('pt赞一个');return false;">赞一个</dd>
								<dd onclick="sendCaitiao('pt给力');return false;">给力</dd>
								<dd onclick="sendCaitiao('pt棒！');return false;">棒！</dd>
							</dl>
						</div>
					</a>
					<a href="javascript:return false;" class="bar_1 bar" id="bar_1_bar"></a>
				</div>
				<div id="sendMsgInput" contenteditable="true">早开户，早盈利，油、银、铜全天解盘，精准喊单，经得住考验</div>
				<a href="javascript:return false;" class="sub_btn" id="sub_info_btn">Enter</a>
				<p class="person" id="_chats_type_util">
					<strong>发言人:</strong>
					<select id="_users_info">
						<option value="">---无---</option>
					</select>
					<select id="_info_types">
						<option value="all">---无---</option>
					</select>
				</p>
			</div>
		</div>
	</div>
	<div class="video">
		<div class="title">
			<span>在线直播视频</span>
			<a href="javascript:flashVideo();">刷新</a>
		</div>
		<div class="emb" >
			<iframe id="videoFrame" src="video.html" frameborder="0" scrolling="no" width="100%" height="100%"></iframe>
		</div>
		<div class="tab">
			<div class="tabTit">
				<a href="javascript:return false;" class="active tab1">公告</a>
				<a href="javascript:return false;" class="tab2">软件下载</a>
				<a href="javascript:return false;" class="tab3">免责声明</a>
				<a href="javascript:return false;" class="tab4">平台背景</a>
			</div>
			<div class="box">
			<div class="htmleaf-container change">
					<div class="scrolls" >
						<div class="nano has-scrollbar">
						  	<div class="nano-content" tabindex="0" style="right: -17px">
								<div class="section-focus-pic" id="section-focus-pic">
									<div class="pages" data-scro="list">
										 <ul>
											<li class="item" style="left:0px;">
												<img src="images/tab1.jpg">
											</li>
											<li class="item">
												<img src="images/tab2.jpg">
											</li>
											<li class="item">
												<img src="images/tab3.jpg">
											</li>
											<li class="item">
												<img src="images/tab4.jpg">
											</li>
										</ul>
									</div>
									<div class="controler" data-scro="controler">
										<b class="down"></b>
										<b></b>
										<b></b>
										<b></b>
									</div>
									<div class="controler2" data-scro="controler2">
										<a href="javascript:;" class="prev"><i></i></a>
										<a href="javascript:;" class="next"><i></i></a>
									</div>
								</div>
							</div>
						</div>
					 </div>
				</div>
				<div class="htmleaf-container state">
					<div class="scrolls">
						<div class="nano has-scrollbar">
						  	<div class="nano-content" tabindex="0" style="right: -17px">
								<dl class="state1">
									<dt>公告</dt>
									<dd>投资有风险，请您理性分析，切记带好止损止盈，不骄不躁，把控风险，请认准正规平台，远离小平台。</dd>
								</dl>
								<dl class="state2">
									<dt>免责声明</dt>
									<dd>分析师发表的言论均代表其个人对于投资环境的市场观点，投资者应充分考虑投资风险，谨慎操作。</dd>
								</dl>
								<dl class="state3">
									<dt>温馨提示</dt>
									<dd>
										<p>一、选择多空方向时，切记不要情绪化，宁可错过机会，决不冒险做错！</p>
										<p>二、战胜自己贪婪、急躁、失控的情绪、没有警戒意识等很容易让投资者忽略市场走势，进而导致错误的交易决定。</p>
										<p>三、闲钱投机，赢钱投资，做到手中有粮，心中不慌。</p>
										<p>四、于适当价位果断斩获盈利，于止损价位坚决终止损失，切勿急于开始新的交易，而应冷静观察、分析行情，期待新的交易时机。</p>
										<p>五、应制定每次操作的交易计划，做好市场、技术分析，把握进出点，避免盲目跟风和轻信他人。</p>
									</dd>
								</dl>
							</div>
						</div>
					 </div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="fly" id="isfly" style="display:none;">
	<b class="fly_word">周二（5月10日）课程表：09:30-11:30（股票专场）【伯懿老师】；13:00-16:00【捕手老师】；16:00-18:00【伯懿老师】；18:00-21:00【清风老师】；21:00-24:00【亮剑老师】！</b>
</div>	

<div class="alertBox">
	<div class="alertqq">
		<span>在线客服：</span>
		<a href="tencent://message/?uin=<%=QQ1%>&Site=&menu=yes" target="_blank" class="qq_link_1"><%=name1%></a>
		<a href="tencent://message/?uin=<%=QQ2%>&Site=&menu=yes" target="_blank" class="qq_link_2"><%=name2%></a>
		<a href="tencent://message/?uin=<%=QQ3%>&Site=&menu=yes" target="_blank" class="qq_link_3"><%=name3%></a>
		<a href="tencent://message/?uin=<%=QQ4%>&Site=&menu=yes" target="_blank" class="qq_link_4"><%=name4%></a>
		<a href="tencent://message/?uin=<%=QQ5%>&Site=&menu=yes" target="_blank" class="qq_link_5"><%=name5%></a>
		<a href="tencent://message/?uin=<%=QQ6%>&Site=&menu=yes" target="_blank" class="qq_link_6"><%=name6%></a>
	</div>
	<a href="javascript:;" class="reg"></a>
	<a href="javascript:;" class="alclose"></a>
</div>
<div class="look" id="is_look" style="display:none;">
	<a href="javascript:;" class="reg"></a>
	<p>
		<a href="tencent://message/?uin=<%=QQ1%>&Site=&menu=yes" target="_blank" class="qq_link_1"><%=name1%></a>
		<a href="tencent://message/?uin=<%=QQ2%>&Site=&menu=yes" target="_blank" class="qq_link_2"><%=name2%></a>
		<a href="tencent://message/?uin=<%=QQ3%>&Site=&menu=yes" target="_blank" class="qq_link_3"><%=name3%></a>
		<a href="tencent://message/?uin=<%=QQ4%>&Site=&menu=yes" target="_blank" class="qq_link_4"><%=name4%></a>
		<a href="tencent://message/?uin=<%=QQ5%>&Site=&menu=yes" target="_blank" class="qq_link_5"><%=name5%></a>
		<a href="tencent://message/?uin=<%=QQ6%>&Site=&menu=yes" target="_blank" class="qq_link_6"><%=name6%></a>
	</p>
	<a href="javascript:;" class="loclose"></a>
</div>

<div id="draggable" style="display:none;">
	<h2>信息审核</h2>
	<div class="htmleaf-container">
		<div id="scrolls" class="scrolls">
		    <div class="nano has-scrollbar">
		      	<div class="nano-content" tabindex="0" style="right: -17px;">
		    		<div class="info" id="_valid_info_box">
		    			
		    		</div>
		    	</div>
		    </div>
		 </div>
	</div>
	<a href="javascript:return false;" class="draClose"></a>
</div>
<div class="pop hide"></div>
<div class="perMain">
	<div class="personal" style="display:none;">
		<h2>个人中心</h2>
		<div class="perBox">
			<p id="_info_account">帐户：清风</p>
			<p>等级昵称：<img id="_info_level" src="images/icon2.png" alt="皇冠"><a href="javascript:return false;" id="_info_nickName">独一无二的肉松饼</a></p>
		</div>
		<form action="javascript:return false;" method="post" class="alter">
			<dl>
				<dt>修改昵称<img src="images/alterDown.png"></dt>
				<dd>
					<input type="text" placeholder="输入新昵称" class="tex" id="newNickName"/>
					<button class="sbm sbmName" id="editNickName">确定修改</button>
				</dd>
			</dl>
			<dl>
				<dt>修改密码<img src="images/alterDown.png"></dt>
				<dd>
					<input type="text" placeholder="请输入原密码" class="tex" id="oldPwd"/>
					<input type="text" placeholder="请输入新密码" class="tex" id="newPwd"/>
					<input type="text" placeholder="请重复输入新密码" class="tex" id="reNewPwd"/>
					<button class="sbm" id="editPwd">确定修改</button>
				</dd>
			</dl>
		</form>
		<a href="javascript:return false;" class="close"></a>
	</div>
</div>
<div class="calendar hide">
	<iframe src="http://dataapi.fx678.com/jian_g_j_y/cal" frameborder="0" style="width:100%; height:100%"></iframe>
	<a href="javascript:;" class="close"></a>
</div>
<div class="course hide">
	<h2>课程安排<a href="javascript:return false;" class="close"></a></h2>
	<div class="cou" >
		<table>
			<thead>
				<tr>
					<th>时间</th>
					<th>周一</th>
					<th>周二</th>
					<th>周三</th>
					<th>周四</th>
					<th>周五</th>
				</tr>
			</thead>
			<tbody id="kcb_tbody">
				<tr>
					<td><%=kcb.getTime1_time() %></td>
					<td>
						<strong><%=kcb.getTime1_lesson_mon() %></strong>
						<p><%=kcb.getTime1_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime1_lesson_tue() %></strong>
						<p><%=kcb.getTime1_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime1_lesson_wed() %></strong>
						<p><%=kcb.getTime1_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime1_lesson_thu() %></strong>
						<p><%=kcb.getTime1_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime1_lesson_fri() %></strong>
						<p><%=kcb.getTime1_teacher_fri() %></p>
					</td>
				</tr>
				<tr>
					<td><%=kcb.getTime2_time() %></td>
					<td>
						<strong><%=kcb.getTime2_lesson_mon() %></strong>
						<p><%=kcb.getTime2_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime2_lesson_tue() %></strong>
						<p><%=kcb.getTime2_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime2_lesson_wed() %></strong>
						<p><%=kcb.getTime2_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime2_lesson_thu() %></strong>
						<p><%=kcb.getTime2_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime2_lesson_fri() %></strong>
						<p><%=kcb.getTime2_teacher_fri() %></p>
					</td>
				</tr>
				<tr>
					<td><%=kcb.getTime3_time() %></td>
					<td>
						<strong><%=kcb.getTime3_lesson_mon() %></strong>
						<p><%=kcb.getTime3_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime3_lesson_tue() %></strong>
						<p><%=kcb.getTime3_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime3_lesson_wed() %></strong>
						<p><%=kcb.getTime3_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime3_lesson_thu() %></strong>
						<p><%=kcb.getTime3_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime3_lesson_fri() %></strong>
						<p><%=kcb.getTime3_teacher_fri() %></p>
					</td>
				</tr>
				<tr>
					<td><%=kcb.getTime4_time() %></td>
					<td>
						<strong><%=kcb.getTime4_lesson_mon() %></strong>
						<p><%=kcb.getTime4_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime4_lesson_tue() %></strong>
						<p><%=kcb.getTime4_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime4_lesson_wed() %></strong>
						<p><%=kcb.getTime4_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime4_lesson_thu() %></strong>
						<p><%=kcb.getTime4_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime4_lesson_fri() %></strong>
						<p><%=kcb.getTime4_teacher_fri() %></p>
					</td>
				</tr>
				<tr>
					<td><%=kcb.getTime5_time() %></td>
					<td>
						<strong><%=kcb.getTime5_lesson_mon() %></strong>
						<p><%=kcb.getTime5_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime5_lesson_tue() %></strong>
						<p><%=kcb.getTime5_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime5_lesson_wed() %></strong>
						<p><%=kcb.getTime5_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime5_lesson_thu() %></strong>
						<p><%=kcb.getTime5_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime5_lesson_fri() %></strong>
						<p><%=kcb.getTime5_teacher_fri() %></p>
					</td>
				</tr>
				<tr>
					<td><%=kcb.getTime6_time() %></td>
					<td>
						<strong><%=kcb.getTime6_lesson_mon() %></strong>
						<p><%=kcb.getTime6_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime6_lesson_tue() %></strong>
						<p><%=kcb.getTime6_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime6_lesson_wed() %></strong>
						<p><%=kcb.getTime6_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime6_lesson_thu() %></strong>
						<p><%=kcb.getTime6_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime6_lesson_fri() %></strong>
						<p><%=kcb.getTime6_teacher_fri() %></p>
					</td>
				</tr>
				<tr>
					<td><%=kcb.getTime7_time() %></td>
					<td>
						<strong><%=kcb.getTime7_lesson_mon() %></strong>
						<p><%=kcb.getTime7_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime7_lesson_tue() %></strong>
						<p><%=kcb.getTime7_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime7_lesson_wed() %></strong>
						<p><%=kcb.getTime7_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime7_lesson_thu() %></strong>
						<p><%=kcb.getTime7_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime7_lesson_fri() %></strong>
						<p><%=kcb.getTime7_teacher_fri() %></p>
					</td>
				</tr>
				<tr>
					<td><%=kcb.getTime8_time() %></td>
					<td>
						<strong><%=kcb.getTime8_lesson_mon() %></strong>
						<p><%=kcb.getTime8_teacher_mon() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime8_lesson_tue() %></strong>
						<p><%=kcb.getTime8_teacher_tue() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime8_lesson_wed() %></strong>
						<p><%=kcb.getTime8_teacher_wed() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime8_lesson_thu() %></strong>
						<p><%=kcb.getTime8_teacher_thu() %></p>
					</td>
					<td>
						<strong><%=kcb.getTime8_lesson_fri() %></strong>
						<p><%=kcb.getTime8_teacher_fri() %></p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="eia hide">
	<h2>EIA原油库存<a href="javascript:;" class="close"></a></h2>
	<div class="eiaBox">
	</div>
</div>
<div class="unNum hide">
	<h2>非农数据<a href="javascript:;" class="close"></a></h2>
	<div class="unBox">
	</div>
</div>
<div class="download hide">
	<ul>
		<li class="current down1">川商商品交易中心<i></i></li>
		<li>行情软件APP下载<i></i></li>
		<li>手机端下载</li>
	</ul>
	<div class="load">
		<div class="one">
			<dl>
				<dt><img src="images/load1.png"></dt>
				<dd>
					<p>川商商品交易中心</p>
					<a href="http://www.sccsce.com/download/SCCjinchenganznzuo.apk">手机客户端</a>
					<b>（APP端）</b>
				</dd>
			</dl>
			<dl>
				<dt><img src="images/load2.png"></dt>
				<dd>
					<p>川商商品交易中心</p>
					<a href="http://www.sccsce.com/download/%E5%B7%9D%E5%95%86%E5%95%86%E5%93%81%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E8%A1%8C%E6%83%85%E5%88%86%E6%9E%90%E7%B3%BB%E7%BB%9F.rar">行情软件</a>
					<b>（PC端）</b>
				</dd>
			</dl>
			<dl>
				<dt><img src="images/load3.png"></dt>
				<dd>
					<p>川商商品交易中心</p>
					<a href="http://www.sccsce.com/download/%E5%B7%9D%E5%95%86%E5%95%86%E5%93%81%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E4%BA%A4%E6%98%93%E7%B3%BB%E7%BB%9F1.rar">实盘软件</a>
					<b>（PC端）</b>
				</dd>
			</dl>
		</div>
		<div class="three">
			<dl>
				<dt><img src="images/ma1.png"></dt>
				<dd>
					<a href="javascript:;">APP掌上原油行情</a>
				</dd>
			</dl>
		</div>
		<div class="three">
			<dl>
				<dt><img src="images/ma2.png"></dt>
				<dd>
					<a href="javascript:;">微信</a>
				</dd>
			</dl>
		</div>
	</div>
	<a href="javascript:;" class="close"></a>
</div>

<div class="plat hide">
	<h2>平台背景</h2>
	<div class="platBox hide" id="platBox">
		<h3>川商中心简介：</h3>
		<p>鑫西都作为川商中心286号会员单位，能更好地为投资者提供专业的金融服务。四川川商商品交易中心有限公司是经四川省人民政府设立的创新型大宗商品交易服务平台，也是目前四川省唯一一家由省政府批复成立的交易平台。注册资本金1.2亿，依托于四川省西部经济中心的区位优势，以立足四川、辐射全国、放眼全球为发展目标，以“金融创新”为宗旨，为有色金属、矿产品以及农产品等商品提供现货交易及现货电子交易和相关商品的结算、交收、物流等方面的专业金融服务。</p>
		<div class="platImg"><img src="images/platImg.jpg" width="344"></div>
		<table>
			<caption>川商中心产品介绍</caption>
			<thead>
				<tr>
					<th>交易品种</th>
					<th colspan="4">川商燃料油</th>
					<th colspan="4">川商银</th>
					<th colspan="2">川商铜</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="tabName">交易品种代码</td>
					<td>R020</td>
					<td>R050</td>
					<td>R0100</td>
					<td>R0200</td>
					<td>AG5</td>
					<td>AG25</td>
					<td>AG50</td>
					<td>AG100</td>
					<td>Cu5</td>
					<td>Cu20</td>
				</tr>
				<tr>
					<td class="tabName">交易单位</td>
					<td>20吨/手</td>
					<td>50吨/手</td>
					<td>100吨/手</td>
					<td>200吨/手</td>
					<td>5千克/手</td>
					<td>25千克/手</td>
					<td>50千克/手</td>
					<td>100千克/手</td>
					<td>5吨/手</td>
					<td>20吨/手</td>
				</tr>
				<tr>
					<td class="tabName">报价单位</td>
					<td colspan="4">人民币元/吨</td>
					<td colspan="4">人民币元/千克</td>
					<td colspan="2">人民币元/吨</td>
				</tr>
				<tr>
					<td class="tabName">最小变价单位</td>
					<td colspan="4">1元/吨</td>
					<td colspan="4">1元/千克</td>
					<td colspan="2">1元/吨</td>
				</tr>
				<tr>
					<td class="tabName">合约期限</td>
					<td colspan="10">连续交易</td>
				</tr>
				<tr>
					<td class="tabName" rowspan="2">交易时间</td>
					<td colspan="8">周一  8:00 ---- 周六 4:00   连续交易</td>
					<td colspan="2">周一 8:00(夏令时)/9:00(冬令时)----周六2:00(夏令时)/3:00(冬令时)连续交易</td>
				</tr>
				<tr>
					<td colspan="10">（清算时间、国家法定假日及国际假日休市除外）</td>
				</tr>
				<tr>
					<td class="tabName">结算时间</td>
					<td colspan="10">每个交易日夏令时凌晨  4:00--6:00冬令时凌晨  4:00--7:00停止交易</td>
				</tr>
				<tr>
					<td class="tabName">最低履约准备金</td>
					<td>5%</td>
					<td>4%</td>
					<td colspan="2">3%</td>
					<td>20%</td>
					<td colspan="3">3%</td>
					<td>20%</td>
					<td>3%</td>
				</tr>
				<tr>
					<td class="tabName">报价点差</td>
					<td colspan="4">5元/吨</td>
					<td colspan="4">5元/千克</td>
					<td colspan="2">35元/吨</td>
				</tr>
				<tr>
					<td class="tabName">手续费率</td>
					<td colspan="10">0.08%</td>
				</tr>
				<tr>
					<td class="tabName">延期费率</td>
					<td colspan="10">0.01%</td>
				</tr>
				<tr>
					<td class="tabName">申报交割时间</td>
					<td colspan="10">每个交易日上午10点----下午16点</td>
				</tr>
				<tr>
					<td class="tabName">交割地点</td>
					<td colspan="10">交易中心及交易中心授权会员交割库</td>
				</tr>
				<tr>
					<td class="tabName">最小交割量</td>
					<td colspan="4">500吨</td>
					<td>50千克</td>
					<td>250千克</td>
					<td>500千克</td>
					<td>1000千克</td>
					<td colspan="2">50吨</td>
				</tr>
			</tbody>
		</table>
		<h3>川商中心八大优势：</h3>
		<div class="goodBox">
			<dl>
				<dt>1</dt>
				<dd>
					<h4>资质优势</h4>
					<p>经四川省人民政府设立的创新型大宗商品交易服务平台。</p>
				</dd>
			</dl>
			<dl>
				<dt>2</dt>
				<dd>
					<h4>税收优势</h4>
					<p>为会员提供最低的税收标准。</p>
				</dd>
			</dl>
			<dl>
				<dt>3</dt>
				<dd>
					<h4>品种优势</h4>
					<p>产品结构合理投资渠道丰富。</p>
				</dd>
			</dl>
		</div>
		<div class="goodBox">
			<dl>
				<dt>4</dt>
				<dd>
					<h4>银行支持优势</h4>
					<p>中国建设银行、中国农业银行为会员及客户提供交易结算服务。 </p>
				</dd>
			</dl>
			<dl>
				<dt>5</dt>
				<dd>
					<h4>配套优势</h4>
					<p>软硬件配套处于行业领先水平。</p>
				</dd>
			</dl>
			<dl>
				<dt>6</dt>
				<dd>
					<h4>增值优势</h4>
					<p>服务体系完善、专业、贴心。</p>
				</dd>
			</dl>
		</div>
		<div class="goodBox goodLast">
			<dl>
				<dt>7</dt>
				<dd>
					<h4>股东结构优势</h4>
					<p>中国铁路物资集团作为交易中心股东，提高平台公信力。</p>
				</dd>
			</dl>
			<dl>
				<dt>8</dt>
				<dd>
					<h4>模式的优势</h4>
					<p>真正实现线上交易，线下交割的现货交易模式。</p>
				</dd>
			</dl>
		</div>
	</div>
	<a href="javascript:;" class="close"></a>
</div>

<div class="register hide">
	<h1>用户注册<span><i></i>设置帐号和密码，在线观看直播视频。</span></h1>
	<div style="width: 330px; margin-left: 77px; margin-top: 32px">
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
			<p class="yan">
				<label class="lab7"></label>
				<input type="text" placeholder="QQ" required="required" id="qq">
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
		<div id="huakuai"></div>
		<div>
		   <p class="yan">
				<label class="lab6"></label>
				<input type="text" placeholder="手机短信验证码" required="required" id="mobileCode">
		   </p>
		   <span>*</span>
		</div>
		<button class="mit" id="register">完成注册</button>
	</div>
	<a href="javascript:return false;" class="close"></a>
</div>
<div class="login hide">
	<h1>用户登录<span><i></i>还未注册帐户？赶紧注册一个<a href="javascript:return false;">GO</a></span></h1>
	<div style="width: 304px; margin: 31px auto 0" onKeyDown="entersubmit(event)">
		<p>
			<label class="lab1"></label>
			<input type="text" placeholder="用户名" required="required" id="userName_login">
		</p>
		<p>
			<label class="lab3"></label>
			<input type="password" placeholder="密码" required="required" id="pwd_login">
		</p>
		<button id="login" class="mit">马上登录</button>
	</div>
	<a href="javascript:return false;" class="close"></a>
</div>

<iframe frameborder="0" src="uploadIMG.jsp?t=123" scrolling="no" style="display:none" id="uploadImg" name="uploadImg"></iframe>
<iframe id="qqAlertFrame" src="qqAlert.html" frameborder="0" scrolling="no" width="100%" height="100%"></iframe> 
<script type="text/javascript" src="js/defaultParam.js"></script>
<script type="text/javascript" src="js/jquery-1.9.js"></script>
<script type="text/javascript" src="js/jquery.mCustomScrollbar.js"></script><!--滚动-->
<script type="text/javascript" src="js/jquery.sinaEmotion.js"></script><!--表情彩条-->
<script type="text/javascript" src="js/jquery-ui.js"></script><!--jquery-ui-->
<script type="text/javascript" src="js/scrollTo.js"></script>
<script type="text/javascript" src="js/resize.js"></script>
<script type="text/javascript" src="js/jquery.JPlaceholder.js"></script>
<script type="text/javascript" src="js/tinybox.js"></script>
<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/jquery.nanoscroller.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="js/indexRegister.js"></script>
<script type="text/javascript" src="js/indexDo.js"></script>
<script type="text/javascript" src="js/fileup.js"></script>
<script type="text/javascript" src="js/editNickNameAndPwd.js"></script>
<script type="text/javascript" src="js/drag.js"></script>
<script type="text/javascript">
$("#platBox").niceScroll({  
	cursorcolor:"#b0b0b0",  
	cursoropacitymax:1,  
	touchbehavior:false,  
	cursorwidth:"5px",  
	cursorborder:"0",  
	cursorborderradius:"5px"  
}); 
</script>

<%
    String c_json = ""; 
%>

<script type="text/javascript">
    $('#huakuai').huakuai();
    sessionId = "<%=sessionId%>";
    time = "<%=time%>";
    
    flashVideo();
			   <%
		      DoLoginService loginService = new DoLoginService();
		      String roomNo = "1";
			  String userName = "";
			  String pwd = "";
			  String ip = new IPUtil().getRemortIP(request);
		      Cookie[] cookies = request.getCookies(); 
		      if(cookies != null){
		    	  for(int i=0;i<cookies.length;i++){
				       if(cookies[i].getName().equals("_Room1_loginName")){  
				              userName = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
				       }
				       if(cookies[i].getName().equals("_Room1_loginPwd")){  
				              pwd = cookies[i].getValue();
				       }
				   }
		      }
			  
		      String[] param = {userName,pwd,roomNo,String.valueOf(time),ip};
			  Customer customerResult = loginService.doLogin(param);
			  session.setAttribute("customer",customerResult);
			  if(customerResult != null){
				  c_json = customerResult.toJson().toString();
			  }
		   %>
	   $(".pop").hide();
       $(".course").hide();
       $(".download").hide();
       $(".register").hide();
       $(".log_out").show();
       $(".login").hide();   //隐藏登录框
       var json = <%=c_json%>;  //将后台传来的json格式字符串转换为object
       $(".head_right").find("span").find("a").html(json.userName);  //将用户名显示到页面上
       var level = json.level;  //获取用户等级
       var src = "images/icon"+(7-parseInt(level))+".png";
       $(".head_right").find("span").find("img").attr("src",src);
       customer=json;
       setCookie("_Room1_loginName", customer.userName, 10);
       setCookie("_Room1_loginPwd", customer.pwd, 10);
       initAction();
	   //初始化时就生成一张图形验证码	
	   //$("#image").attr('src',"../controller/code.jpg;jsessionid="+sessionId);
</script>
<script type="text/javascript" src="js/track.js"></script>
</body>
</html>