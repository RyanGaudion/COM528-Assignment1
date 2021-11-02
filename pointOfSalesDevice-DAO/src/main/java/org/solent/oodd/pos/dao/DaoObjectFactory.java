/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.pos.dao;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author rgaud
 */
public class DaoObjectFactory {
    final static Logger LOG = LogManager.getLogger(DaoObjectFactory.class);

    private static PropertiesDao propertiesDao = null;

    public static PropertiesDao getPropertiesDao() {
        if (propertiesDao == null) {
            synchronized (DaoObjectFactory.class) {
                if (propertiesDao == null) {
                    // creates a singleton of the dao
                    String tempDir = System.getProperty("java.io.tmpdir");
                    File propertiesFile = new File(tempDir + "/application.properties");
                    LOG.debug("using system temp directory: " + tempDir);
                    LOG.debug("using application properties file : " + propertiesFile.getAbsolutePath());
                    propertiesDao = new PropertiesDao(propertiesFile.getAbsolutePath());
                }
            }
        }
        return propertiesDao;
    }
}
