package com.xidu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.xidu.dao.CustomerTypeDao;
import com.xidu.entity.Customer;
import com.xidu.init.SessionCounter;

/**
 * 聊天记录
 * 
 * @author Administrator
 * 
 */
public class UserInfoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		String time = request.getParameter("time"); // 获取时间戳
		String name = request.getParameter("tn");
		String value = request.getParameter("tv");
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		String time2 = String.valueOf(session.getAttribute("time"));
		JSONObject json=new JSONObject();
		if (time.equals(time2)) {     // 如果时间戳通过
			Customer cu=(Customer)session.getAttribute("customer");
			int r=new CustomerTypeDao().update(name, value, id, cu.getId());
			if(r==1){
				if("is_chat".equals(name)){
					SessionCounter.getCustomerById(Long.valueOf(id)).getCustomerType().setIs_chat(Integer.valueOf(value));
					json.accumulate("result", "0");
				}
			}else{
				json.accumulate("result", "1");
			}
		}else{
			json.accumulate("result", "99");
		}
		
		try {
			PrintWriter out = response.getWriter();
			//out.print(request.getParameter("callback")+"("+json.toString()+")");
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
