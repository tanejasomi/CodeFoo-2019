package com.codefoo.dao;

import com.codefoo.exception.AppException;
import com.codefoo.mappings.ConfLoader;
import com.codefoo.model.Author;
import com.codefoo.util.SQLUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private Connection connection;
    final static Logger LOG = Logger.getLogger(AuthorDaoImpl.class);


    public AuthorDaoImpl(Connection connection) throws AppException {
        this.connection = connection;

        if (connection == null) {
            throw new AppException("connection is NULL");
        }
    }

    public List<String> getAuthorForContentId(String contentId) throws SQLException {
        return null;
    }

    public boolean insertAuthors(List<Author> authors) {

        int recCount = 0;
        int totalCount = 0;
        int commitSize = ConfLoader.getConf().getCommitSize();

        PreparedStatement preparedStmt = null;
        try {

            preparedStmt = connection.prepareStatement(SQLUtils.INSERT_INTO_AUTHORS);
            for (Author author : authors) {
                preparedStmt.setString(1, author.getContentId());
                preparedStmt.setString(2, author.getName());

                preparedStmt.addBatch();
                recCount++;

                if (recCount == commitSize) {
                    preparedStmt.executeBatch();

                    totalCount = totalCount + recCount;
                    recCount = 0;
                }
            }

            if (recCount > 0) { // left over content
                preparedStmt.executeBatch();
            }
            LOG.info("Total Records Inserted in Author: " + totalCount);

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            LOG.error("Found duplicate entry while inserting ", e);
            // duplicate entries warning.
           //continue for next chunk
        } catch (SQLException e) {
            LOG.error("Error in insert closing connections ", e);
            return false;
        } finally {
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException e) {
                LOG.error("Error in insert closing connections ", e);
            }
        }

        return true;
    }
}
