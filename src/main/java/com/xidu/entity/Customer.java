package com.xidu.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 用户类
 * @author Administrator
 *
 */
public class Customer extends BaseEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;                                          
	private Integer DELETE_FLAG;   //逻辑删除  当做删除操作时，并不是真的删除，而是将改字段   0：没删   1：已删                                
	private Long CREATE_BY;                                    
	private String CREATE_DATE;                                  
	private Long LAST_UPDATE_BY;                                
	private String LAST_UPDATE_DATE;                              
	private String userName;  //'用户名'                      
	private String nickName;  //'昵称'                        
	private String pwd;   //'密码'                            
	private String mobile; //'手机'                           
	private String qq;                                        
	private String ip;                                        
	private String lastLoginTime; //'最后登陆时间'              
	private Long userId;  //'所属业务员'                      
	private String faceImg;    //'头像'                       
	private Long level;   //'等级'  default:0                 
	private String status;   //'normal/block'  default:normal 
	private String mail;    //'邮件地址'                      
	private String realName;   //'真名'                       
	private String referer;  //'入口'                         
	private String linksource;   //'来源'                     
	private String roomNo;  //'可登陆房间号'  default:1     
	private List<Customer> customerList = new ArrayList<Customer>();    //存放业务员名下的客户
	
    private CustomerType customerType;
    
    private List<Robot> robotList = new ArrayList<Robot>();
    
    public List<Robot> getRobotList() {
		return robotList;
	}


	public void setRobotList(List<Robot> robotList) {
		this.robotList = robotList;
	}


	public Customer(){
    	this.setLevel(0L);
    	this.setStatus("normal");
    }
	  
	  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getDELETE_FLAG() {
		return DELETE_FLAG;
	}


	public void setDELETE_FLAG(Integer dELETE_FLAG) {
		DELETE_FLAG = dELETE_FLAG;
	}


	public Long getCREATE_BY() {
		return CREATE_BY;
	}


	public void setCREATE_BY(Long cREATE_BY) {
		CREATE_BY = cREATE_BY;
	}


	public Long getLAST_UPDATE_BY() {
		return LAST_UPDATE_BY;
	}


	public void setLAST_UPDATE_BY(Long lAST_UPDATE_BY) {
		LAST_UPDATE_BY = lAST_UPDATE_BY;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFaceImg() {
		return faceImg;
	}
	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getLinksource() {
		return linksource;
	}
	public void setLinksource(String linksource) {
		this.linksource = linksource;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}


	public String getCREATE_DATE() {
		return CREATE_DATE;
	}


	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}


	public String getLAST_UPDATE_DATE() {
		return LAST_UPDATE_DATE;
	}


	public void setLAST_UPDATE_DATE(String lAST_UPDATE_DATE) {
		LAST_UPDATE_DATE = lAST_UPDATE_DATE;
	}


	public String getLastLoginTime() {
		return lastLoginTime;
	}


	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


	public List<Customer> getCustomerList() {
		return customerList;
	}


	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public JSONObject toInfoJSONWithLevel(Long level){
		JSONObject json=new JSONObject();
		if(level>80){
			json.accumulate("id", this.id);
			json.accumulate("uid", this.userId);
		}else{
			json.accumulate("id", this.nickName);
			json.accumulate("uid", 0);
		}
		json.accumulate("nickName", this.nickName);
		json.accumulate("login_Flag", "9");
		json.accumulate("level", this.level);
		json.accumulate("roomNo", this.roomNo);
		return json;
	}
	
	public Customer(String nickname,String headimgurl, String uopenid){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setUserName(nickname);
		this.setNickName(nickname);
		this.setLevel(1L);
		this.setFaceImg(headimgurl);
		this.setRoomNo("1");
		this.setCREATE_BY(1L);
		this.setCREATE_DATE(sf.format(new Date()));
		this.setDELETE_FLAG(0);
		this.setLAST_UPDATE_BY(1L);
		this.setLAST_UPDATE_DATE(sf.format(new Date()));
		this.setIp("");
		this.setLastLoginTime(sf.format(new Date()));
		this.setLinksource("");
		this.setMail("");
		this.setMobile("");
		this.setPwd("");
		this.setQq(uopenid);
		this.setRealName("");
		this.setReferer("");
		this.setStatus("normal");
		this.setUserId(0L);
	}
	

}
