package com.codefoo.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDao {

    final static Logger LOG = Logger.getLogger(BaseDao.class);
    protected Connection connection;

    protected void executeBatch(PreparedStatement preparedStmt) throws SQLException {
        try {
            preparedStmt.executeBatch();
        } catch (MySQLIntegrityConstraintViolationException e) {
            LOG.error("Found duplicate entry while inserting ", e);
            // duplicate entries error.
        }
    }
}
