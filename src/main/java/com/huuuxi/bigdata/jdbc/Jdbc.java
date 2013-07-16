package com.huuuxi.bigdata.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**   
 * @Title: Jdbc.java 
 * @Description: 
 * @author huuuxi 
 * @Email huuuxi@gmail.com 
 * @date 2013-4-18 上午11:16:32 
 */
public class Jdbc {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String dbName = "bigdata";
	private static String password = "123456";
	private static String userName = "root";
	private static String url = "jdbc:mysql://192.168.168.234:3306/" + dbName+"?characterEncoding=UTF-8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull";
	
	private static Connection getConnection(){
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static ResultSet executeSelect(String sql){
		ResultSet rs = null;
		try {
			Connection con = getConnection();
			rs = con.prepareStatement(sql).executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public void excuteSql(String sql,List<Object[]> objs){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, userName, password);
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int j =0; j< objs.size(); j++){
				Object[] objt = objs.get(j);
				for (int i = 0; i < objt.length; i++) {
					ps.setString(i+1, (String)objt[i]);
				}
				ps.addBatch();
				System.out.println(j);
				//ps.execute();
			}
			ps.executeBatch();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	public static void main(String[] args) {
		Jdbc jdbc = new Jdbc();
		String sql = "insert into rule(name) values (?)";
		Object[] objt = new Object[]{"1111"};
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(objt);
		jdbc.excuteSql(sql, list);
	}
	
}
