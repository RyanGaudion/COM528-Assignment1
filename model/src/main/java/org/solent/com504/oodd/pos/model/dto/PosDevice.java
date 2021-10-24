/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rgaud
 */
public class PosDevice {
    private Card deviceCard;
    private List<Transaction> transactions = new ArrayList<Transaction>();
    
    public Card GetCard(){
        return deviceCard;
    }
    public Boolean SetCard(Card card){
        deviceCard = card;
        return true;
    }

    public List<Transaction> GetTransactions(){
        return transactions;
    }

    public Boolean AddTransaction(Transaction transaction){
        if(transaction != null){
            transactions.add(transaction);
            return true;
        }
        return false;        
    }
}
