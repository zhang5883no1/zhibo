<%@page import="java.io.OutputStream"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileOutputStream" %>
<%@page import="java.net.URLDecoder" %>
<%@ page contentType="text/html; charset=UTF8"%>
<%
String fileName=request.getParameter("filename");
	fileName=URLDecoder.decode(fileName,"UTF-8");
out.println(fileName);
	//打开指定文件的流信息
	String filepath = request.getRealPath(fileName);

	FileInputStream fs = null;
	try {
		fs = new FileInputStream(new File(filepath));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		out.print("file error");
		return;
	}
	//设置响应头和保存文件名
	response.reset();
	response.setContentType("application/x-download");
	response.setHeader("Content-Disposition", "inline; filename=\""
			+ new String(fileName.getBytes("UTF-8"), "ISO_8859_1") + "\"");
	//写出流信息
	int b = 0;
	try {
		OutputStream ops = response.getOutputStream();
		while ((b = fs.read()) != -1) {
			ops.write(b);
		}
		fs.close();
		out.clear();
		out = pageContext.pushBody();
	} catch (Exception e) {
		e.printStackTrace();
		out.print("download error");
		System.out.println("下载文件失败!");
	}
%>
