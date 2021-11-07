/*
 * Copyright 2021 lholmes.
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
package PropertiesTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.solent.oodd.pos.dao.PropertiesDao;
import org.solent.oodd.pos.dao.DaoObjectFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PropertiesDaoTest {

    final static Logger LOG = LogManager.getLogger(PropertiesDaoTest.class);
    private File propertiesFile;
    private Properties properties = new Properties();
    
    public PropertiesDao propertiesDao = DaoObjectFactory.getPropertiesDao();
    
    public synchronized void setProperty(String propertyKey, String propertyValue) {
        properties.setProperty(propertyKey, propertyValue);
        //savePropertiesTest();
    }

    public synchronized String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    @Test
    /**
     * Find and load the default properties file. Expected: Pass. Find default
     * props file and load.
     */
    public void loadDefaultPropertiesTest() {
        InputStream input = null;
        try {
            LOG.debug("loading properties from: default");
            input = PropertiesDao.class.getClassLoader().getResourceAsStream("application.default.properties");
            properties.load(input);
            assertTrue(input != null);

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

    @Test
    /**
     * Test saving properties to temp properties file Expected: New prop values
     * saves to temp file.
     */
    public void savePropertiesTest() {
        
        OutputStream output = null;
        try {
            String url = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUrl");
            String username = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUsername");
            String password = propertiesDao.getProperty("org.solent.oodd.pos.service.apiPassword");
            String shopKeeperCard = propertiesDao.getProperty("org.solent.oodd.pos.service.shopKeeperCard");

            propertiesDao.setProperty("org.solent.oodd.pos.service.apiUrl", url);
            propertiesDao.setProperty("org.solent.oodd.pos.service.apiUsername", username);
            propertiesDao.setProperty("org.solent.oodd.pos.service.apiPassword", password);
            propertiesDao.setProperty("org.solent.oodd.pos.service.shopKeeperCard", shopKeeperCard);

            LOG.debug("saving properties to: " + propertiesFile.getAbsolutePath());

            output = new FileOutputStream(propertiesFile);
            String comments = "#Test properties file";

            properties.store(output, comments);
            //assertTrue(propertiesFile.exists());

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
}
