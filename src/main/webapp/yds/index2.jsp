﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<%@ page import="com.xidu.util.UtilForYkOnOff" %>
	<%
		String roomNo = "2";
	   UtilForQQ uq = new UtilForQQ();
       String QQ1 = uq.getQQ("room"+roomNo+".QQ1");
       String QQ2 = uq.getQQ("room"+roomNo+".QQ2");
       String QQ3 = uq.getQQ("room"+roomNo+".QQ3");
       String QQ4 = uq.getQQ("room"+roomNo+".QQ4");
       String QQ5 = uq.getQQ("room"+roomNo+".QQ5");
       String QQ6 = uq.getQQ("room"+roomNo+".QQ6");
       String name1 = uq.getQQ("room"+roomNo+".name1");
       String name2 = uq.getQQ("room"+roomNo+".name2");
       String name3 = uq.getQQ("room"+roomNo+".name3");
       String name4 = uq.getQQ("room"+roomNo+".name4");
       String name5 = uq.getQQ("room"+roomNo+".name5");
       String name6 = uq.getQQ("room"+roomNo+".name6");
       
       HttpSession ss=request.getSession(true);
       Object freshTime=ss.getAttribute("fresh");
       if(freshTime!=null){
	       int fresh=Integer.parseInt(freshTime.toString())+1;
	       ss.setAttribute("fresh", fresh);
	       ss.setAttribute("freshTime", new Date().getTime());
	       if(fresh>=5){
	    	   if(new Date().getTime()-Long.valueOf(ss.getAttribute("freshTime").toString())>30*60*1000L){
	    		   ss.setAttribute("fresh", 1);
	    	   }else{
		    	   //response.sendRedirect("http://www.xiduoil.com");
	    	   }
	       }
       }else{
    	   ss.setAttribute("fresh", 1);
       }
       
       String sessionId = ss.getId();
	   Long time = System.currentTimeMillis();
	   ss.setAttribute("time",time);
	   
	   KCB kcb =WebSocketPool.getInstance().getRoom(roomNo).getKcb();
	   
 	  String c_json = ""; 
      DoLoginService loginService = new DoLoginService();
      
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
	  String yk_on_off = new UtilForYkOnOff().getYkOnOff();
	  if(customerResult != null){
		  if(customerResult.getLevel() > 0){   //非游客
			  ss.setAttribute("customer",customerResult);
		  }else{
			   if(yk_on_off.equals("1")){   //开启游客功能
				   ss.setAttribute("customer",customerResult);
			  }
		  }
		  c_json = customerResult.toJson().toString();
	  }
   %>
   
<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<title>银大师直播间_白银直播间_现货直播间_现货白银直播间_白银走势分析直播间</title>
<meta name="description" content="银大师直播间汇集顶尖专业分析师团队，特邀名师解盘，24小时不间断连播，为您提供最新最全面最专业的贵金属，白银投资，原油，石油，EIA,非农数据分析，行情分析操作建议走势分析等服务，完美狙击大行情" />
<meta name="keywords" content="白银走势分析直播间,现货白银直播间,现货黄金直播间,现货直播间,白银直播间,贵金属直播间,原油直播间，EIA行情分析,非农数据分析，现货白银直播室,现货白银直播间喊单,白银喊单，鑫西都,银大师直播间" />

	<link rel="shortcut icon" type="image/x-icon" href="favicon/favicon.ico">
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/layout.css" />
	<link rel="stylesheet" href="css/jquery.mCustomScrollbar.css"/><!--滚动-->
	<link rel="stylesheet" href="css/jquery.sinaEmotion.css"/><!--表情-->
	<link href="jcountdown/jcountdown.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/html5shiv.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="js/mobile.js"></script>
	<link rel="stylesheet" href="css/drag.css"/>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?14af6bb7c52a452bb2dbc3b42a24ed72";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

	
