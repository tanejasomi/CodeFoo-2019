package com.codefoo.service;

import com.codefoo.dao.*;
import com.codefoo.exception.AppException;
import com.codefoo.model.Author;
import com.codefoo.model.Content;
import com.codefoo.model.Tag;
import com.codefoo.model.Thumbnail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppService {
    private ContentDao contentDao;
    private TagDao tagDao;
    private ThumbnailDao thumbnailDao;
    private AuthorDao authorDao;


    public AppService() throws AppException {
        this.contentDao = DaoFactory.getInstance().getContentDao();
        this.tagDao = DaoFactory.getInstance().getTagDao();
        this.authorDao = DaoFactory.getInstance().getAuthorDao();
        this.thumbnailDao = DaoFactory.getInstance().getThumbnailDao();
    }

    public Content getContentById(String contentId) throws SQLException {
        Content content = contentDao.getContent(contentId);
        return content;
    }

    public boolean insertContents(List<Content> contents) {
        // inserting content into content table
        contentDao.insertContent(contents);

        // inserting tags into tags table
        List<Tag> allTags = new ArrayList<Tag>();
        for (Content content : contents) {
            allTags.addAll(content.getTags());
        }
        tagDao.insertTags(allTags);

        // inserting authors into authors table
        List<Author> allAuthors = new ArrayList<Author>();
        for (Content content : contents) {
            allAuthors.addAll(content.getAuthors());
        }
        authorDao.insertAuthors(allAuthors);

        // inserting Thumbnail into Thumbnail table
        List<Thumbnail> allThumbnails = new ArrayList<Thumbnail>();
        for (Content content : contents) {
            allThumbnails.addAll(content.getThumbnails());
        }
        thumbnailDao.insertThumbnail(allThumbnails);

        return true;

    }

    public List<Content> getContentsByAuthors(List<String> tags) {
        return null;
    }

    public List<Content> getContentsByTags(List<String> tags) throws SQLException {

        return tagDao.getContentByTag(tags.get(0));
        // getContentByTag;
    }

    public boolean updateContents(List<Content> contents) {
        return true;
    }

    public boolean updateOnlyTags(Content contentId, List<Tag> tags) {

        return true;
    }

    public boolean updateOnlyAuthor(Content contentId, List<Tag> authors) {

        return true;
    }

    public boolean createTable() {
        return contentDao.createTables();
    }

}
