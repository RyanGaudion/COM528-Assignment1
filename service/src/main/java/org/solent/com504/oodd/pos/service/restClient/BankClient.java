/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.service.restClient;


import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.solent.com504.oodd.pos.model.dto.*;

/**
 *
 * @author rgaud
 */
public class BankClient {

    static final Logger logger = LogManager.getLogger(BankClient.class.getName());

    String urlStr;

    public BankClient(String urlStr) {
        this.urlStr = urlStr;
    }

    public TransactionResponse TransferMoney(Card fromCard, Card toCard, Double amount) {
        logger.info("transfer hit");

        // sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        // allows client to decode json
        client.register(JacksonJsonProvider.class);

        WebTarget webTarget = client.target(urlStr).path("/transactionRequest");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        TransactionRequest transactionRequest = new TransactionRequest(fromCard, toCard, amount);
        Response response = invocationBuilder.post(Entity.entity(transactionRequest, MediaType.APPLICATION_JSON));

        TransactionResponse transactionResponse = response.readEntity(TransactionResponse.class);

        logger.info("Response: " + response.getStatus() + " TransactionResponse: " + transactionResponse);

        return transactionResponse;

    }
}