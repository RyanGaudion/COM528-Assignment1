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
    
    public CardValidationResult(Boolean isValid, String error) {
        this.message = error;
        this.isValid = isValid;
    }
    
    public String getError() {
        return this.message;
    }
    
    public Boolean getIsValid(){
        return this.isValid;
    }
    
    
}
