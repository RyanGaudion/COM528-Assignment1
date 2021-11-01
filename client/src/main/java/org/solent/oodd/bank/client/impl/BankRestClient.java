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
 * The Bank REST Client is responsible for all REST calls to the Bank API and for deserialising these responses to Java Objects
 */
public class BankRestClient implements IBankRestClient {

    final static Logger LOG = LogManager.getLogger(BankRestClient.class);

    String urlStr;

    /** 
     * @param urlStr A String of the URL endpoint that the API is located at.
     */
    public BankRestClient(String urlStr) {
        this.urlStr = urlStr;
    }

    /**
     * @param request The TransactionRequest Object for the request
     * @return TransactionResponse
     * This methdo simply calls the Transaction Request endpoint for the API and returns a TransactionResponse object
     */
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

    /**
     * @param request
     * @param userName
     * @param password
     * @return TransactionResponse
     * This method performs the same function as the other transferMoney method 
     * however this method provides Http Authentication using the username and 
     * password of the ShopKeeper
     */
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
