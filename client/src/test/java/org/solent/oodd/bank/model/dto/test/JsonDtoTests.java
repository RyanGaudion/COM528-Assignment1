/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.bank.model.dto.test;

import org.solent.oodd.pos.model.dto.Card;
import org.solent.oodd.pos.model.dto.TransactionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rgaudion 
 * @author cgallen

 */
public class JsonDtoTests {

    private static final Logger LOG = LogManager.getLogger(JsonDtoTests.class);

    ObjectMapper objectMapper = null;

    @Before
    public void before() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.findAndRegisterModules()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void testJsonMessages() throws JsonProcessingException {        
        
        Card fromCard = new Card();
        Card toCard = new Card();
        Double amount = 100.01;
        
        TransactionRequest request = new TransactionRequest(fromCard, toCard, amount);
        
        String requestString = objectMapper.writeValueAsString(request);

        LOG.debug("Transaction Request String: \n" + requestString);

        TransactionRequest transactionRequestResult = objectMapper.readValue(requestString, TransactionRequest.class);
        
        assertTrue(request.toString().equals(transactionRequestResult.toString()));
    }
}
