package com.xidu.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OnlineQA extends BaseEntity{
	private Long id;
	private int deleteFlag;
	private Long createBy;
	private Long lastUpdateBy;
	private Date crerateDate;
	private Date lastUpdateDate;
	private Long qi;
	private Long ai;
	private String qt;
	private String at;
	private String qn;
	private String an;
	private int status;
	public String getDilogMessage(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dilog="<div class='content-detail'><div class='diloginfo'>" ;
		dilog+="<span class='hd'>在线答疑：</span> "+this.qn+"于"+sf.format(this.crerateDate)+"提出问题："+this.qt+"&nbsp;&nbsp;<span class='ts'></span></div>";
		dilog+="<div class='dilogconsole'><a href='#' onclick='showZXDY("+this.id+")'>查看问题详情</a></div></div>";
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
	public Long getQi() {
		return qi;
	}
	public void setQi(Long qi) {
		this.qi = qi;
	}
	public Long getAi() {
		return ai;
	}
	public void setAi(Long ai) {
		this.ai = ai;
	}
	public String getQt() {
		return qt;
	}
	public void setQt(String qt) {
		this.qt = qt;
	}
	public String getAt() {
		return at;
	}
	public void setAt(String at) {
		this.at = at;
	}
	public String getQn() {
		return qn;
	}
	public void setQn(String qn) {
		this.qn = qn;
	}
	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
