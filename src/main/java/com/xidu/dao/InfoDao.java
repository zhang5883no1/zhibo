package com.xidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.xidu.entity.Code;
import com.xidu.entity.Info;
import com.xidu.util.DBConnection;
import com.xidu.util.MyBatisSqlUtil;

public class InfoDao {
	
	//根据type查询
	public Info selectInfoByType(Info info){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM xd_info where type = ? and roomNo = ? ORDER BY id DESC LIMIT 0,1 ";
		Info c=new Info();
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setInt(1, info.getType());
			stm.setString(2, info.getRoomNo());
			rs = stm.executeQuery();
			while (rs.next()) {
				Long cid = rs.getLong("id");
				String content = rs.getString("content");
				int type = rs.getInt("type");
				String createDate = rs.getString("createDate");
				String roomNo = rs.getString("roomNo");
				c=new Info(cid, content, type, createDate, roomNo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}
		return c;
	}
	
	//添加
	public void addInfo(Info info){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = " insert into xd_info(content,type,createDate,roomNo) values(?,?,?,?)";
		Info c=new Info();
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, info.getContent());
			stm.setInt(2, info.getType());
			stm.setString(3, info.getCreateDate());
			stm.setString(4, info.getRoomNo());
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}
	}
	
}
