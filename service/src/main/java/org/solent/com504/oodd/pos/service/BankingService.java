/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.service;

import java.util.ArrayList;
import java.util.List;
import org.solent.com504.oodd.pos.model.dto.Card;
import org.solent.com504.oodd.pos.model.dto.TransactionRequest;
import org.solent.com504.oodd.pos.model.dto.TransactionResponse;
import org.solent.com504.oodd.pos.model.service.IBankRestClient;
import org.solent.com504.oodd.pos.model.service.IBankingService;
import org.solent.com504.oodd.pos.model.service.Transaction;

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
        IBankRestClient client = ServiceObjectFactory.getBankClient();
               
        TransactionRequest request = new TransactionRequest(fromCard, toCard, amount);
        
        TransactionResponse response = client.transferMoney(request, apiUsername, apiPassword);
        
        Transaction transaction = new Transaction(request, response);
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public Transaction RefundTransaction(Transaction transaction) {
        IBankRestClient client = ServiceObjectFactory.getBankClient();
        
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
