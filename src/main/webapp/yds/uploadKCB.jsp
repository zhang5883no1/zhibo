<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function checkFile() {
		//用元素的id获得该元素的值，从而进行判断选择的文件是否合法  
		var file = document.uploadForm.p.value;
		if (file == null || file == "") {
			alert("你还没有选择任何文件，不能上传!");
			return;
		}
		if (file.lastIndexOf(".") == -1) {
			alert("路径不正确!");
			return;
		}
		var allImgExt = ".png|";
		var extName = file.substring(file.lastIndexOf("."));
		if (allImgExt.indexOf(extName + "|") == -1) {

			errMsg = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
			alert(errMsg);
			return;
		}
		document.uploadForm.submit();
	}
</script>
</head>
<body>

	<form action="uploadServlet3" method="POST"
		enctype="multipart/form-data" name="uploadForm" >
		<input type="file" id="p" name="1" size="20" />
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
