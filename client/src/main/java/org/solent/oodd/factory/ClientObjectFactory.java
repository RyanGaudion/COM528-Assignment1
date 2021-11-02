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
package org.solent.oodd.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.oodd.bank.client.impl.BankRestClient;
import org.solent.oodd.pointofsalesdevice.dao.DaoObjectFactory;
import org.solent.oodd.pos.model.service.IBankRestClient;

/**
 *
 * @author rgaud
 * The Client Object Factory is responsible for creating the BankClient as a Singleton
 */
public class ClientObjectFactory {
    final static Logger LOG = LogManager.getLogger(ClientObjectFactory.class);

    private static IBankRestClient bankClient;
        
    private  ClientObjectFactory(){

    }
    
    /**
     * @return IBankRestClient
     * This method creates a new IBankRestClient if one doesn't exist 
     * or returns the current one if one exits
     */
    public static IBankRestClient getBankClient(){
        LOG.debug("getBankClient Called");
        if (bankClient == null) {
            synchronized (ClientObjectFactory.class) {
                if (bankClient == null) {
                    // TODO - creates a single instance of the dao
                    LOG.debug("ClientObjectFactory created new client");
                    bankClient = new BankRestClient(DaoObjectFactory.getPropertiesDao().getProperty("org.solent.oodd.pos.service.apiUrl"));
                }
            }
        }
        LOG.debug("ClientObjectFactory returned client");
        return bankClient;
    }
}