</head>
<body class="outside">
<span style="display:none" ><script type="text/javascript"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1260323262'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1260323262' type='text/javascript'%3E%3C/script%3E"));</script></span>
<div class="bg layout_sider_left layout_video_right btnColor1">
	<header>
		<h1></h1>
		<a href="javascript:;" class="desktop"></a>
		<nav class="sub">
			<a href="javascript:;" class="calHead">财经日历</a>
			<a href="javascript:;" class="eiaHref">EIA原油库存</a>
			<a href="javascript:;" class="unHref">非农数据</a>
			<a href="javascript:;" class="unRead">新手礼包<span>1</span></a>
			<a href="tencent://message/?uin=2853573049&Site=&menu=yes" target="_blank" class="forthwith"><img src="images/forthwith.gif"></a>
		</nav>
		<div class="head_right">
			<b href="javascript:;" class="layout" style="display:none;">
				<b></b>
				<div class="layMenu" id="layMenu">
						<div class="layBox">
							<h2><span>个性化</span></h2>
							<h3>布局</h3>
							<div class="layoutWrap">
								<h4>固定菜单位置</h4>
								<div class="layoutSider">
									<span class="layoutSiderSpan layoutSider_left active" data-theme="layout_sider_left"></span>
									<span class="layoutSiderSpan layoutSider_right" data-theme="layout_sider_right"></span>
								</div>
								<h4>视频位置</h4>
								<div class="layoutVideo">
									<span class="layoutVideoSpan layoutVideo_left" data-theme="layout_video_left"></span>
									<span class="layoutVideoSpan layoutVideo_center" data-theme="layout_video_center"></span>
									<span class="layoutVideoSpan layoutVideo_right active" data-theme="layout_video_right"></span>
								</div>
							</div>
							<h4>按钮颜色</h4>
							<div class="btnColorWrap">
								<span class="btnColor color1 active" data-theme="btnColor1"></span>
								<span class="btnColor color2" data-theme="btnColor2"></span>
								<span class="btnColor color3" data-theme="btnColor3"></span>
								<span class="btnColor color4" data-theme="btnColor4"></span>
								<span class="btnColor color5" data-theme="btnColor5"></span>
								<span class="btnColor color6" data-theme="btnColor6"></span>
								<span class="btnColor color7" data-theme="btnColor7"></span>
								<span class="btnColor color8" data-theme="btnColor8"></span>
								<span class="btnColor color9" data-theme="btnColor9"></span>
							</div>
							<h4>背景色</h4>
							<div class="backWrap">
								<span class="backColor back1" data-theme="backBg1"></span>
								<span class="backColor back2" data-theme="backBg2"></span>
								<span class="backColor back3" data-theme="backBg3"></span>
								<span class="backColor back4" data-theme="backBg4"></span>
								<span class="backColor back5" data-theme="backBg5"></span>
								<span class="backColor back6" data-theme="backBg6"></span>
								<span class="backColor back7" data-theme="backBg7"></span>
								<span class="backColor back8" data-theme="backBg8"></span>
								<span class="backColor back9" data-theme="backBg9"></span>
							</div>
							<h4>背景图</h4>
							<div class="backWrap bgWrap">
								<span class="backBg bg1 active" data-theme="backBg10"></span>
								<span class="backBg bg2" data-theme="backBg11"></span>
								<span class="backBg bg3" data-theme="backBg12"></span>
								<span class="backBg bg4" data-theme="backBg13"></span>
								<span class="backBg bg5" data-theme="backBg14"></span>
								<span class="backBg bg6" data-theme="backBg15"></span>
								<span class="backBg bg7" data-theme="backBg16"></span>
								<span class="backBg bg8" data-theme="backBg17"></span>
								<span class="backBg bg9" data-theme="backBg18"></span>
							</div>
						</div>
				</div>
				
			</b>
			<a href="javascript:;" class="exit" style="display:none" id="out">退出</a>
			<a href="javascript:;" class="headLog log"></a>
			<a href="javascript:;" class="headReg reg"></a>
			<p class="time1">24小时客服电话：<span>4001-054-080</span></p>
			<p class="time2" style="display:none">尊贵的会员，您可以永久免费观看视频</p>
		</div>
	</header>
	<div class="listLeft">
		<a href="javascript:;" class="courseHref">
			<i class="listIcon list1"></i>
			<span>课程安排</span>
		</a>
		<a href="javascript:;" class="leftHref" id="free">
			<i class="listIcon list2"></i>
			<span>免费开户</span>
		</a>
		<a href="javascript:;" class="leftHref" id="onetoone">
			<i class="listIcon list3"></i>
			<span>1对1指导</span>
		</a>
		<a href="javascript:;" class="leftHref" id="kefu">
			<i class="listIcon list4"></i>
			<span>专属客服</span>
		</a>
		<a href="javascript:;" class="teach">
			<i class="listIcon list5"></i>
			<span>老师介绍</span>
		</a>
	</div>	
	<div id="list">
		<div class="listRight">
			<div class="nickname">
				<p id="level_icon"></p>
				<p class="perName">昵称：彬彬雪依彬雪依依<img src="images/listDowm.png"></p>
			</div>
			<ul class="nameLine">
				<li class="member current">在线会员(
				<%
					List<Customer> customerList=SessionCounter.getAllLiveCustomer();
					for(int i=customerList.size()-1;i>=0;i--){
						if(!roomNo.equals(customerList.get(i).getRoomNo())){
							customerList.remove(i);
						}
					}
					
					if(customerResult.getLevel()<80){
						out.print(customerList.size()+WebSocketPool.getInstance().getRobotlist().size());
					}else{
						out.print(customerList.size());
					}
				%>)</li>
				<li  class="custom">我的客户(
				<%
					List<Customer> myCustomers=null;
					if(customerResult != null){
						myCustomers=SessionCounter.getCustomerByUserId(customerResult.getId());
						for(int i=myCustomers.size()-1;i>=0;i--){
							if(!roomNo.equals(myCustomers.get(i).getRoomNo())){
								myCustomers.remove(i);
							}
						}
						out.print(myCustomers.size());
					}else{
						out.print(0);
					}
				%>
				)</li>
			</ul>
			<div class="htmleaf-container nameList">
				<div class="scrolls">
					<div class="nano has-scrollbar">
					  	<div class="nano-content userlist" tabindex="0" style="right: -17px">
							<ul>
								<%
								if(customerList!=null&&customerList.size()>0){
									for(Customer c:customerList){
										if(c!=null&&c.getLevel()<10){
										%>
											<li 
												<%
													if(customerResult!=null&&customerResult.getLevel()>80){
														out.print("data-id='"+c.getId()+"'");
													}else{
														out.print("data-id='"+c.getNickName()+"'");
													}
												%>
											>
												<span><%=c.getNickName() %></span>
												<img src="images/list<%= c.getLevel() %>.png">
												<div>
														<a href="javascript:;" onclick="is_chat()">禁言</a>
														<!-- <a href="javascript:;">踢出聊天室</a>
														<a href="javascript:;">可观看视频时间</a> -->
													</div>
											</li>
										<%
										}
									}
								}
								%>
								<%
									if(customerResult.getLevel()<80){
										List<Robot> robotList=WebSocketPool.getInstance().getRobotlist();
										for(Robot robot:robotList){
										%>
											<li class="r">
												<span><%=robot.getName() %></span>
												<img src="images/list<%= robot.getLevel() %>.png">
											</li>
										<%
										}
									}
								%>
							</ul>
						</div>
						
						<div class="nano-content userlist" tabindex="0" style="right: -17px;display:none;">
							<ul>
								<%
									if(myCustomers!=null&&myCustomers.size()>0){
										for(Customer c:myCustomers){
										%>
											<li>
												<span><%=c.getNickName() %></span>
												<img src="images/list<%= c.getLevel() %>.png">
											</li>
										<%
										}
									}
								%>
							</ul>
						</div>
						
					</div>
				 </div>
			</div>
		</div>	
		<div id="main">
			<div class="main">
				<div id="topic" style="height: 464px;">
					<div class="topicMain">
						<div class="notice">
							<b></b>
							<div id="marq">
								<ul>
									<li></li>
								</ul>
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
						<div class="msg"  id="bottomChats"></div>
						<div class="qqbts">
							<span>在线客服：</span>
							<a href="javascript:;" class="qqMore">更多客服&gt;&gt;</a>
							<a href="tencent://message/?uin=<%=QQ1%>&Site=&menu=yes" target="_blank" class="qq_link_1"></a>
						    <a href="tencent://message/?uin=<%=QQ2%>&Site=&menu=yes" target="_blank" class="qq_link_2"></a>
						    <a href="tencent://message/?uin=<%=QQ3%>&Site=&menu=yes" target="_blank" class="qq_link_3"></a>
						    <a href="tencent://message/?uin=<%=QQ4%>&Site=&menu=yes" target="_blank" class="qq_link_4"></a>
						    <a href="tencent://message/?uin=<%=QQ5%>&Site=&menu=yes" target="_blank" class="qq_link_5"></a>
						    <a href="tencent://message/?uin=<%=QQ6%>&Site=&menu=yes" target="_blank" class="qq_link_6"></a>
						</div> 
						<div id="topicinput">
							<div class="input_area">
								<div class="tool_bar">
									<a href="javascript:;" id="bt_face" class="bar_2 bar"></a>
									<a href="javascript:;" id="bt_caitiao" class="bar_3 bar" >
										<div id="caitiao">				
											<dl id="c_pt">
												<dd onclick="sendCaitiao('pt掌声')">掌声</dd>
												<dd onclick="sendCaitiao('pt送鲜花')">送鲜花</dd>
												<dd onclick="sendCaitiao('pt顶一个')">顶一个</dd>
												<dd onclick="sendCaitiao('pt赞一个')">赞一个</dd>
												<dd onclick="sendCaitiao('pt给力')">给力</dd>
												<dd onclick="sendCaitiao('pt棒！')">棒！</dd>
											</dl>
										</div>
									</a>
									<a href="javascript:;" class="bar_1 bar" id="bar_1_bar"></a>
								</div>
								<div class="inputBox">
									<div id="sendMsgInput" contenteditable="true">早开户，早盈利，油、银、铜全天解盘，精准喊单，经得住考验</div>
								</div>
								<a href="javascript:return false;" class="sub_btn" id="sub_info_btn">发送</a>
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
				</div>
				<div class="video">
					<div class="videoMain">
						<div class="title">
							<span>在线直播视频</span>
							<a href="javascript:flashVideo();">刷新</a>
							<b>当前可观看视频时间<span class="countdown"></span>秒</b>
						</div>
						<div class="emb">
							<iframe id="videoFrame" src="video.html" frameborder="0" scrolling="no" width="100%" height="100%" allowscriptaccess="always"
                            allowfullscreen="true"
                            wmode="opaque"
                            allowTransparency="true"
                            ></iframe>
						</div>
						<div class="tab">
							<div class="tabTit">
								<a href="javascript:;" class="active tab1">公告</a>
								<a href="javascript:;" class="tab2">软件下载</a>
								<a href="javascript:;" class="tab3">免责声明</a>
								<a href="javascript:;" class="tab4">平台背景</a>
							</div>
							<div class="box">
								<div class="htmleaf-container change">
									<div class="scrolls">
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
														</ul>
													</div>
													<div class="controler" data-scro="controler">
														<b class="down"></b>
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
			</div>
		</div>
	</div>
