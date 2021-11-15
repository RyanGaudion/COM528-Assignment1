/*
 * Copyright 2021 lholmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.solent.oodd.pos.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PropertiesDao {

    final static Logger LOG = LogManager.getLogger(PropertiesDao.class);

    private File propertiesFile;

    private Properties properties = new Properties();

    public PropertiesDao(String propertiesFileLocation) {
        try {
            propertiesFile = new File(propertiesFileLocation);
            if (!propertiesFile.exists()) {
                LOG.debug("properties file does not exist: loading default ");
                LOG.debug(propertiesFileLocation);
                loadDefaultProperties();
            } else {
                LOG.debug("Temp properties file found. Loading...");
                loadProperties();
            }
            LOG.debug(propertiesFileLocation);
        } catch (Exception ex) {
            LOG.error("cannot load properties", ex);
        }
    }

    // synchronized ensures changes are not made by another thread while reading
    public synchronized String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public synchronized void setProperty(String propertyKey, String propertyValue) {
        properties.setProperty(propertyKey, propertyValue);
        saveProperties();
    }

    private void saveProperties() {
        OutputStream output = null;
        try {
            LOG.debug("saving properties to: " + propertiesFile.getAbsolutePath());

            output = new FileOutputStream(propertiesFile);
            String comments = "# properties file";
            properties.store(output, comments);

        } catch (IOException ex) {
            LOG.error("cannot save properties", ex);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    private void loadProperties() {
        
        InputStream input = null;
        try {
            LOG.debug("loading properties from: " + propertiesFile.getAbsolutePath());
            input = new FileInputStream(propertiesFile);
            properties.load(input);
        } catch (IOException ex) {
            LOG.error("cannot load properties", ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
            }
        }
        
//        Properties prop = new Properties();
//        String fileName = System.getProperty("java.io.tmpdir");
//        
//        // InputStream input = null;
//        try {
//            FileInputStream fis = new FileInputStream(fileName + "/application.properties");
//            LOG.debug("loading properties from: temp");
//            // input = new FileInputStream(propertiesFile);
//            prop.load(fis);
//        } catch (IOException ex) {
//            LOG.error("cannot load properties", ex);
//        }
    }

    private void loadDefaultProperties() {
        InputStream input = null;
        try {
            LOG.debug("loading properties from: " + propertiesFile.getAbsolutePath());
            input = PropertiesDao.class.getClassLoader().getResourceAsStream("application.default.properties");
            properties.load(input);
        } catch (IOException ex) {
            LOG.error("cannot load properties", ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
            }
        }
    }

}
