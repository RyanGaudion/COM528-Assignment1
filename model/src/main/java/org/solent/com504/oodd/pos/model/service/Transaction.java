/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.model.service;

import org.solent.com504.oodd.pos.model.dto.TransactionRequest;
import org.solent.com504.oodd.pos.model.dto.TransactionResponse;

/**
 *
 * @author rgaud
 */
public class Transaction {
    private TransactionRequest transactionRequest;
    private TransactionResponse transactionResponse;
    
    public Transaction(){
        
    }
    
    public Transaction(TransactionRequest req, TransactionResponse response){
        this.transactionRequest = req;
        this.transactionResponse = response;
    }
    
    public TransactionRequest getTransactionRequest(){
        return transactionRequest;
    }
    
    public TransactionResponse getTransactionResponse(){
        return transactionResponse;
    }
    
    public boolean setTransactionRequest(TransactionRequest request){
        if(request != null){
            transactionRequest = request;
            return true;
        }
        return false;
    }
    
    public boolean setTransactionResponse(TransactionResponse response){
        if(response != null){
            transactionResponse = response;
            return true;
        }
        return false;
    }
}