/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.oodd.cardchecker;

/**
 *
 * @author Steven
 */
public class CardValidationResult {
    private String message;
    private Boolean isValid;
    private CardCompany cardCompany;
    
    public CardValidationResult(Boolean isValid, String message) {
        this.cardCompany = CardCompany.UNKNOWN;
        this.message = message;
        this.isValid = isValid;
    }
    
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
