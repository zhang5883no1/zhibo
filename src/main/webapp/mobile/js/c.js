//页面初始化时
   $(function(){ 
	   getlive();
	   getValid();
	   setInterval(getlive,10000);
	   setInterval(getValid,10000);
	   
	   $("#room_lock").change(function(){
		   $.ajax({
			  	url:"../controller/CcController?action=setLock&key=islock&sval="+$(this).val(),
			  	type:"get",
			  	dataType:"jsonp",
			  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
			  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
				success:function(res){
					if(res.key=="1"){
						alert("切换成功");
					}
				},
				error:function(){
					
				}
			})
	   });
	   
	   $("#lock_sub").click(function(){
		   $.ajax({
			  	url:"../controller/CcController?action=setLock&key=lockPwd&sval="+$("#lock_pwd").val(),
			  	type:"get",
			  	dataType:"jsonp",
			  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
			  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
				success:function(res){
					if(res.key=="1"){
						alert("提交成功");
					}
				},
				error:function(){
					
				}
			})
	   });
   });
   
   function getlive(){
	   $.ajax({
		  	url:"../controller/CcController?action=getlive",
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				$("#userTable").html("");
				for(var i=0;i<res.length;i++){
					var userid=res[i].id;
					var nickname=res[i].nickName;
					var status=res[i].status;
//					$(".uid"+userid).remove();
					if(status=="normal"){
						$("#userTable").append("<tr class='uid"+userid+"'><td>"+nickname+"</td><td>"+status+"</td><td><input type='button' value='禁言' onclick='jy(this)'/></td></tr>");
					}else{
						$("#userTable").append("<tr class='uid"+userid+"'><td>"+nickname+"</td><td>"+status+"</td><td><input type='button' value='解禁' onclick='unjy(this)'/></td></tr>");
					}
				}
				$("#total").html("总人数:"+$("#userTable tr").length);
			},
			error:function(){
				
			}
		})
   }
   
   function getValid(){
	   $.ajax({
		  	url:"../controller/CcController?action=getValid",
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				$("#chatTable").html("");
				for(var i=0;i<res.length;i++){
					var cid=res[i].id;
					var nickname=res[i].userName;
					var content=res[i].content;
					$(".cid"+cid).remove();
					$("#chatTable").append("<tr class='cid"+cid+"'><td>"+nickname+"</td><td>"+content+"</td><td><input type='button' value='通过' onclick='tg(this)'/><input type='button' value='不通过'  onclick='btg(this)'/></td></tr>");
				}
				
			},
			error:function(){
				
			}
		})
   }
   
   function tg(btn){
	   var id=$(btn).parent().parent().attr("class");
	   var rid=id.replace("cid","");
	   $.ajax({
		  	url:"../controller/CcController?action=doValid&chatid="+rid+"&valid=1&userid="+$("#userid").val(),
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(res.f=="success"){
					$(".cid"+res.id).remove();
				}
			},
			error:function(){
				
			}
		})
   }
   
   function btg(btn){
	   var id=$(btn).parent().parent().attr("class");
	   var rid=id.replace("cid","");
	   $.ajax({
		  	url:"../controller/CcController?action=doValid&chatid="+rid+"&valid=0",
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(res.f=="success"){
					$(".cid"+res.id).remove();
				}
			},
			error:function(){
				
			}
		})
   }
   
   function jy(btn){
	   var id=$(btn).parent().parent().attr("class");
	   var rid=id.replace("uid","");
	   $.ajax({
		  	url:"../controller/CcController?action=changeInfo&name=is_chat&value=jy&id="+rid,
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(res.result=="0"){
					alert("禁言成功");
				}
			},
			error:function(){
				
			}
		})
   }
   
   
   function unjy(btn){
	   var id=$(btn).parent().parent().attr("class");
	   var rid=id.replace("uid","");
	   $.ajax({
		  	url:"../controller/CcController?action=changeInfo&name=is_chat&value=normal&id="+rid,
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(res.result=="0"){
					alert("禁言成功");
				}
			},
			error:function(){
				
			}
		})
   }
