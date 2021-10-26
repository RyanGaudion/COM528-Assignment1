/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.oodd.cardchecker;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Check the validity of a card number in accordance with: - The Luhn algorithm
 * https://en.wikipedia.org/wiki/Luhn_algorithm - PAN number
 * https://en.wikipedia.org/wiki/Payment_card_number
 *
 * @author 5hawks48@solent.ac.uk
 */
public class CardChecker {

    public static Logger logger = LogManager.getLogger(CardChecker.class);

    /**
     * Checks the validity of a given card number.
     *
     * @param cardInput
     * @return CardValidationResult informing on pass/fail of check.
     */
    public static CardValidationResult checkValidity(final String cardInput) {
        logger.error("This is a log4j message from : " + CardChecker.class);

        /**
         * Null values should not be accepted.
         */
        if (cardInput == null) {
            logger.error(CardChecker.class + ": Null card entered.");
            return new CardValidationResult(false, "Card cannot be null.");
        }

        /**
         * Cards are only valid between 13 & 19 digits.
         */
        String cardNumber = cardInput.replaceAll("[^0-9]+$", ""); // Remove all non-numerics.
        if (cardInput.length() < 13 || cardInput.length() > 19) {
            logger.error(CardChecker.class + ": Bad card length.");
            return new CardValidationResult(false, "Card length fell outside of appropriate range.");
        }

        if (!checkLuhn(cardNumber)) {
            return new CardValidationResult(false, cardNumber + ": Failed Luhn check");
        }

        // TODO: Get card company.
        return new CardValidationResult(true, "Card verified.");
    }

    private static int sumDigits(String cardNo, boolean ignoreLastDigit) {
        int offset = 1; // To use the full number.
        if (ignoreLastDigit == true) {
            offset = 2;
        } // To skip check digit.

        // Perform checksum calc.
        int sum = 0;
        int parity = cardNo.length() % 2;
        for (int i = cardNo.length() - offset; i >= 0; i--) {
            int digit = Integer.parseInt(cardNo.substring(i, i + 1));

            if (i % 2 == parity) { // Is true for every other digit.
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return sum;
    }

    /**
     *
     * @param cardNo
     * @return
     */
    private static boolean checkLuhn(String cardNo) {
        if (cardNo == null || cardNo.length() == 0) {
            logger.error(CardChecker.class + ": Null card entered.");
            return false;
        } else if (!cardNo.matches("[0-9]+$")) {
            logger.error(CardChecker.class + ": Non-numeric card entered.");
            return false;
        }
        int sum = sumDigits(cardNo, false);
        // Don't perform mod10 when sum = 0.
        boolean sumCheck = (sum == 0) ? false : (sum % 10 == 0);
        // Compare calculated digit with input card check digit.
        String digit = (10 - sumDigits(cardNo, true) % 10) + "";
        String checkDigit = digit.substring(digit.length() - 1);
        logger.debug("Card: " + cardNo
                + ", Sum: " + sum
                + ", Input:" + cardNo.substring(cardNo.length() - 1)
                + ", Calc:" + checkDigit);
        boolean digitCheck = cardNo.substring(cardNo.length() - 1).equals(checkDigit);
        return sumCheck && digitCheck;
    }
}
