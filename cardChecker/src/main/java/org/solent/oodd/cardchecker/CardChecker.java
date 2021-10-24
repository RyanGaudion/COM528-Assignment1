/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.oodd.cardchecker;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 *
 * @author Steven
 */
public class CardChecker {
    public static Logger logger = LogManager.getLogger(CardChecker.class);
    
    /**
     * Checks the validity of a given card number.
     * @param cardIn
     * @return boolean where true = valid and false = invalid.
     */
    public static boolean isValid(final String cardIn) {
        logger.trace("This is a log4j message from : "+ CardChecker.class);
        return false;
    }
}
