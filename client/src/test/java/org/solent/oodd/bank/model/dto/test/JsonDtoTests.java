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

    /**
     * This test method tests that the TransactionRequest to JsonString and back
     * It First uses objectMapper to write the request as a Json String
     * It then uses objectMapper to deserialise this string and read it as a TransactionRequest
     */
    @Test
    public void testJsonMessages() throws JsonProcessingException {        
        
        Card fromCard = new Card();
        fromCard.setCardNumber("4444444499997777");
        Card toCard = new Card();
        toCard.setCVV("3344");
        toCard.setCardNumber("2222333399996666");
        Double amount = 100.01;
        
        TransactionRequest request = new TransactionRequest(fromCard, toCard, amount);
        
        String requestString = objectMapper.writeValueAsString(request);

        LOG.debug("Transaction Request String: \n" + requestString);

        TransactionRequest transactionRequestResult = objectMapper.readValue(requestString, TransactionRequest.class);
        
        assertTrue(request.toString().equals(transactionRequestResult.toString()));
    }
}
