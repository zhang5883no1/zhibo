package com.xidu.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

public class Bill extends BaseEntity{

	private long id;
	private long create_by;
	private long last_update_by;
	private String create_date;
	private String last_update_date;
	private int delete_flag;
	
	private String openTime;
	private String type;//现价买入/到价买入/到价卖出/限价买入/限价卖出
	private int position;//仓位 %
	private String product;//商品
	private int openPrice;//开仓价
	private int stopLossPrice;//止损价
	private int stopGainPrice;//止盈价
	private String closingTime;//平仓时间
	private int closingPrice;//平仓价
	private int gainPercent;//获利点数
	private String detail;//备注
	private String teacherName;//分析师
	private int status;//
	
	private int count;//赞总数
	public String getDilogMessage(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dilog="<div class='content-detail'><div class='diloginfo'>" ;
		dilog+="<span class='hd'>喊单提醒：</span> 单号："+this.id+"，交易商品【西都石油】已于"+this.closingPrice+"价位平仓，盈利："+(this.closingPrice-this.openPrice)+"&nbsp;&nbsp;<span class='ts'>(注：如本单为止损/止盈成交，实际成交时间以当时的点位为准)</span></div>";
		dilog+="<div class='dilogconsole'><a href='#' onclick='showHDTS("+this.id+")'>查看喊单详情</a></div></div>";
		return dilog;
	}
	
	public String getaddDilogMessage(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dilog="<div class='content-detail'><div class='diloginfo'>" ;
		dilog+="<span class='hd'>喊单提醒：</span> 单号："+this.id+"，交易商品【西都石油】已于"+this.openPrice+"价位买入，止盈价："+this.stopGainPrice+"，止损价："+this.stopLossPrice+"&nbsp;&nbsp;<span class='ts'>(注：如本单为止损/止盈成交，实际成交时间以当时的点位为准)</span></div>";
		dilog+="<div class='dilogconsole'><a href='#' onclick='showHDTS("+this.id+")'>查看喊单详情</a></div></div>";
		return dilog;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreate_by() {
		return create_by;
	}

	public void setCreate_by(long create_by) {
		this.create_by = create_by;
	}

	public long getLast_update_by() {
		return last_update_by;
	}

	public void setLast_update_by(long last_update_by) {
		this.last_update_by = last_update_by;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(int openPrice) {
		this.openPrice = openPrice;
	}

	public int getStopLossPrice() {
		return stopLossPrice;
	}

	public void setStopLossPrice(int stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
	}

	public int getStopGainPrice() {
		return stopGainPrice;
	}

	public void setStopGainPrice(int stopGainPrice) {
		this.stopGainPrice = stopGainPrice;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public int getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(int closingPrice) {
		this.closingPrice = closingPrice;
	}

	public int getGainPercent() {
		return gainPercent;
	}

	public void setGainPercent(int gainPercent) {
		this.gainPercent = gainPercent;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
