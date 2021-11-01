/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.pos.service;

import org.solent.oodd.pos.model.service.IBankingService;

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
