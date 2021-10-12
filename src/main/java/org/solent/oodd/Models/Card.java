/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rgaud
 */
public class Card {
    private String cardNumber;
    private String cvv;
    private Date expiryDate;
    
    public String GetCardNumber(){
        return cardNumber;
    }
    public String GetCVV(){
        return cvv;
    }
    public String GetExpiryDateString(){
        if(expiryDate == null){
            return "";
        }
        else{
            DateFormat df = new SimpleDateFormat("MM/yy");
            return df.format(expiryDate);
        }
    }
    
    public Boolean SetCardNumber(String CardNumber){
        //Removes all whitespace from String
        CardNumber = CardNumber.replaceAll("\\s+","");
        if(CardNumber.length() == 16){
            this.cardNumber = CardNumber;
            return true;
        }
        return false;
    }
    public Boolean SetCVV(String Cvv){
        if(Cvv.length() == 3 || Cvv.length() == 4){
            this.cvv = Cvv;
            return true;
        }
        return false;
    }
    public Boolean SetExpiryDate(Date endDate){
        if(endDate != null){
            this.expiryDate = endDate;
            return true;
        }
        return false;
    }
    
}
