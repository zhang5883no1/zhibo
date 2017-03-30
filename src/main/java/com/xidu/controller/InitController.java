package com.xidu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;


/**
 * 初始化
 * @author Administrator
 *
 */
public class InitController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession();
	      String sessionId = session.getId();
	      Long time = System.currentTimeMillis();   //获取时间戳
	      session.setAttribute("time",time);
//	      session.setAttribute("room", room);
	      JSONObject json=new JSONObject();
	      json.accumulate("time", time);
	      json.accumulate("sessionId", sessionId);
	      PrintWriter out = response.getWriter();
	      out.print(json.toString());
		  out.flush();
		  out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doGet(request, response);
	}
	
}
