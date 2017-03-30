package com.xidu.dto;

import javax.websocket.Session;

import com.xidu.entity.Customer;

public class SocketSessionDto {

	private Session session;
	private Customer customer;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public SocketSessionDto(Session session, Customer customer) {
		super();
		this.session = session;
		this.customer = customer;
	}

	
	
}
