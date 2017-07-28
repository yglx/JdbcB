package com.learn.ying.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class JdbcUtilsImplByMysql implements JdbcUtils {
	private static JdbcUtilsImplByMysql jdbcImpl;
	private Connection conn;
	private Statement stat;
	private Integer count;
	private Set<String> tables;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private JdbcUtilsImplByMysql() {
	}

	public static JdbcUtilsImplByMysql getInstance() {
		if (null == jdbcImpl) {
			jdbcImpl = new JdbcUtilsImplByMysql();
		}
		return jdbcImpl;
	}

	@Override
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/learning";
		String username = "learning";
		String password = "admin";
		try {
			if (null == conn) {
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
			System.out.println("failed to connect the datebase...");
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public ResultSet getResult(String sql) {
		Connection conn = this.getConnection();
		Statement stat;
		ResultSet resu = null;
		try {
			stat = conn.createStatement();
			resu = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resu;
	}
	void getAllTables() throws Exception {
		tables = new HashSet<String>();
		this.getConnection();
		DatabaseMetaData dbmd;
		try {
			dbmd = this.conn.getMetaData();
			ResultSet rs = dbmd.getTables(null, null, "%", null);
			count = 0;
			while (rs.next()) {
				String tableName = rs.getString(3);
				if (tableName.indexOf("/") != -1
						|| tableName.indexOf("$") != -1
						|| tableName.length() < 2
						|| tableName.startsWith("_")
						) {
					continue;
				}
				System.out.println("TN_" +(count++)+":"+rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//this.conn.close();
		}
	}
	public void seek(String sql){
		try {
			this.getAllTables();
			stat = conn.createStatement();
			for (String p : tables) {
				System.out.println("table:\t" + p);
				sql = "select * from " + p + " where 1=2";
				ResultSet rs = stat.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					System.out.print(rsmd.getColumnName(i) + ",");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// new JdbcUtilsImplByOracle().stringMaker();
		String sql="select * from l_user";
		new JdbcUtilsImplByMysql().seek(sql);
	}
}
