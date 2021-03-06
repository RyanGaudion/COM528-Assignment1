/*
 * Copyright 2021 rgaud.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.solent.oodd.password.test;


import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.oodd.password.PasswordUtils;


/**
 *
 * @author gallenc
 * @author rgaudion
 * This test class is responsible for testing the PasswordUtils which 
 * hashes passwords and checks String to hashes
 */
public class PasswordUtilsTest {

    final static Logger logger = LogManager.getLogger(PasswordUtilsTest.class);
    
    /**
     * This test will create a password hash and then check multiple passwords
     * against this hash - testing for both successful and unsuccessful checks
     */
    @Test
    public void testPasswordUtils(){
        
        String TEST_PASSWORD = "1234567890";
        String WRONG_PASSWORD="abcd";
        
        String hashed = PasswordUtils.hashPassword(TEST_PASSWORD);
        logger.debug("password=" + TEST_PASSWORD + " hashedpw=" + hashed);

        // Check that an unencrypted password matches one that has
        // previously been hashed
        if (PasswordUtils.checkPassword(TEST_PASSWORD, hashed)) {
            logger.debug("password " + TEST_PASSWORD + " matches");
        } else {
            logger.debug("password " + TEST_PASSWORD + " does not match");
        }
        assertTrue(PasswordUtils.checkPassword(TEST_PASSWORD, hashed));
        
        if (PasswordUtils.checkPassword(WRONG_PASSWORD, hashed)) {
            logger.debug("password " + TEST_PASSWORD + " matches");
        } else {
            logger.debug("password " + TEST_PASSWORD + " does not match");
        }
        assertFalse(PasswordUtils.checkPassword(WRONG_PASSWORD, hashed));

    }

   
}
