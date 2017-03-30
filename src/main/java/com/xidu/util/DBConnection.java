package com.xidu.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context ctx = new InitialContext();  
	    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jndi/zc.newzhibo");  
	    conn = ds.getConnection();  
		return conn;
	}
	

	public int update(String sql, Connection conn){
		Statement sta = null;
		try {
			sta = conn.createStatement();
			System.out.println(sql);
			int i = sta.executeUpdate(sql);
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	public ResultSet execute(String sql, Connection conn) {
		Statement sta = null;
		try {
			sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}
	public boolean executeSql(String sql, Connection conn) {
		Statement sta = null;
		try {
			sta = conn.createStatement();
			sta.execute(sql);
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		} 
	}
	public void closeAll(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeAll(Connection conn, Statement sta) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (sta != null) {
				sta.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeAll(Connection conn, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeAll(Connection conn, Statement sta, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (sta != null) {
				sta.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void free(Statement sta, ResultSet rs) {
		try {
			if (sta != null) {
				sta.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
