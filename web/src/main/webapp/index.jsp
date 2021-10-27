<%-- 
    Document   : indexjsp
    Created on : 21 Oct 2021, 07:52:36
    Updated on : 21 Oct 2021, 12:07:00
    Authors     : rgaudion, rpriest
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
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
    String userresponse = request.getParameter("userResponse");
    // find what action to perform on the page
    String action = request.getParameter("action");
    
    if ("submitResponse".equals(action)) {
        // do menu stuff
        if (userresponse != null) {
            
        } else {
            
        }
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
        <p>user response: <%= userresponse %></p>
        <form action="./index.jsp" method="get">
            <div id="div-container">
                <div id="div-pininput">
                    <div id="div-userfeedback"></div>
                    <input id="txtpininput" type="text" name="userResponse" value="">
                </div>
                <div id="div-pinpad">
                    
                </div>
                </div>
            <div id="div-cmd">
                <div id="btncancel" class="btn-style">Cancel</div>
                <div id="btnundo" class="btn-style">Undo</div>
                <input type="hidden" name="action" value="submitResponse">
                <button id="btnconfirm" class="btn-style" type="submit">Confirm</button>
            </div>
        </form>
        <!-- page script -->
        <script src="public/scripts/index.js"></script>
    </body>
</html>
