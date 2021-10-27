/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.service.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.solent.com504.oodd.pos.model.dto.Card;
import org.solent.com504.oodd.pos.model.dto.TransactionResponse;
import org.solent.com504.oodd.pos.model.dto.TransactionStatus;
import org.solent.com504.oodd.pos.service.restClient.BankClient;

/**
 *
 * @author rgaud
 */
public class restClientTest {
    BankClient client;
    DateFormat dateFormat = new SimpleDateFormat("MMyy");
    
    @Before
    public void Before(){
        client = new BankClient("http://com528bank.ukwest.cloudapp.azure.com:8080/rest/");
    }
    
        /**
     * Performs a transaction between an valid from card and a valid to card, which should succeed.
     */
    @Test
    public void ValidTransactionTest(){

        Card fromCard = new Card();
        fromCard.SetName("Valid From Card");
        fromCard.SetCVV("111");
        fromCard.SetCardNumber("374245455400126");      
        Date expiryDate;
        try {
            expiryDate = dateFormat.parse("0523");
            fromCard.SetExpiryDate(expiryDate);
        } catch (ParseException ex) {
            Logger.getLogger(restClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        Card toCard = new Card();
        toCard.SetName("Valid To Card");
        toCard.SetCVV("222");
        toCard.SetCardNumber("5425233430109903");
        Date expiryDate2;
        try {
            expiryDate2 = dateFormat.parse("0423");
            fromCard.SetExpiryDate(expiryDate2);
        } catch (ParseException ex) {
            Logger.getLogger(restClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        TransactionResponse response =  client.TransferMoney(fromCard, toCard, 50.0);

        assertEquals (response.getStatus().toString(), TransactionStatus.SUCCESS.toString());
    }
}
