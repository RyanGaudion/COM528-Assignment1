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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

/**
 *
 * @author rgaud
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
