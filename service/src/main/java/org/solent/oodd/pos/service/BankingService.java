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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.oodd.factory.ClientObjectFactory;
import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.solent.oodd.pos.model.dto.TransactionResponse;
import org.solent.oodd.pos.model.service.IBankRestClient;
import org.solent.oodd.pos.model.service.IBankingService;
import org.solent.oodd.pos.model.service.Transaction;

/**
 * The Banking Service responsible for keeping track of transactions as well as accessing the Bank Rest Client
 * @author rgaud
 */
public class BankingService implements IBankingService{

    private final String apiUsername;
    private final String apiPassword;
    private final List<Transaction> transactions = new ArrayList();
    
    final static Logger LOG = LogManager.getLogger(BankingService.class);
    
    public BankingService(){
        //TODO - Change to Properties File
        apiUsername = "";
        apiPassword = "";
    }
    
    
    /**
     * Send Transaction method simply sends money from 1 card to another using the Bank API. 
     * This method implements Http Authentication
     */
    @Override
    public Transaction SendTransaction(Card fromCard, Card toCard, Double amount) {
        LOG.debug("Send Transaction from: " + fromCard.getCardNumber() + " to: " + toCard.getCardNumber() + " for: " + amount);

        IBankRestClient client = ClientObjectFactory.getBankClient();
               
        TransactionRequest request = new TransactionRequest(fromCard, toCard, amount);
        
        TransactionResponse response = client.transferMoney(request, apiUsername, apiPassword);
        
        LOG.debug("Transaction Response Status: " + response.getStatus());

        Transaction transaction = new Transaction(request, response);
        transactions.add(transaction);
        return transaction;
    }

    /**
     * Refund Transaction takes in a full Transaction object and then fetches the 
     * required information to refund the transaction
     */
    @Override
    public Transaction RefundTransaction(Transaction transaction) {
        LOG.debug("Refund Transaction from: " + transaction.getTransactionRequest().getFromCard().getCardNumber() + " to: " + transaction.getTransactionRequest().getToCard().getCardNumber() + " for: " + transaction.getTransactionRequest().getAmount());

        IBankRestClient client = ClientObjectFactory.getBankClient();
        
        Card fromCard = transaction.getTransactionRequest().getToCard();        
        Card toCard = transaction.getTransactionRequest().getFromCard();
        Double amount = transaction.getTransactionRequest().getAmount();
        
        TransactionRequest request = new TransactionRequest(fromCard, toCard, amount);
        
        TransactionResponse response = client.transferMoney(request, apiUsername, apiPassword);
        LOG.debug("Refund Response Status: " + response.getStatus());

        Transaction refundTransaction = new Transaction(request, response);
        transactions.add(refundTransaction);
        return refundTransaction;
        
    }
    
    /**
     * This uses the transactions list to get the 9 most recent transactions
     */
    @Override
    public List<Transaction> GetLatestTransactions(){
        //Returns either all the transactions or the last 9 - whichever is smallest
        LOG.debug("Get Latest Transactions: " + transactions.size());
        List<Transaction> latestTransactions = transactions.subList(transactions.size()- Math.min(transactions.size(), 9), transactions.size());
        return latestTransactions;
    }
    
}
