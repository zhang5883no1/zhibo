package com.xidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.xidu.entity.Chats;
import com.xidu.entity.Robot;
import com.xidu.entity.Room;
import com.xidu.util.DBConnection;
import com.xidu.util.MyBatisSqlUtil;

public class RoomDao {
	
	public List<Room> getallRoom(){
		DBConnection dbcon = null;
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select * from xd_room";
		List<Room> c=new ArrayList<Room>();
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Long cid = rs.getLong("ID");
				String DELETE_FLAG = rs.getString("DELETE_FLAG");
				String CREATE_BY = rs.getString("CREATE_BY");
				String CREATE_DATE = rs.getString("CREATE_DATE");
				String LAST_UPDATE_BY = rs.getString("LAST_UPDATE_BY");
				String LAST_UPDATE_DATE = rs.getString("LAST_UPDATE_DATE");
				String roomCode = rs.getString("roomCode");
				String roomName = rs.getString("roomName");
				Room r=new Room(cid, roomCode, roomName, DELETE_FLAG, CREATE_DATE, CREATE_BY, LAST_UPDATE_DATE, LAST_UPDATE_BY);
				c.add(r);
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
