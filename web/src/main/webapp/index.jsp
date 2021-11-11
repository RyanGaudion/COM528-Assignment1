<%-- 
    Document   : indexjsp
    Created on : 21 Oct 2021, 07:52:36
    Updated on : 02 Nov 2021, 12:40:00
    Authors     : rgaudion, rpriest
--%>
<%@page import="org.solent.oodd.cardchecker.CardValidationResult"%>
<%@page import="org.solent.oodd.cardchecker.CardChecker"%>
<%@page import="com.fasterxml.jackson.databind.exc.InvalidFormatException"%>
<%@page import="org.solent.oodd.pos.model.service.Transaction"%>
<%@page import="org.solent.oodd.pos.model.service.IBankingService"%>
<%@page import="org.solent.oodd.pos.web.WebObjectFactory"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.*"%>;
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.oodd.pos.model.dto.Card"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="org.solent.oodd.pos.dao.DaoObjectFactory"%>
<%@page import="org.solent.oodd.pos.dao.PropertiesDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%
    PropertiesDao propertiesDao = DaoObjectFactory.getPropertiesDao();
    Logger LOG = LogManager.getLogger();    
    LOG.debug("Index page");
        
    String userresponse = request.getParameter("userResponse");
    ArrayList<String> actionHistory = (ArrayList<String>)session.getAttribute("actionHistory");
    //actionHistory.clear();
    if(userresponse == null){
        userresponse = "";
    }
    else{
        actionHistory.add(userresponse);
    }
    
    if(actionHistory == null){
        actionHistory = new ArrayList<String>();
    }
    
    LOG.debug("User response: " + userresponse + "actionHistory: " + actionHistory);
    
    String padText = "Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
    session.setAttribute("actionHistory", actionHistory);
    
    //Check Properties File
    String url = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUrl");
    String username = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUsername");
    String password = propertiesDao.getProperty("org.solent.oodd.pos.service.apiPassword");
    String shopKeeperCard = propertiesDao.getProperty("org.solent.oodd.pos.service.shopKeeperCard");    


    String[] strArray = {url, username, password, shopKeeperCard};
    List<String> propList = Arrays.asList(strArray);

    for(String val : propList){
        if(val == null || val == "" || val.isEmpty()){
            LOG.debug("One or more value in the properties file is blank");
            padText = "1 or more of your properties is not setup correctly - please delete your properties file or head to the properties page to fix. \n \n" + padText;
        }
        else{
            LOG.debug(val);
        }
    }
    
    
    try{
        //First Level Deep
        if(actionHistory.size() > 0){
            LOG.debug("size is greater than 0");
            if("1".equals(actionHistory.get(0))){
                //New Transaction --> Amount
                if(actionHistory.size() == 1){
                    LOG.debug("1st Menu = Transaction Amount");
                    padText = "Please enter a transaction amount";
                }
                //Amount --> Card Number
                else if(actionHistory.size() == 2){
                    LOG.debug("2nd Menu = Card Number");
                    String amountValue = actionHistory.get(1);
                    try{
                        LOG.debug("2nd Menu = Valid Transaction Amount");
                        double d = Double.parseDouble(amountValue);
                        padText = "Please Enter Card Number";
                    }
                    catch(NumberFormatException ex){
                        LOG.debug("2nd Menu = Invalid Transaction Amount");
                        actionHistory.remove(1);
                        padText = "Transaction Amount not in the valid format of Double";
                    }
                }
                //Card Number --> Expiry Date
                else if(actionHistory.size() == 3){
                    String cardNumber = actionHistory.get(2);
                    LOG.debug("3rd Menu = Expiry Date");
                    Card tmpCard = new Card();
                    if(!tmpCard.setCardnumber(cardNumber)){
                        actionHistory.remove(2);
                        LOG.debug("3rd Menu = Invalid Card Number");
                        padText = "Invalid Card Number - Please Try Again";
                    }
                    else{
                        //Card Checker
                        CardValidationResult validationResult = CardChecker.checkValidity(cardNumber);
                        //Card Checker Valid
                        if(validationResult.getIsValid()){
                            padText = "Card Number Validated as: " + validationResult.getCardCompany() +  "\n Please enter the expiry Date in format 'MMyy'";
                            LOG.debug("3rd Menu = Valid Card Number");
                        }
                        //Card Chceker Invalid
                        else{
                            padText = "Invalid Card Number: " + validationResult.getMessage()+  "\n Please try again";
                            LOG.debug("3rd Menu = Invalid Card Number");
                            actionHistory.remove(2);
                        }

                    }
                }
                //Expiry Date --> IssueNumber
                else if(actionHistory.size() == 4){
                    String expiryDateS = actionHistory.get(3);
                    LOG.debug("4th Menu = Issue Number");
                    Card tmpCard = new Card();
                    //Add / into dateString
                    try{
                        expiryDateS = expiryDateS.substring(0, 2) + "/" + expiryDateS.substring(2);
                        if(!tmpCard.setEndDate(expiryDateS)){
                            actionHistory.remove(3);
                            LOG.debug("4th Menu = Invalid Expiry Date");
                            padText = "Invalid Expiry Date - Please enter in the format 'MMyy'";
                        }
                        else{
                            LOG.debug("4th Menu = Valid Expiry Date");
                            padText = "Please enter the card issue number";
                        }
                    }
                    catch(StringIndexOutOfBoundsException ex){
                        LOG.debug("4th Menu = Invalid Expiry Date");
                        actionHistory.remove(3);
                        padText = "Invalid Expiry Date - Please enter in the format 'MMyy'";
                    }

                }
                //Issue Number --> CVV
                else if(actionHistory.size() == 5){
                    LOG.debug("5th Menu - CVV");
                    String issueNumber = actionHistory.get(4);
                    if(issueNumber != null && issueNumber.length() > 0){
                        padText = "Please enter the card CVV number";
                        LOG.debug("5th Menu - Valid Issue Number");
                    }
                    else{
                        actionHistory.remove(4);
                        LOG.debug("5th Menu - Invalid Issue Number");
                        padText = "Invalid Issue Number - Please try again";
                    }
                }
                //CVV --> Complete
                else if(actionHistory.size() == 6){
                    LOG.debug("6th Menu - Complete");
                    String cvv = actionHistory.get(5);
                    Card tmpCard = new Card();
                    if(!tmpCard.setCVV(cvv)){
                        actionHistory.remove(5);
                        LOG.debug("6th Menu - Invalid CVV");
                        padText = "Invalid CVV - Please enter the CVV with length of 3 or 4 digits";
                    }
                    //Complete Transactions
                    else{
                        LOG.debug("6th Menu - Valid CVV");
                        padText = "Complete Transaction";
                        //Setup Transaction
                        Card fromCard = new Card();
                        fromCard.setCVV(actionHistory.get(5));
                        fromCard.setIssueNumber(actionHistory.get(4));
                        //Need to add forward slash to endDate
                        if(actionHistory.get(3).length() > 3){
                            String expiryDateS = actionHistory.get(3).substring(0, 2) + "/" + actionHistory.get(3).substring(2);
                            fromCard.setEndDate(expiryDateS);                      
                        }
                        fromCard.setCardnumber(actionHistory.get(2));
                        Double amount = Double.parseDouble(actionHistory.get(1));

                        //Try to send Transaction
                        try{
                            LOG.debug("6th Menu - Try Transaction");
                            IBankingService bankingService = WebObjectFactory.getBankingService();
                            Transaction transaction = bankingService.sendTransaction(fromCard, amount);
                            //Transaction Success
                            if("SUCCESS".equals(transaction.getTransactionResponse().getStatus())){
                                LOG.debug("6th Menu - Transaction Success");
                                padText = "Successful Transaction \n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                                actionHistory.clear();
                            }
                            //Transaction Fail
                            else{
                                LOG.debug("6th Menu - Transaction Failed: " + transaction.getTransactionResponse().getMessage());
                                if(transaction.getTransactionResponse().getMessage() == null){
                                    padText = "Transaction Failed: "  + "Please ensure both the shopkeeper's and the customer's Card are correct" + "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";

                                }
                                else{
                                    padText = "Transaction Failed: "  + transaction.getTransactionResponse().getMessage() + "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";

                                }
                                actionHistory.clear();
                            }
                        }
                        //Transaction didn't return from API
                        catch(Exception ex){
                            LOG.error(ex);
                            padText = "Transaction Failed: Please ensure you have the correct settings in the app proeprties file and then restart the app" + "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                            actionHistory.clear();
                        }


                    }
                }


            }
            //Refund Transaction
            else if ("2".equals(actionHistory.get(0))){
                //List Refund
                if(actionHistory.size() == 1){
                    LOG.debug("1st Menu = List Refund");
                    padText = "Please select an transaction to refund: ";
                    IBankingService bankingService = WebObjectFactory.getBankingService();
                    List<Transaction> transactions = bankingService.getLatestSuccessfulTransactions();
                    LOG.debug("1st Menu = Refunds Available: " + transactions.size());
                    //Transactions Available to Refund
                    if(transactions.size() > 0){
                        LOG.debug("1st Menu = Refunds Available");
                        for (int i = 0; i < transactions.size(); i++) {
                            Transaction transaction = transactions.get(i);
                            String CardNumber = transaction.getTransactionRequest().getFromCard().getCardnumber();
                            Double Amount = transaction.getTransactionRequest().getAmount();
                            padText = padText + "\n " + i + " - from: " + CardNumber + " - amount: " + Amount;
                        }
                    }
                    //No Transactions to Refund
                    else{
                        LOG.debug("1st Menu = No Refunds Available");
                        padText = "No Transaction History to Refund" + "\n Press 1 for a new Transaction,  2 to refund a transaction or 3 to validate a card";
                        actionHistory.clear();
                    }               
                }
                //Select Refund
                else if(actionHistory.size() == 2){
                    LOG.debug("2nd Menu = Select Refund");
                    IBankingService bankingService = WebObjectFactory.getBankingService();
                    List<Transaction> transactions = bankingService.getLatestSuccessfulTransactions();
                    int transactionInt;
                    try {
                        //Get selected transaction
                        transactionInt = Integer.parseInt(actionHistory.get(1));
                        LOG.debug("2nd Menu = Selected: " + transactionInt);
                        Transaction transactionToRefund = transactions.get(transactionInt);
                        LOG.debug("2nd Menu = Selected from: " + transactionToRefund.getTransactionRequest().getFromCard() + "Amount: " + transactionToRefund.getTransactionRequest().getAmount());
                        try{
                            //Try to refund
                            Transaction refundTransaction = bankingService.refundTransaction(transactionToRefund);

                            //Refund Success
                            if("SUCCESS".equals(refundTransaction.getTransactionResponse().getStatus())){
                                LOG.debug("2nd Menu = Refund Success");
                                padText = "Successful Refund \n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                                actionHistory.clear();
                                //return;
                            }

                            //Refund Failure 
                            else{
                                LOG.debug("2nd Menu = Refund Failed: " + refundTransaction.getTransactionResponse().getMessage());
                                padText = "Refund Failed: "  + refundTransaction.getTransactionResponse().getMessage() + "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                                actionHistory.clear();
                            }
                       }
                        //Catch unknown refund failure
                        catch(Exception ex){
                            LOG.error(ex);
                            padText = "Transaction Failed: Please ensure you have the correct settings in the app proeprties file and then restart the app" + "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                            actionHistory.clear();
                        }

                    }
                    //Catch invalid input exception
                    catch (NumberFormatException e)
                    {
                        LOG.debug("2nd Menu = Invalid ID");
                        actionHistory.remove(1);
                        padText = "Invalid ID - Please select an transaction to refund: ";
                        if(transactions.size() > 0){
                            for (int i = 0; i < transactions.size(); i++) {
                                Transaction transaction = transactions.get(i);
                                String CardNumber = transaction.getTransactionRequest().getFromCard().getCardnumber();
                                Double Amount = transaction.getTransactionRequest().getAmount();
                                padText = padText + "\n " + i + " - from: " + CardNumber + " - amount: " + Amount;
                            }
                        }             
                    }
                    catch(IndexOutOfBoundsException ex){
                        LOG.debug("2nd Menu = Index out of range");
                        actionHistory.remove(1);
                        padText = "Invalid ID - Please select an transaction to refund: ";
                        if(transactions.size() > 0){
                            for (int i = 0; i < transactions.size(); i++) {
                                Transaction transaction = transactions.get(i);
                                String CardNumber = transaction.getTransactionRequest().getFromCard().getCardnumber();
                                Double Amount = transaction.getTransactionRequest().getAmount();
                                padText = padText + "\n " + i + " - from: " + CardNumber + " - amount: " + Amount;
                            }
                        }
                    }
                }

            }
            //Check Luhn Code
            else if("3".equals(actionHistory.get(0))){
                LOG.debug("Check Luhn Code");
                if(actionHistory.size() == 1){
                    LOG.debug("1st Menu = Enter Card to Check");
                    padText = "Please select a Card Number to check ";
                }
                //Select Refund
                else if(actionHistory.size() == 2){
                    //Check Luhn Code
                    String cardNumber = actionHistory.get(1);
                    LOG.debug("2nd Menu = Card to check: " + cardNumber);
                    CardValidationResult validationResult = CardChecker.checkValidity(cardNumber);
                    if(validationResult == null){
                        LOG.debug("2nd Menu = ValidationResult is null");
                        padText = "Unable to validate card "+ "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                    }
                    else if(!validationResult.getIsValid()){
                        LOG.debug("2nd Menu = ValidationResult is invalid");
                        padText = "Invalid Card: \n"+ validationResult.getMessage() + "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                    }
                    else{
                        LOG.debug("2nd Menu = ValidationResult is valid");
                        padText = "Card is a valid and the company is: " + validationResult.getCardCompany().toString() + "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
                    }
                    actionHistory.clear();                
                }
            }


            //Not Option 1 or 2 or 3
            else{
                LOG.debug("1st Menu - Invalid Option");
                actionHistory.clear();
            }
        }
        else{
            LOG.debug("Action History is empty");
            session.setAttribute("actionHistory", new ArrayList<String>());
        } 
    }
    catch(Exception ex){
        LOG.error("Unknown Error - Restarting Stat", ex);
        padText = "Unknown Error - Restarting System Stat"+ "\n Press 1 for a new Transaction, 2 to refund a transaction or 3 to validate a card";
        IBankingService bankingService = WebObjectFactory.getBankingService();
        bankingService.clearTransactions();
        session.setAttribute("actionHistory", new ArrayList<String>());
    }
      

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POS Device</title>
        
        <!-- index CSS -->
        <link rel="stylesheet" type="text/css" href="public/styles/index.css">
        
    </head>
    <body>
        <form action="./index.jsp" method="get">
            <div id="div-container">
                <div id="div-pininput">
                    <div id="div-userfeedback">
                        <p>Menu System</p>
                        <p><%= padText %></p>
                    </div>
                    <input id="txtpininput" type="text" name="userResponse" value="">
                </div>
                <div id="div-pinpad">
                    
                </div>
                </div>
            <div id="div-cmd">
                <div id="btncancel" class="btn-style">Clear</img></div>
                <div id="btnundo" class="btn-style">Undo</div>
                <button id="btnconfirm" class="btn-style-conf" type="submit">Confirm</button>
            </div>
        </form>
        <!-- page script -->
        <script src="public/scripts/index.js"></script>
    </body>
</html>
