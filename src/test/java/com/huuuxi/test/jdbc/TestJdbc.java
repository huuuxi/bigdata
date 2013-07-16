package com.huuuxi.test.jdbc;

import java.sql.ResultSet;

import org.junit.Test;

import com.huuuxi.bigdata.jdbc.Jdbc;

/**   
 * @Title: TestJdbc.java 
 * @Description: 
 * @author huuuxi 
 * @Email huuuxi@gmail.com 
 * @date 2013-7-16 下午8:41:16 
 */
public class TestJdbc {

	private static String SQL_SELECT = "select * from url";
	private static String SQL_INSERT = "insert url(url) value (?)";
	
	@Test
	public void testSelectSql(){
		ResultSet rs =  Jdbc.executeSelect(SQL_SELECT);
		try {
			while (rs.next()) {
				System.out.println(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("end");
		}
	}
	
	@Test
	public void testInsertSql(){
		Jdbc.executeInsert(SQL_INSERT, new Object[]{"2"});
	}
}
