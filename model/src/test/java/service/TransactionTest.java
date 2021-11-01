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
package service;

import org.solent.oodd.pos.model.dto.TransactionResponse;
import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.solent.oodd.pos.model.service.Transaction;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author rgaud
 */
public class TransactionTest {
    
    @Test
    public void fullTransactionTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));
        
        Card card2 = new Card();
        assertEquals(true, card2.SetCardNumber("0002 0002 0002 0002"));
        
        
        TransactionRequest req = new TransactionRequest(card, card2, 100.00);
        
        TransactionResponse response = new TransactionResponse();
        assertEquals(true, response.setCode(300));
        assertEquals(true, response.setFromCardNo(card.GetCardNumber()));
        assertEquals(true, response.setToCardNo(card2.GetCardNumber()));
        
        Transaction transaction = new Transaction();
        assertEquals(true, transaction.setTransactionRequest(req));
        assertEquals(true, transaction.setTransactionResponse(response));  
        
        assertEquals(300, transaction.getTransactionResponse().getCode());
        assertEquals("0002000200020002", transaction.getTransactionRequest().getToCard().GetCardNumber());
    }
}
