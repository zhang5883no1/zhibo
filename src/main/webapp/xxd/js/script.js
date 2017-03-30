function delHtmlTagjs(str){
		return str.replace(/<img/g  ,"xdImg").replace(/<[^>]+>/g,"").replace(/xdImg/g ,"<img ");//去掉所有的html标记
	}
$(document).ready(function(){
	$(".section-focus-pic").height($(".section-focus-pic ul li").height());
	/*sub*/
	$(".calHead").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".calendar").fadeIn(100).css("display","block");
	})
	$(".courseHref").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".course").fadeIn(100).css("display","block");
	})
	$(".eiaHref").click(function(){
		$(".eiaBox").html('<iframe frameborder="0" width="100%" height="100%" src="http://www.ylzc8888.com/apinewsdata/data.php?url=2" scrolling="yes"  id="uploadImg" name="uploadImg"></iframe> ');
		$(".pop").fadeIn(100).css("display","block");
		$(".eia").fadeIn(100).css("display","block");
	})
	$(".unHref").click(function(){
		$(".unBox").html('<iframe frameborder="0" width="100%" height="100%" src="http://www.ylzc8888.com/apinewsdata/data.php?url=1" scrolling="yes"  id="uploadImg" name="uploadImg"></iframe> ');
		$(".pop").fadeIn(100).css("display","block");
		$(".unNum").fadeIn(100).css("display","block");
	})
	$(".unRead").click(function(){
		if(customer.level==0){
			$(".look").addClass("look4reg");
			$(".look").fadeIn(100).css("display","block");
		}else{
			$(".pop").fadeIn(100).css("display","block");
			$(".knowledge").fadeIn(100).css("display","block");
		}
		
	})
	// $(".unRead").click(function(){
	// 	$(".pop").fadeIn(100).css("display","block");
	// 	$(".knowledge").fadeIn(100).css("display","block");
	// })
	$("#onetoone").click(function(){
		$(".leftPop").fadeIn(100).css("display","block");
		if($(this).index()==2){
			$(".leftPop").addClass("leftPop2");
		}else if($(this).index()==3){
			$(".leftPop").addClass("leftPop3");
		}
		
//		if(customer.level==0){
//			
//		}else{
//			
//		}
	})
	
	$("#kefu").click(function(){
		$(".leftPop").fadeIn(100).css("display","block");
		$(".leftPop").fadeIn(100).css("background","url(images/leftPopBg_2.gif)");
		if(customer.level==0){
			
		}else{
			
		}
	})
	
	
	$(".free").click(function(){
		$(".leftPop").fadeIn(100).css("display","block");
		$(".leftPop").fadeIn(100).css("background","url(images/leftPopBg.gif)");
		if(customer.level==0){
			
		}else{
			
		}
	})
	/*marq*/
	marqs=function(divId,speed,content){
		var oDiv=document.getElementById(""+divId);
		$("#"+divId).find("ul").html("<li>"+content+"</li><li>"+content+"</li>");
		var oUl=oDiv.getElementsByTagName("ul")[0];
		var aLi=oUl.getElementsByTagName("li");
		// var aSpan=oDiv.getElementsByTagName("span");
		oUl.innerHTML=oUl.innerHTML;
		var leng = aLi.length;
		var nums=0;
		for(var i=0; i<leng;i++){
		    aLi[i].style.width=aLi[i].offsetWidth+"px";
		    nums=parseInt(aLi[i].style.width)+nums + 160;
		}
		oUl.style.width = nums +"px";

		function animate(){
		   if(oUl.offsetLeft<-oUl.offsetWidth/2)
	            {
	                oUl.style.left="0";
	                }
	            if(oUl.offsetLeft>0)
	            {
	                oUl.style.left=-oUl.offsetWidth/2+"px";
	                }
	            oUl.style.left=oUl.offsetLeft-speed+"px";
	            }
		clearInterval(marqInterval);
		marqInterval=setInterval(animate,30);
	}
	marqs("marq",2,"");
	/*personal*/
	$(".nameList li").click(function(){
		$(this).find("div").show().parent().siblings().find("div").hide();
		return false;
	})
	$("body").click(function(){
		$(".nameList li").find("div").hide();
	})
	$(".alter dd").hide();
	$(".alter dt").click(function(){
		var $con=$(this).siblings("dd");
		$con.find(".tex").val("");
		if($con.is(":visible"))
		{
			$con.slideUp();
			$(this).find("img").attr("src","images/alterDown.png");
		}
		else
		{
			$con.slideDown();
			$(this).find("img").attr("src","images/alterUp.png");
			var $dds=$(this).parent("dl").siblings();
			$dds.find("dd").slideUp();
			$dds.find("img").attr("src","images/alterDown.png");
		}
	})
	$(".perName").click(function(){
		if(customer.level==0){
			return;
		}
		$(".alter dd").hide();
		$(".alter dt img").attr("src","images/alterDown.png");
		$(".perMain").fadeIn(100).css("display","block");
	})
	$(".sbmName").click(function(){
	 	if(!$(this).siblings("input").val() == ""){
	 		var text = $(this).siblings("input").val();
			$(".perBox span").text(text);
			$(this).parent("dd").slideUp();
			$(this).parent("dd").siblings("dt").find("img").attr("src","images/alterDown.png");
	 	}
	 	else{
	 		alert("昵称不能为空!");
	 	}
	 })
	/*tab*/
	$(".tab1").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(".state").hide();
		$(".stateBg").hide();
		$(".change").show();
	})
	$(".tab2").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".download").fadeIn(100).css("display","block");
	})
	$(".close").click(function(){
		$(".hide").hide();
		$(".perMain").hide();
	})
	$(".pop").click(function(){
		$(".hide").hide();
	})
	$(".tab3").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(".stateBg").hide();
		$(".state").show();
		$(".change").hide();
	})
	$(".ascrail2000").hide();
	$(".tab4").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".plat").fadeIn(100).css("display","block");
		$("#platBox").fadeIn(100).css("display","block");
	})
	/*teachtb*/
	$(".teach").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".teachtb").fadeIn(100).css("display","block");
	 })
	 /*alertBox*/
	 $(".alclose").click(function(){
		$(".alertBox").hide();
	 })
	 /*look*/
	 $(".loclose").click(function(){
		$(".look").hide();
		$(".look").removeClass("look4reg");
	 })
	 /*leftPop*/
	 $(".leclose").click(function(){
		$(".leftPop").hide();
		$(".leftPop").removeClass("leftPop2");
		$(".leftPop").removeClass("leftPop3");
	 })
	 /*download*/
	 $(".download ul li").click(function(){
		$(this).addClass("current").siblings().removeClass("current");
		var index = $(this).index();
		$(".load>div").eq(index).show().siblings().hide();
	 })
	 /*register*/
	 $(".reg").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".register").fadeIn(100).css("display","block");
		$(".register div input").val("");
	 })
	 /*login*/
	 $(".log").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".login").fadeIn(100).css("display","block");
		$(".login p input").val("");
	 })
	 $(".login h1 a").click(function(){
		$(".login").hide();
		$(".register").fadeIn(100).css("display","block");
		$(".register div input").val("");
	 })
	 
	 /*layout*/
	 $(".layout").click(function(){
		$(".layMenu").show();
		return false;
	})
	 $("body").click(function(){
		$(".layMenu").hide();
	})

	 var $pageContainer = $(".bg");
	 function themeChangeEvent($themeItems , value){
		var _themeValue = value;
		$themeItems.click(function(){
			var $this = $(this);
			var data = {};
			var theme = $this.data('theme');
			$themeItems.removeClass('active');
			$this.addClass('active');
			$pageContainer.removeClass(_themeValue).addClass(theme);
			_themeValue = theme;
		});
		$themeItems.filter('[data-theme="'+ value +'"]').addClass('active');
	};
	themeChangeEvent( $('.layoutSider span'), 'layout_sider_left');
	themeChangeEvent( $('.layoutVideo span'), 'layout_video_right');
	themeChangeEvent( $('.btnColorWrap span'), 'btnColor1');
	themeChangeEvent( $('.backWrap span'), 'backBg10');
	
	 /*fly*/
	function flystart(){
	        $(".fly b").animate({left:'-3000px'},90000,'linear',function(){
	        $(".fly b").css({left:'100%'})
	        })
	}
	flystart();
	/*draggable*/
	$( "#draggable").draggable();
	$("#draggable").draggable({containment:"parent"});
	$( "#draggable .draClose").click(function(){
		$( "#draggable").hide();
	})
	 /*topiccontent*/
	 chatcontainer.scrollToLast();
	 /*textarea*/
	$('#sendMsgInput').keyup(function(event) {
		if (event.keyCode == 13) {
				var content=$("#sendMsgInput").html();
				$("#sendMsgInput").html(content);
				$(".sub_btn").trigger("click");
				return false;
		}
	})
	/*nano*/
	$('.nano').nanoScroller({
    		preventPageScrolling: true
    })
	
	$('#sendMsgInput').focus(function() {
		var result=$(this).html();
		if($.trim(delHtmlTagjs(result))=="早开户，早盈利，油、银、铜全天解盘，精准喊单，经得住考验"){
			$(this).html("");
		}
	})
	
	$('#sendMsgInput').blur(function() {
		var result=$(this).html();
		if($.trim(delHtmlTagjs(result))==""){
			$(this).html("早开户，早盈利，油、银、铜全天解盘，精准喊单，经得住考验");
		}
	})
	
	$('#sendMsgInput').keydown(function(event) {
		if (event.keyCode == 13) {
				return false;
		}
	})
	/*nano*/
	$('.nano').nanoScroller({
    		preventPageScrolling: true
    	})
})
 /*表情和彩条*/
