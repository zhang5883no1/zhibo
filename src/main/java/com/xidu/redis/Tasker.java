package com.xidu.redis;  

import redis.clients.jedis.JedisPubSub;
  
public class Tasker implements Runnable {  
    private String[] channel = null;//监听的消息通道  
    private JedisPubSub jedisPubSub = null;//消息处理任务  
  
    public Tasker(JedisPubSub jedisPubSub, String ...channel) {  
        this.jedisPubSub = jedisPubSub;  
        this.channel = channel;  
    }  
  
    @Override  
    public void run() {  
        // 监听channel通道的消息  
        MainRedisUtil.subscribe(jedisPubSub, channel);  
    }  

}
