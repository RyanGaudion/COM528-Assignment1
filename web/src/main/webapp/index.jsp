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
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.oodd.pos.model.dto.Card"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    
    Logger LOG = LogManager.getLogger();
    // retrieve the stored users list from the session
    // List<String> users = (List<String>) session.getAttribute("users");
    //if (users == null) {
    //    users = new ArrayList<String>();
    //    session.setAttribute("users", users);
    //}

    // String cardnum = request.getParameter("cardNum");
    // String cardcvv = request.getParameter("cardCVV");
    // String amount = request.getParameter("transactionAmount");
    // String menuitem = request.getParameter("menuItem");
    
    // holds user's response
    
    
    
    LOG.error("-------");
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
    String padText = "Press 1 for a new Transaction or 2 to refund a transaction";
    
    
    LOG.error("1 " + actionHistory);    
    LOG.error("2 " + userresponse);
    session.setAttribute("actionHistory", actionHistory);
    
    //First Level Deep
    if(actionHistory.size() > 0){
        LOG.error("3 " + actionHistory.get(0));
        if("1".equals(actionHistory.get(0))){
            //New Transaction --> Amount
            if(actionHistory.size() == 1){
                LOG.error("4 " + actionHistory.get(0));
                padText = "Please enter a transaction amount";
            }
            //Amount --> Card Number
            else if(actionHistory.size() == 2){
                LOG.error("5 " + actionHistory.get(1));
                String amountValue = actionHistory.get(1);
                try{
                    double d = Double.parseDouble(amountValue);
                    padText = "Please Enter Card Number";
                }
                catch(NumberFormatException ex){
                    actionHistory.remove(1);
                    padText = "Transaction Amount not in the valid format of Double";
                }
            }
            //Card Number --> Expiry Date
            else if(actionHistory.size() == 3){
                String cardNumber = actionHistory.get(2);
                LOG.info("card number " + cardNumber);
                Card tmpCard = new Card();
                if(!tmpCard.setCardnumber(cardNumber)){
                    actionHistory.remove(2);
                    padText = "Invalid Card Number - Please Try Again";
                }
                else{
                    //Card Checker
                    CardValidationResult validationResult = CardChecker.checkValidity(cardNumber);
                    //Card Checker Valid
                    if(validationResult.getIsValid()){
                        padText = "Card Number Validated as: " + validationResult.getCardCompany() +  "\n Please enter the expiry Date in format 'MMyy'";
                    }
                    //Card Chceker Invalid
                    else{
                        padText = "Invalid Card Number: " + validationResult.getMessage()+  "\n Please try again";
                        actionHistory.remove(2);
                    }
                    
                }
            }
            //Expiry Date --> IssueNumber
            else if(actionHistory.size() == 4){
                String expiryDateS = actionHistory.get(3);
                Card tmpCard = new Card();
                //Add / into dateString
                try{
                    expiryDateS = expiryDateS.substring(0, 2) + "/" + expiryDateS.substring(2);
                    if(!tmpCard.setExpiryDate(expiryDateS)){
                        actionHistory.remove(3);
                        padText = "Invalid Expiry Date - Please enter in the format 'MMyy'";
                    }
                    else{
                        padText = "Please enter the card issue number";
                    }
                }
                catch(StringIndexOutOfBoundsException ex){
                    actionHistory.remove(3);
                    padText = "Invalid Expiry Date - Please enter in the format 'MMyy'";
                }

            }
            //Issue Number --> CVV
            else if(actionHistory.size() == 5){
                String issueNumber = actionHistory.get(4);
                if(issueNumber != null && issueNumber.length() > 0){
                    padText = "Please enter the card CVV number";
                }
                else{
                    actionHistory.remove(4);
                    padText = "Invalid Issue Number - Please try again";
                }
            }
            //CVV --> Complete
            else if(actionHistory.size() == 6){
                String cvv = actionHistory.get(5);
                Card tmpCard = new Card();
                if(!tmpCard.setCVV(cvv)){
                    actionHistory.remove(5);
                    padText = "Invalid CVV - Please enter the CVV with length of 3 or 4 digits";
                }
                //Complete Transactions
                else{
                    padText = "Complete Transaction";
                    //Setup Transaction
                    Card fromCard = new Card();
                    fromCard.setCVV(actionHistory.get(5));
                    fromCard.setIssueNumber(actionHistory.get(4));
                    fromCard.setExpiryDate(actionHistory.get(3));
                    fromCard.setCardnumber(actionHistory.get(2));
                    Double amount = Double.parseDouble(actionHistory.get(1));
                    
                    //Try to send Transaction
                    try{
                        IBankingService bankingService = WebObjectFactory.getBankingService();
                        Transaction transaction = bankingService.SendTransaction(fromCard, amount);
                        //Transaction Success
                        if("SUCCESS".equals(transaction.getTransactionResponse().getStatus())){
                            padText = "Successful Transaction \n Press 1 for a new Transaction or 2 to refund a transaction";
                            actionHistory.clear();
                        }
                        //Transaction Fail
                        else{
                            padText = "Transaction Failed: "  + transaction.getTransactionResponse().getMessage() + "\n Press 1 for a new Transaction or 2 to refund a transaction";
                            actionHistory.clear();
                        }
                    }
                    //Transaction didn't return from API
                    catch(Exception ex){
                        LOG.error(ex);
                        padText = "Transaction Failed: Please ensure you have the correct settings in the app proeprties file and then restart the app" + "\n Press 1 for a new Transaction or 2 to refund a transaction";
                        actionHistory.clear();
                    }


                }
            }
            
            
        }
        //Refund Transaction
        else if ("2".equals(actionHistory.get(0))){
            //List Refund
            if(actionHistory.size() == 1){
                padText = "Please select an transaction to refund: ";
                IBankingService bankingService = WebObjectFactory.getBankingService();
                List<Transaction> transactions = bankingService.GetLatestSuccessfulTransactions();
                //Transactions Available to Refund
                if(transactions.size() > 0){
                    for (int i = 0; i < transactions.size(); i++) {
                        Transaction transaction = transactions.get(i);
                        String CardNumber = transaction.getTransactionRequest().getFromCard().getCardnumber();
                        Double Amount = transaction.getTransactionRequest().getAmount();
                        padText = padText + "\n " + i + " - from: " + CardNumber + " - amount: " + Amount;
                    }
                }
                //No Transactions to Refund
                else{
                    padText = "No Transaction History to Refund" + "\n Press 1 for a new Transaction or 2 to refund a transaction";
                    actionHistory.clear();
                }               
            }
            //Select Refund
            else if(actionHistory.size() == 2){
                IBankingService bankingService = WebObjectFactory.getBankingService();
                List<Transaction> transactions = bankingService.GetLatestSuccessfulTransactions();
                int transactionInt;
                try {
                    //Get selected transaction
                    transactionInt = Integer.parseInt(actionHistory.get(1));
                    Transaction tranasactionToRefund = transactions.get(transactionInt);
                    try{
                        //Try to refund
                        Transaction refundTransaction = bankingService.RefundTransaction(tranasactionToRefund);
                        
                        //Refund Success
                        if("SUCCESS".equals(refundTransaction.getTransactionResponse().getStatus())){
                            padText = "Successful Refund \n Press 1 for a new Transaction or 2 to refund a transaction";
                            actionHistory.clear();
                            //return;
                        }
                        
                        //Refund Failure 
                        else{
                            padText = "Refund Failed: "  + refundTransaction.getTransactionResponse().getMessage() + "\n Press 1 for a new Transaction or 2 to refund a transaction";
                            actionHistory.clear();
                        }
                   }
                    //Catch unknown refund failure
                    catch(Exception ex){
                        LOG.error(ex);
                        padText = "Transaction Failed: Please ensure you have the correct settings in the app proeprties file and then restart the app" + "\n Press 1 for a new Transaction or 2 to refund a transaction";
                        actionHistory.clear();
                    }
                   
                }
                //Catch invalid input exception
                catch (NumberFormatException e)
                {
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
    }
    else{
        LOG.error("8 ");
        session.setAttribute("actionHistory", new ArrayList<String>());
    }
    
    //actionHistory.clear();
    
    

    //session.setAttribute("actionHistory", new ArrayList<String>());
    

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
                        <p style="white-space: pre-line"><%= padText %></p>
                    </div>
                    <input id="txtpininput" type="text" name="userResponse" value="">
                </div>
                <div id="div-pinpad">
                    
                </div>
                </div>
            <div id="div-cmd">
                <div id="btncancel" class="btn-style">Cancel</div>
                <div id="btnundo" class="btn-style">Undo</div>
                <!--input type="hidden" name="action" value="submitResponse"-->
                <button id="btnconfirm" class="btn-style" type="submit">Confirm</button>
            </div>
        </form>
        <!-- page script -->
        <script src="public/scripts/index.js"></script>
    </body>
</html>
