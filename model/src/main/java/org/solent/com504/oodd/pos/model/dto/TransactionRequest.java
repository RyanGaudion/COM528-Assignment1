/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.model.dto;

/**
 *
 * @author rgaud
 */
public class TransactionRequest {
    private Card fromCard = null;
    private Card toCard = null;
    private Double amount = null;

    
    public TransactionRequest(Card from, Card to, Double amount){
        fromCard = from;
        toCard = to;
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