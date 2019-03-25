package com.codefoo.dao;

import com.codefoo.model.Content;

import java.sql.SQLException;
import java.util.List;

public interface ContentDao {

    Content getContent(String id) throws SQLException;

    Content getContentByTitle(String title) throws SQLException;

    Content getContentsByTags(List<String> tags);

    Content getContentByAuthor(String author);

    boolean insertContent(List<Content> contents);

    boolean updateContent();

    boolean createTables();
}