function showFacePanel(e, toinput) {
	var offset = $(e).offset();
	var t = offset.top;
	var l = offset.left;
	$('#face').css({"top": t - $('#face').outerHeight(), "left": l});
	$('#face').show();
	$('#face').attr("toinput", toinput);
}
function  initFaceColobar() {
	$('#caitiaonav li').on('click', function() {
		$('#caitiao dl').hide();
    });
}
$('#bt_face').SinaEmotion($('#sendMsgInput'));
/*talk_pic*/
function talk_pic(img){
	var imgsrc=$(img).attr("src");
	TINY.box.show({image: imgsrc});
}
/*time*/
$(function(){
	videoTime();
})


function videoTime(){
		if(level == 0){  //游客
			addHours = 0;
			addMinutes = 5; 
			totalVideoTime = addMinutes  * 60;  //换算成秒
			doVideoTime1();
		}else if(level >=1 && level <=6){
			if(json.customerType.video_time<=json.customerType.used_video_time){
				$(".emb").html("<embed src='http://static.gensee.com/webcast/static/sdk/flash/GenseeEasyLive.swf' wmode='transparent'>");
				alert("视频试观看时间结束，请注册会员获取更多观看时长");
				return;
			}else{
				addDay = Math.floor(totalVideoTime/86400);
				addHours = Math.floor((totalVideoTime%86400)/3600); 
				addMinutes = Math.floor(((totalVideoTime%86400)%3600)/60);  
				seconds = Math.floor(((totalVideoTime%86400)%3600)%60);
				
				doVideoTime2();
			}
			
		}else if(level >= 90 && level <= 100){
			totalVideoTime = 32536000;
			addDay = 365; 
			addHours = 0; 
			addMinutes = 0;  
			seconds = 0;
			doVideoTime2();
		}
		
		if(totalVideoTime == 0){
			$(".emb").html("<embed src='http://static.gensee.com/webcast/static/sdk/flash/GenseeEasyLive.swf' wmode='transparent'>");
			alert("视频试观看时间结束，请注册会员获取更多观看时长");
		}
		videoTimeThread = setInterval(sumVideoTime,60000);
	}


