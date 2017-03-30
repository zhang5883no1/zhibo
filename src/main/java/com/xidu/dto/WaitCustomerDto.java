package com.xidu.dto;

import java.util.Date;

import com.xidu.entity.Customer;

public class WaitCustomerDto {

	private long times;
	private Customer customer;
	public long getTimes() {
		return times;
	}
	public void setTimes() {
		this.times = new Date().getTime();
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public WaitCustomerDto(Customer customer) {
		super();
		this.times=new Date().getTime();
		this.customer = customer;
	}
	public WaitCustomerDto() {
		super();
	}
	
}
