package org.solent.oodd.pos.model.service;

import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.solent.oodd.pos.model.dto.TransactionResponse;


/**
 * Interface for the Bank API Client
 * @author rgaud
 */
public interface IBankRestClient {

    /**
     * Transfer Money with no authentication
     * @param request the transaction request to send to the API
     * @return the Transaction response from the api
     */
    public  TransactionResponse transferMoney(TransactionRequest request);

    /**
     * Transfer money with http authentication
     * @param request the transaction request to send to the API
     * @param userName the username to authenticate with
     * @param password the password to authenticate with
     * @return the Transaction response from the api
     */
    public  TransactionResponse transferMoney(TransactionRequest request, String userName, String password);
}
