package com.xidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.xidu.entity.BillCount;
import com.xidu.entity.Chats;
import com.xidu.util.DBConnection;
import com.xidu.util.MyBatisSqlUtil;

public class ChatsDao {

	public Chats selectChatsById(Long id){
		DBConnection dbcon = null;
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select * from xd_chats where id ='" + id+"'";
		Chats c=new Chats();
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Long cid = rs.getLong("id");
				String content = rs.getString("content");
				String userName = rs.getString("userName");
				Long userId = rs.getLong("userId");
				String date = rs.getString("date");
				String toUser = rs.getString("toUser");
				int valid = rs.getInt("valid");
				int isRobot = rs.getInt("isRobot");
				String roomNo = rs.getString("roomNo");
				String validUser = rs.getString("validUser");
				String faceImg = rs.getString("faceImg");
				int type = rs.getInt("type");
				int DELETE_FLAG = rs.getInt("DELETE_FLAG");
				c=new Chats(cid, content, userName, userId, date, toUser, valid, isRobot, roomNo, validUser, faceImg, type, DELETE_FLAG);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}

		return c;
	}
		
	//分页查询(start 开始数   num 要查询的条数)
	public List<Chats> selectChatsList(Long start,Long num,Long rnum){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "select * from xd_chats where valid = 1 and roomNo=CONCAT('',?,'') order by date desc limit ?,? ";
		List<Chats> c=new ArrayList<Chats>();
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setLong(1, rnum);
			stm.setLong(2, start);
			stm.setLong(3, num);
			rs = stm.executeQuery();
			while (rs.next()) {
				Long cid = rs.getLong("id");
				String content = rs.getString("content");
				String userName = rs.getString("userName");
				Long userId = rs.getLong("userId");
				String date = rs.getString("date");
				String toUser = rs.getString("toUser");
				int valid = rs.getInt("valid");
				int isRobot = rs.getInt("isRobot");
				String roomNo = rs.getString("roomNo");
				String validUser = rs.getString("validUser");
				String faceImg = rs.getString("faceImg");
				int type = rs.getInt("type");
				int DELETE_FLAG = rs.getInt("DELETE_FLAG");
				Chats chats=new Chats(cid, content, userName, userId, date, toUser, valid, isRobot, roomNo, validUser, faceImg, type, DELETE_FLAG);
				c.add(chats);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}

		return c;
	}
	
	//查询总聊天数
	public Long selectChatsNum(){
		DBConnection dbcon = null;
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select count(*) cnum from xd_chats where valid = 1";
		Long cid=0L;
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				cid = rs.getLong("cnum");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}

		return cid;
	}
	
	//添加
	public int addChats(Chats chats){
		int result=0;
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "insert into xd_chats(content,userName,userId,date,toUser,valid,isRobot,roomNo,validUser,faceImg,type) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, chats.getContent());
			stm.setString(2, chats.getUserName());
			stm.setString(3, chats.getUserId().toString());
			stm.setString(4, chats.getDate());
			stm.setString(5, chats.getToUser());
			stm.setString(6, chats.getValid().toString());
			stm.setString(7, chats.getIsRobot().toString());
			stm.setString(8, chats.getRoomNo());
			stm.setString(9, chats.getValidUser());
			stm.setString(10, chats.getFaceImg());
			stm.setString(11, chats.getType().toString());
			int i = stm.executeUpdate();
			ResultSet keys = stm.getGeneratedKeys(); // equivalent to "SELECT last_insert_id();"
	        if (keys.next()) {
	        	result=Integer.valueOf(keys.getLong(1)+"");
	        }
	        return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			dbcon.closeAll(con, stm, rs);
		}
	}
	
	//根据id修改审核状态和审核人
	public void editChats(Chats chats){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "update xd_chats set valid = ?,validUser = ? where id = ?";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, chats.getValid());
			stm.setString(2, chats.getValidUser());
			stm.setString(3, chats.getId().toString());
			int i = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbcon.closeAll(con, stm, rs);
		}
	}
}
