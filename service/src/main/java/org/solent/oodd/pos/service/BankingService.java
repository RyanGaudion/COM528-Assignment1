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
package org.solent.oodd.pos.service;

import java.util.ArrayList;
import java.util.List;
import org.solent.oodd.factory.ClientObjectFactory;
import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.solent.oodd.pos.model.dto.TransactionResponse;
import org.solent.oodd.pos.model.service.IBankRestClient;
import org.solent.oodd.pos.model.service.IBankingService;
import org.solent.oodd.pos.model.service.Transaction;

/**
 *
 * @author rgaud
 */
public class BankingService implements IBankingService{

    private final String apiUsername;
    private final String apiPassword;
    private final List<Transaction> transactions = new ArrayList();
    
    
    public BankingService(){
        //TODO - Change to Properties File
        apiUsername = "";
        apiPassword = "";
    }
    
    
    @Override
    public Transaction SendTransaction(Card fromCard, Card toCard, Double amount) {
        IBankRestClient client = ClientObjectFactory.getBankClient();
               
        TransactionRequest request = new TransactionRequest(fromCard, toCard, amount);
        
        TransactionResponse response = client.transferMoney(request, apiUsername, apiPassword);
        
        Transaction transaction = new Transaction(request, response);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public Transaction RefundTransaction(Transaction transaction) {
        IBankRestClient client = ClientObjectFactory.getBankClient();
        
        Card fromCard = transaction.getTransactionRequest().getToCard();        
        Card toCard = transaction.getTransactionRequest().getFromCard();
        Double amount = transaction.getTransactionRequest().getAmount();
        
        TransactionRequest request = new TransactionRequest(fromCard, toCard, amount);
        
        TransactionResponse response = client.transferMoney(request, apiUsername, apiPassword);
        
        Transaction refundTransaction = new Transaction(request, response);
        transactions.add(refundTransaction);
        return refundTransaction;
        
    }
    
    @Override
    public List<Transaction> GetLatestTransactions(){
        //Returns either all the transactions or the last 9 - whichever is smallest
        List<Transaction> latestTransactions = transactions.subList(transactions.size()- Math.min(transactions.size(), 9), transactions.size());
        return latestTransactions;
    }
    
}
