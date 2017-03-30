package com.xidu.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xidu.constant.Constant;
import com.xidu.dto.LoginInfo;
import com.xidu.entity.Chats;
import com.xidu.entity.Customer;
import com.xidu.entity.Robot;
import com.xidu.init.SessionCounter;
import com.xidu.service.ChatService;
import com.xidu.util.IPUtil;


/**
 * 登录
 * @author Administrator
 *
 */
public class SessionController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			JSONObject json=new JSONObject();
			request.getSession().setAttribute(Constant.LAST_LIVE_TIME, new Date().getTime());
			Customer customer =(Customer)request.getSession().getAttribute("customer");
//			LinkedList<LoginInfo> infolist=(LinkedList<LoginInfo>)request.getSession().getAttribute(Constant.LAST_LOGIN_INFO);
			request.getSession().setAttribute(Constant.LAST_LOGIN_INFO, new LinkedList<LoginInfo>());
			JSONArray infoArray=new JSONArray();
			List<Customer> customerList=SessionCounter.getAllLiveCustomer();
			for(Customer c:customerList){
				infoArray.add(c.toInfoJSONWithLevel(customer.getLevel()));
			}
//			for(LoginInfo info:infolist){
//				infoArray.add(info.toJSONWithLevel(customer.getLevel()));
//			}
			json.accumulate("info", infoArray);
			json.accumulate("is_chat", customer.getCustomerType().getIs_chat());
			out.print(request.getParameter("callback")+"("+json.toString()+")");
			out.flush();
			out.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
	}
}
