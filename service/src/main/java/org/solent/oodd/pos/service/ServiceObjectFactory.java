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
package org.solent.oodd.pos.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.oodd.pos.model.service.IBankingService;

/**
 * This service object factory is responsible for getting a single Banking Service object to
 * use throughout the application
 * @author rgaudion
 */
public class ServiceObjectFactory {
    
    private static IBankingService bankingService;
    
    final static Logger LOG = LogManager.getLogger(ServiceObjectFactory.class);

    private ServiceObjectFactory(){
        
    }
    
    /**
     * Gets a single Banking Service (as singleton). 
     * Is thread safe as it uses a synchronized lock. 
     * Creates the banking service if 1 doesn't exists
     * @return the singleton banking service
     */
    public static IBankingService getBankingService(){
        LOG.debug("getBankingService Called");
        if(bankingService == null){
            synchronized (ServiceObjectFactory.class) {
                if(bankingService == null){
                    LOG.debug("ServiceObjectFactory created new banking service");
                    bankingService = new BankingService();
                }
            }
        }
        LOG.debug("ServiceObjectFactory returned banking service");
        return bankingService;
    }        
}
