package com.codefoo.dao;

import com.codefoo.model.Author;

import java.sql.SQLException;
import java.util.List;
/******************************************************
 *
 */

public interface AuthorDao {
    public List<String> getAuthorForContentId(String contentId) throws SQLException;

    public boolean insertAuthors(List<Author> authors);

}
