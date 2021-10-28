/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.service;

import org.solent.com504.oodd.bank.client.impl.BankRestClient;
import org.solent.com504.oodd.pos.model.service.IBankRestClient;


/**
 *
 * @author rgaudion
 */
public class ServiceObjectFactory {
    
    private static IBankRestClient bankClient;
    
    private ServiceObjectFactory(){
        
    }
    
    public static IBankRestClient getBankClient(){
        
        if (bankClient == null) {
            synchronized (ServiceObjectFactory.class) {
                if (bankClient == null) {
                    // creates a single instance of the dao
                    bankClient = new BankRestClient("url");
                }
            }
        }
        return bankClient;
    }

    
}
