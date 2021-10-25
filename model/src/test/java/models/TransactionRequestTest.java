/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.solent.com504.oodd.pos.model.dto.Card;
import org.solent.com504.oodd.pos.model.dto.TransactionRequest;

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
        
        assertEquals(100.00, req.GetAmount(), 0.0);
        assertEquals(card2, req.GetToCard());        
        assertEquals(card, req.GetFromCard());
        assertEquals("3334", req.GetToCard().GetCVV());        
        assertEquals("343", req.GetFromCard().GetCVV());
        assertEquals("0000000000000000", req.GetFromCard().GetCardNumber());        
        assertEquals("0002000200020002", req.GetToCard().GetCardNumber()); 
    }
    
    @Test
    public void FromCardTest(){
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));        
        assertEquals(true, card.SetCVV("343"));
        
        TransactionRequest req = new TransactionRequest(card, new Card(), 0.0);
        assertEquals("0000000000000000", req.GetFromCard().GetCardNumber());
        assertEquals("343", req.GetFromCard().GetCVV());
    }
    
    @Test
    public void ToCardTest(){
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0002 0020 0002 0020"));        
        assertEquals(true, card.SetCVV("2243"));
        
        TransactionRequest req = new TransactionRequest(new Card(), card, 0.0);
        assertEquals("0002002000020020", req.GetToCard().GetCardNumber());        
        assertEquals("2243", req.GetToCard().GetCVV());
    }
    
    @Test
    public void AmountTest(){
        TransactionRequest req = new TransactionRequest(new Card(), new Card(), 100.0);
        assertEquals(100.0, req.GetAmount(), 0.0);
    }
}
