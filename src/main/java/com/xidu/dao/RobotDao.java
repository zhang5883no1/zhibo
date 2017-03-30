package com.xidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.xidu.entity.Robot;
import com.xidu.util.DBConnection;

public class RobotDao {
	
	    //增加
		public void addRobot(Robot robot){
			DBConnection dbcon = null;
			Connection con = null;
			PreparedStatement stm = null;
			ResultSet rs = null;
			String sql = "insert into xd_robot(DELETE_FLAG,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,name,userId,faceImg,level) values(?,?,?,?,?,?,?,?,?)";
			Robot c=new Robot();
			try {
				dbcon = new DBConnection();
				con = dbcon.getConnection();
				stm = con.prepareStatement(sql);
				stm.setInt(1, robot.getDELETE_FLAG());
				stm.setLong(2, robot.getCREATE_BY());
				stm.setString(3, robot.getCREATE_DATE());
				stm.setLong(4, robot.getLAST_UPDATE_BY());
				stm.setString(5, robot.getLAST_UPDATE_DATE());
				stm.setString(6, robot.getName());
				stm.setString(7, robot.getUserId());
				stm.setString(8, robot.getFaceImg());
				stm.setString(9, robot.getLevel());
			    stm.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				dbcon.closeAll(con, stm, rs);
			}
		}
		
		//根据id删除(逻辑删除,将deleteflag改成1：已删)
		public void deleteRobot(Long id){
			DBConnection dbcon = null;
			Connection con = null;
			PreparedStatement stm = null;
			ResultSet rs = null;
			String sql = "update xd_robot set DELETE_FLAG = 1 where id = ?";
			try {
				dbcon = new DBConnection();
				con = dbcon.getConnection();
				stm = con.prepareStatement(sql);
				stm.setLong(1, id);
			    stm.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				dbcon.closeAll(con, stm, rs);
			}
		}
		
		//查询(根据userid查询)
		public Robot selectRobotByUserId(String uid){
			DBConnection dbcon = null;
			Connection con = null;
			PreparedStatement stm = null;
			ResultSet rs = null;
			String sql = "select * from xd_robot where userId = ?";
			Robot c=new Robot();
			try {
				dbcon = new DBConnection();
				con = dbcon.getConnection();
				stm = con.prepareStatement(sql);
				stm.setString(0, uid);
				rs = stm.executeQuery();
				while (rs.next()) {
					Long cid = rs.getLong("id");
					int DELETE_FLAG = rs.getInt("DELETE_FLAG");
					Long CREATE_BY = rs.getLong("CREATE_BY");
					String CREATE_DATE = rs.getString("CREATE_DATE");
					Long LAST_UPDATE_BY = rs.getLong("LAST_UPDATE_BY");
					String LAST_UPDATE_DATE = rs.getString("LAST_UPDATE_DATE");
					String name = rs.getString("name");
					String userId = rs.getString("userId");
					String faceImg = rs.getString("faceImg");
					String level = rs.getString("level");
					c=new Robot(cid, DELETE_FLAG, CREATE_BY, CREATE_DATE, LAST_UPDATE_BY, LAST_UPDATE_DATE, name, userId, faceImg, level);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				dbcon.closeAll(con, stm, rs);
			}
			return c;
		}
		
		public List<Robot> getRobotByDate(){
			DBConnection dbcon = null;
			Connection con = null;
			PreparedStatement stm = null;
			ResultSet rs = null;
			String sql = "select * from xd_robot where id between ? and ?";
			List<Robot> list=new LinkedList<Robot>();
			try {
				dbcon = new DBConnection();
				con = dbcon.getConnection();
				stm = con.prepareStatement(sql);
				String begin=getRandomBegin();
				stm.setString(1, begin);
				stm.setString(2, begin+188);
				rs = stm.executeQuery();
				while (rs.next()) {
					Long cid = rs.getLong("id");
					int DELETE_FLAG = rs.getInt("DELETE_FLAG");
					Long CREATE_BY = rs.getLong("CREATE_BY");
					String CREATE_DATE = rs.getString("CREATE_DATE");
					Long LAST_UPDATE_BY = rs.getLong("LAST_UPDATE_BY");
					String LAST_UPDATE_DATE = rs.getString("LAST_UPDATE_DATE");
					String name = rs.getString("name");
					String userId = rs.getString("userId");
					String faceImg = rs.getString("faceImg");
					String level = rs.getString("level");
					Robot c=new Robot(cid, DELETE_FLAG, CREATE_BY, CREATE_DATE, LAST_UPDATE_BY, LAST_UPDATE_DATE, name, userId, faceImg, level);
					list.add(c);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				dbcon.closeAll(con, stm, rs);
			}
			return list;
		}
		
		private String getRandomBegin(){
			String sd =new Date().getTime()+"";
			Random rd=new Random();
			return sd.substring(sd.length()-rd.nextInt(3));
		}
		
}
