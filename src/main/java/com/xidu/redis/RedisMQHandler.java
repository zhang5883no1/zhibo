package com.xidu.redis;  

import java.util.Map;

import org.json.JSONObject;

import redis.clients.jedis.JedisPubSub;
  
public class RedisMQHandler extends JedisPubSub{
	MainRedisUtil pushManager = null;  
	  
    public RedisMQHandler(MainRedisUtil pushManager) {  
        super();  
        this.pushManager = pushManager;  
    }  
    
    @Override  
    // 接收到消息后进行分发执行  
    public void onMessage(String channel, String message) {  
        JSONObject jsonObj = new JSONObject(message);  
        System.out.println(channel+","+message);  
        if ("push".equals(channel)) {  
            Map<String,Object> map=jsonObj.toMap();  
            System.out.println("接收到一条推流消息，准备推流："+map);  
//          String appName=pushManager.push(map);  
            //推流完成后还需要发布一个成功消息到返回队列  
              
        } else if ("close".equals(channel)) {  
            String appName=jsonObj.getString("appName");  
            System.out.println("接收到一条关闭消息，准备关闭应用："+appName);  
//          pushManager.closePush(appName);  
        }  
        System.out.println("");
    }  
}
