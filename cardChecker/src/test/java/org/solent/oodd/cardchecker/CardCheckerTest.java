/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.solent.oodd.cardchecker;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Test numbers sourced from: https://www.paypalobjects.com/en_GB/vhelp/paypalmanager_help/credit_card_numbers.htm
 * @author Steven
 */
public class CardCheckerTest {

    public static Logger logger = LogManager.getLogger(CardCheckerTest.class);
    java.util.List<java.util.Map.Entry<CardCompany, String>> cards = new java.util.ArrayList<>();
    String[] invalidCards;

    public CardCheckerTest() {
    }

    @BeforeAll
    public void setUpClass() {
        logger.debug(CardCheckerTest.class + " Setup the test");
        cards = TestResources.getCardPairs();
        this.invalidCards = new String[]{"", "abcdefghijklmno", "11111111111111", "0", "55555555abc554444"};
    }

    /**
     * Test of isValid method, of class CardChecker.
     */
    @Test
    public void testCheckValidityBadInputs() {
        logger.debug("testCheckValidityBadInputs");
        setUpClass();
        for (String cardIn : invalidCards) {
            boolean expResult = false;
            CardValidationResult result = CardChecker.checkValidity(cardIn);
            assertEquals(expResult, result.getIsValid());
        }
    }

    /**
     * Test of isValid method, of class CardChecker.
     */
    @Test
    public void testCheckValidity() {
        logger.debug("testCheckValidity");
        setUpClass();
        for (Entry<CardCompany, String> pair : cards) {
            boolean expResult = true;
            CardValidationResult result = CardChecker.checkValidity(pair.getValue());
            assertEquals(expResult, result.getIsValid());
            assertEquals(pair.getKey(), result.getCardCompany());
        }
    }
}