</div>

<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
<div class="fly" id="isfly" style="display:none;">
	<b class="fly_word">周二（5月10日）课程表：09:30-11:30（股票专场）【伯懿老师】；13:00-16:00【捕手老师】；16:00-18:00【伯懿老师】；18:00-21:00【清风老师】；21:00-24:00【亮剑老师】！</b>
</div>

<div class="alertBox">
	<div class="alertqq">
		<span>在线客服：</span>
		<a href="tencent://message/?uin=<%=QQ1%>&Site=&menu=yes" target="_blank" class="qq_link_1"></a>
		<a href="tencent://message/?uin=<%=QQ2%>&Site=&menu=yes" target="_blank" class="qq_link_2"></a>
		<a href="tencent://message/?uin=<%=QQ3%>&Site=&menu=yes" target="_blank" class="qq_link_3"></a>
		<a href="tencent://message/?uin=<%=QQ4%>&Site=&menu=yes" target="_blank" class="qq_link_4"></a>
		<a href="tencent://message/?uin=<%=QQ5%>&Site=&menu=yes" target="_blank" class="qq_link_5"></a>
		<a href="tencent://message/?uin=<%=QQ6%>&Site=&menu=yes" target="_blank" class="qq_link_6"></a>
	</div>
	<a href="javascript:;" class="reg"></a>
	<a href="javascript:;" class="alclose"></a>
