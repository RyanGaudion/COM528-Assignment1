/*
 * Copyright 2021 rgaud.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.solent.oodd.bank.client.test.manual;

import org.solent.oodd.pos.model.dto.TransactionResponse;
import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.dto.TransactionStatus;
import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.oodd.bank.client.impl.BankRestClient;
/**
 *
 * @author kpeacock 
 * @author rgaudion
 * @author cgallen
 * This Class is responsible for testing the transaction methods of the REST Client
 */
public class BankClientTest {

    final static Logger LOG = LogManager.getLogger(BankClientTest.class);

    String bankUrl = "http://localhost:8080/bank/rest";
    Card fromCard = null;
    Card toCard = null;
    
    String toUsername=null;
    String toPassword=null;

    /*
     * Before the Tests are performed a default fromCard, toCard
     * as well as Username and password are setup
     */
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

        TransactionRequest req = new TransactionRequest(fromCard, toCard, amount);
        TransactionResponse response = client.transferMoney(req);
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
 
        TransactionRequest req = new TransactionRequest(fromCard, toCard, amount);
        TransactionResponse response = client.transferMoney(req, toUsername, toPassword);
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
        TransactionRequest req = new TransactionRequest(invalidFromCard, toCard, 50.0);
        
        TransactionResponse response =  client.transferMoney(req);

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
        TransactionRequest req = new TransactionRequest(fromCard, invalidToCard, 50.0);
        
        TransactionResponse response =  client.transferMoney(req);

        assertEquals (response.getStatus().toString(), "FAIL");
    }
    
    /**
     * Performs a transaction between 2 valid credit cards of an invalid amount (-50.0) , which should fail.
     */
    @Test
    public void InvalidTransactionAmountTest(){
        BankRestClient client = new BankRestClient(bankUrl);
        
        TransactionRequest req = new TransactionRequest(fromCard, toCard, -50.0);
        TransactionResponse response =  client.transferMoney(req);

        assertEquals (response.getStatus().toString(), "FAIL");
    }

}
