package com.bookhub.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 仿照 Spring 的 RowMapper 接口，演示如何将 JDBC ResultSet 转换为领域对象。
 */
@FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
