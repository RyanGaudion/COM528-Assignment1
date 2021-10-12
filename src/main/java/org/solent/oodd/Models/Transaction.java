/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.Models;

import java.util.Date;

/**
 *
 * @author rgaud
 */
public class Transaction {
    private final Card fromCard;
    private final Card toCard;
    private final Double value;
    private final TransactionType transactionType;
    private String transactionId;
    private Date transactionDate;
    private TransactionStatus transactionStatus;
    private String statusMessage;

    
    public Transaction(Card from, Card to, Double value, TransactionType type){
        this.fromCard = from;
        this.toCard = to;
        this.value = value;
        this.transactionType = type;
    }
    
    public Card GetFromCard(){
        return fromCard;
    }
    public Card GetToCard(){
        return toCard;
    }
    public Double GetValue(){
        return value;
    }
    public TransactionType GetTransactionType(){
        return transactionType;
    }
    
    public String GetTransactionId(){
        return transactionId;
    }
    
    public boolean SetTransactionId(String id){
        this.transactionId = id;
        return true;
    }
    
    public Date GetTransactionDate(){
        return transactionDate;
    }
    
    public boolean SetTransactionDate(Date date){
        this.transactionDate = date;
        return true;
    }
    
    public TransactionStatus GetTransactionStatus(){
        return transactionStatus;
    }
    
    public boolean SetTransactionStatus(TransactionStatus status){
        this.transactionStatus = status;
        return true;
    }
    
    public String GetStatusMessage(){
        return statusMessage;
    }
    
    public boolean SetStatusMessage(String statusMessage){
        this.statusMessage = statusMessage;
        return true;
    }
}


