package com.xidu.entity;

import net.sf.json.JSONObject;

public class UserType {

	private int id;
	private String name;
	private int onlineTime;
	private int video1;
	private int video2;
	private int teacherZan;
	private int yuce;
	private int hdjl;
	private int jydy;
	private int czjy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(int onlineTime) {
		this.onlineTime = onlineTime;
	}
	public int getVideo1() {
		return video1;
	}
	public void setVideo1(int video1) {
		this.video1 = video1;
	}
	public int getVideo2() {
		return video2;
	}
	public void setVideo2(int video2) {
		this.video2 = video2;
	}
	public int getTeacherZan() {
		return teacherZan;
	}
	public void setTeacherZan(int teacherZan) {
		this.teacherZan = teacherZan;
	}
	public int getYuce() {
		return yuce;
	}
	public void setYuce(int yuce) {
		this.yuce = yuce;
	}
	public int getHdjl() {
		return hdjl;
	}
	public void setHdjl(int hdjl) {
		this.hdjl = hdjl;
	}
	public int getJydy() {
		return jydy;
	}
	public void setJydy(int jydy) {
		this.jydy = jydy;
	}
	public int getCzjy() {
		return czjy;
	}
	public void setCzjy(int czjy) {
		this.czjy = czjy;
	}
	
	public JSONObject addJson(JSONObject json){
		json.accumulate("type_name", name);
		json.accumulate("type_onlineTime", onlineTime);
		json.accumulate("type_video1", video1);
		json.accumulate("type_video2", video2);
		json.accumulate("type_teacherZan", teacherZan);
		json.accumulate("type_yuce", yuce);
		json.accumulate("type_hdjl", hdjl);
		json.accumulate("type_jydy", jydy);
		json.accumulate("type_czjy", czjy);
		return json;
	}
	
}
