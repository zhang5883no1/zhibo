package com.xidu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xidu.entity.Customer;
import com.xidu.entity.CustomerType;
import com.xidu.entity.Robot;
import com.xidu.util.DBConnection;
import com.xidu.util.MD5Util;

public class CustomerDao {
	
	//增加
	public int addCustomer(Customer customer){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		dbcon = new DBConnection();
		String sql = "insert into xd_customer values(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			 pstm.setInt(1,customer.getDELETE_FLAG());      
			 pstm.setLong(2, customer.getCREATE_BY());       
			 pstm.setString(3, customer.getCREATE_DATE());     
			 pstm.setLong(4, customer.getLAST_UPDATE_BY());       
			 pstm.setString(5,customer.getLAST_UPDATE_DATE());      
			 pstm.setString(6,customer.getUserName());        
			 pstm.setString(7,customer.getNickName());      
			 pstm.setString(8,customer.getPwd());        
			 pstm.setString(9,customer.getMobile());      
			 pstm.setString(10,customer.getQq());        
			 pstm.setString(11,customer.getIp());      
			 pstm.setString(12,customer.getLastLoginTime());      
			 pstm.setLong(13,customer.getUserId());        
			 pstm.setString(14,customer.getFaceImg());      
			 pstm.setLong(15,customer.getLevel());        
			 pstm.setString(16,customer.getStatus());      
			 pstm.setString(17,customer.getMail());        
			 pstm.setString(18,customer.getRealName());      
			 pstm.setString(19,customer.getReferer());        
			 pstm.setString(20,customer.getLinksource());    
			 pstm.setString(21,customer.getRoomNo()); 
			 result = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con, pstm, rs);
		}
		return result;
	}
	
	
	//根据id删除(逻辑删除,将deleteflag改成1：已删)
	public void deleteCustomer(Long id){}

	//修改昵称
	public int updateCustomerNickName(Customer customer){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		dbcon = new DBConnection();
		try {
			con = dbcon.getConnection();
	        String sql1 = "update xd_customer set nickName = ? where id = ? and roomNo = ?";
			pstm = con.prepareStatement(sql1);
			pstm.setString(1, customer.getNickName());
			pstm.setLong(2,customer.getId());
			pstm.setString(3, customer.getRoomNo());
			result = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con, pstm, rs);
		}
		return result;
	}
	
		public int updateCustomerStatus(String id,String status){
			DBConnection dbcon = null;
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int result = 0;
			dbcon = new DBConnection();
			try {
				con = dbcon.getConnection();
		        String sql1 = "update xd_customer set status = ? where id = ? ";
				pstm = con.prepareStatement(sql1);
				pstm.setString(1, status);
				pstm.setString(2,id);
				result = pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbcon.closeAll(con, pstm, rs);
			}
			return result;
		}
	
	
    //修改密码
	public int updateCustomerPwd(Customer customer){
			DBConnection dbcon = null;
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int result = 0;
			dbcon = new DBConnection();
			try {
				con = dbcon.getConnection();
			    String sql2 = "update xd_customer set pwd = ? where id = ? and roomNo = ?";
				pstm = con.prepareStatement(sql2);
				pstm.setString(1, customer.getPwd());
				pstm.setLong(2,customer.getId());
				pstm.setString(3, customer.getRoomNo());
				result = pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				dbcon.closeAll(con, pstm, rs);
			}
			return result;
		}
	
	
	//当用户登录成功后，修改用户的最后登录时间
	public int updateCustomerLastLoginTime(Customer customer){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		dbcon = new DBConnection();
		try {
			con = dbcon.getConnection();
		    String sql2 = "update xd_customer set lastLoginTime = ? ,nickName = ? where  qq = ? and roomNo = ?";
			pstm = con.prepareStatement(sql2);
			pstm.setString(1, customer.getLastLoginTime());
			pstm.setString(2, customer.getNickName());
			pstm.setString(3,customer.getQq());
			pstm.setString(4, customer.getRoomNo());
			result = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con, pstm, rs);
		}
		return result;
	}
	
	
	
	//查询(当业务员登录时，将该业务员名下的所有客户查询出来)
	public ArrayList<Customer> selectCustomerBy(Customer customer){
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Customer> customerList = null;
		Long userId = null;
		String sql = "SELECT id FROM ts_fmwk_user WHERE user_type = ?";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1,customer.getId());
			rs = pstm.executeQuery();
			
			while(rs.next()){
				userId = rs.getLong(1);
			}
			pstm.close();
			rs.close();
			
			String sql2 = "SELECT a.id a_id,a.DELETE_FLAG a_DELETE_FLAG,a.CREATE_BY a_CREATE_BY,a.CREATE_DATE a_CREATE_DATE,a.LAST_UPDATE_BY a_LAST_UPDATE_BY,a.LAST_UPDATE_DATE a_LAST_UPDATE_DATE,a.userName a_userName,a.nickName a_nickName,a.pwd a_pwd,a.mobile a_mobile,a.qq a_qq,a.ip a_ip,a.lastLoginTime a_lastLoginTime,a.userId a_userId,a.faceImg a_faceImg,a.LEVEL a_level,a.STATUS a_status,a.mail a_mail,a.realName a_realName,a.referer a_referer,a.linksource a_linksource,a.roomNo a_roomNo,"+
                          " b.id b_id,b.DELETE_FLAG b_DELETE_FLAG,b.CREATE_BY b_CREATE_BY,b.CREATE_DATE b_CREATE_DATE,b.LAST_UPDATE_BY b_LAST_UPDATE_BY,b.LAST_UPDATE_DATE b_LAST_UPDATE_DATE,b.customer_id b_customer_id,b.chat_time b_chat_time,b.video_time b_video_time,b.used_video_time b_used_video_time,b.is_chat b_is_chat,b.is_scrol b_is_scrol,b.is_top b_is_top,b.is_font b_is_font,b.is_valid b_is_valid,b.is_float b_is_float"+
                          " FROM xd_customer a left JOIN xd_customer_type b"+
                          " ON a.id = b.customer_id"+
                          " where a.userId = ?";
			pstm = con.prepareStatement(sql2);
			pstm.setLong(1,userId);
			rs = pstm.executeQuery();
			customerList = new ArrayList<Customer>();
			while(rs.next()){
				//1.获取customer表中的字段值
				 Customer customerResult = new Customer();
				 Long a_id = rs.getLong(1);
				 Integer a_DELETE_FLAG = rs.getInt(2);   
				 Long a_CREATE_BY = rs.getLong(3);       
				 String a_CREATE_DATE = rs.getString(4);    
				 Long a_LAST_UPDATE_BY = rs.getLong(5);   
				 String a_LAST_UPDATE_DATE = rs.getString(6);
				 String a_userName = rs.getString(7);       
				 String a_nickName = rs.getString(8);       
				 String a_pwd = rs.getString(9);            
				 String a_mobile = rs.getString(10);         
				 String a_qq = rs.getString(11);             
				 String a_ip = rs.getString(12);             
				 String a_lastLoginTime = rs.getString(13);  
				 Long a_userId  = rs.getLong(14);          
				 String a_faceImg = rs.getString(15);        
				 Long a_level = rs.getLong(16);            
				 String a_status = rs.getString(17);         
				 String a_mail = rs.getString(18);           
				 String a_realName = rs.getString(19);       
				 String a_referer = rs.getString(20);        
				 String a_linksource = rs.getString(21);     
				 String a_roomNo = rs.getString(22); 
				 
				 
				//2.获取customerType表中的字段值
				 Long b_id = rs.getLong(23);                
				 Integer b_DELETE_FLAG = rs.getInt(24);    
				 Long b_CREATE_BY = rs.getLong(25);         
				 String b_CREATE_DATE = rs.getString(26);     
				 Long b_LAST_UPDATE_BY = rs.getLong(27);    
				 String b_LAST_UPDATE_DATE = rs.getString(28);
				 Long b_customer_id = rs.getLong(29);       
				 Long b_chat_time = rs.getLong(30);         
				 Long b_video_time = rs.getLong(31);        
				 Long b_used_video_time = rs.getLong(32);   
				 Integer b_is_chat = rs.getInt(33);         
				 Integer b_is_scrol = rs.getInt(34);      
				 Integer b_is_top = rs.getInt(35);         
				 Integer b_is_font = rs.getInt(36);        
				 Integer b_is_valid = rs.getInt(37);        
				 Integer b_is_float = rs.getInt(38); 
				 
				 customerResult.setId(a_id);
				 customerResult.setDELETE_FLAG(a_DELETE_FLAG);
				 customerResult.setCREATE_BY(a_CREATE_BY);
				 customerResult.setCREATE_DATE(a_CREATE_DATE);
				 customerResult.setLAST_UPDATE_BY(a_LAST_UPDATE_BY);
				 customerResult.setLAST_UPDATE_DATE(a_LAST_UPDATE_DATE);
				 customerResult.setUserName(a_userName);
				 customerResult.setNickName(a_nickName);
				 customerResult.setPwd(a_pwd);
				 customerResult.setMobile(a_mobile);
				 customerResult.setQq(a_qq);
				 customerResult.setIp(a_ip);
				 customerResult.setLastLoginTime(a_lastLoginTime);
				 customerResult.setUserId(a_userId);
				 customerResult.setFaceImg(a_faceImg);
				 customerResult.setLevel(a_level);
				 customerResult.setStatus(a_status);
				 customerResult.setMail(a_mail);
				 customerResult.setRealName(a_realName);
				 customerResult.setReferer(a_referer);
				 customerResult.setLinksource(a_linksource);
				 customerResult.setRoomNo(a_roomNo);
				 customerResult.setRobotList(null);
				 customerResult.setCustomerList(null);
				 
				 CustomerType ct = new CustomerType();
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
				 
				 customerResult.setCustomerType(ct);
				 
				 customerList.add(customerResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con,pstm,rs);
		}
		return customerList;
	}
	
	//查询(用户名、房间号和密码查询,并且把对应的customerType表查询出来)
    public Customer selectCustomerByUserNamePwd(Customer customer){
    	DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Customer customerResult = null;
		String sql = "SELECT a.id a_id,a.DELETE_FLAG a_DELETE_FLAG,a.CREATE_BY a_CREATE_BY,a.CREATE_DATE a_CREATE_DATE,a.LAST_UPDATE_BY a_LAST_UPDATE_BY,a.LAST_UPDATE_DATE a_LAST_UPDATE_DATE,a.userName a_userName,a.nickName a_nickName,a.pwd a_pwd,a.mobile a_mobile,a.qq a_qq,a.ip a_ip,a.lastLoginTime a_lastLoginTime,a.userId a_userId,a.faceImg a_faceImg,a.LEVEL a_level,a.STATUS a_status,a.mail a_mail,a.realName a_realName,a.referer a_referer,a.linksource a_linksource,a.roomNo a_roomNo,"+
                     " b.id b_id,b.DELETE_FLAG b_DELETE_FLAG,b.CREATE_BY b_CREATE_BY,b.CREATE_DATE b_CREATE_DATE,b.LAST_UPDATE_BY b_LAST_UPDATE_BY,b.LAST_UPDATE_DATE b_LAST_UPDATE_DATE,b.customer_id b_customer_id,b.chat_time b_chat_time,b.video_time b_video_time,b.used_video_time b_used_video_time,b.is_chat b_is_chat,b.is_scrol b_is_scrol,b.is_top b_is_top,b.is_font b_is_font,b.is_valid b_is_valid,b.is_float b_is_float,"+
                     " c.id c_id,c.DELETE_FLAG c_DELETE_FLAG,c.CREATE_BY c_CREATE_BY,c.CREATE_DATE c_CREATE_DATE,c.LAST_UPDATE_BY c_LAST_UPDATE_BY,c.LAST_UPDATE_DATE c_LAST_UPDATE_DATE,c.name c_name,c.userId c_userId,c.faceImg c_faceImg,c.level c_level"+
                     " FROM xd_customer a left JOIN xd_customer_type b"+
                     " ON a.id = b.customer_id"+
                     " left JOIN xd_robot c"+
                     " ON a.id = c.userId"+ 
                     " WHERE a.qq = ?  AND a.DELETE_FLAG = 0";
		//AND a.roomNo = ?
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, customer.getQq());
//			pstm.setString(4,customer.getRoomNo());
			rs = pstm.executeQuery();
			int num = 0;
			List<Robot> robotList = new ArrayList<Robot>();
			while(rs.next()){
				if(num == 0){   //正常情况下customer和customerType相对应的数据只有一条，robot数据可以多条
					//1.获取customer表中的字段值
					 customerResult = new Customer();
					 Long a_id = rs.getLong(1);
					 Integer a_DELETE_FLAG = rs.getInt(2);   
					 Long a_CREATE_BY = rs.getLong(3);       
					 String a_CREATE_DATE = rs.getString(4);    
					 Long a_LAST_UPDATE_BY = rs.getLong(5);   
					 String a_LAST_UPDATE_DATE = rs.getString(6);
					 String a_userName = rs.getString(7);       
					 String a_nickName = rs.getString(8);       
					 String a_pwd = rs.getString(9);            
					 String a_mobile = rs.getString(10);         
					 String a_qq = rs.getString(11);             
					 String a_ip = rs.getString(12);             
					 String a_lastLoginTime = rs.getString(13);  
					 Long a_userId  = rs.getLong(14);          
					 String a_faceImg = rs.getString(15);        
					 Long a_level = rs.getLong(16);            
					 String a_status = rs.getString(17);         
					 String a_mail = rs.getString(18);           
					 String a_realName = rs.getString(19);       
					 String a_referer = rs.getString(20);        
					 String a_linksource = rs.getString(21);     
					 String a_roomNo = rs.getString(22); 
					 
					 
					//2.获取customerType表中的字段值
					 Long b_id = rs.getLong(23);                
					 Integer b_DELETE_FLAG = rs.getInt(24);    
					 Long b_CREATE_BY = rs.getLong(25);         
					 String b_CREATE_DATE = rs.getString(26);     
					 Long b_LAST_UPDATE_BY = rs.getLong(27);    
					 String b_LAST_UPDATE_DATE = rs.getString(28);
					 Long b_customer_id = rs.getLong(29);       
					 Long b_chat_time = rs.getLong(30);         
					 Long b_video_time = rs.getLong(31);        
					 Long b_used_video_time = rs.getLong(32);   
					 Integer b_is_chat = rs.getInt(33);         
					 Integer b_is_scrol = rs.getInt(34);      
					 Integer b_is_top = rs.getInt(35);         
					 Integer b_is_font = rs.getInt(36);        
					 Integer b_is_valid = rs.getInt(37);        
					 Integer b_is_float = rs.getInt(38); 
					 
					 customerResult.setId(a_id);
					 customerResult.setDELETE_FLAG(a_DELETE_FLAG);
					 customerResult.setCREATE_BY(a_CREATE_BY);
					 customerResult.setCREATE_DATE(a_CREATE_DATE);
					 customerResult.setLAST_UPDATE_BY(a_LAST_UPDATE_BY);
					 customerResult.setLAST_UPDATE_DATE(a_LAST_UPDATE_DATE);
					 customerResult.setUserName(a_userName);
					 customerResult.setNickName(a_nickName);
					 customerResult.setPwd(a_pwd);
					 customerResult.setMobile(a_mobile);
					 customerResult.setQq(a_qq);
					 customerResult.setIp(a_ip);
					 customerResult.setLastLoginTime(a_lastLoginTime);
					 customerResult.setUserId(a_userId);
					 customerResult.setFaceImg(a_faceImg);
					 customerResult.setLevel(a_level);
					 customerResult.setStatus(a_status);
					 customerResult.setMail(a_mail);
					 customerResult.setRealName(a_realName);
					 customerResult.setReferer(a_referer);
					 customerResult.setLinksource(a_linksource);
					 customerResult.setRoomNo(a_roomNo);
					 
					 CustomerType ct = new CustomerType();
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
					 
					 customerResult.setCustomerType(ct);
				}
				
				 //3.获取robot表的字段值
				 Long c_id = rs.getLong(39);             
				 Integer c_DELETE_FLAG = rs.getInt(40);     
				 Long c_CREATE_BY = rs.getLong(41);          
				 String c_CREATE_DATE = rs.getString(42);      
				 Long c_LAST_UPDATE_BY = rs.getLong(43);    
				 String c_LAST_UPDATE_DATE = rs.getString(44); 
				 String c_name = rs.getString(45);             
				 String c_userId = rs.getString(46);           
				 String c_faceImg = rs.getString(47);           
				 String c_level = rs.getString(48); 
				 
                 Robot robot = new Robot();
                 robot.setId(c_id);
                 robot.setDELETE_FLAG(c_DELETE_FLAG);
                 robot.setCREATE_BY(c_CREATE_BY);
                 robot.setCREATE_DATE(c_CREATE_DATE);
                 robot.setLAST_UPDATE_BY(c_LAST_UPDATE_BY);
                 robot.setLAST_UPDATE_DATE(c_LAST_UPDATE_DATE);
                 robot.setName(c_name);
                 robot.setUserId(c_userId);
                 robot.setFaceImg(c_faceImg);
                 robot.setLevel(c_level);
                 
                 robotList.add(robot);
                 num++;
			}
			
			if(customerResult != null){
				customerResult.setRobotList(robotList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con,pstm,rs);
		}
    	return customerResult;
    }
    
    
    //查询(根据用户名查询,判断是否用户名重复)
    public int selectCustomerByUserName(Customer customer){
    	DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0 ;
		String sql = "SELECT a.id a_id,a.DELETE_FLAG a_DELETE_FLAG,a.CREATE_BY a_CREATE_BY,a.CREATE_DATE a_CREATE_DATE,a.LAST_UPDATE_BY a_LAST_UPDATE_BY,a.LAST_UPDATE_DATE a_LAST_UPDATE_DATE,a.userName a_userName,a.nickName a_nickName,a.pwd a_pwd,a.mobile a_mobile,a.qq a_qq,a.ip a_ip,a.lastLoginTime a_lastLoginTime,a.userId a_userId,a.faceImg a_faceImg,a.LEVEL a_level,a.STATUS a_status,a.mail a_mail,a.realName a_realName,a.referer a_referer,a.linksource a_linksource,a.roomNo a_roomNo"+
                     " FROM xd_customer a WHERE a.qq = ? AND a.roomNo = ? AND a.DELETE_FLAG = 0";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, customer.getQq());
			pstm.setString(2,customer.getRoomNo());
			rs = pstm.executeQuery();
			while(rs.next()){
					result++;
			}
		  	
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con,pstm,rs);
		}
    	return result;
    }
    
    
    
    //查询(根据手机号查询,判断是否手机号重复)
    public int selectCustomerByMobile(Customer customer){
    	DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT a.id a_id,a.DELETE_FLAG a_DELETE_FLAG,a.CREATE_BY a_CREATE_BY,a.CREATE_DATE a_CREATE_DATE,a.LAST_UPDATE_BY a_LAST_UPDATE_BY,a.LAST_UPDATE_DATE a_LAST_UPDATE_DATE,a.userName a_userName,a.nickName a_nickName,a.pwd a_pwd,a.mobile a_mobile,a.qq a_qq,a.ip a_ip,a.lastLoginTime a_lastLoginTime,a.userId a_userId,a.faceImg a_faceImg,a.LEVEL a_level,a.STATUS a_status,a.mail a_mail,a.realName a_realName,a.referer a_referer,a.linksource a_linksource,a.roomNo a_roomNo"+
                     " FROM xd_customer a WHERE a.mobile = ? AND a.roomNo = ? AND a.DELETE_FLAG = 0";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, customer.getMobile());
			pstm.setString(2,customer.getRoomNo());
			rs = pstm.executeQuery();
			while(rs.next()){
				result++;
			}
		  	
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con,pstm,rs);
		}
    	return result;
    }
    
    //查询(根据id和房间号查询)
    public Customer selectCustomerById(Long id,String roomNo){
    	DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Customer customer = new Customer();
		String sql = "SELECT a.id a_id,a.DELETE_FLAG a_DELETE_FLAG,a.CREATE_BY a_CREATE_BY,a.CREATE_DATE a_CREATE_DATE,a.LAST_UPDATE_BY a_LAST_UPDATE_BY,a.LAST_UPDATE_DATE a_LAST_UPDATE_DATE,a.userName a_userName,a.nickName a_nickName,a.pwd a_pwd,a.mobile a_mobile,a.qq a_qq,a.ip a_ip,a.lastLoginTime a_lastLoginTime,a.userId a_userId,a.faceImg a_faceImg,a.LEVEL a_level,a.STATUS a_status,a.mail a_mail,a.realName a_realName,a.referer a_referer,a.linksource a_linksource,a.roomNo a_roomNo,"+
                     " b.id b_id,b.DELETE_FLAG b_DELETE_FLAG,b.CREATE_BY b_CREATE_BY,b.CREATE_DATE b_CREATE_DATE,b.LAST_UPDATE_BY b_LAST_UPDATE_BY,b.LAST_UPDATE_DATE b_LAST_UPDATE_DATE,b.customer_id b_customer_id,b.chat_time b_chat_time,b.video_time b_video_time,b.used_video_time b_used_video_time,b.is_chat b_is_chat,b.is_scrol b_is_scrol,b.is_top b_is_top,b.is_font b_is_font,b.is_valid b_is_valid,b.is_float b_is_float,"+
                     " c.id c_id,c.DELETE_FLAG c_DELETE_FLAG,c.CREATE_BY c_CREATE_BY,c.CREATE_DATE c_CREATE_DATE,c.LAST_UPDATE_BY c_LAST_UPDATE_BY,c.LAST_UPDATE_DATE c_LAST_UPDATE_DATE,c.name c_name,c.userId c_userId,c.faceImg c_faceImg,c.level c_level"+
                     " FROM xd_customer a left JOIN xd_customer_type b"+
                     " ON a.id = b.customer_id"+
                     " left JOIN xd_robot c"+
                     " ON a.id = c.userId"+ 
                     " WHERE a.id = ? AND a.roomNo = ? AND a.DELETE_FLAG = 0";
		try {
			dbcon = new DBConnection();
			con = dbcon.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setLong(1,id);
			pstm.setString(2,roomNo);
			rs = pstm.executeQuery();
			int num = 0;
			List<Robot> robotList = new ArrayList<Robot>();
			while(rs.next()){
				if(num == 0){   //正常情况下customer和customerType相对应的数据只有一条，robot数据可以多条
					//1.获取customer表中的字段值
					 Long a_id = rs.getLong(1);
					 Integer a_DELETE_FLAG = rs.getInt(2);   
					 Long a_CREATE_BY = rs.getLong(3);       
					 String a_CREATE_DATE = rs.getString(4);    
					 Long a_LAST_UPDATE_BY = rs.getLong(5);   
					 String a_LAST_UPDATE_DATE = rs.getString(6);
					 String a_userName = rs.getString(7);       
					 String a_nickName = rs.getString(8);       
					 String a_pwd = rs.getString(9);            
					 String a_mobile = rs.getString(10);         
					 String a_qq = rs.getString(11);             
					 String a_ip = rs.getString(12);             
					 String a_lastLoginTime = rs.getString(13);  
					 Long a_userId  = rs.getLong(14);          
					 String a_faceImg = rs.getString(15);        
					 Long a_level = rs.getLong(16);            
					 String a_status = rs.getString(17);         
					 String a_mail = rs.getString(18);           
					 String a_realName = rs.getString(19);       
					 String a_referer = rs.getString(20);        
					 String a_linksource = rs.getString(21);     
					 String a_roomNo = rs.getString(22); 
					 
					 
					//2.获取customerType表中的字段值
					 Long b_id = rs.getLong(23);                
					 Integer b_DELETE_FLAG = rs.getInt(24);    
					 Long b_CREATE_BY = rs.getLong(25);         
					 String b_CREATE_DATE = rs.getString(26);     
					 Long b_LAST_UPDATE_BY = rs.getLong(27);    
					 String b_LAST_UPDATE_DATE = rs.getString(28);
					 Long b_customer_id = rs.getLong(29);       
					 Long b_chat_time = rs.getLong(30);         
					 Long b_video_time = rs.getLong(31);        
					 Long b_used_video_time = rs.getLong(32);   
					 Integer b_is_chat = rs.getInt(33);         
					 Integer b_is_scrol = rs.getInt(34);      
					 Integer b_is_top = rs.getInt(35);         
					 Integer b_is_font = rs.getInt(36);        
					 Integer b_is_valid = rs.getInt(37);        
					 Integer b_is_float = rs.getInt(38); 
					 
					 customer.setId(a_id);
					 customer.setDELETE_FLAG(a_DELETE_FLAG);
					 customer.setCREATE_BY(a_CREATE_BY);
					 customer.setCREATE_DATE(a_CREATE_DATE);
					 customer.setLAST_UPDATE_BY(a_LAST_UPDATE_BY);
					 customer.setLAST_UPDATE_DATE(a_LAST_UPDATE_DATE);
					 customer.setUserName(a_userName);
					 customer.setNickName(a_nickName);
					 customer.setPwd(a_pwd);
					 customer.setMobile(a_mobile);
					 customer.setQq(a_qq);
					 customer.setIp(a_ip);
					 customer.setLastLoginTime(a_lastLoginTime);
					 customer.setUserId(a_userId);
					 customer.setFaceImg(a_faceImg);
					 customer.setLevel(a_level);
					 customer.setStatus(a_status);
					 customer.setMail(a_mail);
					 customer.setRealName(a_realName);
					 customer.setReferer(a_referer);
					 customer.setLinksource(a_linksource);
					 customer.setRoomNo(a_roomNo);
					 
					 CustomerType ct = new CustomerType();
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
					 
					 customer.setCustomerType(ct);
				}
				 //3.获取robot表的字段值
				 Long c_id = rs.getLong(39);             
				 Integer c_DELETE_FLAG = rs.getInt(40);     
				 Long c_CREATE_BY = rs.getLong(41);          
				 String c_CREATE_DATE = rs.getString(42);      
				 Long c_LAST_UPDATE_BY = rs.getLong(43);    
				 String c_LAST_UPDATE_DATE = rs.getString(44); 
				 String c_name = rs.getString(45);             
				 String c_userId = rs.getString(46);           
				 String c_faceImg = rs.getString(47);           
				 String c_level = rs.getString(48); 
				 
                 Robot robot = new Robot();
                 robot.setId(c_id);
                 robot.setDELETE_FLAG(c_DELETE_FLAG);
                 robot.setCREATE_BY(c_CREATE_BY);
                 robot.setCREATE_DATE(c_CREATE_DATE);
                 robot.setLAST_UPDATE_BY(c_LAST_UPDATE_BY);
                 robot.setLAST_UPDATE_DATE(c_LAST_UPDATE_DATE);
                 robot.setName(c_name);
                 robot.setUserId(c_userId);
                 robot.setFaceImg(c_faceImg);
                 robot.setLevel(c_level);
                 
                 robotList.add(robot);
                 num++;
			}
			
			customer.setRobotList(robotList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbcon.closeAll(con,pstm,rs);
		}
    	return customer;
    }
}
