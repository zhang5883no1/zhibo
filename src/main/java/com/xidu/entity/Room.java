package com.xidu.entity;

public class Room {

	private Long id;
	private String roomCode;
	private String roomName;
	private String deleteFlag;
	private String create_date;
	private String create_by;
	private String last_update_date;
	private String last_update_by;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Room(Long id, String roomCode, String roomName, String deleteFlag,
			String create_date, String create_by, String last_update_date,
			String last_update_by) {
		super();
		this.id = id;
		this.roomCode = roomCode;
		this.roomName = roomName;
		this.deleteFlag = deleteFlag;
		this.create_date = create_date;
		this.create_by = create_by;
		this.last_update_date = last_update_date;
		this.last_update_by = last_update_by;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public Room() {
		super();
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getLast_update_by() {
		return last_update_by;
	}
	public void setLast_update_by(String last_update_by) {
		this.last_update_by = last_update_by;
	}
	
}
