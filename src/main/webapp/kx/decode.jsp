<%@page import="java.net.URLDecoder" %>
<%@page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%
	String utm_source=request.getParameter("utm_source");
	String utm_term=request.getParameter("utm_term");
	String source =URLDecoder.decode(utm_source,"GBK");
	String term=URLDecoder.decode(utm_term,"GBK");
	String s1 = new String(utm_source.getBytes("utf-8"),"gbk");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<script type="text/javascript" src="js/jquery-1.9.js"></script>
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	$(document).ready(
		function() {
			window.parent.document.utm_source=<%=utm_source%>;
			window.parent.document.utm_term=<%=utm_term%>;
		}
	);
</script>
<%out.println("1:"); %>
<%out.println(utm_source+";"+utm_term); %>
<%out.println("2:"); %>
<%out.println(source+";"+term); %>
<%out.println("3:"); %>
<%out.println(s1); %>
</body>
</html>
