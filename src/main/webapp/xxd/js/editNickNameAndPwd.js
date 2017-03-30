
//修改昵称和密码
$(function(){
	
	//点击修改昵称按钮
	$("#editNickName").on('click',function(){
		var newNickName = $.trim($("#newNickName").val());
		var id = customer.id;
		var roomNo = "1";
		if(newNickName.length > 0){
			$.ajax({
			  	url:"../controller/updateNickNameAndPwdController;jsessionid="+sessionId,
			  	type:"post",
			  	dataType:"text",
			  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
			  	data:{"id":id,"newNickName":encodeURI(newNickName),"time":time,"number":"1","roomNo":roomNo},
				success:function(res){
					if(res == "0"){
						alert("修改昵称失败");
					}else{
						customer = JSON.parse(res);
						$("#_info_nickName").html(customer.nickName);
						$(".perName").html("昵称："+customer.nickName+"<img src='images/listDowm.png'>");
						$(".hide").hide();
						$(".perMain").hide();
						alert("修改昵称成功");
					}
					$("#newNickName").val("");
				},
				error:function(){
					alert("error");
				}
			});
		}
	});
	
	
	//点击修改密码按钮
	$("#editPwd").on('click',function(){
		var oldPwd = $.trim($("#oldPwd").val());
		var newPwd = $.trim($("#newPwd").val());
		var reNewPwd = $.trim($("#reNewPwd").val());
		var id = customer.id;
		var roomNo = "1";
		if(newPwd == reNewPwd){  //如果两次输入的密码相等
			if(oldPwd.length > 0 && newPwd.length > 0 && reNewPwd.length > 0){
				if(oldPwd != customer.pwd){
					alert("原密码错误");
				}else{
					$.ajax({
					  	url:"../controller/updateNickNameAndPwdController;jsessionid="+sessionId,
					  	type:"post",
					  	dataType:"text",
					  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",     //解决传中文乱码
					  	data:{"id":id,"newPwd":newPwd,"time":time,"number":"2","roomNo":roomNo},
						success:function(res){
							if(res == "0"){
								alert("修改密码失败");
							}else{
								$(".hide").hide();
								$(".perMain").hide();
								customer = JSON.parse(res);
								alert("修改密码成功");
							}
							$("#oldPwd").val("");
				    		$("#newPwd").val("");
				    		$("#reNewPwd").val("");
						},
						error:function(){
							alert("error");
						}
					});
				}
			}else{
				alert("密码不能为空");
			}
		}else{
			alert("两次输入密码不一致");
		}
	});
})