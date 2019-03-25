package com.codefoo.dao;

import com.codefoo.exception.AppException;
import com.codefoo.mappings.ConfLoader;
import com.codefoo.model.*;
import com.codefoo.util.SQLUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl implements TagDao {

    final static Logger LOG = Logger.getLogger(TagDaoImpl.class);

    private Connection connection;

    public TagDaoImpl(Connection connection) throws AppException {
        this.connection = connection;

        if (connection == null) {
            throw new AppException("connection is NULL");
        }
    }

    public List<String> getTagsForContentId(String contentId) throws SQLException {
        List<String> tags = new ArrayList<String>();

        PreparedStatement preparedStmt = connection.prepareStatement(SQLUtils.SELECT_CONTENT_BY_TAGS);

        preparedStmt.setString(1, contentId);
        ResultSet rs = preparedStmt.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                tags.add(rs.getString("tag"));
            }
        }

        return tags;
    }

    public List<Content> getContentByTag(String queryTag) throws SQLException {

        PreparedStatement preparedStmt = connection.prepareStatement(SQLUtils.SELECT_CONTENT_BY_TAGS);
        preparedStmt.setString(1, queryTag);
        ResultSet rs = preparedStmt.executeQuery();

        List<Content> contents = new ArrayList<Content>();

        if (rs != null) {

            while (rs.next()) {
                Content content = new Content(rs.getString("content_id"));

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

                contents.add(content);
            }

            rs.close();
        }

        return contents;
    }

    public boolean insertTags(List<Tag> tags) {
        int recCount = 0;
        int totalCount = 0;
        int commitSize = ConfLoader.getConf().getCommitSize();

        PreparedStatement preparedStmt = null;

        try {

            preparedStmt = connection.prepareStatement(SQLUtils.INSERT_INTO_TAGS);

            for (Tag tag : tags) {
                preparedStmt.setString(1, tag.getContentId());
                preparedStmt.setString(2, tag.getTag());

                preparedStmt.addBatch();
                recCount++;

                if (recCount == commitSize) {
                    try {
                        preparedStmt.executeBatch();
                    } catch (MySQLIntegrityConstraintViolationException e) {
                        LOG.error("Found duplicate entry while inserting ", e);
                        // duplicate entries warning.
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
                }
            }

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
            LOG.info("Total Records Inserted in Tags: " + totalCount);
        }

        return true;
    }

}
