package com.xidu.init;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.xidu.constant.Constant;
import com.xidu.constant.RedisConstant;
import com.xidu.dto.LoginInfo;
import com.xidu.entity.Chats;
import com.xidu.entity.Customer;
import com.xidu.quartz.CustomerTimer;
import com.xidu.redis.RedisUtil;
import com.xidu.util.SerializeUtil;

public class SessionCounter implements Serializable{

	private static LinkedList<HttpSession> sessionMap=new LinkedList<HttpSession>();
    
      
    public static void add(HttpSession s)  
    {   
    	sessionMap.add(s);
    }  
      
    public static void reduce(HttpSession s)  
    {   
    	sessionMap.remove(s); 
    	/*try {
    		LoginInfoService.AddLogInfo((Customer)s.getAttribute(Constant.SESSION_CUSTOMER), Constant.LOGOUT_FLAG);
		} catch (Exception e) {
			System.out.println("session is already delete");
		}finally{
			sessionMap.remove(s); 
		}*/
    	Customer cu=(Customer)s.getAttribute(Constant.SESSION_CUSTOMER);
    	List<byte[]> slist=RedisUtil.getAllList(RedisConstant.customer().getBytes());
    	if(slist!=null&&slist.size()>0){
    		for(byte[] b:slist){
    			Customer cc=(Customer)SerializeUtil.unserialize(b);
    			try {
    				if(cu.equals(cc.getId())){
    					RedisUtil.removeValueFromList(RedisConstant.customer().getBytes(), SerializeUtil.serialize(cc));
    				}
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
    		}
    	}
    }   
    
    public static int getOnline(){
    	return sessionMap.size();
    }

    
    public static LinkedList<HttpSession> getSessionMap() {
		return sessionMap;
	}

	public static List<Customer> getCustomerByUserId(Long id){
    	List<Customer> list=new LinkedList<Customer>();
    	if(sessionMap.size()>0){
	    	for(int i=0;i<sessionMap.size();i++){
	    		try {
	    			if(id.equals(((Customer)sessionMap.get(i).getAttribute("customer")).getUserId())){
	    				list.add((Customer)sessionMap.get(i).getAttribute("customer"));
	    			}
				} catch (Exception e) {
					// TODO: handle exception
				}
	    	}
    	}
    	return list;
    }
	
	public static Customer getCustomerById(Long id){
//		if(sessionMap.size()>0){
//	    	for(int i=0;i<sessionMap.size();i++){
//	    		try {
//	    			if(id.equals(((Customer)sessionMap.get(i).getAttribute("customer")).getId())){
//	    				return (Customer)sessionMap.get(i).getAttribute("customer");
//	    			}
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//	    	}
//		}
		List<byte[]> slist=RedisUtil.getAllList(RedisConstant.customer().getBytes());
    	if(slist!=null&&slist.size()>0){
    		for(byte[] s:slist){
    			Customer cu=(Customer)SerializeUtil.unserialize(s);
    			if(id.equals(cu.getId())){
    				CustomerTimer.getInstance().addTime(cu.getId());
    				return cu;
    			}
    		}
    	}
    	return null;
	}
    
    
    public static void sendToUser(Chats chats){
    	LinkedList<Chats> list=new LinkedList<Chats>();
    	for(int i=0;i<sessionMap.size();i++){
    		try {
    			if(chats.getToUser().equals(((Customer)sessionMap.get(i).getAttribute("customer")).getId())){
    				LinkedList<Chats> clist=(LinkedList<Chats>)sessionMap.get(i).getAttribute(Constant.SESSION_SIMPLE_CHATS);
    				if(clist!=null&&clist.size()>0){
    					list=clist;
    				}
    				list.add(chats);
    				sessionMap.get(i).setAttribute(Constant.SESSION_SIMPLE_CHATS,list);
    			}
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
    }
    
    public static void AddLoginInfoToSession(Customer info){
    	List<byte[]> slist=RedisUtil.getAllList(RedisConstant.customer().getBytes());
    	if(slist!=null&&slist.size()>0){
    		for(byte[] s:slist){
    			Customer cu=(Customer)SerializeUtil.unserialize(s);
    			if(info.getId().equals(cu.getId())){
    				RedisUtil.removeValueFromList(RedisConstant.customer().getBytes(), SerializeUtil.serialize(cu));
    			}
    		}
    	}
    	RedisUtil.putList(RedisConstant.customer().getBytes(), SerializeUtil.serialize(info));
    }
    
    
    public static List<Customer> getAllLiveCustomer(){
    	List<Customer> list=new LinkedList<Customer>();
//    	if(sessionMap.size()>0){
//	    	for(int i=0;i<sessionMap.size();i++){
//	    		try {
//	    			if(((Customer)sessionMap.get(i).getAttribute("customer")).getLevel()<10){
//	    				list.add((Customer)sessionMap.get(i).getAttribute("customer"));
//	    			}
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//	    	}
//    	}
    	List<byte[]> slist=RedisUtil.getAllList(RedisConstant.customer().getBytes());
    	if(slist!=null&&slist.size()>0){
    		for(byte[] s:slist){
    			Customer cu=(Customer)SerializeUtil.unserialize(s);
    			list.add(cu);		
    		}
    	}
    	return list;
    }

	public static void updateInfoById(Long id, String value) {
		// TODO Auto-generated method stub  
		List<byte[]> slist=RedisUtil.getAllList(RedisConstant.customer().getBytes());
    	if(slist!=null&&slist.size()>0){
    		for(byte[] s:slist){
    			Customer cu=(Customer)SerializeUtil.unserialize(s);
    			if(id.equals(cu.getId())){
    				RedisUtil.removeValueFromList(RedisConstant.customer().getBytes(), SerializeUtil.serialize(cu));
    				cu.setStatus(value);
    				RedisUtil.putList(RedisConstant.customer().getBytes(), SerializeUtil.serialize(cu));
    			}
    		}
    	}
	}
}
