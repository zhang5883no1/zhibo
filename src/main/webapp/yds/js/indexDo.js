   //页面初始化时
   $(function(){ 
		//登录
		$("#login").on('click',function(){
			 var userName = $("#userName_login").val();
			 var pwd = $("#pwd_login").val();
			 if(userName.length > 0 && pwd.length > 0){
				 doLogin(userName,pwd);
			 }
		});

		$("#bt_caitiao").click(function(){
			$('#caitiao').toggle();
			return false;
		});
	   
	   $("#sub_info_btn").click(function(){
		   if(!subInfoFlag){
			   notSend();
			   return false;
		   }
		   if(is_chat=="1"){
			   alert("您已被管理员禁言");
			   return false;
		   }
		   var content=delHtmlTag($("#sendMsgInput").html());
		   if(content.replace("<div><br>&lt;/</div>").replace(" ").replace("<br>")==""||content.replace("<div><br>&lt;/</div>").replace(" ").replace("<br>")=="早开户，早盈利，油、银、铜全天解盘，精准喊单，经得住考验"){
			   return;
		   }
			   
		   var userName=customer.nickName;
		   var userId=customer.id;
		   var toUser="";
		   var isRobot="0";
		   var type="all";
		   var level=customer.level;
		   if(customer.level>90){
			   var _select_robot=$("#_users_info").val();
			   if(_select_robot!=""){
				   userId=_select_robot.split("###")[0];
				   isRobot="1";
				   userName=_select_robot.split("###")[1];
				   level=_select_robot.split("###")[2];
			   }
			   if((customer.level==100||customer.level==99)&&userId==customer.id){
				   type=$("#_info_types").val();
			   }
		   }
		   var c=content.split("@");
		   if(c.length==3){
			   content=c[2];
			   toUser=c[1];
			   if(type=="red"){
				   type="redSimple";
			   }else if(type=="black"){
				   type="blackSimple"
			   }else{
				   type="simple";
			   }
		   }
		   $("#sendMsgInput").html("");
		   if(type=="red"){
			   var willPutChatsInfo = {"type":10,"faceImg":level,"userName":userName,"content":content,"date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
			   putInfo(willPutChatsInfo);
		   }else if(type=="black"){
			   var willPutChatsInfo = {"type":11,"faceImg":level,"userName":userName,"content":content,"date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
			   putInfo(willPutChatsInfo);
		   }else if(type=="all"){
			   var willPutChatsInfo = {"type":0,"faceImg":level,"userName":userName,"content":content,"date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
			   putInfo(willPutChatsInfo);
		   }else if(type=="simple"){
			   var willPutChatsInfo = {"type":1,"faceImg":level,"userName":userName,"content":content,"toUser":toUser,"date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
			   putInfo(willPutChatsInfo);
		   }else if(type=="redSimple"){
			   var willPutChatsInfo = {"type":20,"faceImg":level,"userName":userName,"content":content,"toUser":toUser,"date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
			   putInfo(willPutChatsInfo);
		   }else if(type=="blackSimple"){
			   var willPutChatsInfo = {"type":21,"faceImg":level,"userName":userName,"content":content,"toUser":toUser,"date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
			   putInfo(willPutChatsInfo);
		   }
		   
		   
		   if(parseInt(customer.level)<90){
				subInfoFlag=false;
				subInfoThread=setInterval(setSendInfoFlag,1000);
			}
		   
		   $.ajax({
			  	url:"../controller/putChats;jsessionid="+sessionId,
			  	type:"post",
			  	dataType:"jsonp",
			  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
			  	data:{"content":encodeURI(content),"userName":encodeURI(userName),"time":time,"userId":userId,"toUser":encodeURI(toUser),"isRobot":isRobot,"type":type},      //encodeURI(xxx) 编码
				success:function(res){
					//alert();
				},
				error:function(){
					
				}
			});  
	   });
	   
	   
	   $("#out").off().on().click(function(){
		   setCookie("_Room"+roomNo+"_loginName","");
		   setCookie("_Room"+roomNo+"_loginPwd","");
		   location.reload();
	   });
	   
	   $(".desktop").click(function(){
		   window.open("/download.jsp?filename="+encodeURI(encodeURI("鑫西都直播间.url")));
	   });
   });

   function initCustomerInfo(){
	   if(customer.level>90){
		   $("#_chats_type_util").show();
		   var robots=customer.robotList;
		   var h=[];
		   for(var i=0;i<robots.length;i++){
			   if(robots[i].DELETE_FLAG==0){
				   h[h.length]="<option value='"+robots[i].id+"###"+robots[i].name+"###"+robots[i].level+"'>"+robots[i].name+"</option>";
			   }
		   }
		   $("#_users_info").append(h.join(""));
		   
		   if(customer.level==99||customer.level==100){
			   h=[];
			   h[h.length]="<option value='top'>公告</option>";
			   h[h.length]="<option value='scrol'>滚动</option>";
			   h[h.length]="<option value='bottom'>底部公告</option>";
			   h[h.length]="<option value='red'>聊天样式-红字</option>";
			   h[h.length]="<option value='black'>聊天样式-黑字</option>";
			   $("#_info_types").append(h.join(""));
		   }else{
				$("#_info_types").hide();
		   }
	   }else{
		   $("#_chats_type_util").hide();
	   }
   }
   
   function invokeGetInfo(){
	   $.ajax({
		  	url:"../controller/putChats;jsessionid="+sessionId,
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	data:{"id":_last_info_id,"time":time},      //encodeURI(xxx) 编码
			success:function(res){
				var normalChats=res.normalChats;
				if(typeof(normalChats) != "undefined"&&normalChats.length>0){
					for(var i=0;i<normalChats.length;i++){
						$("#valid_info_"+normalChats[i].id).remove();
						if(normalChats[i].userId==customer.id){
			     		   _last_info_id=normalChats[i].id;
			     	    }else{
			     	    	var robots=customer.robotList;
			     	    	var rf=false;
			     	    	for(var p=0;p<robots.length;p++){
			     	    		if(robots[p].id==normalChats[i].userId){
			     	    			rf=true;
			     	    		}
			     	    	}
			     	    	if(rf){
			     	    		_last_info_id=normalChats[i].id;
			     	    	}else{
			     	    		putInfo(normalChats[i]);
			     	    	}
			     	    }
						
					}
				}
				var validChats=res.validChats;
				if(typeof(validChats) != "undefined"&&validChats.length>0){
					for(var i=0;i<validChats.length;i++){
						addValidChats(validChats[i]);
					}
				}
				
				var falidChats=res.falidChats;
				if(typeof(falidChats) != "undefined"&&falidChats.length>0){
					for(var i=0;i<falidChats.length;i++){
						$("#valid_info_"+falidChats[i]).remove();
					}
				}
				
				var scrolChats=res.scrolInfo;
				if(scrolChats!=null&&scrolChats!=$("#isfly .fly_word").html()){
					if(new Date().getTime()-parseInt(scrolChats.createDate)<300000){
						$("#isfly").show();
						$("#isfly .fly_word").html(scrolChats.content);
						$(".fly b").css("left","100%");
						$(".fly b").animate({left:'-1000px'},60000,'linear',function(){
							$(".fly b").css({left:'100%'})
						})
						hideScrolInfo=setInterval(hideScrolInfoAction,300000);
					}
				}
				
				var topChats=res.topInfo;
				if(topChats!=null&&topChats!=$("#marq ul li").html()){
					if($("#marq ul li:eq(0)").html()==topChats.content){
						
					}else{
						marqs("marq",2,topChats.content);
					}
				}
				
				var bottomChats=res.bottomInfo;
				if(bottomChats!=null&&bottomChats!=$("#bottomChats").html()){
					$("#bottomChats").html(bottomChats.content);
				}
				
			},
			error:function(){
				
			}
		});  
   }


   function getHistory(){
	   $.ajax({
		  	url:"../controller/ChatsController;jsessionid="+sessionId,
		  	type:"post",
		  	dataType:"text",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(res == "0"){
					location.reload(true);
				}else{
					var _json = JSON.parse(res);
			        for(var i=_json.length-1;i>=0;i--){
			        	putInfo(_json[i]);
			        }

				}
			},
			error:function(){
				
			}
		})
   }


   
   function putInfo(chat){
	   var ds=chat.date.split(" ")[1].split(":");
	   var content= chat.content.replace(new RegExp("<img ","gm"),"<img onclick='talk_pic(this)' ");
	   if(chat.type==0||chat.type==10||chat.type==11){
		   var h=[];
		   if(chat.type==10){
			   h[h.length]='<div class="talk xun"><span class="time">'+ds[0]+':'+ds[1]+'</span><span><img title="皇冠" src="images/icon'+chat.faceImg+'.png">';
			   h[h.length]='<div class="person_name"><a class="name" href="javascript:showName(\''+chat.userName+'\');">'+chat.userName+'</a></div></span>';
			   h[h.length]='<i class="speak">说：</i><div class="talk_hua talk_hua2"><p>'+content+'</p></div></div>';
		   }else if(chat.type==11){
			   h[h.length]='<div class="talk guan"><span class="time">'+ds[0]+':'+ds[1]+'</span><span><img title="皇冠" src="images/icon'+chat.faceImg+'.png">';
			   h[h.length]='<div class="person_name"><a class="name" href="javascript:showName(\''+chat.userName+'\');">'+chat.userName+'</a></div></span>';
			   h[h.length]='<i class="speak">说：</i><div class="talk_hua talk_hua1"><p>'+content+'</p></div></div>';
		   }else{
			   if(chat.faceImg>98){
				   h[h.length]='<div class="talk xun"><span class="time">'+ds[0]+':'+ds[1]+'</span><span><img title="皇冠" src="images/icon'+chat.faceImg+'.png">';
			   }else{
				   h[h.length]='<div class="talk"><span class="time">'+ds[0]+':'+ds[1]+'</span><span><img title="皇冠" src="images/icon'+chat.faceImg+'.png">';
			   }
			  
			   h[h.length]='<div class="person_name"><a class="name" href="javascript:showName(\''+chat.userName+'\');">'+chat.userName+'</a></div></span>';
			   h[h.length]='<i class="speak">说：</i><div class="talk_hua"><p>'+content+'</p></div></div>';
		   }
		   $("#topicbox").append(h.join(""));
		   chatcontainer.scrollToLast();
	   }else if(chat.type==1||chat.type==20||chat.type==21){
		   var h=[];
		   if(chat.faceImg>98){
			   h[h.length]='<div class="talk xun"><span class="time">'+ds[0]+':'+ds[1]+'</span><span><img title="皇冠" src="images/icon'+chat.faceImg+'.png">';
		   }else{
			   h[h.length]='<div class="talk"><span class="time">'+ds[0]+':'+ds[1]+'</span><span><img title="皇冠" src="images/icon'+chat.faceImg+'.png">';
		   }
		   h[h.length]='<div class="person_name"><a class="name" href="javascript:showName(\''+chat.userName+'\');">'+chat.userName+'</a></div></span>';
		   h[h.length]='<i class="at">对</i><div class="person_name"><a class="name" href="javascript:showName(\''+chat.toUser+'\');">'+chat.toUser+'</a></div>';
		   if(chat.type==20){
			   h[h.length]='<i class="speak">说：</i><div class="talk_hua talk_hua2"><p>'+content+'</p></div></div>';
		   }else if(chat.type==21){
			   h[h.length]='<i class="speak">说：</i><div class="talk_hua talk_hua1"><p>'+content+'</p></div></div>';
		   }else{
			   h[h.length]='<i class="speak">说：</i><div class="talk_hua"><p>'+content+'</p></div></div>';
		   }
		   
		   $("#topicbox").append(h.join(""));
		   chatcontainer.scrollToLast();
	   }
	   if(chat.id!=null&&typeof(chat.id) != "undefined"){
		   _last_info_id=chat.id;
	   }
	   
   }
   
   function addValidChats(chat){
	   var ds=chat.date.split(" ")[1].split(":");
	   var h=[];
	   h[h.length]='<div id="valid_info_'+chat.id+'"><p><span class="hours">'+ds[0]+':'+ds[1]+'</span><img alt="铂金" src="images/icon'+chat.faceImg+'.png">';
	   h[h.length]='<a href="javascript:return false;">'+chat.userName+'</a><b>'+chat.content+'</b></p>';
	   h[h.length]='<strong><a href="javascript:valid('+chat.id+',1)">通过</a>';
	   h[h.length]='<a href="javascript:valid('+chat.id+',0)">不通过</a></strong></div>';
	   $("#_valid_info_box").append(h.join(""));
	   $("#draggable").show();
   }

   function valid(chatid,valid){
	   $("#valid_info_"+chatid).remove();
	   $.ajax({
		  	url:"../controller/validInfo;jsessionid="+sessionId,
		  	type:"post",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	data:{"id":chatid,"valid":valid,"time":time}, 
			success:function(res){
				var valid=res.valid;
				var id=res.id;
				//$("#valid_info_"+id).remove();
				if(valid==1){
					putInfo(res);
				}
			},
			error:function(){
				
			}
		})
   }
   
   function initAction(){
       getHistory();
       initCustomerInfo();
       _getInfo=setInterval(invokeGetInfo,5000);
       _getLogInfo=setInterval(invokeGetLogInfo,60000);
       if(parseInt(customer.level)>0){
    	   $(".log").hide();
           $(".reg").hide();
           $("#out").show();
       }else{
    	   alertImg=setInterval(showImg,300000);
       }
       $("#_info_account").html("帐户："+customer.userName);
       $("#_info_nickName").html(customer.nickName);
       $("#_info_level").attr("src","images/icon"+customer.level+".png");
       if(customer.level==0){
    	   clearInterval(_getInfo);
    	   clearInterval(_getLogInfo);
       }
   }
   function showImg(){
	   if(parseInt(customer.level)>0){
		   clearInterval(alertImg);
	   }else{
		   $("#is_look").css("z-index",1000);
		   $("#is_look").show();
		   $(".pop").fadeIn(100).css("display","block");
		   $(".pop").fadeIn(100).css("background","rgba(0, 0, 0, 0.92)");
		   $(".register").css("z-index","1001");
		   $(".pop").removeClass("hide");
		   $(".loclose").hide();
		   $(".emb").html("<embed src='http://static.gensee.com/webcast/static/sdk/flash/GenseeEasyLive.swf' wmode='transparent'>");
	   }
   }
   
   function hideScrolInfoAction(){
	   $("#isfly").hide();
	   clearInterval(hideScrolInfo);
   }
   
   Date.prototype.Format = function(fmt)   
   { //author: meizz   
     var o = {   
       "M+" : this.getMonth()+1,                 //月份   
       "d+" : this.getDate(),                    //日   
       "h+" : this.getHours(),                   //小时   
       "m+" : this.getMinutes(),                 //分   
       "s+" : this.getSeconds(),                 //秒   
       "q+" : Math.floor((this.getMonth()+3)/3), //季度   
       "S"  : this.getMilliseconds()             //毫秒   
     };   
     if(/(y+)/.test(fmt))   
       fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
     for(var k in o)   
       if(new RegExp("("+ k +")").test(fmt))   
     fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
     return fmt;   
   }  

   
   function setCookie(cname, cvalue, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+d.toUTCString();
	    document.cookie = cname + "=" + encodeURI(cvalue) + "; " + expires;
	}

   function getCookie(c_name) {
		if (document.cookie.length > 0) {
			var c_start = document.cookie.indexOf(c_name + "=");
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1;
				c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1) {
					c_end = document.cookie.length;
				}
				return decodeURI(unescape(document.cookie.substring(c_start, c_end)));
			}
		}
		return "";
	}

function doLogin(userName,pwd){
	$.ajax({
	  	url:"../controller/LoginController;jsessionid="+sessionId,
	  	type:"post",
	  	dataType:"json",
	  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
	  	data:{"userName":encodeURI(userName),"pwd":pwd,"time":time,"roomNo":roomNo},      //encodeURI(xxx) 编码
		success:function(res){
			afertLogin(res);
		},
		error:function(){
			
		}
	})  
}

function doRegVisiter(){
	$.ajax({
	  	url:"../controller/LoginController;jsessionid="+sessionId+"?time="+time+"&roomNo="+roomNo,
	  	type:"get",
	  	dataType:"json",
		success:function(res){
			afertLogin(res);
		},
		error:function(){
			
		}
	})  
}

function afertLogin(data){
	var json = data;  //将后台传来的json格式字符串转换为object
	if(data == "0" || data.status == 'block'||(json.roomNo!=roomNo&&json.level<80)){   //登录失败
		alert("用户名或密码错误");
	}else{   //登录成功
		$(".hide").hide();
		$(".perMain").hide();
		$(".loclose").show();
        level = json.level;  //获取用户等级
        var src = "images/icon"+level+".png";
        if(parseInt(level) == 0){
     	   $("#level_icon").html("等级："+level+"级<img src='"+src+"'>");
     	   $(".perName").html("昵称："+json.nickName);
     	   addHours = 2;
        }else{
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
        }
        customer=json;
        
        setCookie("_Room"+roomNo+"_loginName", customer.userName, 10);
        setCookie("_Room"+roomNo+"_loginPwd", customer.pwd, 10);
        initAction();
        location.reload();
	}
}



function sendCaitiao(type){
	if(type=="pt掌声"){
		$("#sendMsgInput").html($("#sendMsgInput").html()+"<img width='28' height='28'  src='images/autoImg/shandian/1.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/shandian/2.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/shandian/3.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/shandian/4.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/shandian/5.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/shandian/6.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/shandian/7.gif'>");
	}else if(type=="pt送鲜花"){
		  $("#sendMsgInput").html($("#sendMsgInput").html()+"<img width='28' height='28'  src='images/autoImg/meigui/1.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/meigui/2.jpg'>"+
				     "<img width='28' height='28'  src='images/autoImg/meigui/3.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/meigui/4.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/meigui/5.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/meigui/6.jpg'>"+
				     "<img width='28' height='28'  src='images/autoImg/meigui/7.gif'>");
	}else if(type=="pt顶一个"){
		 $("#sendMsgInput").html($("#sendMsgInput").html()+"<img width='28' height='28'  src='images/autoImg/ding/1.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/ding/2.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/ding/3.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/ding/4.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/ding/5.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/ding/6.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/ding/7.gif'>");
	}else if(type=="pt赞一个"){
		  $("#sendMsgInput").html($("#sendMsgInput").html()+"<img width='28' height='28'  src='images/autoImg/zan/1.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/zan/2.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/zan/3.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/zan/4.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/zan/5.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/zan/6.gif'>"+
				     "<img width='28' height='28'  src='images/autoImg/zan/7.gif'>");
	}else if(type=="pt给力"){
		 $("#sendMsgInput").html($("#sendMsgInput").html()+"<img width='28' height='28'  src='images/autoImg/geili/1.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/geili/2.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/geili/3.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/geili/4.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/geili/5.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/geili/6.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/geili/7.gif'>");
	}else if(type=="pt棒！"){
		$("#sendMsgInput").html($("#sendMsgInput").html()+"<img width='28' height='28'  src='images/autoImg/bang/1.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/bang/2.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/bang/3.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/bang/4.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/bang/5.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/bang/6.gif'>"+
			     "<img width='28' height='28'  src='images/autoImg/bang/7.gif'>");
	}
	po_Last_Div();
 }



function showName(nameString){
   var content=$("#sendMsgInput").html("");
   content="@"+nameString+"@";
   $("#sendMsgInput").html(content);
   po_Last_Div();
}


function po_Last_Div() {
	$("#sendMsgInput").html($("#sendMsgInput").html().replace("早开户，早盈利，油、银、铜全天解盘，精准喊单，经得住考验",""));
	var obj=document.getElementById("sendMsgInput");
    if (window.getSelection) {//ie11 10 9 ff safari
        obj.focus(); //解决ff不获取焦点无法定位问题
        var range = window.getSelection();//创建range
        range.selectAllChildren(obj);//range 选择obj下所有子内容
        range.collapseToEnd();//光标移至最后
    }
    else if (document.selection) {//ie10 9 8 7 6 5
        var range = document.selection.createRange();//创建选择对象
        //var range = document.body.createTextRange();
        range.moveToElementText(obj);//range定位到obj
        range.collapse(false);//光标移至最后
        range.select();
    }
}

function setSendInfoFlag(){
	if(subInfoTime==10){
		subInfoFlag=true;
		subInfoTime=0;
		 clearInterval(subInfoThread);
	}else{
		subInfoTime++;
	}
}

function notSend(){
	alert("还剩"+(10-subInfoTime)+"秒");
}

function flashVideo(){
	 $('#videoFrame').attr("src","video.html");  
}

function setkcb(kcb){
	var h=[];
	h[h.length-1]="<tr><td>"+kcb.time1_time+"</td>";
	h[h.length-1]="<td><strong>"+kcb.time1_lesson_mon+"</strong><p>"+kcb.time1_teacher_mon+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time1_lesson_tue+"</strong><p>"+kcb.time1_teacher_tue+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time1_lesson_wed+"</strong><p>"+kcb.time1_teacher_wed+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time1_lesson_thu+"</strong><p>"+kcb.time1_teacher_thu+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time1_lesson_fri+"</strong><p>"+kcb.time1_teacher_fri+"</p></td></tr>";
	h[h.length-1]="<tr><td>"+kcb.time2_time+"</td>";
	h[h.length-1]="<td><strong>"+kcb.time2_lesson_mon+"</strong><p>"+kcb.time2_teacher_mon+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time2_lesson_tue+"</strong><p>"+kcb.time2_teacher_tue+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time2_lesson_wed+"</strong><p>"+kcb.time2_teacher_wed+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time2_lesson_thu+"</strong><p>"+kcb.time2_teacher_thu+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time2_lesson_fri+"</strong><p>"+kcb.time2_teacher_fri+"</p></td></tr>";
	h[h.length-1]="<tr><td>"+kcb.time3_time+"</td>";
	h[h.length-1]="<td><strong>"+kcb.time3_lesson_mon+"</strong><p>"+kcb.time3_teacher_mon+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time3_lesson_tue+"</strong><p>"+kcb.time3_teacher_tue+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time3_lesson_wed+"</strong><p>"+kcb.time3_teacher_wed+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time3_lesson_thu+"</strong><p>"+kcb.time3_teacher_thu+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time3_lesson_fri+"</strong><p>"+kcb.time3_teacher_fri+"</p></td></tr>";
	h[h.length-1]="<tr><td>"+kcb.time4_time+"</td>";
	h[h.length-1]="<td><strong>"+kcb.time4_lesson_mon+"</strong><p>"+kcb.time4_teacher_mon+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time4_lesson_tue+"</strong><p>"+kcb.time4_teacher_tue+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time4_lesson_wed+"</strong><p>"+kcb.time4_teacher_wed+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time4_lesson_thu+"</strong><p>"+kcb.time4_teacher_thu+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time4_lesson_fri+"</strong><p>"+kcb.time4_teacher_fri+"</p></td></tr>";
	h[h.length-1]="<tr><td>"+kcb.time5_time+"</td>";
	h[h.length-1]="<td><strong>"+kcb.time5_lesson_mon+"</strong><p>"+kcb.time5_teacher_mon+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time5_lesson_tue+"</strong><p>"+kcb.time5_teacher_tue+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time5_lesson_wed+"</strong><p>"+kcb.time5_teacher_wed+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time5_lesson_thu+"</strong><p>"+kcb.time5_teacher_thu+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time5_lesson_fri+"</strong><p>"+kcb.time5_teacher_fri+"</p></td></tr>";
	h[h.length-1]="<tr><td>"+kcb.time6_time+"</td>";
	h[h.length-1]="<td><strong>"+kcb.time6_lesson_mon+"</strong><p>"+kcb.time6_teacher_mon+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time6_lesson_tue+"</strong><p>"+kcb.time6_teacher_tue+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time6_lesson_wed+"</strong><p>"+kcb.time6_teacher_wed+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time6_lesson_thu+"</strong><p>"+kcb.time6_teacher_thu+"</p></td>";
	h[h.length-1]="<td><strong>"+kcb.time6_lesson_fri+"</strong><p>"+kcb.time6_teacher_fri+"</p></td></tr>";
	$("#kcb_tbody").html(h.join(""));
}

function setQQ(qq){
	
}

//通过点击回车键提交表单
function entersubmit(e) {
	var charCode = (navigator.appName == "Netscape") ? e.which : e.keyCode;
	if (charCode == 13) {
		 var userName = $("#userName_login").val();
		 var pwd = $("#pwd_login").val();
		 if(userName.length > 0 && pwd.length > 0){
			 doLogin(userName,pwd);
		 }
	}
}

function delHtmlTag(str){
	return str.replace(/<img/g  ,"xdImg").replace(/<[^>]+>/g,"").replace(/xdImg/g ,"<img ");//去掉所有的html标记
}

function invokeGetLogInfo(){
	   $.ajax({
		  	url:"../controller/getInfo;jsessionid="+sessionId,
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
			success:function(res){
				var infos=res.info;
				$(".userlist:eq(0)").find("ul li:not([class='r'])").remove();
				$(".userlist:eq(1)").find("ul").html("");
				if(typeof(infos) != "undefined"&&infos.length>0){
					for(var i=0;i<infos.length;i++){
//						$(".userlist:eq(0)").find("li[data-id='"+infos[i].id+"']").remove();
						//if(infos[i].login_Flag=="1"){
							if(parseInt(infos[i].level)<99&&infos[i].roomNo==roomNo){
								if(parseInt(level)>80){
									if(infos[i].uid==json.id){
										$(".userlist:eq(1)").find("ul").append('<li data-id="'+infos[i].id+'"><span>'+infos[i].nickName+'</span><img src="images/list'+infos[i].level+'.png"><div><a href="javascript:;" onclick="is_chat()">禁言</a></div></li>');
									}
									$(".userlist:eq(0)").find("ul").append('<li data-id="'+infos[i].id+'"><span>'+infos[i].nickName+'</span><img src="images/list'+infos[i].level+'.png"><div><a href="javascript:;" onclick="is_chat()">禁言</a></div></li>');
								}else{
									$(".userlist:eq(0)").find("ul").append('<li data-id="'+infos[i].nickName+'"><span>'+infos[i].nickName+'</span><img src="images/list'+infos[i].level+'.png"><div><a href="javascript:;" onclick="is_chat()">禁言</a></div></li>');
								}
							}
						//}else if(infos[i].login_Flag=="0"){
							
						//}
					}
				}
				$(".nameLine").find(".member").html("在线会员("+$(".userlist:eq(0)").find("ul li").length+")");
				is_chat=res.is_chat;
			},
			error:function(){
				
			}
		});  
}

