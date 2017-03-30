package com.xidu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class KCB extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String time1_time;
	private String time1_lesson_mon;
	private String time1_teacher_mon;
	private String time1_lesson_tue;
	private String time1_teacher_tue;
	private String time1_lesson_wed;
	private String time1_teacher_wed;
	private String time1_lesson_thu;
	private String time1_teacher_thu;
	private String time1_lesson_fri;
	private String time1_teacher_fri;
	private String time2_time;
	private String time2_lesson_mon;
	private String time2_teacher_mon;
	private String time2_lesson_tue;
	private String time2_teacher_tue;
	private String time2_lesson_wed;
	private String time2_teacher_wed;
	private String time2_lesson_thu;
	private String time2_teacher_thu;
	private String time2_lesson_fri;
	private String time2_teacher_fri;
	private String time3_time;
	private String time3_lesson_mon;
	private String time3_teacher_mon;
	private String time3_lesson_tue;
	private String time3_teacher_tue;
	private String time3_lesson_wed;
	private String time3_teacher_wed;
	private String time3_lesson_thu;
	private String time3_teacher_thu;
	private String time3_lesson_fri;
	private String time3_teacher_fri;
	private String time4_time;
	private String time4_lesson_mon;
	private String time4_teacher_mon;
	private String time4_lesson_tue;
	private String time4_teacher_tue;
	private String time4_lesson_wed;
	private String time4_teacher_wed;
	private String time4_lesson_thu;
	private String time4_teacher_thu;
	private String time4_lesson_fri;
	private String time4_teacher_fri;
	private String time5_time;
	private String time5_lesson_mon;
	private String time5_teacher_mon;
	private String time5_lesson_tue;
	private String time5_teacher_tue;
	private String time5_lesson_wed;
	private String time5_teacher_wed;
	private String time5_lesson_thu;
	private String time5_teacher_thu;
	private String time5_lesson_fri;
	private String time5_teacher_fri;
	private String time6_time;
	private String time6_lesson_mon;
	private String time6_teacher_mon;
	private String time6_lesson_tue;
	private String time6_teacher_tue;
	private String time6_lesson_wed;
	private String time6_teacher_wed;
	private String time6_lesson_thu;
	private String time6_teacher_thu;
	private String time6_lesson_fri;
	private String time6_teacher_fri;
	
	private String time7_time;
	private String time7_lesson_mon;
	private String time7_teacher_mon;
	private String time7_lesson_tue;
	private String time7_teacher_tue;
	private String time7_lesson_wed;
	private String time7_teacher_wed;
	private String time7_lesson_thu;
	private String time7_teacher_thu;
	private String time7_lesson_fri;
	private String time7_teacher_fri;
	
	private String time8_time;
	private String time8_lesson_mon;
	private String time8_teacher_mon;
	private String time8_lesson_tue;
	private String time8_teacher_tue;
	private String time8_lesson_wed;
	private String time8_teacher_wed;
	private String time8_lesson_thu;
	private String time8_teacher_thu;
	private String time8_lesson_fri;
	private String time8_teacher_fri;
	private String roomId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime1_time() {
		return time1_time;
	}
	public KCB() {
		super();
	}
	public String getTime8_time() {
		return time8_time;
	}
	public void setTime8_time(String time8_time) {
		this.time8_time = time8_time;
	}
	public String getTime8_lesson_mon() {
		return time8_lesson_mon;
	}
	public void setTime8_lesson_mon(String time8_lesson_mon) {
		this.time8_lesson_mon = time8_lesson_mon;
	}
	public String getTime8_teacher_mon() {
		return time8_teacher_mon;
	}
	public void setTime8_teacher_mon(String time8_teacher_mon) {
		this.time8_teacher_mon = time8_teacher_mon;
	}
	public String getTime8_lesson_tue() {
		return time8_lesson_tue;
	}
	public void setTime8_lesson_tue(String time8_lesson_tue) {
		this.time8_lesson_tue = time8_lesson_tue;
	}
	public String getTime8_teacher_tue() {
		return time8_teacher_tue;
	}
	public void setTime8_teacher_tue(String time8_teacher_tue) {
		this.time8_teacher_tue = time8_teacher_tue;
	}
	public String getTime8_lesson_wed() {
		return time8_lesson_wed;
	}
	public void setTime8_lesson_wed(String time8_lesson_wed) {
		this.time8_lesson_wed = time8_lesson_wed;
	}
	public String getTime8_teacher_wed() {
		return time8_teacher_wed;
	}
	public void setTime8_teacher_wed(String time8_teacher_wed) {
		this.time8_teacher_wed = time8_teacher_wed;
	}
	public String getTime8_lesson_thu() {
		return time8_lesson_thu;
	}
	public void setTime8_lesson_thu(String time8_lesson_thu) {
		this.time8_lesson_thu = time8_lesson_thu;
	}
	public String getTime8_teacher_thu() {
		return time8_teacher_thu;
	}
	public void setTime8_teacher_thu(String time8_teacher_thu) {
		this.time8_teacher_thu = time8_teacher_thu;
	}
	public String getTime8_lesson_fri() {
		return time8_lesson_fri;
	}
	public void setTime8_lesson_fri(String time8_lesson_fri) {
		this.time8_lesson_fri = time8_lesson_fri;
	}
	public String getTime8_teacher_fri() {
		return time8_teacher_fri;
	}
	public void setTime8_teacher_fri(String time8_teacher_fri) {
		this.time8_teacher_fri = time8_teacher_fri;
	}
	public String getTime7_time() {
		return time7_time;
	}
	public void setTime7_time(String time7_time) {
		this.time7_time = time7_time;
	}
	public String getTime7_lesson_mon() {
		return time7_lesson_mon;
	}
	public void setTime7_lesson_mon(String time7_lesson_mon) {
		this.time7_lesson_mon = time7_lesson_mon;
	}
	public String getTime7_teacher_mon() {
		return time7_teacher_mon;
	}
	public void setTime7_teacher_mon(String time7_teacher_mon) {
		this.time7_teacher_mon = time7_teacher_mon;
	}
	public String getTime7_lesson_tue() {
		return time7_lesson_tue;
	}
	public void setTime7_lesson_tue(String time7_lesson_tue) {
		this.time7_lesson_tue = time7_lesson_tue;
	}
	public String getTime7_teacher_tue() {
		return time7_teacher_tue;
	}
	public void setTime7_teacher_tue(String time7_teacher_tue) {
		this.time7_teacher_tue = time7_teacher_tue;
	}
	public String getTime7_lesson_wed() {
		return time7_lesson_wed;
	}
	public void setTime7_lesson_wed(String time7_lesson_wed) {
		this.time7_lesson_wed = time7_lesson_wed;
	}
	public String getTime7_teacher_wed() {
		return time7_teacher_wed;
	}
	public void setTime7_teacher_wed(String time7_teacher_wed) {
		this.time7_teacher_wed = time7_teacher_wed;
	}
	public String getTime7_lesson_thu() {
		return time7_lesson_thu;
	}
	public void setTime7_lesson_thu(String time7_lesson_thu) {
		this.time7_lesson_thu = time7_lesson_thu;
	}
	public String getTime7_teacher_thu() {
		return time7_teacher_thu;
	}
	public void setTime7_teacher_thu(String time7_teacher_thu) {
		this.time7_teacher_thu = time7_teacher_thu;
	}
	public String getTime7_lesson_fri() {
		return time7_lesson_fri;
	}
	public void setTime7_lesson_fri(String time7_lesson_fri) {
		this.time7_lesson_fri = time7_lesson_fri;
	}
	public String getTime7_teacher_fri() {
		return time7_teacher_fri;
	}
	public void setTime7_teacher_fri(String time7_teacher_fri) {
		this.time7_teacher_fri = time7_teacher_fri;
	}
	public void setTime1_time(String time1_time) {
		this.time1_time = time1_time;
	}
	public String getTime1_lesson_mon() {
		return time1_lesson_mon;
	}
	public void setTime1_lesson_mon(String time1_lesson_mon) {
		this.time1_lesson_mon = time1_lesson_mon;
	}
	public String getTime1_teacher_mon() {
		return time1_teacher_mon;
	}
	public void setTime1_teacher_mon(String time1_teacher_mon) {
		this.time1_teacher_mon = time1_teacher_mon;
	}
	public String getTime1_lesson_tue() {
		return time1_lesson_tue;
	}
	public void setTime1_lesson_tue(String time1_lesson_tue) {
		this.time1_lesson_tue = time1_lesson_tue;
	}
	public String getTime1_teacher_tue() {
		return time1_teacher_tue;
	}
	public void setTime1_teacher_tue(String time1_teacher_tue) {
		this.time1_teacher_tue = time1_teacher_tue;
	}
	public String getTime1_lesson_wed() {
		return time1_lesson_wed;
	}
	public void setTime1_lesson_wed(String time1_lesson_wed) {
		this.time1_lesson_wed = time1_lesson_wed;
	}
	public String getTime1_teacher_wed() {
		return time1_teacher_wed;
	}
	public void setTime1_teacher_wed(String time1_teacher_wed) {
		this.time1_teacher_wed = time1_teacher_wed;
	}
	public String getTime1_lesson_thu() {
		return time1_lesson_thu;
	}
	public void setTime1_lesson_thu(String time1_lesson_thu) {
		this.time1_lesson_thu = time1_lesson_thu;
	}
	public String getTime1_teacher_thu() {
		return time1_teacher_thu;
	}
	public void setTime1_teacher_thu(String time1_teacher_thu) {
		this.time1_teacher_thu = time1_teacher_thu;
	}
	public String getTime1_lesson_fri() {
		return time1_lesson_fri;
	}
	public void setTime1_lesson_fri(String time1_lesson_fri) {
		this.time1_lesson_fri = time1_lesson_fri;
	}
	public String getTime1_teacher_fri() {
		return time1_teacher_fri;
	}
	public void setTime1_teacher_fri(String time1_teacher_fri) {
		this.time1_teacher_fri = time1_teacher_fri;
	}
	public KCB(int id, String time1_time, String time1_lesson_mon,
			String time1_teacher_mon, String time1_lesson_tue,
			String time1_teacher_tue, String time1_lesson_wed,
			String time1_teacher_wed, String time1_lesson_thu,
			String time1_teacher_thu, String time1_lesson_fri,
			String time1_teacher_fri, String time2_time,
			String time2_lesson_mon, String time2_teacher_mon,
			String time2_lesson_tue, String time2_teacher_tue,
			String time2_lesson_wed, String time2_teacher_wed,
			String time2_lesson_thu, String time2_teacher_thu,
			String time2_lesson_fri, String time2_teacher_fri,
			String time3_time, String time3_lesson_mon,
			String time3_teacher_mon, String time3_lesson_tue,
			String time3_teacher_tue, String time3_lesson_wed,
			String time3_teacher_wed, String time3_lesson_thu,
			String time3_teacher_thu, String time3_lesson_fri,
			String time3_teacher_fri, String time4_time,
			String time4_lesson_mon, String time4_teacher_mon,
			String time4_lesson_tue, String time4_teacher_tue,
			String time4_lesson_wed, String time4_teacher_wed,
			String time4_lesson_thu, String time4_teacher_thu,
			String time4_lesson_fri, String time4_teacher_fri,
			String time5_time, String time5_lesson_mon,
			String time5_teacher_mon, String time5_lesson_tue,
			String time5_teacher_tue, String time5_lesson_wed,
			String time5_teacher_wed, String time5_lesson_thu,
			String time5_teacher_thu, String time5_lesson_fri,
			String time5_teacher_fri, String time6_time,
			String time6_lesson_mon, String time6_teacher_mon,
			String time6_lesson_tue, String time6_teacher_tue,
			String time6_lesson_wed, String time6_teacher_wed,
			String time6_lesson_thu, String time6_teacher_thu,
			String time6_lesson_fri, String time6_teacher_fri,
			String time7_time, String time7_lesson_mon,
			String time7_teacher_mon, String time7_lesson_tue,
			String time7_teacher_tue, String time7_lesson_wed,
			String time7_teacher_wed, String time7_lesson_thu,
			String time7_teacher_thu, String time7_lesson_fri,
			String time7_teacher_fri, String time8_time,
			String time8_lesson_mon, String time8_teacher_mon,
			String time8_lesson_tue, String time8_teacher_tue,
			String time8_lesson_wed, String time8_teacher_wed,
			String time8_lesson_thu, String time8_teacher_thu,
			String time8_lesson_fri, String time8_teacher_fri, String roomId) {
		super();
		this.id = id;
		this.time1_time = time1_time;
		this.time1_lesson_mon = time1_lesson_mon;
		this.time1_teacher_mon = time1_teacher_mon;
		this.time1_lesson_tue = time1_lesson_tue;
		this.time1_teacher_tue = time1_teacher_tue;
		this.time1_lesson_wed = time1_lesson_wed;
		this.time1_teacher_wed = time1_teacher_wed;
		this.time1_lesson_thu = time1_lesson_thu;
		this.time1_teacher_thu = time1_teacher_thu;
		this.time1_lesson_fri = time1_lesson_fri;
		this.time1_teacher_fri = time1_teacher_fri;
		this.time2_time = time2_time;
		this.time2_lesson_mon = time2_lesson_mon;
		this.time2_teacher_mon = time2_teacher_mon;
		this.time2_lesson_tue = time2_lesson_tue;
		this.time2_teacher_tue = time2_teacher_tue;
		this.time2_lesson_wed = time2_lesson_wed;
		this.time2_teacher_wed = time2_teacher_wed;
		this.time2_lesson_thu = time2_lesson_thu;
		this.time2_teacher_thu = time2_teacher_thu;
		this.time2_lesson_fri = time2_lesson_fri;
		this.time2_teacher_fri = time2_teacher_fri;
		this.time3_time = time3_time;
		this.time3_lesson_mon = time3_lesson_mon;
		this.time3_teacher_mon = time3_teacher_mon;
		this.time3_lesson_tue = time3_lesson_tue;
		this.time3_teacher_tue = time3_teacher_tue;
		this.time3_lesson_wed = time3_lesson_wed;
		this.time3_teacher_wed = time3_teacher_wed;
		this.time3_lesson_thu = time3_lesson_thu;
		this.time3_teacher_thu = time3_teacher_thu;
		this.time3_lesson_fri = time3_lesson_fri;
		this.time3_teacher_fri = time3_teacher_fri;
		this.time4_time = time4_time;
		this.time4_lesson_mon = time4_lesson_mon;
		this.time4_teacher_mon = time4_teacher_mon;
		this.time4_lesson_tue = time4_lesson_tue;
		this.time4_teacher_tue = time4_teacher_tue;
		this.time4_lesson_wed = time4_lesson_wed;
		this.time4_teacher_wed = time4_teacher_wed;
		this.time4_lesson_thu = time4_lesson_thu;
		this.time4_teacher_thu = time4_teacher_thu;
		this.time4_lesson_fri = time4_lesson_fri;
		this.time4_teacher_fri = time4_teacher_fri;
		this.time5_time = time5_time;
		this.time5_lesson_mon = time5_lesson_mon;
		this.time5_teacher_mon = time5_teacher_mon;
		this.time5_lesson_tue = time5_lesson_tue;
		this.time5_teacher_tue = time5_teacher_tue;
		this.time5_lesson_wed = time5_lesson_wed;
		this.time5_teacher_wed = time5_teacher_wed;
		this.time5_lesson_thu = time5_lesson_thu;
		this.time5_teacher_thu = time5_teacher_thu;
		this.time5_lesson_fri = time5_lesson_fri;
		this.time5_teacher_fri = time5_teacher_fri;
		this.time6_time = time6_time;
		this.time6_lesson_mon = time6_lesson_mon;
		this.time6_teacher_mon = time6_teacher_mon;
		this.time6_lesson_tue = time6_lesson_tue;
		this.time6_teacher_tue = time6_teacher_tue;
		this.time6_lesson_wed = time6_lesson_wed;
		this.time6_teacher_wed = time6_teacher_wed;
		this.time6_lesson_thu = time6_lesson_thu;
		this.time6_teacher_thu = time6_teacher_thu;
		this.time6_lesson_fri = time6_lesson_fri;
		this.time6_teacher_fri = time6_teacher_fri;
		this.time7_time = time7_time;
		this.time7_lesson_mon = time7_lesson_mon;
		this.time7_teacher_mon = time7_teacher_mon;
		this.time7_lesson_tue = time7_lesson_tue;
		this.time7_teacher_tue = time7_teacher_tue;
		this.time7_lesson_wed = time7_lesson_wed;
		this.time7_teacher_wed = time7_teacher_wed;
		this.time7_lesson_thu = time7_lesson_thu;
		this.time7_teacher_thu = time7_teacher_thu;
		this.time7_lesson_fri = time7_lesson_fri;
		this.time7_teacher_fri = time7_teacher_fri;
		this.time8_time = time8_time;
		this.time8_lesson_mon = time8_lesson_mon;
		this.time8_teacher_mon = time8_teacher_mon;
		this.time8_lesson_tue = time8_lesson_tue;
		this.time8_teacher_tue = time8_teacher_tue;
		this.time8_lesson_wed = time8_lesson_wed;
		this.time8_teacher_wed = time8_teacher_wed;
		this.time8_lesson_thu = time8_lesson_thu;
		this.time8_teacher_thu = time8_teacher_thu;
		this.time8_lesson_fri = time8_lesson_fri;
		this.time8_teacher_fri = time8_teacher_fri;
		this.roomId = roomId;
	}
	public String getTime2_time() {
		return time2_time;
	}
	public void setTime2_time(String time2_time) {
		this.time2_time = time2_time;
	}
	public String getTime2_lesson_mon() {
		return time2_lesson_mon;
	}
	public void setTime2_lesson_mon(String time2_lesson_mon) {
		this.time2_lesson_mon = time2_lesson_mon;
	}
	public String getTime2_teacher_mon() {
		return time2_teacher_mon;
	}
	public void setTime2_teacher_mon(String time2_teacher_mon) {
		this.time2_teacher_mon = time2_teacher_mon;
	}
	public String getTime2_lesson_tue() {
		return time2_lesson_tue;
	}
	public void setTime2_lesson_tue(String time2_lesson_tue) {
		this.time2_lesson_tue = time2_lesson_tue;
	}
	public String getTime2_teacher_tue() {
		return time2_teacher_tue;
	}
	public void setTime2_teacher_tue(String time2_teacher_tue) {
		this.time2_teacher_tue = time2_teacher_tue;
	}
	public String getTime2_lesson_wed() {
		return time2_lesson_wed;
	}
	public void setTime2_lesson_wed(String time2_lesson_wed) {
		this.time2_lesson_wed = time2_lesson_wed;
	}
	public String getTime2_teacher_wed() {
		return time2_teacher_wed;
	}
	public void setTime2_teacher_wed(String time2_teacher_wed) {
		this.time2_teacher_wed = time2_teacher_wed;
	}
	public String getTime2_lesson_thu() {
		return time2_lesson_thu;
	}
	public void setTime2_lesson_thu(String time2_lesson_thu) {
		this.time2_lesson_thu = time2_lesson_thu;
	}
	public String getTime2_teacher_thu() {
		return time2_teacher_thu;
	}
	public void setTime2_teacher_thu(String time2_teacher_thu) {
		this.time2_teacher_thu = time2_teacher_thu;
	}
	public String getTime2_lesson_fri() {
		return time2_lesson_fri;
	}
	public void setTime2_lesson_fri(String time2_lesson_fri) {
		this.time2_lesson_fri = time2_lesson_fri;
	}
	public String getTime2_teacher_fri() {
		return time2_teacher_fri;
	}
	public void setTime2_teacher_fri(String time2_teacher_fri) {
		this.time2_teacher_fri = time2_teacher_fri;
	}
	public String getTime3_time() {
		return time3_time;
	}
	public void setTime3_time(String time3_time) {
		this.time3_time = time3_time;
	}
	public String getTime3_lesson_mon() {
		return time3_lesson_mon;
	}
	public void setTime3_lesson_mon(String time3_lesson_mon) {
		this.time3_lesson_mon = time3_lesson_mon;
	}
	public String getTime3_teacher_mon() {
		return time3_teacher_mon;
	}
	public void setTime3_teacher_mon(String time3_teacher_mon) {
		this.time3_teacher_mon = time3_teacher_mon;
	}
	public String getTime3_lesson_tue() {
		return time3_lesson_tue;
	}
	public void setTime3_lesson_tue(String time3_lesson_tue) {
		this.time3_lesson_tue = time3_lesson_tue;
	}
	public String getTime3_teacher_tue() {
		return time3_teacher_tue;
	}
	public void setTime3_teacher_tue(String time3_teacher_tue) {
		this.time3_teacher_tue = time3_teacher_tue;
	}
	public String getTime3_lesson_wed() {
		return time3_lesson_wed;
	}
	public void setTime3_lesson_wed(String time3_lesson_wed) {
		this.time3_lesson_wed = time3_lesson_wed;
	}
	public String getTime3_teacher_wed() {
		return time3_teacher_wed;
	}
	public void setTime3_teacher_wed(String time3_teacher_wed) {
		this.time3_teacher_wed = time3_teacher_wed;
	}
	public String getTime3_lesson_thu() {
		return time3_lesson_thu;
	}
	public void setTime3_lesson_thu(String time3_lesson_thu) {
		this.time3_lesson_thu = time3_lesson_thu;
	}
	public String getTime3_teacher_thu() {
		return time3_teacher_thu;
	}
	public void setTime3_teacher_thu(String time3_teacher_thu) {
		this.time3_teacher_thu = time3_teacher_thu;
	}
	public String getTime3_lesson_fri() {
		return time3_lesson_fri;
	}
	public void setTime3_lesson_fri(String time3_lesson_fri) {
		this.time3_lesson_fri = time3_lesson_fri;
	}
	public String getTime3_teacher_fri() {
		return time3_teacher_fri;
	}
	public void setTime3_teacher_fri(String time3_teacher_fri) {
		this.time3_teacher_fri = time3_teacher_fri;
	}
	public String getTime4_time() {
		return time4_time;
	}
	public void setTime4_time(String time4_time) {
		this.time4_time = time4_time;
	}
	public String getTime4_lesson_mon() {
		return time4_lesson_mon;
	}
	public void setTime4_lesson_mon(String time4_lesson_mon) {
		this.time4_lesson_mon = time4_lesson_mon;
	}
	public String getTime4_teacher_mon() {
		return time4_teacher_mon;
	}
	public void setTime4_teacher_mon(String time4_teacher_mon) {
		this.time4_teacher_mon = time4_teacher_mon;
	}
	public String getTime4_lesson_tue() {
		return time4_lesson_tue;
	}
	public void setTime4_lesson_tue(String time4_lesson_tue) {
		this.time4_lesson_tue = time4_lesson_tue;
	}
	public String getTime4_teacher_tue() {
		return time4_teacher_tue;
	}
	public void setTime4_teacher_tue(String time4_teacher_tue) {
		this.time4_teacher_tue = time4_teacher_tue;
	}
	public String getTime4_lesson_wed() {
		return time4_lesson_wed;
	}
	public void setTime4_lesson_wed(String time4_lesson_wed) {
		this.time4_lesson_wed = time4_lesson_wed;
	}
	public String getTime4_teacher_wed() {
		return time4_teacher_wed;
	}
	public void setTime4_teacher_wed(String time4_teacher_wed) {
		this.time4_teacher_wed = time4_teacher_wed;
	}
	public String getTime4_lesson_thu() {
		return time4_lesson_thu;
	}
	public void setTime4_lesson_thu(String time4_lesson_thu) {
		this.time4_lesson_thu = time4_lesson_thu;
	}
	public String getTime4_teacher_thu() {
		return time4_teacher_thu;
	}
	public void setTime4_teacher_thu(String time4_teacher_thu) {
		this.time4_teacher_thu = time4_teacher_thu;
	}
	public String getTime4_lesson_fri() {
		return time4_lesson_fri;
	}
	public void setTime4_lesson_fri(String time4_lesson_fri) {
		this.time4_lesson_fri = time4_lesson_fri;
	}
	public String getTime4_teacher_fri() {
		return time4_teacher_fri;
	}
	public void setTime4_teacher_fri(String time4_teacher_fri) {
		this.time4_teacher_fri = time4_teacher_fri;
	}
	public String getTime5_time() {
		return time5_time;
	}
	public void setTime5_time(String time5_time) {
		this.time5_time = time5_time;
	}
	public String getTime5_lesson_mon() {
		return time5_lesson_mon;
	}
	public void setTime5_lesson_mon(String time5_lesson_mon) {
		this.time5_lesson_mon = time5_lesson_mon;
	}
	public String getTime5_teacher_mon() {
		return time5_teacher_mon;
	}
	public void setTime5_teacher_mon(String time5_teacher_mon) {
		this.time5_teacher_mon = time5_teacher_mon;
	}
	public String getTime5_lesson_tue() {
		return time5_lesson_tue;
	}
	public void setTime5_lesson_tue(String time5_lesson_tue) {
		this.time5_lesson_tue = time5_lesson_tue;
	}
	public String getTime5_teacher_tue() {
		return time5_teacher_tue;
	}
	public void setTime5_teacher_tue(String time5_teacher_tue) {
		this.time5_teacher_tue = time5_teacher_tue;
	}
	public String getTime5_lesson_wed() {
		return time5_lesson_wed;
	}
	public void setTime5_lesson_wed(String time5_lesson_wed) {
		this.time5_lesson_wed = time5_lesson_wed;
	}
	public String getTime5_teacher_wed() {
		return time5_teacher_wed;
	}
	public void setTime5_teacher_wed(String time5_teacher_wed) {
		this.time5_teacher_wed = time5_teacher_wed;
	}
	public String getTime5_lesson_thu() {
		return time5_lesson_thu;
	}
	public void setTime5_lesson_thu(String time5_lesson_thu) {
		this.time5_lesson_thu = time5_lesson_thu;
	}
	public String getTime5_teacher_thu() {
		return time5_teacher_thu;
	}
	public void setTime5_teacher_thu(String time5_teacher_thu) {
		this.time5_teacher_thu = time5_teacher_thu;
	}
	public String getTime5_lesson_fri() {
		return time5_lesson_fri;
	}
	public void setTime5_lesson_fri(String time5_lesson_fri) {
		this.time5_lesson_fri = time5_lesson_fri;
	}
	public String getTime5_teacher_fri() {
		return time5_teacher_fri;
	}
	public void setTime5_teacher_fri(String time5_teacher_fri) {
		this.time5_teacher_fri = time5_teacher_fri;
	}
	public String getTime6_time() {
		return time6_time;
	}
	public void setTime6_time(String time6_time) {
		this.time6_time = time6_time;
	}
	public String getTime6_lesson_mon() {
		return time6_lesson_mon;
	}
	public void setTime6_lesson_mon(String time6_lesson_mon) {
		this.time6_lesson_mon = time6_lesson_mon;
	}
	public String getTime6_teacher_mon() {
		return time6_teacher_mon;
	}
	public void setTime6_teacher_mon(String time6_teacher_mon) {
		this.time6_teacher_mon = time6_teacher_mon;
	}
	public String getTime6_lesson_tue() {
		return time6_lesson_tue;
	}
	public void setTime6_lesson_tue(String time6_lesson_tue) {
		this.time6_lesson_tue = time6_lesson_tue;
	}
	public String getTime6_teacher_tue() {
		return time6_teacher_tue;
	}
	public void setTime6_teacher_tue(String time6_teacher_tue) {
		this.time6_teacher_tue = time6_teacher_tue;
	}
	public String getTime6_lesson_wed() {
		return time6_lesson_wed;
	}
	public void setTime6_lesson_wed(String time6_lesson_wed) {
		this.time6_lesson_wed = time6_lesson_wed;
	}
	public String getTime6_teacher_wed() {
		return time6_teacher_wed;
	}
	public void setTime6_teacher_wed(String time6_teacher_wed) {
		this.time6_teacher_wed = time6_teacher_wed;
	}
	public String getTime6_lesson_thu() {
		return time6_lesson_thu;
	}
	public void setTime6_lesson_thu(String time6_lesson_thu) {
		this.time6_lesson_thu = time6_lesson_thu;
	}
	public String getTime6_teacher_thu() {
		return time6_teacher_thu;
	}
	public void setTime6_teacher_thu(String time6_teacher_thu) {
		this.time6_teacher_thu = time6_teacher_thu;
	}
	public String getTime6_lesson_fri() {
		return time6_lesson_fri;
	}
	public void setTime6_lesson_fri(String time6_lesson_fri) {
		this.time6_lesson_fri = time6_lesson_fri;
	}
	public String getTime6_teacher_fri() {
		return time6_teacher_fri;
	}
	public void setTime6_teacher_fri(String time6_teacher_fri) {
		this.time6_teacher_fri = time6_teacher_fri;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}


}
