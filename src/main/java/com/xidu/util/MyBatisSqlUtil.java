package com.xidu.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisSqlUtil {

	private static SqlSessionFactory sqlSessionFactory=null;

	public static SqlSessionFactory getInstance() {

		if (sqlSessionFactory == null) {
			try {
				Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); // 建立sessionFactory
			} catch (IOException e) {
				
			}// 读取配置文件，连接数据库
		}
		return sqlSessionFactory;
	}

}
