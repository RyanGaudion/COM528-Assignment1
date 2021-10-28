/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.factory;

import org.solent.com504.oodd.bank.client.impl.BankRestClient;
import org.solent.com504.oodd.pos.model.service.IBankRestClient;

/**
 *
 * @author rgaud
 */
public class ClientObjectFactory {
    private static IBankRestClient bankClient;
        
    private  ClientObjectFactory(){

    }
    
    public static IBankRestClient getBankClient(){

        if (bankClient == null) {
            synchronized (ClientObjectFactory.class) {
                if (bankClient == null) {
                    // creates a single instance of the dao
                    bankClient = new BankRestClient("url");
                }
            }
        }
        return bankClient;
    }
}
