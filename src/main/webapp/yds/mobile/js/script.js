$("input").attr('data-role','none');  
$("select").attr('data-role','none');

$(document).ready(function(){
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
		$(".video").html("<iframe id='videoFrame' frameborder='0' scrolling='no' width='100%' height='100%'></iframe>"); 
		$(".btnBox").hide();
	 })
	 /*login*/
	 $(".log").click(function(){
		$(".login").fadeIn(100).css("display","block");
		$(".login p input").val("");
		$(".video").html("<iframe id='videoFrame' frameborder='0' scrolling='no' width='100%' height='100%'></iframe>");  
		$(".btnBox").hide();
	 })
	 $(".login span a").click(function(){
	 	$(".register").css("opacity","1");
	 	$(".register").show();
		$(".register div input").val("");
		$(".login").hide();
	 })
	$(".close").click(function(){
		$('#videoFrame').attr("src","video.html");
		$(".hide").hide();
		$(".btnBox").show();
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
	$('#caitiaonav li').on('click', function() {
		$('#caitiao dl').hide();
    });
}
function sendCaitiao(e){
	$("#sendMsgInput").val('['+e+']');
}
$('#bt_face').SinaEmotion($('#sendMsgInput'));
/*talk_pic*/
function talk_pic(img){
	var imgsrc=$(img).attr("src");
	TINY.box.show({image: imgsrc});
}