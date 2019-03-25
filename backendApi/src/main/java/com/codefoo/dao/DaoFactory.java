package com.codefoo.dao;

import com.codefoo.exception.AppException;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    private static DaoFactory daoFactory = new DaoFactory();
    private static Connection connection;

    public static DaoFactory getInstance() throws AppException {
        connection = ConnectionFactory.getInstance().getConnection();

        return daoFactory;
    }

    public ContentDao getContentDao() throws AppException {
        try {
            return new ContentDaoImpl(connection);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    public TagDao getTagDao() throws AppException {
        try {
            return new TagDaoImpl(connection);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    public AuthorDao getAuthorDao() throws AppException {
        try {
            return new AuthorDaoImpl(connection);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    public ThumbnailDao getThumbnailDao() throws AppException {
        try {
            return new ThumbnailDaoImpl(connection);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}
