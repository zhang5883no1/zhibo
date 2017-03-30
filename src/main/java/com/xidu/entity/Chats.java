package com.xidu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天类
 * @author Administrator
 *
 */
public class Chats extends BaseEntity implements Serializable{

	  private Long id;                       
	  private String content;     // 内容                                
	  private String userName;    // 发言人昵称                 
	  private Long userId;        // 发言人id                       
	  private String date;          // 发言时间                           
	  private String toUser;      // 接收方名称               
	  private Integer valid;      // 是否审核 0没审核 1 已审核  DEFAULT 0          
	  private Integer isRobot;    // 发言是否是机器人0不是 1是   DEFAULT 0       
	  private String roomNo;      // 房间号                   
	  private String validUser;   // 审核人                
	  private String faceImg;                              
	  private Integer type;      // 发言类型 0群聊，1私聊，2置顶，3滚动  DEFAULT 0
	  private int DELETE_FLAG;
	  private String ip;    // ip
	  public int getDELETE_FLAG() {
		return DELETE_FLAG;
	}

	public void setDELETE_FLAG(int dELETE_FLAG) {
		DELETE_FLAG = dELETE_FLAG;
	}

	public Chats(){
		  this.setValid(0);
		  this.setIsRobot(0);
		  this.setType(0);
	  }
	  
	public Chats(Long id, String content, String userName, Long userId,
			String date, String toUser, Integer valid, Integer isRobot,
			String roomNo, String validUser, String faceImg, Integer type,
			int dELETE_FLAG) {
		super();
		this.id = id;
		this.content = content;
		this.userName = userName;
		this.userId = userId;
		this.date = date;
		this.toUser = toUser;
		this.valid = valid;
		this.isRobot = isRobot;
		this.roomNo = roomNo;
		this.validUser = validUser;
		this.faceImg = faceImg;
		this.type = type;
		DELETE_FLAG = dELETE_FLAG;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Integer getIsRobot() {
		return isRobot;
	}
	public void setIsRobot(Integer isRobot) {
		this.isRobot = isRobot;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getValidUser() {
		return validUser;
	}
	public void setValidUser(String validUser) {
		this.validUser = validUser;
	}
	public String getFaceImg() {
		return faceImg;
	}
	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
	
}
