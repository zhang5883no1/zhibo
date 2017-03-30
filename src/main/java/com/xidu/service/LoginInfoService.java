package com.xidu.service;

import com.xidu.dto.LoginInfo;
import com.xidu.entity.Customer;
import com.xidu.init.SessionCounter;
import com.xidu.init.WebSocketPool;
import com.xidu.quartz.CustomerTimer;

public class LoginInfoService {

	
	public static void AddLogInfo(Customer cu,String type){
		if(cu.getId()==0L){
			return;
		}else{
//			LoginInfo info=new LoginInfo();
//			info.setId(cu.getId());
//			info.setLevel(cu.getLevel().toString());
//			info.setLogin_Flag(type);
//			info.setNickName(cu.getNickName());
			
			SessionCounter.AddLoginInfoToSession(cu);
			CustomerTimer.getInstance().addTime(cu.getId());
		}
	}
}
