package org.solent.oodd.pos.model.service;

import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.solent.oodd.pos.model.dto.TransactionResponse;


public interface IBankRestClient {

    public  TransactionResponse transferMoney(TransactionRequest request);

    public  TransactionResponse transferMoney(TransactionRequest request, String userName, String password);
}
