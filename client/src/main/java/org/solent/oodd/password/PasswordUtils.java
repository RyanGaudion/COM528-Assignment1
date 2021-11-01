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
package org.solent.oodd.password;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gallenc
 * This utility is responsible for hashing a password using BCrypt 
 * as well as checking a password against a hash
 */
public class PasswordUtils {
    
    final static Logger LOG = LogManager.getLogger(PasswordUtils.class);

    /**
     * @param password The password as a String that should be hashed
     * @return String - The BCrypt hash of the password String
     */
    public static String hashPassword(String password){
        LOG.debug("Hash Password hit");
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    
    /**
     * @param password The Password that needs to be checked
     * @param hashed The Hash to check the password against
     * @return True if the passwords match
     */
    public static boolean checkPassword(String password, String hashed){
        LOG.debug("Check Password hit");
        return BCrypt.checkpw(password, hashed);
    }
    
}
