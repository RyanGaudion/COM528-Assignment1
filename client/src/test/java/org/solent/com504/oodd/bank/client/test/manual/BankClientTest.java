/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.client.test.manual;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.oodd.bank.client.impl.BankRestClient;
import org.solent.com504.oodd.pos.model.dto.*;
/**
 *
 * @author cgallen
 */
public class BankClientTest {

    final static Logger LOG = LogManager.getLogger(BankClientTest.class);

    String bankUrl = "http://localhost:8080/bank/rest";
    Card fromCard = null;
    Card toCard = null;
    
    String toUsername=null;
    String toPassword=null;

    @Before
    public void before() {
        fromCard = new Card();
        fromCard.SetCardNumber("5133880000000012");
        fromCard.SetCVV("123");
        fromCard.SetExpiryDate("11/21");
        fromCard.SetName("test user1");

        toCard = new Card();
        toCard.SetCardNumber("4285860000000021");
        toCard.SetCVV("123");
        toCard.SetExpiryDate("11/21");
        toCard.SetName("test user2");
        
        toUsername = "testuser2";
        toPassword = "defaulttestpass";
    }

    /**
     * Performs a valid transaction without authentication
     */
    @Test
    public void testClient() {

        BankRestClient client = new BankRestClient(bankUrl);

        Double amount = 0.0;

        TransactionResponse response = client.transferMoney(fromCard, toCard, amount);
        LOG.debug("transaction reply:" + response);

        assertEquals(TransactionStatus.SUCCESS, response.getStatus());

    }

    /**
     * Performs a valid transaction using authentication of username and password
     */
    @Test
    public void testClientAuth() {

        BankRestClient client = new BankRestClient(bankUrl);

        Double amount = 0.0;

        // testing with auth
 
        TransactionResponse response = client.transferMoney(fromCard, toCard, amount, toUsername, toPassword);
        LOG.debug("transaction with auth reply:" + response);
        
        assertEquals(TransactionStatus.SUCCESS, response.getStatus());

    }
    
    /**
     * Performs a transaction between an invalid from card and a valid to card, which should fail.
     */
    @Test
    public void invalidFromCardTest(){
        Card invalidFromCard = new Card();
        invalidFromCard.SetName("Invalid From Card");
        invalidFromCard.SetCVV("989");
        invalidFromCard.SetCardNumber("invalid");
        invalidFromCard.SetExpiryDate("23/43");

        BankRestClient client = new BankRestClient(bankUrl);
        
        TransactionResponse response =  client.transferMoney(invalidFromCard, toCard, 50.0);

        assertEquals (response.getStatus().toString(), "FAIL");
    }
    
    /**
     * Performs a transaction between a valid from card and an invalid to card, which should fail.
     */
    @Test
    public void invalidToCardTest(){
        Card invalidToCard = new Card();
        invalidToCard.SetName("Invalid To Card");
        invalidToCard.SetCVV("989");
        invalidToCard.SetCardNumber("invalid");
        invalidToCard.SetExpiryDate("11/22");
        
        BankRestClient client = new BankRestClient(bankUrl);

        TransactionResponse response =  client.transferMoney(fromCard, invalidToCard, 50.0);

        assertEquals (response.getStatus().toString(), "FAIL");
    }
    
    /**
     * Performs a transaction between 2 valid credit cards of an invalid amount (-50.0) , which should fail.
     */
    @Test
    public void InvalidTransactionAmountTest(){
        BankRestClient client = new BankRestClient(bankUrl);

        TransactionResponse response =  client.transferMoney(fromCard, toCard, -50.0);

        assertEquals (response.getStatus().toString(), "FAIL");
    }

}
