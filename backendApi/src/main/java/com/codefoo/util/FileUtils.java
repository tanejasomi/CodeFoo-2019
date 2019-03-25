package com.codefoo.util;


import com.codefoo.exception.AppException;
import com.codefoo.mappings.Conf;
import com.codefoo.mappings.ConfLoader;
import com.codefoo.model.*;
import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {
    private final static Logger LOG = Logger.getLogger(FileUtils.class);
    private final static String COLON = ",";

    public static List<Content> readFileAndConvertToCount() throws AppException {
        List<Content> contents = new ArrayList<Content>();

        Conf conf = ConfLoader.getConf();
        LOG.info("Reading input file " + conf.getInputFile());

        File file = new File(conf.getInputFile());
        CSVReader csvReader = null;
        try {
            // bf = new BufferedReader(new FileReader(file));

            int counter = 0;
            String[] values;

            csvReader = new CSVReader(new FileReader(file), ',');
            //String[] nextRecord;
            csvReader.readNext();

            //while (StringUtils.isNotEmpty(line = bf.readLine())) {
            while ((values = csvReader.readNext()) != null) {
                counter++;

                if (values.length != 29) {
                    LOG.error("Number of columns in the line are not correct " + values.length);
                }

                Integer duration = null;
                if (StringUtils.isNotEmpty(values[10])) {
                    duration = Integer.parseInt(values[10]);
                }

                Date publishDate = null;
                if (StringUtils.isNotEmpty(values[6])) {
                    publishDate = convertToDate(values[6]);
                }

                Content content = new Content(values[1], ContentType.getTypeFromName(values[2]), values[3], values[4], values[5], publishDate, values[7], values[9], duration, values[11]);

                //Adding tags
                content.addTag(new Tag(values[1], values[14]));
                content.addTag(new Tag(values[1], values[15]));
                content.addTag(new Tag(values[1], values[16]));

                //Adding Author
                content.addAuthor(new Author(values[1], values[12]));
                content.addAuthor(new Author(values[1], values[13]));

                if (StringUtils.isNotEmpty(values[17])) { // skip when URL is empty
                    Float url1width = null;
                    if (StringUtils.isNotEmpty(values[19]))
                        url1width = Float.valueOf(values[19]);


                    Float url2width = null;
                    if (StringUtils.isNotEmpty(values[23]))
                        url1width = Float.valueOf(values[23]);

                    Float url3width = null;
                    if (StringUtils.isNotEmpty(values[27]))
                        url1width = Float.valueOf(values[27]);

                    //Adding thumbnail
                    content.addThumbnail(new Thumbnail(values[1], values[17], values[18], url1width, Float.valueOf(values[20])));
                    content.addThumbnail(new Thumbnail(values[1], values[21], values[22], url2width, Float.valueOf(values[24])));
                    content.addThumbnail(new Thumbnail(values[1], values[25], values[26], url3width, Float.valueOf(values[28])));
                }

                // LOG.info("  " + values[20] + " " + values[17] + " " + values[22]);
                contents.add(content);
            }
            LOG.info("Total number of line reads from input file is " + counter);

        } catch (FileNotFoundException e) {
            throw new AppException(e);
        } catch (ParseException e) {
            throw new AppException(e);
        } catch (IOException e) {
            throw new AppException(e);
        } finally {
            if (csvReader != null) {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    new AppException(e);
                }
            }
        }

        return contents;
    }


    public static Date convertToDate(String dateStr) throws ParseException {
        //2019-01-23T00:21:00+0000

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        java.util.Date date = formatter.parse(dateStr);

        return date;

    }

}
