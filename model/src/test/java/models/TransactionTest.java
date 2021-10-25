/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.solent.com504.oodd.pos.model.dto.*;

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
        assertEquals("0002000200020002", transaction.getTransactionRequest().GetToCard().GetCardNumber());
    }
    
    @Test
    public void transactionRequest(){
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));        
        assertEquals(true, card.SetCVV("343"));

        Card card2 = new Card();
        assertEquals(true, card2.SetCardNumber("0002 0002 0002 0002"));        
        assertEquals(true, card2.SetCVV("3334"));
        TransactionRequest req = new TransactionRequest(card, card2, 100.00);
        //req.SetFromCard(card);
        
        assertEquals(100.00, req.GetAmount(), 0.0);
        assertEquals(card2, req.GetToCard());        
        assertEquals(card, req.GetFromCard());
        assertEquals("3334", req.GetToCard().GetCVV());        
        assertEquals("343", req.GetFromCard().GetCVV());
        assertEquals("0000000000000000", req.GetFromCard().GetCardNumber());        
        assertEquals("0002000200020002", req.GetToCard().GetCardNumber());


        
    }
}