</div>

<div class="look" id="is_look" style="display:none;">
	<a href="javascript:;" class="reg"></a>
	<p>
		<a href="tencent://message/?uin=<%=QQ1%>&Site=&menu=yes" target="_blank" class="qq_link_1"></a>
		<a href="tencent://message/?uin=<%=QQ2%>&Site=&menu=yes" target="_blank" class="qq_link_2"></a>
		<a href="tencent://message/?uin=<%=QQ3%>&Site=&menu=yes" target="_blank" class="qq_link_3"></a>
		<a href="tencent://message/?uin=<%=QQ4%>&Site=&menu=yes" target="_blank" class="qq_link_4"></a>
		<a href="tencent://message/?uin=<%=QQ5%>&Site=&menu=yes" target="_blank" class="qq_link_5"></a>
		<a href="tencent://message/?uin=<%=QQ6%>&Site=&menu=yes" target="_blank" class="qq_link_6"></a>
	</p>
	<a href="javascript:;" class="loclose"></a>
</div>
<div class="leftPop">
	<a href="javascript:;" class="reg"></a>
	<p>
		<a href="tencent://message/?uin=<%=QQ1%>&Site=&menu=yes" target="_blank" class="qq_link_1"></a>
		<a href="tencent://message/?uin=<%=QQ2%>&Site=&menu=yes" target="_blank" class="qq_link_2"></a>
		<a href="tencent://message/?uin=<%=QQ3%>&Site=&menu=yes" target="_blank" class="qq_link_3"></a>
		<a href="tencent://message/?uin=<%=QQ4%>&Site=&menu=yes" target="_blank" class="qq_link_4"></a>
		<a href="tencent://message/?uin=<%=QQ5%>&Site=&menu=yes" target="_blank" class="qq_link_5"></a>
		<a href="tencent://message/?uin=<%=QQ6%>&Site=&menu=yes" target="_blank" class="qq_link_6"></a>
	</p>
	<a href="javascript:;" class="leclose"></a>
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

