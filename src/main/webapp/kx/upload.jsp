<%@page import="com.xidu.dao.UploadDao"%>
<%@page import="java.util.List"%>
<%@page import="com.xidu.entity.Upload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/jquery-1.11.0.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/upload.css" />
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
		var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.rar|.zip|.gif|.ico|.doc|.pdf|.ppt|.exe|.xls|.doc|.wps|.et|.dps|";
		var extName = file.substring(file.lastIndexOf("."));
		if (allImgExt.indexOf(extName + "|") == -1) {

			errMsg = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
			alert(errMsg);
			return;
		}
		document.uploadForm.submit();
	}
</script>
<style type="text/css">
<!--
.STYLE1 {
	font-family: "新宋体";
	font-weight: bold;
	font-size: 24px;
}

.STYLE3 {
	font-size: 16px
}

.STYLE4 {
	color: #FF0000
}

.STYLE6 {
	font-family: Arial, Helvetica, sans-serif
}
-->
</style>
</head>
<body>
	<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
			out.println(message);
		}
		UploadDao dao=new UploadDao();
		List<Upload> list=dao.getAllUpload();
	%>
	<form action="uploadServlet" method="POST"
		enctype="multipart/form-data" name="uploadForm" style="display:none;">
		<input type="file" id="p" name="" size="20" />
		<input type="hidden" name="type" value="save" />
	</form> 

	<div class="list-content-wrap">
		<div class="uploadfile-wrap">
			<div id="errorContainer" class="error-wrap"></div>
			<div class="flash-btn-wrap">
				<div class="btnupload-wrap">
					<div class="flash-upload-btn">
						上传共享文件
					</div>
					<div class="flash-upload-format">
						<div>可上传的文件格式：*.rar;*.zip;*.png;*.jpg;*.gif;*.ico;*.bmp;*.doc;*.pdf;*.ppt;*.exe;*.xls;*.doc;*.wps;*.et;*.dps</div>
						<div>可上传文件大小：25 MB</div>
					</div>
				</div>
			</div>
		</div>
		<div id="content-container">
			<div class="list-header l-header">
				<ul>
					<li class="l-h-title"><span>标题</span></li>
					<li class="l-h-time"><span>发布时间</span></li>
					<li class="l-h-action"><span>操作</span></li>
				</ul>
			</div>

			<ul id="list-container" class="list-items">
				<%
					for(Upload up:list){
						%>
						<li class="l-i-title"><span><%=up.getName() %></span></li>
						<li class="l-i-time"><span><%=up.getLastUpdateDate() %></span></li>
						<li class="l-i-action"><a target="_blank"
							href="<%=up.getSrc()%>">下载</a></li>
						<%
					}
				%>
			</ul>
			<div class="pager" id="pager"></div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(
		function() {
			$("#p").attr("name",window.parent.id);
			
			if(window.parent.typeList==3||window.parent.typeList==4||window.parent.typeList==5){
				
			}else{
				$(".uploadfile-wrap").remove();
			}
			//window.parent.nickName
			$(".flash-upload-btn").click(function(){
				$("#p").click();
			});
			$("#p").change(function(){
				checkFile();
			});
			
		}
	);
</script>
</body>
</html>
