/*
 * Copyright 2021 rgaud.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
