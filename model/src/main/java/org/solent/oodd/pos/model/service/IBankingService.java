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

import org.solent.oodd.pos.model.dto.Card;
import java.util.List;

/**
 *
 * @author rgaud
 */
public interface IBankingService {    
    //Returns Transactionresponse but merges this with request inside of the method
    public Transaction SendTransaction(Card fromCard, Card toCard, Double amount);

    //Automatically creates a request with the negative number of the transaction.getresponse.getamount
    //Also checks to see if the transaction to refund was successful in the first place before trying to refund it
    public Transaction RefundTransaction(Transaction transaction);
    
    List<Transaction> GetLatestTransactions();

}
