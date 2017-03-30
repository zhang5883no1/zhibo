package com.xidu.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xidu.dao.CustomerDao;
import com.xidu.entity.Chats;
import com.xidu.entity.Customer;
import com.xidu.init.GetChatsUtil;
import com.xidu.init.SessionCounter;
import com.xidu.redis.RedisUtil;
import com.xidu.service.ChatService;


/**
 * 登录
 * @author Administrator
 *
 */
public class ConsoleController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			String type=request.getParameter("action");
			if("getlive".equals(type)){
				out.print(request.getParameter("callback")+"("+getAllCustomer().toString()+")");
			}else if("getValid".equals(type)){
				out.print(request.getParameter("callback")+"("+getValid().toString()+")");
			}else if("doValid".equals(type)){
				out.print(request.getParameter("callback")+"("+doValid(request.getParameter("chatid"),request.getParameter("valid"),request.getParameter("userid")).toString()+")");
			}else if("changeInfo".equals(type)){
				out.print(request.getParameter("callback")+"("+changeInfo(request.getParameter("name"),request.getParameter("value"),request.getParameter("id")).toString()+")");
			}else if("getInfo".equals(type)){
				out.print(request.getParameter("callback")+"("+getInfo(request).toString()+")");
			}else if("validLockPwd".equals(type)){
				out.print(request.getParameter("callback")+"("+validLockPwd(request).toString()+")");
			}else if("setLock".equals(type)){
				out.print(request.getParameter("callback")+"("+setLock(request).toString()+")");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	private JSONArray getAllCustomer(){
		JSONArray array=new JSONArray();
		List<Customer> list=SessionCounter.getAllLiveCustomer();
		for(Customer cu:list){
			array.add(cu.toJson());
		}
		return array;
	}
	
	private JSONArray getValid(){
		JSONArray array=new JSONArray();
		GetChatsUtil getChatsUtil=new GetChatsUtil();
		List<Chats> validChatList=getChatsUtil.getValidChats("1", 1L);
		for(Chats c:validChatList){
			array.add(c.toJson());
		}
		return array;
	}
	
	private JSONObject doValid(String chatid,String valid,String userid){
		JSONObject json=new JSONObject();
		Chats chat=new ChatService().validChats(chatid, valid, "1" ,Long.valueOf(userid));
		if(chat!=null){
			json.accumulate("id", chat.getId());
			json.accumulate("f", "success");
		}else{
			json.accumulate("f", "falid");
		}
		return json;
	}
	
	private JSONObject changeInfo(String name,String value,String id){
		int r=new CustomerDao().updateCustomerStatus(id, value);
		JSONObject json=new JSONObject();
		if(r==1){
			if("is_chat".equals(name)){
//				SessionCounter.getCustomerById(Long.valueOf(id)).setStatus(value);
				SessionCounter.updateInfoById(Long.valueOf(id),value);
				json.accumulate("result", "0");
			}
		}else{
			json.accumulate("result", "1");
		}
		return json;
	}
	
	private JSONObject getInfo(HttpServletRequest request){
		Customer customer=(Customer)request.getSession().getAttribute("customer");
		customer= SessionCounter.getCustomerById(customer.getId());
		if(customer!=null){
			return customer.toJson();
		}else{
			return new JSONObject();
		}
	}
	
	private JSONObject validLockPwd(HttpServletRequest request) {
		// TODO Auto-generated method stub  
		JSONObject json=new JSONObject();
		String pwd=request.getParameter("pwd"); 
		String redisPwd=RedisUtil.getString("lockPwd");
		if(redisPwd.equals(pwd)){
			json.accumulate("key", 1);
		}else{
			json.accumulate("key", 2);
		}
		return json;
	}
	
	private JSONObject setLock(HttpServletRequest request) {
		// TODO Auto-generated method stub  
		JSONObject json=new JSONObject();
		String key=request.getParameter("key");
		String sval=request.getParameter("sval"); 
		try {
			RedisUtil.setString(key,sval);
			json.accumulate("key", 1);
		} catch (Exception e) {
			// TODO: handle exception
			json.accumulate("key", 2);
		}
		return json;
	}
	
}
