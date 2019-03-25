package com.codefoo.dao;

import com.codefoo.model.Content;
import com.codefoo.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagDao {
    public List<String> getTagsForContentId(String contentId) throws SQLException;

    public boolean insertTags(List<Tag> tags);

    public List<Content> getContentByTag(String tags) throws SQLException;

}
