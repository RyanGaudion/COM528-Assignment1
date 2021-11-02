<%-- 
    Document   : indexjsp
    Created on : 21 Oct 2021, 07:52:36
    Updated on : 21 Oct 2021, 12:07:00
    Authors     : rgaudion, rpriest
--%>
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
                    padText = "Valid Amount - Please Enter Card Number";
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
                if(!tmpCard.setCardNumber(cardNumber)){
                    actionHistory.remove(2);
                    padText = "Invalid Card Number - Please Try Again";
                }
                else{
                    padText = "Please enter the expiry Date in format 'MMyy'";
                }
            }
            //Expiry Date --> CVV
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
                        padText = "Please enter the card CVV number";
                    }
                }
                catch(StringIndexOutOfBoundsException ex){
                    actionHistory.remove(3);
                    padText = "Invalid Expiry Date - Please enter in the format 'MMyy'";
                }

            }
            //CVV --> Complete
            else if(actionHistory.size() == 5){
                String cvv = actionHistory.get(4);
                Card tmpCard = new Card();
                if(!tmpCard.setCVV(cvv)){
                    actionHistory.remove(4);
                    padText = "Invalid CVV - Please enter the CVV with length of 3 or 4 digits";
                }
                else{
                    padText = "Complete Transaction";
                    //Do Transaction
                    
                    //Cleanup
                    actionHistory.clear();
                }
            }
            
            
        }
        else if ("2".equals(actionHistory.get(0))){
            if(actionHistory.size() == 1){
                padText = "Please select an transaction to refund";
            }
            
        }
    }
    else{
        LOG.error("8 ");
        session.setAttribute("actionHistory", new ArrayList<String>());
    }
    
    

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
                        <p><%= padText %></p>
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
