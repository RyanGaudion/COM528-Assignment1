/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
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
 * @author Steven
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
    
}
