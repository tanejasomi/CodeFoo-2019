package com.codefoo.dao;

import com.codefoo.model.Thumbnail;

import java.sql.SQLException;
import java.util.List;

public interface ThumbnailDao {
    public List<String> getThumbnailForContentId(String contentId) throws SQLException;

    public boolean insertThumbnail(List<Thumbnail> thumbnails);

}
