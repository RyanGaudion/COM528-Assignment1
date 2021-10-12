/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.Models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author rgaud
 */
public class TransactionTests {
    private Transaction testTransaction; 
    
    private void SetTestTransaction(){
        Card toCard = new Card();
        assertEquals(true, toCard.SetCardNumber("0000 0000 0000 0010"));
        assertEquals(true,toCard.SetCVV("003"));
        
        Card fromCard = new Card();
        assertEquals(true, fromCard.SetCardNumber("0000 0000 0000 1020"));
        assertEquals(true, fromCard.SetCVV("019"));
        
        Double amount = 10.54;
        
        testTransaction = new Transaction(fromCard, toCard, amount, TransactionType.PAYMENT);
    }
    
    @Test
    public void constructorTest()
    {
        SetTestTransaction();
        
        assertEquals("0000000000000010", testTransaction.GetToCard().GetCardNumber());        
        assertEquals("003", testTransaction.GetToCard().GetCVV());        
        
        assertEquals("0000000000001020", testTransaction.GetFromCard().GetCardNumber());        
        assertEquals("019", testTransaction.GetFromCard().GetCVV());

        assertEquals(10.54, testTransaction.GetValue(), 0);           
        assertEquals(TransactionType.PAYMENT, testTransaction.GetTransactionType());        
    }
    
    @Test
    public void SetTransactionIdTest(){
        SetTestTransaction();
        
        assertEquals(true, testTransaction.SetTransactionId("aedf45-42"));
        assertEquals("aedf45-42", testTransaction.GetTransactionId());
    }
    
    @Test
    public void SetTransactionDateTest(){
        SetTestTransaction();
        
        try{
            String pattern = "hh:mm yyyy-MM-dd";
            SimpleDateFormat parser = new SimpleDateFormat(pattern);
            Date date = parser.parse("08:35 2021-10-12");
            assertEquals(true, testTransaction.SetTransactionDate(date));
            //dow mon dd hh:mm:ss zzz yyyy
            

            DateFormat df = new SimpleDateFormat(pattern);
            assertEquals("08:35 2021-10-12", df.format(testTransaction.GetTransactionDate()));
        }
        catch(ParseException ex){
            Assert.fail("string formatter failed");
        }
    }
    @Test
    public void SetTransactionStatusTest(){
        SetTestTransaction();
        
        assertEquals(true, testTransaction.SetTransactionStatus(TransactionStatus.SUCCESS));
        assertEquals(TransactionStatus.SUCCESS, testTransaction.GetTransactionStatus());
    }
    
    @Test
    public void SetStatusMessageTest(){
        SetTestTransaction();
        
        assertEquals(true, testTransaction.SetStatusMessage("Error due to this and that"));
        assertEquals("Error due to this and that", testTransaction.GetStatusMessage());
    }
}
