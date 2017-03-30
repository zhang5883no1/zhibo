package com.xidu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.xidu.constant.Constant;
import com.xidu.dao.CustomerDao;
import com.xidu.entity.Customer;
import com.xidu.service.LoginInfoService;
import com.xidu.util.IPUtil;


/**
 * 登录
 * @author Administrator
 *
 */
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String time1=session.getAttribute("time").toString();
		String time2=request.getParameter("time");
		String roomNo = request.getParameter("roomNo");
		  if(time1.equals(time2)){
			  Long time = System.currentTimeMillis();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  Customer customer = new Customer();
			  customer.setDELETE_FLAG(0);
			  customer.setCREATE_BY(0L);
			  customer.setCREATE_DATE(sdf.format(new Date()));
			  customer.setLAST_UPDATE_BY(0L);
			  customer.setLAST_UPDATE_DATE(sdf.format(new Date()));
			  customer.setUserName("游客_yk"+String.valueOf(time).substring(8,13));
			  customer.setNickName("游客_yk"+String.valueOf(time).substring(8,13));
			  customer.setPwd("123456");
			  customer.setIp(new IPUtil().getRemortIP(request));
			  customer.setRoomNo(roomNo);
			  customer.setUserId(0L);  //游客的所属业务员初始化为0
			  
			  CustomerDao cd = new CustomerDao();
			  cd.addCustomer(customer);       //新增用户
			  Customer c = cd.selectCustomerByUserNamePwd(customer);
			  LoginInfoService.AddLogInfo(c, Constant.LOGIN_FLAG);
			try {
				PrintWriter out = response.getWriter();
				out.print(c.toJson());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			String userName = URLDecoder.decode(request.getParameter("userName"),"UTF-8");
			String pwd = request.getParameter("pwd");
			String time = request.getParameter("time");
			String roomNo = request.getParameter("roomNo");
			HttpSession session = request.getSession();
			String time2 = String.valueOf(session.getAttribute("time"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(time.equals(time2)){        //如果时间戳通过
				Customer c = new Customer();
				c.setUserName(userName);
				c.setPwd(pwd);
				c.setRoomNo(roomNo);
				
				CustomerDao cd = new CustomerDao();
				if(userName.indexOf("游客") != -1){
					response.getWriter().print(0);
				}else{
					Customer customer = cd.selectCustomerByUserNamePwd(c);
					if(customer != null){         //如果登录成功，则返回1，登录失败则返回0
						
						customer.setLastLoginTime(sdf.format(new Date()));    //记录用户最后登录时间
						cd.updateCustomerLastLoginTime(customer);   //修改数据库用户的最后登录时间
						
						//如果是业务员登录(业务员等级为：90<level<99),则查询出该业务员下的所有客户
						if(customer.getLevel() > 90 && customer.getLevel() < 99){
							ArrayList<Customer> customerList = cd.selectCustomerBy(customer);
							customer.setCustomerList(customerList);
						}
						
						session.setAttribute("customer",customer);
						LoginInfoService.AddLogInfo(customer, Constant.LOGIN_FLAG);
						JSONObject json = JSONObject.fromObject(customer);
						response.getWriter().print(json);
					}else{
					    response.getWriter().print(0);
					}
				}
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
