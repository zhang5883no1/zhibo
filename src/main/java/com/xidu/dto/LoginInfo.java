package com.xidu.dto;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class LoginInfo implements Serializable{

	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLogin_Flag() {
		return login_Flag;
	}
	public void setLogin_Flag(String login_Flag) {
		this.login_Flag = login_Flag;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	private String nickName;
	private String login_Flag;
	private String level;
	
	public JSONObject toJSONWithLevel(Long level){
		JSONObject json=new JSONObject();
		if(level>80){
			json.accumulate("id", this.id);
		}else{
			json.accumulate("id", this.nickName);
		}
		json.accumulate("nickName", this.nickName);
		json.accumulate("login_Flag", this.login_Flag);
		json.accumulate("level", this.level);
		return json;
	}
	
}
