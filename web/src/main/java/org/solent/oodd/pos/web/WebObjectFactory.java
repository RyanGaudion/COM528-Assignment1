/*
 * Copyright 2021 rgaud.
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
package org.solent.oodd.pos.web;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.oodd.pos.dao.DaoObjectFactory;
import org.solent.oodd.pos.dao.PropertiesDao;
import org.solent.oodd.pos.model.service.IBankingService;
import org.solent.oodd.pos.service.ServiceObjectFactory;

/**
 *
 * @author rgaudion
 */
public class WebObjectFactory {

    // cannot instantiate
    private WebObjectFactory() {

    }

    final static Logger logger = LogManager.getLogger(WebObjectFactory.class);

    public static IBankingService getBankingService() {
        return ServiceObjectFactory.getBankingService();
    }
    
    public static PropertiesDao getPropertiesDao(){
        return DaoObjectFactory.getPropertiesDao();
    }

}
