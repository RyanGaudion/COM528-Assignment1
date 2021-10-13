package org.solent.oodd.Models;

import java.util.ArrayList;

public class PosDevice {
    private Card deviceCard;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Card GetCard(){
        return deviceCard;
    }
    public Boolean SetCard(Card card){
        deviceCard = card;
        return true;
    }

    public ArrayList<Transaction> GetTransactions(){
        return transactions;
    }

    public Boolean AddTransaction(Transaction transaction){
        return transactions.add(transaction);
    }
}
