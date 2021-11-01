/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.dto.TransactionRequest;

/**
 *
 * @author rgaud
 */
public class TransactionRequestTest {
    @Test
    public void RequestFullTest(){
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));        
        assertEquals(true, card.SetCVV("343"));

        Card card2 = new Card();
        assertEquals(true, card2.SetCardNumber("0002 0002 0002 0002"));        
        assertEquals(true, card2.SetCVV("3334"));
        TransactionRequest req = new TransactionRequest(card, card2, 100.00);
        
        assertEquals(100.00, req.getAmount(), 0.0);
        assertEquals(card2, req.getToCard());        
        assertEquals(card, req.getFromCard());
        assertEquals("3334", req.getToCard().GetCVV());        
        assertEquals("343", req.getFromCard().GetCVV());
        assertEquals("0000000000000000", req.getFromCard().GetCardNumber());        
        assertEquals("0002000200020002", req.getToCard().GetCardNumber()); 
    }
    
    @Test
    public void FromCardTest(){
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));        
        assertEquals(true, card.SetCVV("343"));
        
        TransactionRequest req = new TransactionRequest(card, new Card(), 0.0);
        assertEquals("0000000000000000", req.getFromCard().GetCardNumber());
        assertEquals("343", req.getFromCard().GetCVV());
    }
    
    @Test
    public void ToCardTest(){
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0002 0020 0002 0020"));        
        assertEquals(true, card.SetCVV("2243"));
        
        TransactionRequest req = new TransactionRequest(new Card(), card, 0.0);
        assertEquals("0002002000020020", req.getToCard().GetCardNumber());        
        assertEquals("2243", req.getToCard().GetCVV());
    }
    
    @Test
    public void AmountTest(){
        TransactionRequest req = new TransactionRequest(new Card(), new Card(), 100.0);
        assertEquals(100.0, req.getAmount(), 0.0);
    }
}
