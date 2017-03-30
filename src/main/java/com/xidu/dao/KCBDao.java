package com.xidu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.xidu.entity.KCB;
import com.xidu.util.DBConnection;

public class KCBDao {
	
	public KCB queryByRoom(Long id){
		DBConnection dbcon = null;
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select * from xd_kcb where roomId = "+id;
		KCB c=new KCB();
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				 int cid=rs.getInt("id");
				 String time1_time = rs.getString("time1_time");
				 String time1_lesson_mon = rs.getString("time1_lesson_mon");
				 String time1_teacher_mon = rs.getString("time1_teacher_mon");
				 String time1_lesson_tue = rs.getString("time1_lesson_tue");
				 String time1_teacher_tue = rs.getString("time1_teacher_tue");
				 String time1_lesson_wed = rs.getString("time1_lesson_wed");
				 String time1_teacher_wed = rs.getString("time1_teacher_wed");
				 String time1_lesson_thu = rs.getString("time1_lesson_thu");
				 String time1_teacher_thu = rs.getString("time1_teacher_thu");
				 String time1_lesson_fri = rs.getString("time1_lesson_fri");
				 String time1_teacher_fri = rs.getString("time1_teacher_fri");
				 String time2_time = rs.getString("time2_time");
				 String time2_lesson_mon = rs.getString("time2_lesson_mon");
				 String time2_teacher_mon = rs.getString("time2_teacher_mon");
				 String time2_lesson_tue = rs.getString("time2_lesson_tue");
				 String time2_teacher_tue = rs.getString("time2_teacher_tue");
				 String time2_lesson_wed = rs.getString("time2_lesson_wed");
				 String time2_teacher_wed = rs.getString("time2_teacher_wed");
				 String time2_lesson_thu = rs.getString("time2_lesson_thu");
				 String time2_teacher_thu = rs.getString("time2_teacher_thu");
				 String time2_lesson_fri = rs.getString("time2_lesson_fri");
				 String time2_teacher_fri = rs.getString("time2_teacher_fri");
				 String time3_time = rs.getString("time3_time");
				 String time3_lesson_mon = rs.getString("time3_lesson_mon");
				 String time3_teacher_mon = rs.getString("time3_teacher_mon");
				 String time3_lesson_tue = rs.getString("time3_lesson_tue");
				 String time3_teacher_tue = rs.getString("time3_teacher_tue");
				 String time3_lesson_wed = rs.getString("time3_lesson_wed");
				 String time3_teacher_wed = rs.getString("time3_teacher_wed");
				 String time3_lesson_thu = rs.getString("time3_lesson_thu");
				 String time3_teacher_thu = rs.getString("time3_teacher_thu");
				 String time3_lesson_fri = rs.getString("time3_lesson_fri");
				 String time3_teacher_fri = rs.getString("time3_teacher_fri");
				 String time4_time = rs.getString("time4_time");
				 String time4_lesson_mon = rs.getString("time4_lesson_mon");
				 String time4_teacher_mon = rs.getString("time4_teacher_mon");
				 String time4_lesson_tue = rs.getString("time4_lesson_tue");
				 String time4_teacher_tue = rs.getString("time4_teacher_tue");
				 String time4_lesson_wed = rs.getString("time4_lesson_wed");
				 String time4_teacher_wed = rs.getString("time4_teacher_wed");
				 String time4_lesson_thu = rs.getString("time4_lesson_thu");
				 String time4_teacher_thu = rs.getString("time4_teacher_thu");
				 String time4_lesson_fri = rs.getString("time4_lesson_fri");
				 String time4_teacher_fri = rs.getString("time4_teacher_fri");
				 String time5_time = rs.getString("time5_time");
				 String time5_lesson_mon = rs.getString("time5_lesson_mon");
				 String time5_teacher_mon = rs.getString("time5_teacher_mon");
				 String time5_lesson_tue = rs.getString("time5_lesson_tue");
				 String time5_teacher_tue = rs.getString("time5_teacher_tue");
				 String time5_lesson_wed = rs.getString("time5_lesson_wed");
				 String time5_teacher_wed = rs.getString("time5_teacher_wed");
				 String time5_lesson_thu = rs.getString("time5_lesson_thu");
				 String time5_teacher_thu = rs.getString("time5_teacher_thu");
				 String time5_lesson_fri = rs.getString("time5_lesson_fri");
				 String time5_teacher_fri = rs.getString("time5_teacher_fri");
				 String time6_time = rs.getString("time6_time");
				 String time6_lesson_mon = rs.getString("time6_lesson_mon");
				 String time6_teacher_mon = rs.getString("time6_teacher_mon");
				 String time6_lesson_tue = rs.getString("time6_lesson_tue");
				 String time6_teacher_tue = rs.getString("time6_teacher_tue");
				 String time6_lesson_wed = rs.getString("time6_lesson_wed");
				 String time6_teacher_wed = rs.getString("time6_teacher_wed");
				 String time6_lesson_thu = rs.getString("time6_lesson_thu");
				 String time6_teacher_thu = rs.getString("time6_teacher_thu");
				 String time6_lesson_fri = rs.getString("time6_lesson_fri");
				 String time6_teacher_fri = rs.getString("time6_teacher_fri");
				 
				 String time7_time = rs.getString("time7_time");
				 String time7_lesson_mon = rs.getString("time7_lesson_mon");
				 String time7_teacher_mon = rs.getString("time7_teacher_mon");
				 String time7_lesson_tue = rs.getString("time7_lesson_tue");
				 String time7_teacher_tue = rs.getString("time7_teacher_tue");
				 String time7_lesson_wed = rs.getString("time7_lesson_wed");
				 String time7_teacher_wed = rs.getString("time7_teacher_wed");
				 String time7_lesson_thu = rs.getString("time7_lesson_thu");
				 String time7_teacher_thu = rs.getString("time7_teacher_thu");
				 String time7_lesson_fri = rs.getString("time7_lesson_fri");
				 String time7_teacher_fri = rs.getString("time7_teacher_fri");
				 
				 String time8_time = rs.getString("time8_time");
				 String time8_lesson_mon = rs.getString("time8_lesson_mon");
				 String time8_teacher_mon = rs.getString("time8_teacher_mon");
				 String time8_lesson_tue = rs.getString("time8_lesson_tue");
				 String time8_teacher_tue = rs.getString("time8_teacher_tue");
				 String time8_lesson_wed = rs.getString("time8_lesson_wed");
				 String time8_teacher_wed = rs.getString("time8_teacher_wed");
				 String time8_lesson_thu = rs.getString("time8_lesson_thu");
				 String time8_teacher_thu = rs.getString("time8_teacher_thu");
				 String time8_lesson_fri = rs.getString("time8_lesson_fri");
				 String time8_teacher_fri = rs.getString("time8_teacher_fri");
				 String roomId = rs.getString("roomId");
				 c=new KCB(cid, time1_time, time1_lesson_mon, time1_teacher_mon, time1_lesson_tue, time1_teacher_tue, time1_lesson_wed, time1_teacher_wed, time1_lesson_thu, time1_teacher_thu, time1_lesson_fri, time1_teacher_fri, time2_time, time2_lesson_mon, time2_teacher_mon, time2_lesson_tue, time2_teacher_tue, time2_lesson_wed, time2_teacher_wed, time2_lesson_thu, time2_teacher_thu, time2_lesson_fri, time2_teacher_fri, time3_time, time3_lesson_mon, time3_teacher_mon, time3_lesson_tue, time3_teacher_tue, time3_lesson_wed, time3_teacher_wed, time3_lesson_thu, time3_teacher_thu, time3_lesson_fri, time3_teacher_fri, time4_time, time4_lesson_mon, time4_teacher_mon, time4_lesson_tue, time4_teacher_tue, time4_lesson_wed, time4_teacher_wed, time4_lesson_thu, time4_teacher_thu, time4_lesson_fri, time4_teacher_fri, time5_time, time5_lesson_mon, time5_teacher_mon, time5_lesson_tue, time5_teacher_tue, time5_lesson_wed, time5_teacher_wed, time5_lesson_thu, time5_teacher_thu, time5_lesson_fri, time5_teacher_fri, time6_time, time6_lesson_mon, time6_teacher_mon, time6_lesson_tue, time6_teacher_tue, time6_lesson_wed, time6_teacher_wed, time6_lesson_thu, time6_teacher_thu, time6_lesson_fri, time6_teacher_fri, time7_time, time7_lesson_mon, time7_teacher_mon, time7_lesson_tue, time7_teacher_tue, time7_lesson_wed, time7_teacher_wed, time7_lesson_thu, time7_teacher_thu, time7_lesson_fri, time7_teacher_fri, time8_time, time8_lesson_mon, time8_teacher_mon, time8_lesson_tue, time8_teacher_tue, time8_lesson_wed, time8_teacher_wed, time8_lesson_thu, time8_teacher_thu, time8_lesson_fri, time8_teacher_fri, roomId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}
		return c;
	}

}
