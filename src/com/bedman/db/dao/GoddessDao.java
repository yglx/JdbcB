package com.bedman.db.dao;

import com.bedman.db.DBUtil;
import com.bedman.db.model.Goddess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiangwan on 2016/11/22.
 */
public class GoddessDao {

    public void addGoddess(Goddess g) throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT" +
                " into imooc_goddess" +
                "(user_name,age,update_date) " +
                "values(?,?,current_date())";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1,g.getUser_name());
        ptmt.setInt(2,g.getAge());
        ptmt.execute();

    }

    public void updateGoddess() {

    }

    public void delGoddess() {

    }

    public List<Goddess> query() throws SQLException {
        Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs =stmt.executeQuery("SELECT user_name,age FROM imooc_goddess");
        List<Goddess> gs = new ArrayList<>();
        Goddess g =null;
        while(rs.next()){
            g = new Goddess();
            g.setUser_name(rs.getString("user_name"));
            g.setAge(rs.getInt("age"));
            gs.add(g);
        }
        return gs;
    }

    public Goddess get() {
        return null;
    }
}
