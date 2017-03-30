package com.xidu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户类型类
 * @author Administrator
 *
 */
public class CustomerType implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;                             
	private Integer DELETE_FLAG;   //逻辑删除  当做删除操作时，并不是真的删除，而是将改字段   0：没删   1：已删                                
	private Long CREATE_BY;                                    
	private String CREATE_DATE;                                  
	private Long LAST_UPDATE_BY;                                
	private String LAST_UPDATE_DATE;                              
	private Long customer_id;          // 对应用户id                 
	private Long chat_time;            // 聊天间隔单位秒               
	private Long video_time;           // 总视频时间 单位分钟         
	private Long used_video_time;      // 已用视频时间 单位分钟  
	private Integer is_chat;           // 是否禁言 0未禁言，1已禁言  DEFAULT：0          
	private Integer is_scrol;          // 是否滚动，0不可滚动，1可滚动         DEFAULT：0 
	private Integer is_top;            // 是否置顶 0不可，1可          DEFAULT：0           
	private Integer is_font;           // 是否字体 0 不可，1可         DEFAULT：0             
	private Integer is_valid;          // 是否审核 0 不可，1 可        DEFAULT：0     
	private Integer is_float;          // 是否浮动 0 不可，1 可        DEFAULT：0   
	 
	public CustomerType(){
		this.setIs_chat(0);
		this.setIs_scrol(0);
		this.setIs_top(0);
		this.setIs_font(0);
		this.setIs_valid(0);
		this.setIs_float(0);
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

	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public Long getChat_time() {
		return chat_time;
	}
	public void setChat_time(Long chat_time) {
		this.chat_time = chat_time;
	}
	public Long getVideo_time() {
		return video_time;
	}
	public void setVideo_time(Long video_time) {
		this.video_time = video_time;
	}
	public Long getUsed_video_time() {
		return used_video_time;
	}
	public void setUsed_video_time(Long used_video_time) {
		this.used_video_time = used_video_time;
	}
	public Integer getIs_chat() {
		return is_chat;
	}
	public void setIs_chat(Integer is_chat) {
		this.is_chat = is_chat;
	}
	public Integer getIs_scrol() {
		return is_scrol;
	}
	public void setIs_scrol(Integer is_scrol) {
		this.is_scrol = is_scrol;
	}
	public Integer getIs_top() {
		return is_top;
	}
	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}
	public Integer getIs_font() {
		return is_font;
	}
	public void setIs_font(Integer is_font) {
		this.is_font = is_font;
	}
	public Integer getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}

	public Integer getIs_float() {
		return is_float;
	}

	public void setIs_float(Integer is_float) {
		this.is_float = is_float;
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
