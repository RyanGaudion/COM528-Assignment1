/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.model.dto;

import java.util.Date;

/**
 *
 * @author rgaud
 */
public class Transaction {
    private final Card fromCard;
    private final Card toCard;
    private final Double amount;

    
    public Transaction(Card from, Card to, Double amount){
        this.fromCard = from;
        this.toCard = to;
        this.amount = amount;
    }
    
    public Card GetFromCard(){
        return fromCard;
    }
    public Card GetToCard(){
        return toCard;
    }
    public Double GetAmount(){
        return amount;
    }
}
