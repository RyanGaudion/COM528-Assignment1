/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.model.service;

import org.solent.com504.oodd.pos.model.dto.*;

/**
 *
 * @author rgaud
 */
public interface IBankService {    
    //Returns Transactionresponse but merges this with request inside of the method
    public Transaction SendTransaction(TransactionRequest req);

    //Automatically creates a request with the negative number of the transaction.getresponse.getamount
    //Also checks to see if the transaction to refund was successful in the first place before trying to refund it
    public Transaction RefundTransaction(Transaction transaction);

}
