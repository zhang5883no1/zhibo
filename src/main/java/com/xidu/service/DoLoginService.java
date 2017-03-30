package com.xidu.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;

import com.xidu.constant.Constant;
import com.xidu.dao.CustomerDao;
import com.xidu.entity.Customer;
import com.xidu.entity.CustomerType;
import com.xidu.util.IPUtil;
import com.xidu.util.WeiXinUtil;

public class DoLoginService {
	
	private CustomerDao customerDao = new CustomerDao();

	//页面初始化登录
	public Customer doLogin(String[] param){
		  Customer customerResult = null; 
		  String code = param[0];
		  if(code==null||"".equals(code)){
			  return customerResult;
		  }
		  customerResult=WeiXinUtil.getInfo(code);
		  if(customerResult!=null){
			  customerResult=validWeixinUser(customerResult);
			  LoginInfoService.AddLogInfo(customerResult, Constant.LOGIN_FLAG);
		  }
		   
		  return customerResult;
	}
	
	
	private Customer validWeixinUser(Customer customerResult) {
		// TODO Auto-generated method stub  
		int result=customerDao.selectCustomerByUserName(customerResult);
		//已存在
		if(result==1){
			customerDao.updateCustomerLastLoginTime(customerResult);
		//首次登录
		}else{
			customerDao.addCustomer(customerResult);
		}
		customerResult=customerDao.selectCustomerByUserNamePwd(customerResult);
		return customerResult;
	}


	//游客注册
	public Customer visitorRegiest(Long time,String roomNo,String ip){
		  Long currentTime = System.currentTimeMillis();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Customer customer4 = new Customer();
		  customer4.setDELETE_FLAG(0);
		  customer4.setCREATE_BY(0L);
		  customer4.setCREATE_DATE(sdf.format(new Date()));
		  customer4.setLAST_UPDATE_BY(0L);
		  customer4.setLAST_UPDATE_DATE(sdf.format(new Date()));
		  customer4.setUserName("yk_"+String.valueOf(time).substring(5,10));
		  customer4.setNickName("yk_"+String.valueOf(time).substring(5,10));
		  customer4.setPwd("123456");
		  customer4.setIp(ip);
		  customer4.setRoomNo(roomNo);
		  customer4.setUserId(0L);
		  customer4.setId(0L);

		  CustomerType customerType = new CustomerType();
		  customerType.setId(0L);
		  customerType.setDELETE_FLAG(0);
		  customerType.setCREATE_BY(0L);
		  customerType.setCREATE_DATE(sdf.format(new Date()));
		  customerType.setLAST_UPDATE_BY(0L);
		  customerType.setLAST_UPDATE_DATE(sdf.format(new Date()));  
		  customerType.setCustomer_id(0L);
		  customerType.setChat_time(10L);
		  customerType.setVideo_time(120L);   
		  customerType.setUsed_video_time(0L);
		       
		  customer4.setCustomerType(customerType);
		  //Integer resultAdd = customerDao.addCustomer(customer4);       //新增用户
		  //Object[] result = {customer4,resultAdd};
		  return customer4;
	}
}
