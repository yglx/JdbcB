package com.bedman.db;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jiangwan on 2016/11/21.
 */
public class DBUtil {
    public static final String URL = "";
    public static final String USER = "jiangwan";
    public static final String PASSWORD = "abc";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
