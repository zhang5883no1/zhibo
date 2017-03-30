package com.xidu.entity;

public class Info {
	
	private Long id;
	private String content;
	private Integer type;    //类型   1：滚动   2：浮动  3：置顶  4：聊天下方
	private String createDate;
	private String roomNo;
	public Info() {
		super();
	}
	public Info(Integer type, String roomNo) {
		super();
		this.type = type;
		this.roomNo = roomNo;
	}
	public Info(Long id, String content, int type, String createDate,
			String roomNo) {
		super();
		this.id = id;
		this.content = content;
		this.type = type;
		this.createDate = createDate;
		this.roomNo = roomNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	

}
