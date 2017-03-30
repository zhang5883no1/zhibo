package com.xidu.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MarketComment extends BaseEntity{

	private Long id;
	private int deleteFlag;
	private Long createBy;
	private Long lastUpdateBy;
	private Date crerateDate;
	private Date lastUpdateDate;
	private String title;
	private String content;
	private int level;
	private String teacherName;
	
	
	public String getDilogMessage(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dilog="<div class='content-detail'><div class='diloginfo'>" ;
		dilog+="<span class='hd'>市场评论：</span> 讲师："+this.teacherName+"，于"+sf.format(this.crerateDate)+"发布："+this.level+"星级提示："+this.title+"&nbsp;&nbsp;<span class='ts'>(注：如本单为止损/止盈成交，实际成交时间以当时的点位为准)</span></div>";
		dilog+="<div class='dilogconsole'><a href='#' onclick='showSCPL("+this.id+")'>查看评论详情</a></div></div>";
		return dilog;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public Date getCrerateDate() {
		return crerateDate;
	}
	public void setCrerateDate(Date crerateDate) {
		this.crerateDate = crerateDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
}
