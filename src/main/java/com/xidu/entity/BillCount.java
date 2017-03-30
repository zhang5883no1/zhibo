package com.xidu.entity;

public class BillCount extends BaseEntity{
	
	private long billId;
	private long userId;
	public long getBillId() {
		return billId;
	}
	public void setBillId(long billId) {
		this.billId = billId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
 
}
