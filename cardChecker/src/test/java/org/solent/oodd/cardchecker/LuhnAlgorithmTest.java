/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.solent.oodd.cardchecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Steven
 */
public class LuhnAlgorithmTest {
    public static Logger logger = LogManager.getLogger(LuhnAlgorithmTest.class);
    String[] cardList;

    public LuhnAlgorithmTest() {
    }

    @BeforeEach
    public void setUp() {
        logger.error("Setup the test");
        this.cardList = new String[]{"378282246310005", "371449635398431", "378734493671000", "5610591081018250", "30569309025904", "38520000023237"};
    }

    /**
     * Test of checkLuhn method, of class LuhnAlgorithm.
     */
    @Test
    public void testCheckLuhn() {
        setUp();
        for (String card : cardList) {
            System.out.println("CHECKING: " + card);
            String result = LuhnAlgorithm.checkLuhn(card);
            assertEquals("0", result);
        }
    }

}
