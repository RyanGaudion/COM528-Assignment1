/*
 * Copyright 2021 Steven Hawkins <5hawks48@solent.ac.uk>.
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
package org.solent.oodd.cardchecker;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @author Steven Hawkins <5hawks48@solent.ac.uk>
 */
public class CardCompanyTest {
    public static Logger logger = LogManager.getLogger(CardCompanyTest.class);
    
    public CardCompanyTest() {
    }
    
    /**
     * Test of detect method, of class CardCompany.
     */
    @Test
    public void testDetect() {
        logger.debug(CardCompanyTest.class + ", CardCompany Detect test.");
        for (Map.Entry<CardCompany, String> pair : TestResources.getCardPairs()) {
            assertEquals(pair.getKey(), CardCompany.detect(pair.getValue()));
        }      
    }
    
    @Test
    public void testDetectInvalidCards() {
        logger.debug(CardCompanyTest.class + ", CardCompany Detect invalid cards test.");
        for (String card : TestResources.getInvalidCards()) {
            assertEquals(CardCompany.UNKNOWN, CardCompany.detect(card));
        }      
    }
    
    
    
}
