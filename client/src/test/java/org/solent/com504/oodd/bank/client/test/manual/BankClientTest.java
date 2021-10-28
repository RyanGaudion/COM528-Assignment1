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
import org.solent.com504.oodd.bank.client.impl.BankRestClientImpl;
import org.solent.com504.oodd.bank.model.client.BankRestClient;
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

    @Test
    public void testClient() {

        BankRestClient client = new BankRestClientImpl(bankUrl);

        Double amount = 0.0;

        TransactionResponse response = client.transferMoney(fromCard, toCard, amount);
        LOG.debug("transaction reply:" + response);

        assertEquals(TransactionStatus.SUCCESS, response.getStatus());

    }

    @Test
    public void testClientAuth() {

        BankRestClient client = new BankRestClientImpl(bankUrl);

        Double amount = 0.0;

        // testing with auth
 
        TransactionResponse response = client.transferMoney(fromCard, toCard, amount, toUsername, toPassword);
        LOG.debug("transaction with auth reply:" + response);
        
        assertEquals(TransactionStatus.SUCCESS, response.getStatus());

    }
}
