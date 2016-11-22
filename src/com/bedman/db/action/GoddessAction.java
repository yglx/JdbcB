package com.bedman.db.action;

import com.bedman.db.dao.GoddessDao;
import com.bedman.db.model.Goddess;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jiangwan on 2016/11/22.
 */
public class GoddessAction {

    public static void main(String[] args) throws SQLException {
        GoddessDao g = new GoddessDao();
        List<Goddess> gs = g.query();
        for (Goddess goddess :
                gs) {
            System.out.println(goddess.getUser_name()+goddess.getAge());
        }
    }
}
