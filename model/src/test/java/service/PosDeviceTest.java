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

import static org.junit.Assert.*;
import org.junit.Test;
import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.service.PosDevice;
import org.solent.oodd.pos.model.service.Transaction;
import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.solent.oodd.pos.model.dto.TransactionResponse;


/**
 *
 * @author rgaud
 */
public class PosDeviceTest {
    @Test
    public void setCardTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));
        assertEquals(true, card.SetCVV("111"));

        PosDevice pos = new PosDevice();
        assertEquals(true, pos.SetCard(card));

        assertEquals("0000000000000000", pos.GetCard().GetCardNumber());
        assertEquals("111", pos.GetCard().GetCVV());
    }
    
    @Test
    public void addTransactionTest()
    {
        Card toCard = new Card();
        assertEquals(true, toCard.SetCardNumber("0000 0000 0000 0000"));

        Card fromCard = new Card();
        assertEquals(true, fromCard.SetCardNumber("1111 1111 1111 1111"));

        TransactionRequest tr = new TransactionRequest(fromCard, toCard, 5.00);
        TransactionRequest tr2 = new TransactionRequest(fromCard, toCard, -5.00);

        Transaction t = new Transaction();
        t.setTransactionRequest(tr);
        
        Transaction t2 = new Transaction();
        t.setTransactionRequest(tr2);
        
        PosDevice pos = new PosDevice();
        assertEquals(true, pos.AddTransaction(t));
        assertEquals(true, pos.AddTransaction(t2));


        assertEquals(2, pos.GetTransactions().size());

        assertEquals(true, pos.GetTransactions().contains(t));
        assertEquals(true, pos.GetTransactions().contains(t2));
    }
}
