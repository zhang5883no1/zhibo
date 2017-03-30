$("input").attr('data-role','none');  
$("select").attr('data-role','none');

$(document).ready(function(){
	$(".huakuai").huakuai();
	$(".register").hide();
	$('#topicinput').bind("touchmove",function(e){
	            e.preventDefault();
	});
	// $('.btnBox').bind("touchmove",function(e){
	//             e.preventDefault();
	//  });
	 /*register*/
	 $(".reg").click(function(){
	 	$(".register").css("opacity","1");
	 	$(".register").fadeIn(100).css("display","block");
		$(".register div input").val("");
	 })
	 /*login*/
	 $(".log").click(function(){
		$(".login").fadeIn(100).css("display","block");
		$(".login p input").val("");
	 })
	 $(".login span a").click(function(){
	 	$(".register").css("opacity","1");
	 	$(".register").show();
		$(".register div input").val("");
		$(".login").hide();
	 })
	$(".close").click(function(){
		$(".hide").hide();
	})
	/*popPsd*/
	$(".passSub").click(function(){
		$.ajax({
		  	url:"../controller/CcController?action=validLockPwd&pwd="+$("#lockPwd").val(),
		  	type:"get",
		  	dataType:"jsonp",
		  	contentType:"application/x-www-form-urlencoded;charset=UTF-8",   //解决传中文乱码
		  	//data:{"page":startPage,"time":time,"roomNo":roomNo}, 
			success:function(res){
				if(res.key=="1"){
					$(".popPsd").hide();
					showAll();
				}else{
					alert("密码错误");
				}
			},
			error:function(){
				
			}
		})
	})
	 /*topiccontent*/
	 chatcontainer.scrollToLast();
})
 /*表情和彩条*/
function showFacePanel(e, toinput) {
	var offset = $(e).offset();
	var t = offset.top;
	var l = offset.left;
	$('#face').css({"top": t - $('#face').outerHeight(), "left": 0});
	$('#face').show();
	$('#face').attr("toinput", toinput);
}
function  initFaceColobar() {
	$('#bt_caitiao').on('click', function() {
			$('#caitiao').show();
	});
	$('#caitiaonav li').on('click', function() {
			$('#caitiao dl').hide();
	});
}
$(function() {
	$('#bt_caitiao').on('click', function() {
			$('#caitiao').show();
	});
	$(document).bind('mouseup', function(e) {
			if ($(e.target).attr('isnav') != '1')
			{
					$('#caitiao').hide();
			}
	})
})
function sendCaitiao(e){
	$("#sendMsgInput").val('['+e+']');
}
$('#bt_face').SinaEmotion($('#sendMsgInput'));
/*talk_pic*/
function talk_pic(img){
        TINY.box.show({image: img});
}