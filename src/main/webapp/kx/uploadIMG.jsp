<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.9.js"></script>
<script type="text/javascript" src="js/fileup.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function checkFile() {
		//用元素的id获得该元素的值，从而进行判断选择的文件是否合法  
		var file = document.getElementById("p").value;
		if (file == null || file == "") {
			alert("你还没有选择任何文件，不能上传!");
			return;
		}
		if (file.lastIndexOf(".") == -1) {
			alert("路径不正确!");
			return;
		}
		var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.rar|.zip|.gif|.ico|.doc|.pdf|.ppt|.exe|.xls|.doc|.wps|.et|.dps|";
		var extName = file.substring(file.lastIndexOf("."));
		if (allImgExt.indexOf(extName + "|") == -1) {

			errMsg = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
			alert(errMsg);
			return;
		}
		
		//document.uploadForm.submit();  
		
		//用ajax上传图片，需导入ajaxfileupload.js文件
//		var sessionIdfor = $(window.parent.document).find("#sessionId").val();
		var sessionIdfor = window.parent.sessionId;
		$.ajaxFileUpload({
			url:'controller/uploadServlet2;jsessionid='+sessionIdfor,             //需要链接到服务器地址  
            secureuri:false,  
            fileElementId:'p',                         //文件选择框的id属性  
            dataType:'jsonp',
            success: function (data)  //服务器成功响应处理函数
            {
            	var first = data.indexOf("{");
            	var last = data.lastIndexOf("}");
            	var path = data.substring(first+9,last-1);
            	$(window.parent.document).find("#sendMsgInput").html("<img src='upload"+path+"'/>");
            	location.reload();
            }
		});
	}

		
 
</script>
</head>
<body>
	<form action="" method="POST" enctype="multipart/form-data" name="uploadForm" >
		<input type="file" id="p" name="1" size="20"/>
		<input type="hidden" name="type" value="nosave" />
	</form> 

<script type="text/javascript">
	$(document).ready(
		function() {
			$("#p").change(function(){
				checkFile();
			});
		}
	);
</script>
</body>
</html>
