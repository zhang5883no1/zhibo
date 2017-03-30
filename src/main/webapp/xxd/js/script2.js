$(document).ready(function(){
	$(".nameLine li").click(function(){
		$(".userlist").hide();
		$(".userlist").eq($(this).index()).show();
	});
	
});