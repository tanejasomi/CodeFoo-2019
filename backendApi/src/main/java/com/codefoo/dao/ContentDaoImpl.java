package com.codefoo.dao;

import com.codefoo.exception.AppException;
import com.codefoo.mappings.ConfLoader;
import com.codefoo.model.*;
import com.codefoo.util.SQLUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDaoImpl implements ContentDao {

    final static Logger LOG = Logger.getLogger(ContentDaoImpl.class);

    private Connection connection;

    public ContentDaoImpl(Connection connection) throws AppException {
        this.connection = connection;

        if (connection == null) {
            throw new AppException("connection is NuLL");
        }
    }

    public Content getContent(String id) throws SQLException {

        PreparedStatement preparedStmt = connection.prepareStatement(SQLUtils.SELECT_CONTENT_BY_ID);
        preparedStmt.setString(1, id);
        ResultSet rs = preparedStmt.executeQuery();
        Content content = null;

        if (rs != null) {

            if (rs.getFetchSize() != 1) {
                LOG.warn("More than one record found for content id: " + id);
            }

            while (rs.next()) {
                content = new Content(rs.getString("content_id"));

                content.setContentType(ContentType.getTypeFromName(rs.getString("content_type")));
                content.setDescription(rs.getString("description"));
                content.setHeadline(rs.getString("headline"));
                content.setPublishDate(rs.getDate("publish_date"));

                content.setSlug(rs.getString("slug"));
                content.setDuration(rs.getInt("duration"));
                content.setTitle(rs.getString("title"));
                content.setVideoSeries(rs.getString("video_series"));

                // Get the tags, author and thumpnails
                String tagValues = rs.getString("tags");
                if (tagValues != null) {
                    List<Tag> tags = new ArrayList<Tag>();

                    String[] tagSplitValues = tagValues.split(",");
                    for (String tagValue : tagSplitValues) {
                        Tag tag = new Tag(tagValue);
                        tag.setContentId(rs.getString("content_id"));
                        tags.add(tag);

                        content.setTags(tags);
                    }
                }

                String authorValues = rs.getString("authors");
                if (authorValues != null) {
                    List<Author> authors = new ArrayList<Author>();

                    String[] authorSplitValues = authorValues.split(",");
                    for (String authorValue : authorSplitValues) {
                        Author author = new Author(authorValue);
                        author.setContentId(rs.getString("content_id"));
                        authors.add(author);

                        content.setAuthors(authors);
                    }
                }

                String thumbnailURL = rs.getString("url");
                if (thumbnailURL != null) {
                    List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();

                    String[] thumbnailSplitValues = thumbnailURL.split(",");
                    for (String thumbnailValue : thumbnailSplitValues) {
                        Thumbnail thumbnail = new Thumbnail();
                        thumbnail.setContentID(rs.getString("content_id"));
                        thumbnail.setThumbnailURL(thumbnailValue);
                        thumbnails.add(thumbnail);

                        content.setThumbnails(thumbnails);
                    }
                }
                break;
            }

            rs.close();
        }

        return content;
    }

    public Content getContentByTitle(String title) throws SQLException {
        return null;
    }

    public Content getContentsByTags(List<String> tags) {
        return null;
    }

    public Content getContentByAuthor(String author) {
        return null;
    }

    public boolean insertContent(List<Content> contents) {

        int recCount = 0;
        int totalCount = 0;
        int commitSize = ConfLoader.getConf().getCommitSize();

        PreparedStatement preparedStmt = null;

        try {

            // create the mysql insert preparedstatement
            preparedStmt = connection.prepareStatement(SQLUtils.INSERT_INTO_CONTENTS);

            for (Content content : contents) {
                preparedStmt.setString(1, content.getContentID());
                preparedStmt.setString(2, content.getContentType().getName());
                preparedStmt.setString(3, content.getTitle());
                preparedStmt.setString(4, content.getHeadline());
                preparedStmt.setString(5, content.getDescription());
                preparedStmt.setDate(6, new java.sql.Date(content.getPublishDate().getTime()));
                preparedStmt.setString(7, content.getSlug());
                preparedStmt.setString(8, content.getState());
                preparedStmt.setInt(9, (content.getDuration() == null) ? -1 : content.getDuration());
                preparedStmt.setString(10, content.getVideoSeries());

                preparedStmt.addBatch();
                recCount++;

                if (recCount == commitSize) {
                    executeBatch(preparedStmt);

                    totalCount = totalCount + recCount;
                    recCount = 0;
                }
            }

            if (recCount > 0) { // left over content
                executeBatch(preparedStmt);
            }
            LOG.info("Total Records Inserted in CONTENTS: " + totalCount);


        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            LOG.error("Found duplicate entry while inserting ", e);
            // duplicate entries warning.
            return false;
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

    public boolean createTables() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            //Create Tables here
            statement.execute("CREATE TABLE IF NOT EXISTS MASTER(" +
                    " content_id VARCHAR(60) NOT NULL PRIMARY KEY," + "" +
                    "content_type VARCHAR(20) NOT NULL," +
                    "title VARCHAR(150) NULL," +
                    "headline VARCHAR(150) NULL," +
                    "description VARCHAR(300)  NULL," +
                    //"publish_date TIMESTAMP NOT NULL,"+
                    "publish_date VARCHAR(30) NOT NULL," +
                    "slug VARCHAR(300) NULL," +
                    "state VARCHAR(20) NOT NULL," +
                    "duration VARCHAR(20) NULL," +
                    "video_series VARCHAR(50) NULL," +
                    "author_1 VARCHAR(50) NULL," +
                    "author_2 VARCHAR(50) NULL," +
                    "tag_1 VARCHAR(50) NULL," +
                    "tag_2 VARCHAR(50) NULL," +
                    "tag_3 VARCHAR(50) NULL," +
                    "thumbnail_1_URL VARCHAR(300) NULL," +
                    "thumbnail_1_size VARCHAR(20) NULL," +
                    "thumbnail_1_width VARCHAR(20) NULL," +
                    "thumbnail_1_height VARCHAR(20) NULL," +
                    "thumbnail_2_URL VARCHAR(300) NULL, " +
                    "thumbnail_2_size VARCHAR(20) NULL," +
                    "thumbnail_2_width VARCHAR(20) NULL," +
                    "thumbnail_2_height VARCHAR(20) NULL," +
                    "thumbnail_3_URL VARCHAR(300) NULL," +
                    "thumbnail_3_size VARCHAR(20) NULL," +
                    "thumbnail_3_width VARCHAR(20) NULL," +
                    "thumbnail_3_height VARCHAR(20) NULL);"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS CONTENTS(" +
                    "content_id VARCHAR(60) NOT NULL PRIMARY KEY," +
                    "content_type VARCHAR(20) NOT NULL," +
                    "title VARCHAR(150) NULL," +
                    "headline VARCHAR(150) NULL," +
                    "description VARCHAR(300)  NULL," +
                    //"publish_date TIMESTAMP NOT NULL,"+
                    "publish_date DATETIME(6) NOT NULL," +
                    "slug VARCHAR(300) NULL," +
                    "state VARCHAR(20) NOT NULL," +
                    "duration SMALLINT NULL," +
                    "video_series VARCHAR(50) NULL);"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS THUMBNAILS(" +
                    "content_id varchar(60) NOT NULL," +
                    "url VARCHAR (300) NOT NULL," +
                    "size VARCHAR(12), " +
                    "width DECIMAL(5, 2) , " +
                    "height DECIMAL(5, 2), " +
                    "INDEX (size), " +
                    "FOREIGN KEY (content_id) REFERENCES Contents(content_id) " +
                    ");"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS AUTHORS(" +
                    "content_id VARCHAR(60) NOT NULL," +
                    "author VARCHAR(60) NOT NULL, PRIMARY KEY(content_id, author));"
            );

            statement.execute("CREATE TABLE IF NOT EXISTS TAGS(" +
                    "content_id VARCHAR(60) NOT NULL," +
                    "tag VARCHAR(60) NOT NULL, PRIMARY KEY(content_id, tag));"
            );

        } catch (SQLException e) {
            LOG.error(e);
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOG.error(e);
            }

        }
        return true;
    }

    private void executeBatch(PreparedStatement preparedStmt) throws SQLException {
        try {
            preparedStmt.executeBatch();
        } catch (MySQLIntegrityConstraintViolationException e) {
            LOG.error("Found duplicate entry while inserting ", e);
            // duplicate entries warning.
        }
    }

    public boolean updateContent() {
        return false;
    }
}
