package org.solent.com504.oodd.pos.model.service;

import org.solent.com504.oodd.pos.model.dto.*;


public interface IBankRestClient {

    public  TransactionResponse transferMoney(TransactionRequest request);

    public  TransactionResponse transferMoney(TransactionRequest request, String userName, String password);
}
