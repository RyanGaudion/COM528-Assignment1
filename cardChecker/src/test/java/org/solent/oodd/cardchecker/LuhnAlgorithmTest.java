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

/**
 *
 * @author Steven
 */
public class LuhnAlgorithmTest {
    
    public LuhnAlgorithmTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkLuhn method, of class LuhnAlgorithm.
     */
    @Test
    public void testCheckLuhn() {
        System.out.println("checkLuhn");
        String cardNo = "79927398713";
        String expResult = "0";
        String result = LuhnAlgorithm.checkLuhn(cardNo);
        assertEquals(expResult, result);
        result = LuhnAlgorithm.checkLuhn("5500005555555559");
        assertEquals(expResult, result);
    }
    
}
