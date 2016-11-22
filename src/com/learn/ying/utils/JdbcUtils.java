package com.learn.ying.utils;

import java.sql.Connection;
import java.sql.ResultSet;

public interface JdbcUtils {
	Connection getConnection();
	ResultSet getResult(String sql);
}
