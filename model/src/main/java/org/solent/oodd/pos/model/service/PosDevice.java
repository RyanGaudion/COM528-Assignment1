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
package org.solent.oodd.pos.model.service;

import java.util.ArrayList;
import java.util.List;
import org.solent.oodd.pos.model.dto.Card;

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
