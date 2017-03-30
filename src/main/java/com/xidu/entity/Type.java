package com.xidu.entity;

import java.util.Date;

public class Type {

	private Long id;
	private int deleteFlag;
	private Long createBy;
	private Long lastUpdateBy;
	private Date crerateDate;
	private Date lastUpdateDate;
	private int KONGZHITAI;
	private int CAIJINGRILI;
	private int ZAIXIANDAYI;
	private int HANDANTIXING;
	private int SHICHANGPINGLUN;
	private int JIAOYITISHI;
	private int TOUPIAO;
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
	public int getKONGZHITAI() {
		return KONGZHITAI;
	}
	public void setKONGZHITAI(int kONGZHITAI) {
		KONGZHITAI = kONGZHITAI;
	}
	public int getCAIJINGRILI() {
		return CAIJINGRILI;
	}
	public void setCAIJINGRILI(int cAIJINGRILI) {
		CAIJINGRILI = cAIJINGRILI;
	}
	public int getZAIXIANDAYI() {
		return ZAIXIANDAYI;
	}
	public void setZAIXIANDAYI(int zAIXIANDAYI) {
		ZAIXIANDAYI = zAIXIANDAYI;
	}
	public int getHANDANTIXING() {
		return HANDANTIXING;
	}
	public void setHANDANTIXING(int hANDANTIXING) {
		HANDANTIXING = hANDANTIXING;
	}
	public int getSHICHANGPINGLUN() {
		return SHICHANGPINGLUN;
	}
	public void setSHICHANGPINGLUN(int sHICHANGPINGLUN) {
		SHICHANGPINGLUN = sHICHANGPINGLUN;
	}
	public int getJIAOYITISHI() {
		return JIAOYITISHI;
	}
	public void setJIAOYITISHI(int jIAOYITISHI) {
		JIAOYITISHI = jIAOYITISHI;
	}
	public int getTOUPIAO() {
		return TOUPIAO;
	}
	public void setTOUPIAO(int tOUPIAO) {
		TOUPIAO = tOUPIAO;
	}
	
}
