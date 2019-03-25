package com.codefoo.mappings;

import com.codefoo.dao.ContentDaoImpl;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class ConfLoader {

    private final static Logger LOG = Logger.getLogger(ContentDaoImpl.class);
    private final static String CONFFILE_NAME = "conf.yml";
    private static final Conf conf;

    static {
        LOG.info("Starting to load conf file");
        Yaml yaml = new Yaml();
        InputStream confStream = ConfLoader.class.getClassLoader().getResourceAsStream(CONFFILE_NAME);
        conf = yaml.loadAs(confStream, Conf.class);
        LOG.info("Conf is created with following parameter " + conf);
    }

    public static Conf getConf() {
        return conf;
    }
}
