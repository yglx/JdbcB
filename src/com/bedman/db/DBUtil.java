package com.bedman.db;

import com.bedman.db.dao.GoddessDao;
import com.bedman.db.model.Goddess;

import java.sql.*;

/**
 * Created by Jiangwan on 2016/11/21.
 */
public class DBUtil {
    public static final String URL = "jdbc:mysql://127.0.0.1/bedman";
    public static final String USER = "jiangwan";
    public static final String PASSWORD = "abc";
    private static Connection conn = null;
    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
 /*       Class.forName("com.mysql.jdbc.Driver");
       Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs =stmt.executeQuery("SELECT user_name,age FROM imooc_goddess");

        while(rs.next()){
            System.out.println(rs.getString("user_name")+","+rs.getInt("age"));
        }*/
        GoddessDao gD = new GoddessDao();
        Goddess g = new Goddess();
        g.setUser_name("yingxun");
        g.setAge(22);
        gD.addGoddess(g);
    }
}
