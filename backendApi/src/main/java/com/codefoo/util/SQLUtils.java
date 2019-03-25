package com.codefoo.util;

public class SQLUtils {

    public static final String SELECT_CONTENT_BY_ID = "SELECT C.*, GROUP_CONCAT(DISTINCT A.Author) as authors, GROUP_CONCAT(DISTINCT TAG) as tags, GROUP_CONCAT(DISTINCT TH.URL)  as url, GROUP_CONCAT(DISTINCT TH.size)  as size, GROUP_CONCAT(DISTINCT TH.height)  as height, GROUP_CONCAT(DISTINCT TH.width)  as width " +
            "FROM CONTENTS C  " +
            "LEFT JOIN TAGS T ON C.CONTENT_ID = T.CONTENT_ID " +
            "LEFT JOIN AUTHORS A ON C.CONTENT_ID = A.CONTENT_ID " +
            "LEFT JOIN THUMBNAILS TH ON C.CONTENT_ID = TH.CONTENT_ID " +
            "WHERE C.CONTENT_ID = ?";

    public static final String SELECT_TAGS_BY_CONTENT_ID = "SELECT tag FROM TAGS WHERE content_id =?";

    public static final String INSERT_INTO_CONTENTS = "INSERT INTO CONTENTS(content_id, content_type, title, headline, description, publish_date, slug, state, duration, video_series)" +
            " VALUES(?,?,?,?,?,?,?,?,?,?)";

    public static final String INSERT_INTO_TAGS = "INSERT INTO TAGS(content_id, tag)" +
            " VALUES(?,?)";

    public static final String INSERT_INTO_AUTHORS = "INSERT INTO AUTHORS(content_id, author)" +
            "  VALUES(?,?)";

    public static final String INSERT_INTO_THUMBNAILS = "INSERT INTO THUMBNAILS(content_id, url, size, width, height)" +
            "  VALUES(?,?,?,?,?)";

    public static final String SELECT_CONTENT_BY_TAGS = "SELECT C.*, GROUP_CONCAT(DISTINCT A.Author) as authors, GROUP_CONCAT(DISTINCT TAG) as tags, GROUP_CONCAT(DISTINCT TH.URL)  as url, GROUP_CONCAT(DISTINCT TH.size)  as size, GROUP_CONCAT(DISTINCT TH.height)  as height, GROUP_CONCAT(DISTINCT TH.width)  as width " +
            "FROM CONTENTS C  " +
            "LEFT JOIN TAGS T ON C.CONTENT_ID = T.CONTENT_ID " +
            "LEFT JOIN AUTHORS A ON C.CONTENT_ID = A.CONTENT_ID " +
            "LEFT JOIN THUMBNAILS TH ON C.CONTENT_ID = TH.CONTENT_ID " +
            "WHERE T.tag = ?";
}