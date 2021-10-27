/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.pos.service.restClient;


import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
    final Logger logger;
    final String urlStr;
    
    public BankClient(String bankURL){
        urlStr = bankURL;
        logger = Logger.getLogger(BankClient.class.getName());
    }
    
    
    public TransactionResponse TransferMoney(TransactionRequest request){
        // sets up logging for the client       
        Client client = ClientBuilder.newClient(new ClientConfig().register(
                new LoggingFeature(java.util.logging.Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000)));

        // allows client to decode json
        client.register(JacksonJsonProvider.class);

        WebTarget webTarget = client.target(urlStr).path("/transactionRequest");
        logger.log(Level.INFO, webTarget.getUri().toString());
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(request);

        TransactionResponse replyMessage = response.readEntity(TransactionResponse.class);
        //LOG.debug("Response status=" + response.getStatus() + " ReplyMessage: " + replyMessage);

        //return replyMessage.getCardValidationResult().isValid();
        return replyMessage;
    }
}
