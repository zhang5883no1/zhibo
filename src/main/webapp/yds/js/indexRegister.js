var roomNo=2;
var data_id;
$(function(){
	
	   //点击在线会员名
	   $(".nameList li").click(function(){
		   if(level == 100){
			   $(this).find("div").show().parent().siblings().find("div").hide();
			   data_id = $(this).attr("data-id");
		   }else{
			   $(this).find("div").hide();
		   }
		   return false;
	   });
	
       //点击图片，切换图形验证码
    	$("#image").on('click',function(){
        	var rd = Math.random();
        	$("#image").attr('src',"../controller/code.jpg;jsessionid="+sessionId+"?rd="+rd);
    	});
    	
    	//点击注册框的go链接，打开登录框，关闭注册框
    	$("#register_go").on('click',function(){
    		$(".login").fadeIn(100).css("display","block");  //打开登录框
    		$(".register").fadeIn(100).css("display","none"); //关闭注册框
    		$(".login").css("z-index",1000);
    	});
    	
    	//点击注册、登录的关闭按钮时清空文本框
    	$(".close").click(function(){
    		
    		$("#userName").val("");
    		$("#nickName").val("");
    		$("#pwd").val("");
    		$("#rePwd").val("");
    		$("#mobile").val("");
    		$("#mobileCode").val("");
    		$("#qq").val("");
    		
    		$("#userName_login").val("");
    		$("#pwd_login").val("");
    		
    		$("#newNickName").val("");
    		$("#oldPwd").val("");
    		$("#newPwd").val("");
    		$("#reNewPwd").val("");
    		
    		$('#huakuai').empty();
    		$('#huakuai').removeAttr("style");
    		yanzheng = "";
    		$('#huakuai').huakuai();
    	})
    	
    	
      	//点击'完成注册'按钮
    	$("#register").on('click',function(){
    		var referer=document.referrer;
    		var linksource=(location+"").replace("#","");
    		var userName = $("#userName").val();
    		var nickName = $("#nickName").val();
    		var pwd = $("#pwd").val();
    		var rePwd = $("#rePwd").val();
    		var mobile = $("#mobile").val();
    		var mobileCode = $("#mobileCode").val();
    		var qq = $("#qq").val();
    		if(pwd != rePwd){
    			alert("两次密码输入不一致");
    			return;
    		}
    		
    		if(mobile==""||mobile=="12345678900"){
    			alert("请输入手机号");
    			$("#mobile").focus();
    			return;
    		}
    		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
    		if (reg.test(mobile)){
    			if(yanzheng == "ok"){
    				if(userName.length>0 && nickName.length>0 && pwd.length>0 && rePwd.length>0 && mobile.length>0 && qq.length>0){
            			$.ajax({
            				url:"../controller/CustomerRegisterController;jsessionid="+sessionId,
            				type:"post",
            				dataType:"json",
            				contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
            				data:{"userName":encodeURI(userName),"nickName":encodeURI(nickName),"pwd":pwd,"mobile":mobile,"referer":encodeURI(referer),"linksource":encodeURI(linksource),"time":time,"roomNo":roomNo,"mobileCode":mobileCode,"qq":qq},      //encodeURI(xxx) 编码
            				success:function(res){
            					if(res == "1"){      //1:用户名和手机号已存在   2:用户名已存在  3:手机号已存在 4:填入的信息有误 5:手机验证码不存在，请重新获取 6:手机验证超时，请重新获取 7:手机验证码错误，请重新输入
            						alert("用户名和手机号已存在");
            					}else if(res == "2"){
            					    alert("用户名已存在");
            					}else if(res == "3"){
            						alert("手机号已存在");
            					}else if(res == "4"){
            						alert("填入的注册信息有误");
            					}else if(res == "5"){
            						alert("手机验证码不存在，请重新获取");
            					}else if(res == "6"){
            						alert("手机验证超时，请重新获取");
            					}else if(res == "7"){
            						alert("手机验证码错误，请重新输入");
            					}else{
                					afertLogin(res);
                					$("#is_look").css("z-index",999);
                					$(".register").css("z-index",999);
                					$(".login").css("z-index",999);
                					$(".pop").css("background","");
                					$(".pop").addClass("hide");
                					$(".loclose").show();
                					$("#is_look").hide();
            					}
            				},
            			error:function(){
            								
            			}
                     })
            	   }else{
           			alert("您输入的信息不全");
           		   } 
    			}else{
    				alert("请滑动滑块验证");
    			}
    		}else{
    			alert("号码有误");
    			$("#mobile").focus();
    			return;
    		};
    	})
    });
    
    
  //检验再次输入密码
	function checkPwd(){
		var pwd = $("#pwd").val();
		var rePwd = $("#rePwd").val();
		if(pwd != rePwd){
			alert("两次密码输入不一致");
			$("#pwd").focus();
			return;
		}
	}
  
  
	//获取短信验证码
	function getMobileCode(o){
		var mobile = $("#mobile").val();
		var imgCode = $("#imgCode").val();
		if(mobile==""||mobile=="12345678900"){
			alert("请输入手机号");
			$("#mobile").focus();
			return;
		}
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if (reg.test(mobile)) {
			
		}else{
			alert("号码有误");
			$("#mobile").focus();
			return;
		};
		
		if(imgCode==""){
			alert("请输入验证码");
			$("#imgCode").focus();
			return;
		}
		
		
		$.ajax({
			url:'../controller/codeconfig.shtml;jsessionid='+sessionId,
			type:"post",
			dataType:"text",
			data:{"mobile":mobile,"imgCode":imgCode},
			contentType:"application/x-www-form-urlencoded;charset=UTF-8",   
			success:function(msg){
				alert(msg);
			}
		});
		daojishi1(o);
	};
	
	//手机验证码倒计时
	function daojishi1(o){
		if(wait == 0) {
			o.removeAttribute("disabled");   
			o.value="点击免费获取";
			wait = 5;
		}else{
			o.setAttribute("disabled", true);
			o.value="重新发送(" + wait + ")";
			wait--;
			setTimeout(function() {
				daojishi1(o)
			},
			1000)
		}
	}

	
	function is_chat(){
		$.ajax({
			url:"../controller/UserInfoUpdateController;jsessionid="+sessionId,
			type:"post",
			dataType:"json",
			contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
			data:{"tn":"is_chat","tv":"1","time":time,"id":data_id},      //encodeURI(xxx) 编码
			success:function(res){
				$(".nameList li").find("div").hide();
				if(res.result == "0"){
					alert("禁言成功 \\^o^/");
				}else if(res.result == "1"){
					alert("禁言失败 \\(╯-╰)/");
				}else if(res.result == "99"){
					alert("禁言失败 \\(╯-╰)/");
				}
			}
	   })
	}