package org.solent.com504.oodd.bank.model.client;

import org.solent.com504.oodd.pos.model.dto.*;


public interface IBankRestClient {

    public  TransactionResponse transferMoney(Card fromCard, Card toCard, Double amount);

    public  TransactionResponse transferMoney(Card fromCard, Card toCard, Double amount, String userName, String password);
}
