/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.oodd.bank.client.impl;

import org.solent.oodd.pos.model.dto.TransactionRequest;
import org.solent.oodd.pos.model.dto.TransactionResponse;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.solent.oodd.pos.model.service.IBankRestClient;

/**
 *
 * @author rgaudion 
 * @author cgallen
 */
public class BankRestClient implements IBankRestClient {

    final static Logger LOG = LogManager.getLogger(BankRestClient.class);

    String urlStr;

    public BankRestClient(String urlStr) {
        this.urlStr = urlStr;
    }

    @Override
    public TransactionResponse transferMoney(TransactionRequest request) {
        LOG.debug("transferMoney called: ");

        // sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        // allows client to decode json
        client.register(JacksonJsonProvider.class);

        WebTarget webTarget = client.target(urlStr).path("/transactionRequest");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(request, MediaType.APPLICATION_JSON));

        TransactionResponse transactionReplyMessage = response.readEntity(TransactionResponse.class);

        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + transactionReplyMessage);

        return transactionReplyMessage;

    }

    @Override
    public TransactionResponse transferMoney(TransactionRequest request, String userName, String password) {
        LOG.debug("transferMoney called: ");

        // sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        // basic authentication
        HttpAuthenticationFeature basicAuthfeature = HttpAuthenticationFeature.basic(userName, password);
        client.register(basicAuthfeature);
        
        
        // allows client to decode json
        client.register(JacksonJsonProvider.class);
        WebTarget webTarget = client.target(urlStr).path("/transactionRequest");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(request, MediaType.APPLICATION_JSON));

        TransactionResponse transactionReplyMessage = response.readEntity(TransactionResponse.class);

        LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + transactionReplyMessage);

        return transactionReplyMessage;

    }
}
