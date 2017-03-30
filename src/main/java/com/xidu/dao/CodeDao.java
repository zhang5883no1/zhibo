package com.xidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.xidu.entity.Chats;
import com.xidu.entity.Code;
import com.xidu.util.DBConnection;
import com.xidu.util.MyBatisSqlUtil;

public class CodeDao {
	
	//查询
	public Code selectCodeBy(String mob){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "select * from xd_code where mobile = ?";
		Code c = null;
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, mob);
			rs = stm.executeQuery();
			while (rs.next()) {
				Long cid = rs.getLong("id");
				String mobile = rs.getString("mobile");
				String code = rs.getString("code");
				String date = rs.getString("date");
				String rurl = rs.getString("rurl");
				String lurl = rs.getString("lurl");
				int sumIndex = rs.getInt("sumIndex");
				c=new Code(cid, mobile, code, date, rurl, lurl, sumIndex);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}
		return c;
	}
	
	//新增
	public void addCode(Code code){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "insert into xd_code(mobile ,code,date,rurl,lurl,sumIndex) values(?,?,?,?,?,?)";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, code.getMobile());
			stm.setString(2, code.getCode());
			stm.setString(3, code.getDate());
			stm.setString(4, code.getRurl());
			stm.setString(5, code.getLurl());
			stm.setInt(6,code.getSumIndex());
			int i=stm.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}
	}
	
	//修改
	public void editCode(Code code){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "update xd_code set code = ? ,date = ? ,rurl = ? ,lurl = ? ,sumIndex = ?  where mobile = ?";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			stm = con.prepareStatement(sql);
			stm.setString(1, code.getCode());
			stm.setString(2, code.getDate());
			stm.setString(3, code.getRurl());
			stm.setString(4, code.getLurl());
			stm.setInt(5, code.getSumIndex());
			stm.setString(6, code.getMobile());
			int i=stm.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.closeAll(con, stm, rs);
		}
	}

}