<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->

<div class="pop hide"></div>
<div class="knowledge hide">
	<h2>宝典下载<a href="javascript:;" class="close"></a></h2>
	<div class="know">
		<dl>
			<dt><img src="images/know1.png"></dt>
			<dd>
				<p>《投资入门手册》</p>
				<a href="javascript:;">点击下载</a>
			</dd>
		</dl>
		<dl>
			<dt><img src="images/know2.png"></dt>
			<dd>
				<p>《投资理财宝典》</p>
				<a href="javascript:;">点击下载</a>
			</dd>
		</dl>
	</div>
</div>
<div class="teachtb hide">
	<h2>老师介绍<a href="javascript:;" class="close"></a></h2>
	<div class="teachBox">
		<dl>
			<dt>
				<img src="images/teachBox1.jpg">
			</dt>
			<dd>
				<h3>拙锋老师<span>(15从业时间 )</span></h3>
				<p>近15年金融行业从业经验，拥有丰富的实战操盘经验，在不断的实践中形成了自己的分析思路及投资理念，构筑了自己的交才能稳健盈利。面对不同的市场，通过控制仓位来控制风险，简单战法严格执行稳健盈利。</p>
				<ul>
					<li>总收益：<span class="zong">7981500</span> </li>
					<li>月收益：<span class="yue">362000</span> </li>
					<li>胜率：<span>87.02</span> </li>
					<li>盈利点数：<span>103820</span> </li>
					<li>日均单量：<span>1.10 </span> </li>
					<li>单均收益率：<span>0.73</span> </li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt>
				<img src="images/teachBox2.jpg">
			</dt>
			<dd>
				<h3>伯懿老师<span>(10从业时间 )</span></h3>
				<p>毕业于华东政法大学金融管理专业，毕业后曾先后就职于国际知名外汇交易商以及国内大型商品交易机构，专职从事外汇，原油，贵金属等现货衍生品种的分析和交易工作。操盘风格稳健，分析思路清晰、缜密，圈内负有"短线圣手"的美名。</p>
				<ul>
					<li>总收益：<span class="zong">13706500</span> </li>
					<li>月收益：<span class="yue">300000</span> </li>
					<li>胜率：<span>87.25</span> </li>
					<li>盈利点数：<span>191245</span> </li>
					<li>日均单量：<span>0.86</span> </li>
					<li>单均收益率：<span>0.95</span> </li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt>
				<img src="images/teachBox3.jpg">
			</dt>
			<dd>
				<h3>清风老师<span>(12从业时间 )</span></h3>
				<p>毕业于上海财经大学金融系，学生时代即涉足外汇，贵金属等交易领域，并极具交易天赋。毕业后曾供职于国外某知名外汇交易机构，实战技术派典范，少年老成。擅长量化模型分析并综合其他技术指标运用来研判行情。</p>
				<ul>
					<li>总收益：<span class="zong">4227400</span> </li>
					<li>月收益：<span class="yue">268000</span> </li>
					<li>胜率：<span>89.42</span> </li>
					<li>盈利点数：<span>51523</span> </li>
					<li>日均单量：<span>0.87</span> </li>
					<li>单均收益率：<span>0.91</span> </li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt>
				<img src="images/teachBox4.jpg">
			</dt>
			<dd>
				<h3>亮剑老师<span>(9从业时间 )</span></h3>
				<p>毕业于西南大学经济学专业，近十年金融衍生品交易分析经验。曾供职国内某大型财富管理机构研发部以及某私募基金交易团队，拥有丰富的分析和大资金操盘经验。</p>
				<ul>
					<li>总收益：<span class="zong">3876170</span> </li>
					<li>月收益：<span class="yue">46000</span> </li>
					<li>胜率：<span>92.59</span> </li>
					<li>盈利点数：<span>73720</span> </li>
					<li>日均单量：<span>0.84</span> </li>
					<li>单均收益率：<span>1.18</span> </li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt>
				<img src="images/teachBox5.jpg">
			</dt>
			<dd>
				<h3>温蒂老师<span>(3从业时间 )</span></h3>
				<p>毕业于英国University of Central Lancashire大学传媒系 ，大学时期开始接触金融市场并热衷于研究外汇市场和大宗商品的价格波动轨迹，交易伊始即显露出极高的交易天赋。</p>
				<ul>
					<li>总收益：<span class="zong">951000</span> </li>
					<li>月收益：<span class="yue">402000</span> </li>
					<li>胜率：<span>100.00</span> </li>
					<li>盈利点数：<span>6880</span> </li>
					<li>日均单量：<span>0.39</span> </li>
					<li>单均收益率：<span>5.56</span> </li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt>
				<img src="images/teachBox6.jpg">
			</dt>
			<dd>
				<h3>申屠老师<span>(6从业时间 )</span></h3>
				<p>毕业于浙江财经大学金融管理系，2009年开始涉足大宗商品和外汇市场的分析和交易工作，曾任某大型商品投资机构首席分析师。秉承交易是一门“观阵、布局、用兵、制胜”的战略系统科学；又是融汇“洞察时局、交易策略、执行计划、完成交易”的市场行为科学。</p>
				<ul>
					<li>总收益：<span class="zong">374000</span> </li>
					<li>月收益：<span class="yue">374000</span> </li>
					<li>胜率：<span>100.00</span> </li>
					<li>盈利点数：<span>1870</span> </li>
					<li>日均单量：<span>0.50</span> </li>
					<li>单均收益率：<span>50.00</span> </li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt>
				<img src="images/teachBox7.jpg">
			</dt>
			<dd>
				<h3>忘忧老师<span>(10从业时间 )</span></h3>
				<p>十年交易生涯甘苦自知，2009年毅然辞职，正式迈入交易员行列。始于无心插柳，终见绿树成阴！曾效力于某大型金融机构研究部分析师一职，长期跟踪研究大宗商品，外汇和股票的趋势性交易机会，并在交易中不断完善自我和交易系统。</p>
				<ul>
					<li>总收益：<span class="zong">394000</span> </li>
					<li>月收益：<span class="yue">394000</span> </li>
					<li>胜率：<span>100.00</span> </li>
					<li>盈利点数：<span>1970</span> </li>
					<li>日均单量：<span>0.75</span> </li>
					<li>单均收益率：<span>33.33</span> </li>

				</ul>
			</dd>
		</dl>
		<dl>
			<dt>
				<img src="images/teachBox8.jpg">
			</dt>
			<dd>
				<h3>捕手老师<span>(6从业时间 )</span></h3>
				<p>东北财经大学风险管理专业毕业，6年资本市场和大宗商品研究经验，曾就职于国内某知名私募基金研究部门。趋势交易倡导者，并将中国传统哲学与现代交易理论结合，独创现货投资操盘秘籍——《潜龙战法》，提出技术面分析共振原则，成功帮助无数投资者树立了正确的投资理念并带来稳定的投资收益。交易格言：财富会变，投机者会变，但人性永不改变！</p>
				<ul>
					<li>总收益：<span class="zong">252000</span> </li>
					<li>月收益：<span class="yue">252000</span> </li>
					<li>胜率：<span>100.00</span> </li>
					<li>盈利点数：<span>1260</span> </li>
					<li>日均单量：<span>0.50</span> </li>
					<li>单均收益率：<span>100.00</span> </li>

				</ul>
			</dd>
		</dl>
	</div>
