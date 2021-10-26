/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.solent.oodd.cardchecker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Steven
 */
public class CardValidationResultTest {
    public static Logger logger = LogManager.getLogger(CardValidationResult.class);
    
    public CardValidationResultTest() {
    }
    
    /**
     * Test of getError method, of class CardValidationResult.
     */
    @Test
    public void testGetError() {
        logger.debug("getError");
        CardValidationResult instance = new CardValidationResult(false, "error msg");
        String expResult = "error msg";
        String result = instance.getError();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsValid method, of class CardValidationResult.
     */
    @Test
    public void testGetIsValid() {
        logger.debug("getIsValid");
        CardValidationResult instance = new CardValidationResult(false, "error msg");;
        Boolean expResult = false;
        Boolean result = instance.getIsValid();
        assertEquals(expResult, result);
    }
    
}
