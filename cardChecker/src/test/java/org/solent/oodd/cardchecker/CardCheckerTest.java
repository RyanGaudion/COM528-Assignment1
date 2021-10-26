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
public class CardCheckerTest {

    public static Logger logger = LogManager.getLogger(CardCheckerTest.class);
    String[] validCards;
    String[] invalidCards;

    public CardCheckerTest() {
    }

    @BeforeAll
    public void setUpClass() {
        logger.debug("Setup the test");
        this.validCards = new String[]{"378282246310005", "371449635398431", "378734493671000",
            "5610591081018250", "30569309025904", "38520000023237", "6011111111111117",
            "6011000990139424", "3530111333300000", "3566002020360505", "5555555555554444",
            "5105105105105100", "4111111111111111", "4012888888881881", "4222222222222"};
        this.invalidCards = new String[]{"", "abcdefghijklmno", "11111111111111", "0", "55555555abc554444"};
    }

    /**
     * Test of isValid method, of class CardChecker.
     */
    @Test
    public void testIsValid() {
        logger.debug("testIsValid");
        setUpClass();
        for (String cardIn : validCards) {
            boolean expResult = true;
            CardValidationResult result = CardChecker.checkValidity(cardIn);
            assertEquals(expResult, result.getIsValid());
        }
    }

    /**
     * Test of isValid method, of class CardChecker.
     */
    @Test
    public void testIsValidBadInputs() {
        logger.debug("testIsValidBadInputs");
        setUpClass();
        for (String cardIn : invalidCards) {
            boolean expResult = false;
            CardValidationResult result = CardChecker.checkValidity(cardIn);
            assertEquals(expResult, result.getIsValid());
        }
    }
}
