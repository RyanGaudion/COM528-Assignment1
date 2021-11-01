/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static org.junit.Assert.*;
import org.junit.Test;
import org.solent.oodd.pos.model.dto.Card;

/**
 *
 * @author rgaud
 */
public class CardTest {
    @Test
    public void setBasicCardNumberTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetCardNumber("0000 0000 0000 0000"));
        assertEquals("0000000000000000", card.GetCardNumber());
    }
    
    @Test
    public void setNameTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetName("Joe Bloggs"));
        assertEquals("Joe Bloggs", card.GetName());
    }
    
    @Test
    public void setInvalidCardNumberTest()
    {
        Card card = new Card();
        assertEquals(false, card.SetCardNumber("000000000000000"));
        assertEquals("", card.GetCardNumber());
    }
    
    @Test
    public void setCVVTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetCVV("846"));
        assertEquals("846", card.GetCVV());
    }
    
    @Test
    public void setIssueNumberTest()
    {
        Card card = new Card();
        assertEquals(true, card.setIssueNumber("02"));
        assertEquals("02", card.getIssueNumber());
    }
    
    @Test
    public void setCVV4DigitTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetCVV("8461"));
        assertEquals("8461", card.GetCVV());
    }
    
    @Test
    public void setInvalidCVVTest()
    {
        Card card = new Card();
        assertEquals(false, card.SetCVV("84615"));
        assertEquals("", card.GetCVV());
    }
    
    @Test
    public void setExpiryDateTest()
    {
        Card card = new Card();
        assertEquals(true, card.SetExpiryDate("05/21"));
        assertEquals("05/21", card.GetExpiryDateString());
    }
    
    @Test
    public void setInvalidExpiryDate(){
        Card card = new Card();
        assertEquals(false, card.SetExpiryDate("abcde123"));        
        assertEquals(false, card.SetExpiryDate("14/-0"));        
        assertEquals(false, card.SetExpiryDate("14/24"));        
        assertEquals(true, card.SetExpiryDate("11/24"));
    }
    
}