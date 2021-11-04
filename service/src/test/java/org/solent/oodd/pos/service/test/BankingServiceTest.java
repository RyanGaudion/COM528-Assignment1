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
package org.solent.oodd.pos.service.test;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.service.IBankingService;
import org.solent.oodd.pos.model.service.Transaction;
import org.solent.oodd.pos.service.ServiceObjectFactory;

/**
 *
 * @author rgaud
 */
public class BankingServiceTest {
    
    @Test
    public void testTransaction() {        
        
        Card fromCard = new Card();
        fromCard.setCardnumber("5133880000000012");
        fromCard.setCVV("123");
        fromCard.setIssueNumber("01");
        fromCard.setEndDate("11/21");
        fromCard.setName("test user1");
        
        IBankingService bankingService = ServiceObjectFactory.getBankingService();
        
        Transaction transaction = bankingService.sendTransaction(fromCard, 1.0);
        
        assertEquals("SUCCESS", transaction.getTransactionResponse().getStatus());
    }
    
    @Test
    public void testGetTransactions(){
        testTransaction();
        IBankingService bankingService = ServiceObjectFactory.getBankingService();
        List<Transaction> transactions = bankingService.getLatestSuccessfulTransactions();
        
        assertTrue(transactions.size() > 0);
    }
    
    @Test
    public void testRefund(){
        testGetTransactions();
        IBankingService bankingService = ServiceObjectFactory.getBankingService();
        List<Transaction> transactions = bankingService.getLatestSuccessfulTransactions();
        assertTrue(transactions.size() > 0);
        Transaction refund = bankingService.refundTransaction(transactions.get(0));
        assertEquals("SUCCESS", refund.getTransactionResponse().getStatus());

    }
    
    @Test
    public void testClear(){
        testTransaction();
        
        IBankingService bankingService = ServiceObjectFactory.getBankingService();
        List<Transaction> transactions = bankingService.getLatestSuccessfulTransactions();
        assertTrue(transactions.size() > 0);
        
        assertTrue(bankingService.clearTransactions());
        List<Transaction> transactions2 = bankingService.getLatestSuccessfulTransactions();

        assertTrue(transactions2.isEmpty());
    }
}
