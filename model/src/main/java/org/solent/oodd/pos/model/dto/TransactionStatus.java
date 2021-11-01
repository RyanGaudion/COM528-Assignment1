/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.pos.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author rgaud
 */
public enum TransactionStatus{
    @JsonProperty("SUCCESS")
    SUCCESS, 
    @JsonProperty("FAIL")
    FAIL
}