</div>
<div class="perMain">
	<div class="personal">
		<h2>个人中心</h2>
		<div class="perBox">
			<p>帐户：清风</p>
			<p>等级昵称：<img id="_info_level" src="images/icon3.png" alt="团长"><a href="javascript:;" id="_info_nickName"><span>独一无二的肉松饼</span></a></p>
		</div>
		<form action="javascript:;" method="post" class="alter">
			<dl>
				<dt>修改昵称<img src="images/alterDown.png"></dt>
				<dd>
					<input type="text" placeholder="输入新昵称" class="tex" id="newNickName">
					<input type="button" value="确定修改" class="sbm sbmName" id="editNickName">
				</dd>
			</dl>
			<dl>
				<dt>修改密码<img src="images/alterDown.png"></dt>
				<dd>
					<input type="text" placeholder="请输入原密码" class="tex" id="oldPwd">
					<input type="text" placeholder="请输入新密码" class="tex" id="newPwd">
					<input type="text" placeholder="请重复输入新密码" class="tex" id="reNewPwd">
					<input type="button" value="确定修改" class="sbm" id="editPwd">
				</dd>
			</dl>
		</form>
		<a href="javascript:;" class="close"></a>
	</div>
</div>
<div class="calendar hide">
	<iframe src="http://dataapi.fx678.com/jian_g_j_y/cal" frameborder="0" style="width:100%; height:100%"></iframe>
	<a href="javascript:;" class="close"></a>
