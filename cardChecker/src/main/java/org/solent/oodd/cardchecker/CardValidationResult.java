/*
 * Copyright 2021 Steven Hawkins .
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
package org.solent.oodd.cardchecker;

/**
 * Class providing details of a card validation.
 * @author Steven Hawkins 5hawks48@solent.ac.uk
 */
public class CardValidationResult {
    private String message;
    private Boolean isValid;
    private CardCompany cardCompany;
    
    /**
     * Constructor to use for invalid results.
     * @param isValid true is the card is valid or false if it is not
     * @param message if the card is not valid then the message will give a reason
     */
    public CardValidationResult(Boolean isValid, String message) {
        this.cardCompany = CardCompany.UNKNOWN;
        this.message = message;
        this.isValid = isValid;
    }
    
    /**
     * Constructor to use for valid results.
     * @param message reason why the card is or is not valid
     * @param cardCompany company that the card relates to
     */
    public CardValidationResult(String message, CardCompany cardCompany) {
        this.isValid = true;
        this.message = message;
        this.cardCompany = cardCompany;
    }
    
    public CardCompany getCardCompany() {
        return this.cardCompany;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Boolean getIsValid(){
        return this.isValid;
    }     
}
