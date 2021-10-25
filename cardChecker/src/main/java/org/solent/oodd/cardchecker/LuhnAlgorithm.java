/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.oodd.cardchecker;

/**
 * Implementation of the Luhn algorithm as described by ISO/IEC 7812-1.
 * https://en.wikipedia.org/wiki/Luhn_algorithm
 *
 * @author Steven
 */
public class LuhnAlgorithm {

    /**
     * Calculate the check digit of a card number using the Luhn algorithm.
     *
     * @param cardNo
     * @return
     */
    public static String checkLuhn(String cardNo) {
        int sum = 0;
        int parity = cardNo.length() % 2;
        for (int i = cardNo.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(cardNo.substring(i, i + 1));
            boolean checker = (i % 2 == parity); // Is true for every other digit.
            if (checker) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
        }
        int ans = (sum % 10);
        return String.valueOf(ans);
    }
}
