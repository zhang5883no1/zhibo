package com.xidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xidu.entity.Customer;
import com.xidu.entity.CustomerType;
import com.xidu.util.DBConnection;

public class CustomerTypeDao {
	
	//增加
	public int addCustomerType(CustomerType customerType){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		dbcon = new DBConnection();
		String sql = "insert into xd_customer_type values(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			 pstm.setInt(1,customerType.getDELETE_FLAG());      
			 pstm.setLong(2, customerType.getCREATE_BY());       
			 pstm.setString(3, customerType.getCREATE_DATE());     
			 pstm.setLong(4, customerType.getLAST_UPDATE_BY());       
			 pstm.setString(5,customerType.getLAST_UPDATE_DATE());      
			 pstm.setLong(6,customerType.getCustomer_id());        
			 pstm.setLong(7,customerType.getChat_time());      
			 pstm.setLong(8,customerType.getVideo_time());        
			 pstm.setLong(9,customerType.getUsed_video_time());      
			 pstm.setInt(10,customerType.getIs_chat());        
			 pstm.setInt(11,customerType.getIs_scrol());      
			 pstm.setInt(12,customerType.getIs_top());      
			 pstm.setInt(13,customerType.getIs_font());        
			 pstm.setInt(14,customerType.getIs_valid());      
			 pstm.setInt(15,customerType.getIs_float());        
			 result = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con, pstm, rs);
		}
		return result;
		
	}

	//根据id删除(逻辑删除,将deleteflag改成1：已删)
	public void deleteCustomerType(Long id){}
	
	//查询(根据id查询)
	public CustomerType selectCustomerTypeById(Long id){
		return null;
	}
	
	//查询(根据customerId查询)
	public CustomerType selectCustomerTypeByCustomerId(Long customerId){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM xd_customer_type WHERE customer_id = ?";
		CustomerType ct = null;
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, customerId);
			rs = pstm.executeQuery();
			while(rs.next()){
				 ct = new CustomerType();
				 Long b_id = rs.getLong(1);                
				 Integer b_DELETE_FLAG = rs.getInt(2);    
				 Long b_CREATE_BY = rs.getLong(3);         
				 String b_CREATE_DATE = rs.getString(4);     
				 Long b_LAST_UPDATE_BY = rs.getLong(5);    
				 String b_LAST_UPDATE_DATE = rs.getString(6);
				 Long b_customer_id = rs.getLong(7);       
				 Long b_chat_time = rs.getLong(8);         
				 Long b_video_time = rs.getLong(9);        
				 Long b_used_video_time = rs.getLong(10);   
				 Integer b_is_chat = rs.getInt(11);         
				 Integer b_is_scrol = rs.getInt(12);      
				 Integer b_is_top = rs.getInt(13);         
				 Integer b_is_font = rs.getInt(14);        
				 Integer b_is_valid = rs.getInt(15);        
				 Integer b_is_float = rs.getInt(16); 
				 
				 ct.setId(b_id);
				 ct.setDELETE_FLAG(b_DELETE_FLAG);
				 ct.setCREATE_BY(b_CREATE_BY);
				 ct.setCREATE_DATE(b_CREATE_DATE);
				 ct.setLAST_UPDATE_BY(b_LAST_UPDATE_BY);
				 ct.setLAST_UPDATE_DATE(b_LAST_UPDATE_DATE);
				 ct.setCustomer_id(b_customer_id);
				 ct.setChat_time(b_chat_time);
				 ct.setVideo_time(b_video_time);
				 ct.setUsed_video_time(b_used_video_time);
				 ct.setIs_chat(b_is_chat);
				 ct.setIs_scrol(b_is_scrol);
				 ct.setIs_top(b_is_top);
				 ct.setIs_font(b_is_font);
				 ct.setIs_valid(b_is_valid);
				 ct.setIs_float(b_is_float);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con, pstm, rs);
		}
		return ct;
	}
	
	public int update(String updateName, String updateValue, String id, Long doUpdateUserId){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="";
		if("is_chat".equals(updateName)){
			sql = "update xd_customer_type set is_chat = ?, LAST_UPDATE_BY=?, LAST_UPDATE_DATE=? where id = ?";
		}else {
			return 0;
		}
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, updateValue);
			pstm.setLong(2, doUpdateUserId);
			pstm.setString(3, sf.format(new Date()));
			pstm.setString(4, id);
			result = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con, pstm, rs);
		}
		return result;
	}
	
	
	
	//修改用户观看视频时间
		public int updateUsedVideoTime(Long customerId){
			DBConnection dbcon = null;
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int result = 0;
			String sql = "UPDATE xd_customer_type SET used_video_time  = (SELECT a.used_video_time FROM (SELECT used_video_time FROM xd_customer_type WHERE customer_id = ?) a) + 1 WHERE customer_id = ?";
			try {
				dbcon = new DBConnection();
				con = dbcon.getConnection();
				pstm = con.prepareStatement(sql);
				pstm.setLong(1, customerId);
				pstm.setLong(2, customerId);
				result = pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbcon.closeAll(con, pstm, rs);
			}
			return result;
		}
}


