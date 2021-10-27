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
    List<String> users = (List<String>) session.getAttribute("users");
    //if (users == null) {
    //    users = new ArrayList<String>();
    //    session.setAttribute("users", users);
    //}

    String cardnum = request.getParameter("cardNum");
    String cardcvv = request.getParameter("cardCVV");
    String amount = request.getParameter("transactionAmount");
    String menuitem = request.getParameter("menuItem");
    String userresponse = request.getParameter("userResponse");

    // button.ui-pinpad-command ui-button ui-corner-all ui-widget ui-pinpad-command-confirm
    //if ("getResponse".equals(action)) {
    //    users.remove(name);
    //} else if ("addUser".equals(action)) {
    //    users.add(name);
    //}
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
        <script>
            function checkVal() {
                var boxObj = document.getElementById("div-pininput");
                if (boxObj.innerHTML) {
                    alert(boxObj.innerHTML);
                }
            }
        </script>
        <div id="div-container">
            <div id="div-pininput"></div>
            <div id="div-pinpad"></div>
            </div>
        <div id="div-cmd">
            <button id="btncancel" class="btn-style">Cancel</button>
            <button id="btnundo" class="btn-style">Undo</button>
            <button id="btnconfirm" class="btn-style" onclick="checkVal();">Confirm</button>
        </div>
        <!-- page script -->
        <script src="public/scripts/index.js"></script>
    </body>
</html>