</div>
<div class="course hide">
	<h2>课程安排<a href="javascript:;" class="close"></a></h2>
	<div class="cou">
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
	<div class="eiaBox"></div>
</div>
<div class="unNum hide">
	<h2>非农数据<a href="javascript:;" class="close"></a></h2>
	<div class="unBox"></div>
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
					<a href="http://www.sccsce.com/download/SCCjinchenganznzuo.apk" target="_blank">手机客户端</a>
					<b>（APP端）</b>
				</dd>
			</dl>
			<dl>
				<dt><img src="images/load2.png"></dt>
				<dd>
					<p>川商商品交易中心</p>
					<a href="http://www.sccsce.com/download/%E5%B7%9D%E5%95%86%E5%95%86%E5%93%81%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E8%A1%8C%E6%83%85%E5%88%86%E6%9E%90%E7%B3%BB%E7%BB%9F.rar" target="_blank">行情软件</a>
					<b>（PC端）</b>
				</dd>
			</dl>
			<dl>
				<dt><img src="images/load3.png"></dt>
				<dd>
					<p>川商商品交易中心</p>
					<a href="http://www.sccsce.com/download/%E5%B7%9D%E5%95%86%E5%95%86%E5%93%81%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E4%BA%A4%E6%98%93%E7%B3%BB%E7%BB%9F1.rar" target="_blank">实盘软件</a>
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
		<div class="two">
			<dl>
				<dt><img src="images/ma2.png"></dt>
				<dd>
					<a href="javascript:;">手机直播间</a>
				</dd>
			</dl>
			<dl>
				<dt><img src="images/ma3.png"></dt>
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
		<h3>川商所简介：</h3>
		<p>鑫至尊直播间作为川商所286号会员单位，能更好地为投资者提供专业的金融服务。四川川商商品交易中心有限公司是经四川省人民政府设立的创新型大宗商品交易服务平台，也是目前四川省唯一一家由省政府批复成立的交易平台。注册资本金1.2亿，依托于四川省西部经济中心的区位优势，以立足四川、辐射全国、放眼全球为发展目标，以“金融创新”为宗旨，为有色金属、矿产品以及农产品等商品提供现货交易及现货电子交易和相关商品的结算、交收、物流等方面的专业金融服务。</p>
		<div class="platImg"><img src="images/platImg.jpg" width="344"></div>
		<table>
			<caption>川商所产品介绍</caption>
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
		<h3>川商所八大优势：</h3>
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
	<h1>用户注册<span><i></i>已经注册过了？还等什么，赶紧登录吧<a href="javascript:;" style="color: #303030;font-size: 16px;font-weight: bold;text-decoration: underline;margin-left: 4px;" id="register_go">GO</a></span></h1>
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
		<div id="huakuai"></div>
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
	<div class="logLeft">
		<h1>用户登录<span><i></i>还未注册帐户？赶紧注册一个<a href="javascript:;">GO</a></span></h1>
		<form onsubmit="javascript:return false;" method="post">
			<p>
				<label class="lab1"></label>
				<input type="text" placeholder="用户名" required="required" id="userName_login">
			</p>
			<p>
				<label class="lab3"></label>
				<input type="text" placeholder="密码" required="required" id="pwd_login">
			</p>
			<input type="submit" value="马上登录" class="mit" id="login">
		</form>
		<a href="javascript:;" class="close"></a>
	</div>
	<div class="logRight">
		<a href="tencent://message/?uin=2853573049&Site=&menu=yes" target="_blank"></a>
	</div>
</div>