function sumVideoTime(){
	
	if(level == 0){
		if(totalVideoTime == 0){
			$(".emb").html("<embed src='http://static.gensee.com/webcast/static/sdk/flash/GenseeEasyLive.swf' wmode='transparent'>");
			alert("视频试观看时间结束，请注册会员获取更多观看时长");
		}else{
			totalVideoTime = totalVideoTime - 60;
		}
	}
	
	if(level >=1 && level <=6 && totalVideoTime >= 0){
		$.ajax({
		  	url:"../controller/SumVideoTimeController;jsessionid="+sessionId,
		  	type:"post",
		  	dataType:"text",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	data:{"customer_id":customer.customerType.customer_id,"time":time},      //encodeURI(xxx) 编码
			success:function(res){
				if(res != "请重新登录"){
					totalVideoTime = parseInt(res) * 60;   // 获取剩余视频总时间
					if(totalVideoTime == 0){
						$(".emb").html("<embed src='http://static.gensee.com/webcast/static/sdk/flash/GenseeEasyLive.swf' wmode='transparent'>");
						alert("视频试观看时间结束，请注册会员获取更多观看时长");
					}else{
						totalVideoTime = totalVideoTime - 60;
					}
				}else{
					location.reload();
				}
			},
			error:function(){
				
			}
		})  
	}
}

