/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClientTests;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.solent.com504.oodd.pos.service.RestClient.BankClient;
import org.solent.com504.oodd.pos.model.dto.Card;
import org.solent.com504.oodd.pos.model.dto.TransactionResponse;
import org.solent.com504.oodd.pos.model.dto.TransactionStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author pc
 */
public class TransferTest {
    BankClient BC = new BankClient();
    DateFormat dateFormat = new SimpleDateFormat("MMyy");
    
    /**
     * Performs a transaction between an invalid from card and a valid to card, which should fail.
     */
    @Test
    public void invalidFromCardTest(){
        Card fromCard = new Card();
        fromCard.SetName("Invalid From Card");
        fromCard.SetCVV("989");
        fromCard.SetCardNumber("invalid");
        fromCard.SetExpiryDate(new Date());
        
        Card toCard = new Card();
        toCard.SetName("Valid To Card");
        toCard.SetCVV("111");
        toCard.SetCardNumber("374245455400126");      
        try{
            Date expiryDate = dateFormat.parse("0523");
            toCard.SetExpiryDate(expiryDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        
        TransactionResponse response =  BC.TransferMoney(fromCard, toCard, new Double(50));
        
        assertEquals (response.TransactionStatus.toString(), "FAIL");
    }
    
    
    /**
     * Performs a transaction between a valid from card and an invalid to card, which should fail.
     */
    @Test
    public void invalidToCardTest(){
        
        Card fromCard = new Card();
        fromCard.SetName("Valid From Card");
        fromCard.SetCVV("111");
        fromCard.SetCardNumber("374245455400126");      
        try{
            Date expiryDate = dateFormat.parse("0523");
            fromCard.SetExpiryDate(expiryDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        
        Card toCard = new Card();
        toCard.SetName("Invalid To Card");
        toCard.SetCVV("989");
        toCard.SetCardNumber("invalid");
        toCard.SetExpiryDate(new Date());
               
        TransactionResponse response =  BC.TransferMoney(fromCard, toCard, new Double(50));
        
        assertEquals (response.TransactionStatus.toString(), "FAIL");
    }
    /**
     * Performs a transaction between an valid from card and a valid to card, which should succeed.
     */
    @Test
    public void ValidTransactionTest(){
        
        Card fromCard = new Card();
        fromCard.SetName("Valid From Card");
        fromCard.SetCVV("111");
        fromCard.SetCardNumber("374245455400126");      
        try{
            Date expiryDate = dateFormat.parse("0523");
            fromCard.SetExpiryDate(expiryDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        
        Card toCard = new Card();
        toCard.SetName("Valid To Card");
        toCard.SetCVV("222");
        toCard.SetCardNumber("5425233430109903");      
        try{
            Date expiryDate = dateFormat.parse("0423");
            fromCard.SetExpiryDate(expiryDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
       
        TransactionResponse response =  BC.TransferMoney(fromCard, toCard, new Double(50));
        
        assertEquals (response.TransactionStatus.toString(), "SUCCESS");
    }
    
    /**
     * Performs a transaction between 2 valid credit cards of a sum of -50, which should fail.
     */

    @Test
    public void InvalidTransactionAmountTest(){

    Card fromCard = new Card();
    fromCard.SetName("Valid From Card");
    fromCard.SetCVV("111");
    fromCard.SetCardNumber("374245455400126");      
    try{
        Date expiryDate = dateFormat.parse("0523");
        fromCard.SetExpiryDate(expiryDate);
    }
    catch (ParseException e){
        e.printStackTrace();
    }

    Card toCard = new Card();
    toCard.SetName("Valid To Card");
    toCard.SetCVV("222");
    toCard.SetCardNumber("5425233430109903");      
    try{
        Date expiryDate = dateFormat.parse("0423");
        fromCard.SetExpiryDate(expiryDate);
    }
    catch (ParseException e){
        e.printStackTrace();
    }

    TransactionResponse response =  BC.TransferMoney(fromCard, toCard, new Double(-50));

    assertEquals (response.TransactionStatus.toString(), "FAIL");
    }
}
