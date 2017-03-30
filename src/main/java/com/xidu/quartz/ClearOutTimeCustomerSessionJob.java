package com.xidu.quartz;

import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.xidu.constant.Constant;
import com.xidu.init.SessionCounter;

public class ClearOutTimeCustomerSessionJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LinkedList<HttpSession> sessionMap = SessionCounter.getSessionMap();
		long nowTime = new Date().getTime();
		if (sessionMap != null && sessionMap.size() > 0) {
			for (HttpSession session : sessionMap) {
				try {
					String times = session.getAttribute(Constant.LAST_LIVE_TIME)
							.toString();
					if (null != times && !"".equals(times)) {
						long r = nowTime - Long.valueOf(times);
						if (r > 2 * 60 * 1000L) {
							SessionCounter.reduce(session);
						}
					}
				} catch (Exception e) {
					session.setAttribute(Constant.LAST_LIVE_TIME, new Date().getTime());
					continue;
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(Long.valueOf(""));
	}
}
