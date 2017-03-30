package com.xidu.redis;

import org.json.JSONObject;

public class MainTest {
	public static void main(String[] args) throws InterruptedException {
		MainRedisUtil pushManager = new MainRedisUtil();
		Thread t1 = new Thread(new Tasker(new RedisMQHandler(pushManager),
				"push"));
		Thread t2 = new Thread(new Tasker(new RedisMQHandler(pushManager),
				"close"));
		t1.start();
		t2.start();

		LivePushEntity livePushInfo = new LivePushEntity();
		livePushInfo.setAppName("test1");
		JSONObject json = new JSONObject(livePushInfo);
		
		pushManager.publish("push", json.toString());
		pushManager.publish("close", json.toString());
		Thread.sleep(2000);
		pushManager.publish("push", json.toString());
		pushManager.publish("close", json.toString());
		Thread.sleep(2000);
		pushManager.publish("push", json.toString());
		pushManager.publish("close", json.toString());

	}
}
