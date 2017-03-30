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
	/*marquee*/
	function marqueeStart(){
	var dom=$(".marquee");
	var L=dom.text().length;
	var s=L*200;
	var marginLeftval="-"+(L*1.5)+"%";
		dom.animate({marginLeft:marginLeftval},s,'linear',function(){
			dom.css({marginLeft:'100%'})
			setTimeout(marqueeStart,1000)
		})
   	}
	marqueeStart();
	/*personal*/
	$(".alter dd").hide();
	$(".alter dt").click(function(){
		var $con=$(this).siblings("dd");
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
		$(".pop").fadeIn(100).css("display","block");
		$(".perMain").fadeIn(100).css("display","block");
		$(".personal").fadeIn(100).css("display","block");
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
	
	$(".tab3").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(".state").show();
		$(".stateBg").hide();
		$(".change").hide();
	})
	$(".ascrail2000").hide();
	$(".tab4").click(function(){
		$(".pop").fadeIn(100).css("display","block");
		$(".plat").fadeIn(100).css("display","block");
		$("#platBox").fadeIn(100).css("display","block");
	})
	
	$(".close").click(function(){
		$(".hide").hide();
		$(".perMain").hide();
	})
	$(".pop").click(function(){
		$(".hide").hide();
	})
	
	 /*alertBox*/
	 $(".alclose").click(function(){
		$(".alertBox").hide();
	 })
	 /*look*/
	 $(".loclose").click(function(){
		$(".look").hide();
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
    
});
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