<iframe frameborder="0" src="uploadIMG.jsp?t=123" scrolling="no" style="display:none" id="uploadImg" name="uploadImg"></iframe>
<script type="text/javascript" src="js/jquery.mCustomScrollbar.js"></script><!--滚动-->
<script type="text/javascript" src="js/jquery.sinaEmotion.js"></script><!--表情彩条-->
<script type="text/javascript" src="js/jquery-ui.js"></script><!--jquery-ui-->
<script type="text/javascript" src="js/scrollTo.js"></script>
<script type="text/javascript" src="js/resize.js"></script>
<script type="text/javascript" src="js/jquery.JPlaceholder.js"></script>
<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript" src="js/tinybox.js"></script>
<script src="jcountdown/jquery.jcountdown.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/script2.js"></script>
<script type="text/javascript" src="js/jquery.nanoscroller.js"></script>
<script type="text/javascript" src="js/defaultParam.js"></script>
<script type="text/javascript" src="js/indexRegister.js"></script>
<script type="text/javascript" src="js/indexDo.js"></script>
<script type="text/javascript" src="js/fileup.js"></script>
<script type="text/javascript" src="js/editNickNameAndPwd.js"></script>
<script type="text/javascript" src="js/drag.js"></script>
<script>
$(function(){
	$(window).load(function(){ 
		$(".section-focus-pic").height($(".section-focus-pic ul img").height());
	})
})
</script>
<script type="text/javascript" src="js/jquery.nicescroll.js"></script>
<script type="text/javascript">
$("#platBox").niceScroll({  
	cursorcolor:"#b0b0b0",  
	cursoropacitymax:1,  
	touchbehavior:false,  
	cursorwidth:"5px",  
	cursorborder:"0",  
	cursorborderradius:"5px"  
})
</script>

<script type="text/javascript">
    $('#huakuai').huakuai();
    sessionId = "<%=sessionId%>";
    time = "<%=time%>";
    is_chat="<%=customerResult.getCustomerType().getIs_chat()%>";
    yk_on_off = "<%=yk_on_off%>";
    flashVideo();
			  
	   $(".pop").hide();
       $(".course").hide();
       $(".download").hide();
       $(".register").hide();
       $(".log_out").show();
       $(".login").hide();   //隐藏登录框
       var json = <%=c_json%>;  //将后台传来的json格式字符串转换为object
       //$(".head_right").find("span").find("a").html(json.userName);  //将用户名显示到页面上
       level = json.level;  //获取用户等级
       var src = "images/icon"+level+".png";
       if(parseInt(level) == 0 && yk_on_off == "1"){
    	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
    	   $(".perName").html("昵称："+json.nickName);
       }else if(parseInt(level) > 0){
    	   if(parseInt(level) == 1){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   totalVideoTime = json.customerType.video_time * 60;  //获取用户的观看视频时长(这里的单位是秒)
           }
           if(parseInt(level) == 2){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   totalVideoTime = json.customerType.video_time * 60;  //获取用户的观看视频时长(这里的单位是秒)
           }
           if(parseInt(level) == 3){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   totalVideoTime = json.customerType.video_time * 60;  //获取用户的观看视频时长(这里的单位是秒)
           }
           if(parseInt(level) == 4){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   totalVideoTime = json.customerType.video_time * 60;  //获取用户的观看视频时长(这里的单位是秒)
           }
           if(parseInt(level) == 5){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   totalVideoTime = json.customerType.video_time * 60;  //获取用户的观看视频时长(这里的单位是秒)
           }
           if(parseInt(level) == 6){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   totalVideoTime = json.customerType.video_time * 60;  //获取用户的观看视频时长(这里的单位是秒)
           }
           
           if(parseInt(level) == 91){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           if(parseInt(level) == 92){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           if(parseInt(level) == 93){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           if(parseInt(level) == 94){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           if(parseInt(level) == 95){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           if(parseInt(level) == 96){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           
           if(parseInt(level) == 99){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           if(parseInt(level) == 100){
        	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
        	   $(".outside").removeClass("outside");
           }
           
           $(".perName").html("昵称："+json.nickName+"<img src='images/listDowm.png'>");
       }else{
    	   $(".perName").html("");
       }
       
       
       customer=json;
       setCookie("_Room"+roomNo+"_loginName", customer.userName, 10);
       setCookie("_Room"+roomNo+"_loginPwd", customer.pwd, 10);
       initAction();
	   //初始化时就生成一张图形验证码	
	   //$("#image").attr('src',"../controller/code.jpg;jsessionid="+sessionId);
</script>
<script type="text/javascript" src="js/track.js"></script>
</body>
</html>