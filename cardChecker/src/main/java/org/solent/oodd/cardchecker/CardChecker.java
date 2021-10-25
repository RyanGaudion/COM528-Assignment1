/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.oodd.cardchecker;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Check the validity of a card number in accordance with:
 * - The Luhn algorithm https://en.wikipedia.org/wiki/Luhn_algorithm
 * - PAN number https://en.wikipedia.org/wiki/Payment_card_number
 * @author 5hawks48@solent.ac.uk
 */
public class CardChecker {
    public static Logger logger = LogManager.getLogger(CardChecker.class);
    
    /**
     * Checks the validity of a given card number.
     * @param cardInput
     * @return CardValidationResult informing on pass/fail of check.
     */
    public static CardValidationResult checkValidity(final String cardInput) {
        logger.error("This is a log4j message from : "+ CardChecker.class);
        
        /**
         * Null values should not be accepted.
         */
        if (cardInput == null) {
            logger.error(CardChecker.class + ": Null card entered.");
            return new CardValidationResult(false, "Card cannot be null.");
        }
        
        String cardNumber = cardInput.replaceAll("[^0-9]+", ""); // Remove all non-numerics.
        /**
         * Cards are only valid between 13 & 19 digits.
         */
        if (cardInput.length() < 13 || cardInput.length() > 19) {
            logger.error(CardChecker.class + ": Bad card length.");
            return new CardValidationResult(false, "Card length fell outside of appropriate range."); 
        }
        
        return new CardValidationResult(true, "Card verified.");
    }
}