//只显示时分秒
function doVideoTime1(){
	var b = new Date;
    var myDate = new Date();
    var b = -b.getTimezoneOffset() / 60;
    
    /*var addHours = 2;  //小时
    var addMinutes = 0;  //分钟*/    
    
    //var i = '2016__regexoperators___/06/28 14:13:00';
    var i = myDate.getFullYear()+'__regexoperators___/'+(myDate.getMonth()+1)+'/'+myDate.getDate()+' '+(myDate.getHours())+':'+(myDate.getMinutes() + addMinutes)+':'+myDate.getSeconds();
    var config = {
        timeText: i, //倒计时时间
        timeZone: b, //时区
        style: "flip", //显示的样式，可选值有flip,slide,metal,crystal
        color: "white", //显示的颜色，可选值white,black
        width: 123, //倒计时宽度
        textGroupSpace: 10, //天、时、分、秒之间间距
        textSpace: 4, //数字之间间距
        reflection: 0, //是否显示倒影
        reflectionOpacity: 10, //倒影透明度
        reflectionBlur: 0, //倒影模糊程度
        dayTextNumber: 3, //倒计时天数数字个数
        displayDay: 0, //是否显示天数
        displayHour: !0, //是否显示小时数
        displayMinute: !0, //是否显示分钟数
        displaySecond: !0, //是否显示秒数
        displayLabel: 0, //是否显示倒计时底部label
        onFinish: function() {}
    };
    $(".countdown").jCountdown(config);
}

//只显示日时分秒
function doVideoTime2(){
	var b = new Date;
    var myDate = new Date();
    var b = -b.getTimezoneOffset() / 60;
    /*var addHours = 2;  //小时
    var addMinutes = 0;  //分钟*/ 
    
    //var i = '2016__regexoperators___/06/28 14:13:00';

    var i = myDate.getFullYear()+'__regexoperators___/'+(myDate.getMonth()+1)+'/'+(myDate.getDate() +addDay )+' '+(myDate.getHours()+addHours )+':'+(myDate.getMinutes() + addMinutes)+':'+(myDate.getSeconds() + addSeconds);

    var config = {
        timeText: i, //倒计时时间
        timeZone: b, //时区
        style: "flip", //显示的样式，可选值有flip,slide,metal,crystal
        color: "white", //显示的颜色，可选值white,black
        width: 205, //倒计时宽度
        textGroupSpace: 10, //天、时、分、秒之间间距
        textSpace: 4, //数字之间间距
        reflection: 0, //是否显示倒影
        reflectionOpacity: 10, //倒影透明度
        reflectionBlur: 0, //倒影模糊程度
        dayTextNumber: 3, //倒计时天数数字个数
        displayDay: !0, //是否显示天数
        displayHour: !0, //是否显示小时数
        displayMinute: !0, //是否显示分钟数
        displaySecond: !0, //是否显示秒数
        displayLabel: 0, //是否显示倒计时底部label
        onFinish: function() {}
    };
    $(".countdown").jCountdown(config);
}