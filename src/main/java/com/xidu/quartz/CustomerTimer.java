package com.xidu.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.xidu.constant.RedisConstant;
import com.xidu.entity.Customer;
import com.xidu.redis.RedisUtil;
import com.xidu.util.SerializeUtil;

public class CustomerTimer {

	private static final Long timeOut=2*60*1000L;
	private static CustomerTimer instance = null;
	private static HashMap<Long, Long> timeMap = null;

	private CustomerTimer() {

	}

	public static CustomerTimer getInstance() {
		if (instance == null) {
			instance = new CustomerTimer();
		}
		if (timeMap == null) {
			timeMap = new HashMap<Long, Long>();
		}
		return instance;
	}
	
	
	public static void clearTimeOut(){
		LinkedList<Long> keyslist=new LinkedList<Long>();
		System.out.println("in clear");
		Long currentTime=new Date().getTime();
		Iterator<Long> keys=timeMap.keySet().iterator();
		while(keys.hasNext()){
			Long key=keys.next();
			Long time=timeMap.get(key);
			System.out.println("iterator id : "+key);
			System.out.println("c : "+currentTime+", t : "+time);
			if(currentTime-time>timeOut){
				System.out.println("in clear redis");
//				timeMap.remove(key);
				keyslist.add(key);
				List<byte[]> culist=RedisUtil.getAllList(RedisConstant.customer().getBytes());
				for(byte[] bs:culist){
					Customer cu=(Customer)SerializeUtil.unserialize(bs);
					if(cu.getId().equals(key)){
						System.out.println("redis clear success");
						RedisUtil.removeValueFromList(RedisConstant.customer().getBytes(), bs);
					}
				}
			}
		}
		
		for(Long l:keyslist){
			timeMap.remove(l);
		}
	}
	
	public static void addTime(Long id){
		Long currentTime=new Date().getTime();
		System.out.println(id+ " add time : "+currentTime);
		timeMap.put(id, currentTime);
	}
	
}
