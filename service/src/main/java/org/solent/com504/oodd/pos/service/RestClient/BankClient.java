/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.service.RestClient;

import java.util.logging.Level;
import javax.ws.rs.GET;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.solent.com504.oodd.pos.model.dto.Card;
import org.solent.com504.oodd.pos.model.dto.TransactionResponse;
/**
 *
 * @author Kain
 */
public class BankClient {
    
    static final Logger LOG = LogManager.getLogger(BankClient.class.getName());
   
    /**
     * Sends a GET request to the bank service, requesting a transaction to take place.
     * 
     * @param fromCard  the card to transfer money from
     * @param toCard    the card to transfer money to   
     * @param transferAmount    the amount of funds to transfer
     * @return the response from the server after requesting the transfer
     */
    @GET
    public TransactionResponse TransferMoney(Card fromCard, Card toCard, Double transferAmount)
    {
        String urlStr =  "http://com528bank.ukwest.cloudapp.azure.com:8080/";
        LOG.info("Commencing transaction of £"+transferAmount + " from " + fromCard.GetName() + " to " + toCard.GetName());
        
        // create new logging client for get request 
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));
        
        
        
        
        //decode the json response
        client.register(JacksonJsonProvider.class);
        //send get request
        WebTarget webTarget = client.target(urlStr).path("/rest/api-v1/transferMoney").queryParam("fromCard", fromCard, "toCard", toCard, "amount", transferAmount);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        //get response
        Response response = invocationBuilder.get();
        TransactionResponse transactionResponse = response.readEntity(TransactionResponse.class);
               
        return transactionResponse;
    }
    
    /**
     * Gets the transaction history of a particular card.
     * @param card  the card to find the transaction history of 
     * @return  the response from the server - a list of past transactions
     */
    @GET
    public TransactionList GetTransactions(Card card){
        String urlStr =  "http://com528bank.ukwest.cloudapp.azure.com:8080/";
        LOG.info("Getting transaction history for card "+card.GetCardNumber());
        
        // create new logging client for get request 
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000))); 
        
        //decode the json response
        client.register(JacksonJsonProvider.class);
        //send get request
        WebTarget webTarget = client.target(urlStr).path("/rest/api-v1/findBankTransactionsFromCreditCardNumber").queryParam("card", card.GetCardNumber());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        //get response
        Response response = invocationBuilder.get();
        TransactionList transactionList = response.readEntity(TransactionList.class);
               
        return transactionList;
        
    }
}
