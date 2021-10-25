/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.solent.com504.oodd.pos.model.dto.Card;
import org.solent.com504.oodd.pos.model.service.PosDevice;
import org.solent.com504.oodd.pos.model.service.Transaction;
import org.solent.com504.oodd.pos.model.dto.TransactionRequest;
import org.solent.com504.oodd.pos.model.dto.TransactionResponse;


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
