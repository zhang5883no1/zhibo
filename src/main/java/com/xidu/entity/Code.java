package com.xidu.entity;

/**
 * 手机验证码
 * @author Administrator
 *
 */
public class Code {

	private Long id;
	private String mobile;
	private String code;
	private String date;
	private String rurl;
	private String lurl;
	private Integer sumIndex;
	
	public Code(Long id, String mobile, String code, String date, String rurl,
			String lurl, int sumIndex) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.code = code;
		this.date = date;
		this.rurl = rurl;
		this.lurl = lurl;
		this.sumIndex = sumIndex;
	}
	public Code() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRurl() {
		return rurl;
	}
	public void setRurl(String rurl) {
		this.rurl = rurl;
	}
	public String getLurl() {
		return lurl;
	}
	public void setLurl(String lurl) {
		this.lurl = lurl;
	}
	public Integer getSumIndex() {
		return sumIndex;
	}
	public void setSumIndex(Integer sumIndex) {
		this.sumIndex = sumIndex;
	}
	
	
	
}
