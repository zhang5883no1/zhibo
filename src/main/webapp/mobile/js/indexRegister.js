var roomNo=1;
	
	//点击获取验证码
	function _getCode(){
		var mobile = $("#reg_mob").val();
		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
		if(mobile==""||mobile=="12345678900"){
			alert("请重新输入手机号码");
			$("#reg_mob").focus();
			return;
		}
		
		if(reg.test(mobile)){
			$.ajax({
				url:"../controller/codeService;jsessionid="+sessionId,
				type:"post",
				dataType:"text",
				contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
				data:{"mobile":mobile,"roomNo":roomNo},      //encodeURI(xxx) 编码
				success:function(res){ //1:验证码已发送  2:短信发送失败  3:手机号码已注册过  4:短信一天发送次数超过5次  5:短信获取间隔小于5分钟  6:手机号码格式不正确
					if(res == "1"){
						alert("验证码已发送");
					}else if(res == "2"){
						alert("短信发送失败 ");
					}else if(res == "3"){
						alert("手机号码已注册过");
					}else if(res == "4"){
						alert("短信一天发送次数超过5次");
					}else if(res == "5"){
						alert("短信获取间隔小于5分钟");
					}else if(res == "6"){
						alert("手机号码格式不正确");
					}
				}
         });
		}else{
			alert("手机号码有误");
			$("#reg_mob").focus();
		}
	}
	
	
      	//点击'完成注册'按钮
    	function _Reg(){
    		var referer = document.referrer;
    		var linksource = (location+"").replace("#","");
    		var userName = $("#reg_account").val();
    		var nickName = $("#reg_nickName").val();
    		var pwd = $("#reg_pwd").val();
    		var rePwd = $("#reg_pwd1").val();
    		var mobile = $("#reg_mob").val();
    		var mobileCode = $("#reg_code").val();
    		var qq = "888888";
    		if(pwd != rePwd){
    			alert("两次密码输入不一致");
    			return;
    		}
    		
    		if(mobile==""||mobile=="12345678900"){
    			alert("请输入手机号");
    			$("#reg_mob").focus();
    			return;
    		}
    		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
    		if (reg.test(mobile)){
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
            					}
            				},
            			 error:function(){
            								
            			 }
                     });
            	   }else{
           			alert("您输入的信息不全");
           		   } 
    		}else{
    			alert("号码有误");
    			$("#reg_mob").focus();
    			return;
    		}
    }
    
    
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