var getInfoThread;
var getCuInfoThread;
//页面初始化时
   $(function(){ 
	  
	   var dateHistory1;   //获取第一次取得的历史记录
	   getHistory();  //页面初始化时获取历史数据
	   getInfoThread=setInterval(invokeGetInfo,5000);    //每过5秒到后台取一次已审核通过的聊天记录
	   getCuInfoThread=setInterval(getInfo,30000);
	   //setInterval(getHistory,8000);    
	   setTimeout(initScrol, 2000 );
	   initlock();
	   
	   
	   $("#headImg").attr("src",customer.faceImg);
	   $("#nickName").html(customer.nickName);
	   $("#postMessage").click(function(){
		   if(customer.status!="normal"){
			   alert("你被禁言了");
			   return ;
		   }
			var msg = $("#sendMsgInput").html();
			//去除空格 如果为空返回
			var vmsg=msg.replace(/[ ]/ig,'');
			if(vmsg==""){
				return;
			}
			//替换“为‘
			msg=msg.split("\"").join("\'");
			var chatTimeAll = new Date().Format("yyyy-MM-dd hh:mm:ss");
			$("#sendMsgInput").html("");
			//chatcontainer.scrollToLast(); 
			
			var userName=customer.nickName;
			var userId=customer.id;
			var toUser="";
			var isRobot="0";
			var type="all";
			var level=customer.level;
			var content = msg;
			var willPutChatsInfo = {"id":0,"type":0,"faceImg":level,"userName":userName,"content":content,"date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
			putInfo(willPutChatsInfo);
			//将消息发送到后台
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
	   
	   
	   $("#clearAll").click(function(){
		   $("#topicbox").empty();
	   });
   });
   
   function initScrol(){
	   var willPutChatsInfo = {"id":0,"type":0,"faceImg":"100","userName":"管理员","content":"欢迎来到直播间","date":new Date().Format("yyyy-MM-dd hh:mm:ss")};
		putInfo(willPutChatsInfo);
   }
   
   function getHistory(){
	   //$("#appent").html("");
	   $.ajax({
		  	url:"../controller/ChatsController;jsessionid="+sessionId,
		  	type:"post",
		  	dataType:"text",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(res == "0"){
					//location.reload(true);
				}else{
					var _json = JSON.parse(res);
					console.log(_json);
			        for(var i=_json.length-1;i>=0;i--){
			        	putInfo(_json[i]);
			        }
				}
			},
			error:function(){
				
			}
		})
		//chatcontainer.scrollToLast();
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
						_last_info_id = normalChats[i].id;
						if(normalChats[i].valid == "1"){
							putInfo(normalChats[i]);
						}
					}
				}
			},
			error:function(){
				
			}
		});  
   }

   function getInfo(){
	   $.ajax({
		  	url:"../controller/CcController;jsessionid="+sessionId+"?action=getInfo",
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(typeof(res) != "undefined"){
					customer=res;
					if(customer.status=="block"){
						alert("被踢出");
						closeAll();
					}
				}
			},
			error:function(){
				
			}
		})
   }

   
   
   function putInfo(chat){
		var ds=chat.date.split(" ")[1].split(":");
		var h=[];
		if(chat.type==0||chat.type==10||chat.type==11){
			 if(chat.faceImg>98){
				 h[h.length]='<div class="talk xun rm'+chat.id+'"><span class="time">'+ds[0]+':'+ds[1]+'</span>';
				 h[h.length]='<span><img src="images/icon8.png" title="巡管">';
				 h[h.length]='<div class="person_name"><a href="javascript:;" class="name">管理员</a>';
				 h[h.length]='</div></span><div class="talk_hua talk_hua1"><p>'+chat.content+'</p></div></div>';
			 }else{
				 h[h.length]='<div class="talk rm'+chat.id+'"><span class="time">'+ds[0]+':'+ds[1]+'</span>';
				 h[h.length]='<span><div class="person_name"><a href="javascript:;" class="name">'+chat.userName+'</a>';
				 h[h.length]='</div></span><div class="talk_hua"><p>'+chat.content+'</p></div></div>';
			 }
		}else{
			 if(chat.faceImg>98){
				 h[h.length]='<div class="talk xun rm'+chat.id+'"><span class="time">'+ds[0]+':'+ds[1]+'</span>';
				 h[h.length]='<span><img src="images/icon8.png" title="巡管"><div class="person_name">';
				 h[h.length]='<a href="javascript:;" class="name">管理员</a></div>';
				 h[h.length]='</span><i class="at">对</i><div class="person_name">';
				 h[h.length]='<a href="javascript:;" class="name">'+chat.toUser+'</a></div>';
				 h[h.length]='<i class="speak">说：</i><div class="talk_hua"><p>'+chat.content+'</p></div></div>';
			 }else{
				 h[h.length]='<div class="talk rm'+chat.id+'"><span class="time">'+ds[0]+':'+ds[1]+'</span>';
				 h[h.length]='<span><div class="person_name"><a href="javascript:;" class="name">'+chat.userName+'</a>';
				 h[h.length]='</div></span><i class="at">对</i><div class="person_name">';
				 h[h.length]='<a href="javascript:;" class="name">'+chat.toUser+'</a></div>';
				 h[h.length]='<i class="speak">说：</i><div class="talk_hua"><p>'+chat.content+'</p></div></div>';
			 }
		}
		if(chat.id!=0){
			 $(".rm0").remove();
		}
		$("#topicbox").append(h.join(""));
		chatcontainer.scrollToLast();
	   //chatcontainer.scrollToLast();
	   if(chat.id!=null&&typeof(chat.id) != "undefined"&&chat.id!=0){
		   _last_info_id=chat.id;
	   }
   }
   
   
   
   function initlock(){
	   if(islock=="lock"){
		   $(".popPsd").show();
		   closeAll();
	   }else if(islock=="unlock"){
		   $(".popPsd").hide();
	   }
   }
   
   function closeAll(){
	   clearInterval(getInfoThread);
	   clearInterval(getCuInfoThread);
		$("#myplayer").html("");
   }
   
   function showAll(){
	   getInfoThread=setInterval(invokeGetInfo,5000);    //每过5秒到后台取一次已审核通过的聊天记录
	   getCuInfoThread=setInterval(getInfo,30000);
	   $("#myplayer").html("<iframe id='videoFrame' src='video.html' frameborder='0' scrolling='no' width='100%' height='100%'></iframe>");
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
   
   
   

   
