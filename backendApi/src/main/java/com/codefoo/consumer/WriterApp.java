package com.codefoo.consumer;

import com.codefoo.dao.ContentDaoImpl;
import com.codefoo.exception.AppException;
import com.codefoo.model.Content;
import com.codefoo.service.AppService;
import com.codefoo.util.FileUtils;
import org.apache.log4j.Logger;

import java.util.List;

/***
 * This is sample starting point to read data from input file and write to table.
 *
 * Input file location, commit size, user name and password are extracted from conf.yml file.
 *
 * High level Implementation Architecture is
 *
 *    Consumer -> Service layer -> DAO -> database
 *
 *
 */

public class WriterApp {
    final static Logger LOG = Logger.getLogger(ContentDaoImpl.class);

    public static void main(String[] args) {
        try {

            AppService contentService = new AppService();

            boolean tableExists = contentService.createTable();
            if (tableExists) {
                LOG.info("Table are created or exists already");
            }

            // Read the data and convert into Content Object to send to DAO layer.
            List<Content> contents = FileUtils.readFileAndConvertToCount();

            // Dao layer will be call internally in insertContents which will store data into Table.
            contentService.insertContents(contents);
        } catch (AppException e) {
            LOG.error(e);
        }


    }
}
