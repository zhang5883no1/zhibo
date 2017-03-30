package com.xidu.entity;

public class User {
	private Long id;
	private String name;
	private String nickName;
	private String pwd;
	private String mobile;
	private String source;//来源
	private String date;
	private String linkSource;//连接地址
	
	private String sex;
	private String qq;
	private String mail;
	private String head;//头像
	private Long groupId;//分组id
	
	private Long salesId;//所属业务员
	
	
	public User (){
		super();
	}
	
	public User(String name, String nickName, String pwd, String mobile,
			String source, String date, String linkSource) {
		super();
		this.name = name;
		this.nickName = nickName;
		this.pwd = pwd;
		this.mobile = mobile;
		this.source = source;
		this.date = date;
		this.linkSource = linkSource;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLinkSource() {
		return linkSource;
	}
	public void setLinkSource(String linkSource) {
		this.linkSource = linkSource;
	}
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	
}
