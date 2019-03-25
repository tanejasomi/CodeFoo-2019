package com.codefoo.consumer;
import com.codefoo.exception.AppException;
import com.codefoo.model.Content;
import com.codefoo.service.AppService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryApp {
    final static Logger LOG = Logger.getLogger(QueryApp.class);

    /***
     * @Author Somya Taneja
     * This is sample starting point to retrieve saved data.
     * Input file location, commit size, user name and password are extracted from conf.yml file.
     *
     * High level Implementation Architecture is
     *
     *    Consumer -> Service layer -> DAO -> database
     */

    public static void main(String[] args) {
        try {

            String inputContentId = "5c52a611e4b0c3a735a33c72";

            // Get content by id:
            AppService service = new AppService();
            Content contentById = service.getContentById(inputContentId);
            LOG.info("Returned Content Id" + contentById.toString());

            List<String> queryTags = new ArrayList<String>();
            queryTags.add("action");
            queryTags.add("news");

            // Get content by tags:
            List<Content> contentsByTags = service.getContentsByTags(queryTags);

            LOG.info(contentsByTags.size() + " number of contents found by tag: " + queryTags);

        } catch (AppException e) {
            LOG.error(e);
        } catch (SQLException e) {
            LOG.error(e);
        }

    }
}