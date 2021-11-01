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
package org.solent.oodd.pos.model.dto;

import java.text.ParseException;
import java.time.YearMonth;  
import java.time.format.DateTimeFormatter;  
import java.time.DateTimeException;

/**
 *
 * @author rgaud
 */
public class Card {
    private String cardNumber = "";
    private String cvv = "";
    private String expiryDate = "";
    private String name = "";
    private String issueNumber = "";
    
    
    //Get Methods
    public String GetCardNumber(){
        return cardNumber;
    }
    public String GetCVV(){
        return cvv;
    }
    
    public String GetName(){
        return name;
    }
    
    public String GetExpiryDateString(){
        return expiryDate;
    }
    
    //Set Methods
    public Boolean SetCardNumber(String CardNumber){
        //Removes all whitespace from String
        CardNumber = CardNumber.replaceAll("\\s+","");
        //Valdiates for a 16 digit card number
        if(CardNumber.length() == 16){
            this.cardNumber = CardNumber;
            return true;
        }
        return false;
    }
    
    public Boolean SetName(String Name){
        this.name = Name;
        return true;
    }
    
    public Boolean SetCVV(String Cvv){
        //Vallidates for a 3 or 4 digit CVV number
        if(Cvv.length() == 3 || Cvv.length() == 4){
            this.cvv = Cvv;
            return true;
        }
        return false;
    }
    
    //MM/yy
    public Boolean SetExpiryDate(String endDate){        
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth date = YearMonth.parse(endDate, formatter);
            
            if(date.getYear() < 1000){
                throw new ParseException("invalid date string", 0);
            }
            //If Parse was successful
            this.expiryDate = endDate;
            return true;
            
        }
        catch(ParseException ex){
            return false;
        }
        catch(DateTimeException ex){
            return false;
        }
    }
    
    public String getIssueNumber() {
        return issueNumber;
    }

    public boolean setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
        return true;
    }
    
    @Override
    public String toString() {
        return "Card{" + "name=" + name + ", expiryDate=" + expiryDate + ", cardNumber=" + cardNumber + ", cvv=NOT PRINTED" + '}';
    }
}
