/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.model.dto;

import java.util.Date;

/**
 *
 * @author rgaud
 */
public class TransactionResponse {
    private int code;
    private String message;
    private TransactionStatus status;
    private String fromCardNo;
    private String toCardNo;
    private String transactionId; //Could be a GUID
    private Date transactionDate;

    
    public TransactionResponse(){
        
    }
    
    //Get Methods
    public int getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
    public TransactionStatus getStatus(){
        return status;
    }
    public String getFromCardNo(){
        return fromCardNo;
    }
    public String getToCardNo(){
        return toCardNo;
    }
    public String getTransactionId(){
        return transactionId;
    }
    public Date getTransactionDate(){
        return transactionDate;
    }
    
    //Set Methods
    public Boolean setCode(int code){
        //Check Code is 3 digits
        if(String.valueOf(code).length() == 3){
            this.code = code;
            return true;
        }
        return false;
    }
    
    public Boolean setMessage(String message){
        this.message = message;
        return true;
    }
    public Boolean setStatus(TransactionStatus status){
        this.status = status;
        return true;
    }
    public Boolean setFromCardNo(String fromCardNo){
        this.fromCardNo = fromCardNo;
        return true;
    }
    public Boolean setToCardNo(String toCardNo){
        this.toCardNo = toCardNo;
        return true;
    }
    public Boolean setTransactionId(String transactionId){
        this.transactionId = transactionId;
        return true;
    }
    public Boolean setTransactionDate(Date date){
        this.transactionDate = date;
        return true;
    } 
}
