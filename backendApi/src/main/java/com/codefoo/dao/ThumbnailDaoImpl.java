package com.codefoo.dao;

import com.codefoo.exception.AppException;
import com.codefoo.mappings.ConfLoader;
import com.codefoo.model.Thumbnail;
import com.codefoo.util.SQLUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ThumbnailDaoImpl implements ThumbnailDao {

    final static Logger LOG = Logger.getLogger(ThumbnailDaoImpl.class);
    private Connection connection;

    public ThumbnailDaoImpl(Connection connection) throws AppException {
        this.connection = connection;

        if (connection == null) {
            throw new AppException("connection is NULL");
        }
    }

    public List<String> getThumbnailForContentId(String contentId) {
        return null;
    }

    public boolean insertThumbnail(List<Thumbnail> thumbnails) {

        int recCount = 0;
        int totalCount = 0;
        int commitSize = ConfLoader.getConf().getCommitSize();

        PreparedStatement preparedStmt = null;

        try {

            preparedStmt = connection.prepareStatement(SQLUtils.INSERT_INTO_THUMBNAILS);
            for (Thumbnail thumbnail : thumbnails) {
                preparedStmt.setString(1, thumbnail.getContentID());
                preparedStmt.setString(2, thumbnail.getThumbnailURL());
                preparedStmt.setString(3, thumbnail.getSize());
                preparedStmt.setFloat(4, (thumbnail.getWidth() == null ? -1 : thumbnail.getWidth()));
                preparedStmt.setFloat(5, (thumbnail.getHeight() == null ? -1 : thumbnail.getHeight()));

                preparedStmt.addBatch();
                recCount++;

                if (recCount == commitSize) {
                    try {
                        preparedStmt.executeBatch();
                    } catch (MySQLIntegrityConstraintViolationException e) {
                        LOG.error("Found duplicate entry while inserting ", e);
                        // duplicate entries warning.
                        // return false;
                    }

                    totalCount = totalCount + recCount;
                    recCount = 0;
                }
            }

            if (recCount > 0) { // left over content
                try {
                    preparedStmt.executeBatch();
                } catch (MySQLIntegrityConstraintViolationException e) {
                    LOG.error("Found duplicate entry while inserting ", e);
                    // duplicate entries warning.
                    // return false;
                }
            }
            LOG.info("Total Records Inserted in Thumbnail: " + totalCount);

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
