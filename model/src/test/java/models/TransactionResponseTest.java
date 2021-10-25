/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import org.solent.com504.oodd.pos.model.dto.TransactionResponse;
import org.solent.com504.oodd.pos.model.dto.TransactionStatus;

/**
 *
 * @author rgaud
 */
public class TransactionResponseTest {
    @Test
    public void ResponseCodeTest(){
        TransactionResponse response = new TransactionResponse();
        assertEquals(true, response.setCode(200));
        assertEquals(200, response.getCode());
    }
    
    @Test
    public void ResponseFromCardNoTest(){
        TransactionResponse response = new TransactionResponse();
        assertEquals(true, response.setFromCardNo("2000000000000000"));
        assertEquals("2000000000000000", response.getFromCardNo());
    }
    
    @Test
    public void ResponseToCardNoTest(){
        TransactionResponse response = new TransactionResponse();
        assertEquals(true, response.setToCardNo("4000000000000000"));
        assertEquals("4000000000000000", response.getToCardNo());
    }
    
    @Test
    public void ResponseMessageTest(){
        TransactionResponse response = new TransactionResponse();
        assertTrue(response.setMessage("Invalid Card Name Selected"));
        assertEquals("Invalid Card Name Selected", response.getMessage());
    }
    
    @Test
    public void ResponseStatus(){
        TransactionResponse response = new TransactionResponse();
        assertTrue(response.setStatus(TransactionStatus.SUCCESS));
        assertEquals(TransactionStatus.SUCCESS, response.getStatus());
        
        assertTrue(response.setStatus(TransactionStatus.FAIL));
        assertEquals(TransactionStatus.FAIL, response.getStatus());
    }
    
    @Test
    public void ResponseDate(){
        TransactionResponse response = new TransactionResponse();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
        Date date;
        try {
            date = parser.parse("2021-05-24-08-14");
            assertTrue(response.setTransactionDate(date));
            assertEquals("2021-05-24-08-14", parser.format(response.getTransactionDate()));
        } catch (ParseException ex) {
            Assert.fail("string formatter failed");
        }
        
    }
    
    @Test
    public void ResponseId(){
        TransactionResponse response = new TransactionResponse();
        assertTrue(response.setTransactionId("abcdefg1234"));
        assertEquals("abcdefg1234", response.getTransactionId());
        
    }
    
}
