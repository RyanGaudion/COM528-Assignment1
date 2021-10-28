/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.service;

import org.solent.com504.oodd.pos.model.service.IBankingService;

/**
 *
 * @author rgaudion
 */
public class ServiceObjectFactory {
    
    private static IBankingService bankingService;
    
    private ServiceObjectFactory(){
        
    }
    
    public static IBankingService getBankingService(){
        if(bankingService == null){
            synchronized (ServiceObjectFactory.class) {
                if(bankingService == null){
                    bankingService = new BankingService();
                }
            }
        }
        return bankingService;
    }        
}
