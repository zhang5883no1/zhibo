package com.xidu.entity;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

public class Robot implements Serializable{

	  private Long id;      
	  private Integer DELETE_FLAG;   //逻辑删除  当做删除操作时，并不是真的删除，而是将改字段   0：没删   1：已删                                
	  private Long CREATE_BY;                                    
	  private String CREATE_DATE;                                  
	  private Long LAST_UPDATE_BY;                                
	  private String LAST_UPDATE_DATE;    
	  private String name;      // 昵称      
	  private String userId;    // 对应用户
	  private String faceImg;   // 头像   
	  private String level;     // 等级  
	  
	  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param dELETE_FLAG
	 * @param cREATE_BY
	 * @param cREATE_DATE
	 * @param lAST_UPDATE_BY
	 * @param lAST_UPDATE_DATE
	 * @param name
	 * @param userId
	 * @param faceImg
	 * @param level
	 */
	public Robot(Long id, int dELETE_FLAG, Long cREATE_BY,
			String cREATE_DATE, Long lAST_UPDATE_BY, String lAST_UPDATE_DATE,
			String name, String userId, String faceImg, String level) {
		super();
		this.id = id;
		this.DELETE_FLAG = dELETE_FLAG;
		this.CREATE_BY = cREATE_BY;
		this.CREATE_DATE = cREATE_DATE;
		this.LAST_UPDATE_BY = lAST_UPDATE_BY;
		this.LAST_UPDATE_DATE = lAST_UPDATE_DATE;
		this.name = name;
		this.userId = userId;
		this.faceImg = faceImg;
		this.level = level;
	}
	public Robot() {
		super();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFaceImg() {
		return faceImg;
	}
	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	
	
	
}
