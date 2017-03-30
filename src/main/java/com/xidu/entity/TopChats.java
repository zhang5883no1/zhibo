package com.xidu.entity;

import java.util.Date;

import net.sf.json.JSONObject;

public class TopChats {
	private long id;
	private long create_by;
	private long lastupdate_by;
	private Date create_date;
	private Date lastupdate_date;
	private String content;
	
	public JSONObject toJson(){
		JSONObject json=new JSONObject();
		json.accumulate("content", this.content);
		return json;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCreate_by() {
		return create_by;
	}
	public void setCreate_by(long create_by) {
		this.create_by = create_by;
	}
	public long getLastupdate_by() {
		return lastupdate_by;
	}
	public void setLastupdate_by(long lastupdate_by) {
		this.lastupdate_by = lastupdate_by;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getLastupdate_date() {
		return lastupdate_date;
	}
	public void setLastupdate_date(Date lastupdate_date) {
		this.lastupdate_date = lastupdate_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
