/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.pos.model.dto;

/**
 *
 * @author rgaud
 */
public class TransactionRequest {
    private Card fromCard;
    private Card toCard;
    private Double amount;

    public TransactionRequest(){
        
    }
    
    public TransactionRequest(Card from, Card to, Double amount){
        fromCard = from;
        toCard = to;
        this.amount = amount;
    }
    
    public boolean setFromCard(Card card){
        this.fromCard = card;
        return true;
    }
    
    public boolean setToCard(Card card){
        this.toCard = card;
        return true;
    }
    
    public boolean setAmount(double amount){
        this.amount = amount;
        return true;
    }
    
    public Card getFromCard(){
        return fromCard;
    }
    public Card getToCard(){
        return toCard;
    }
    public Double getAmount(){
        return amount;
    }
    @Override
    public String toString() {
        return "TransactionRequest{" + "fromCard=" + fromCard + ", toCard=" + toCard + ", amount=" + amount + '}';
    }
}
