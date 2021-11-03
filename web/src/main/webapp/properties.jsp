<%-- 
    Document   : properties.jsp
    Created on : Nov 3, 2021, 11:43:46 AM
    Author     : ISA06002471
--%>
<%@page import="org.solent.oodd.pos.model.dto.Card"%>
<%@page import="org.solent.oodd.pos.dao.PropertiesDao"%>
<%@page import="org.solent.oodd.pos.dao.DaoObjectFactory"%>
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
    PropertiesDao propertiesDao = DaoObjectFactory.getPropertiesDao();
    String url = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUrl");
    String username = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUsername");
    String password = propertiesDao.getProperty("org.solent.oodd.pos.service.apiPassword");
    String shopKeeperCard = propertiesDao.getProperty("org.solent.oodd.pos.service.shopKeeperCard");
    String message = "";

    String action = (String) request.getParameter("action");
    if ("updateProperties".equals(action)) {
        message = "updating properties";
        url = (String) request.getParameter("apiUrl");
        username = (String) request.getParameter("apiUsername");
        password = (String) request.getParameter("apiPassword");
        shopKeeperCard = (String) request.getParameter("shopKeeperCard");
        propertiesDao.setProperty("org.solent.oodd.pos.service.apiUrl", url);
        propertiesDao.setProperty("org.solent.oodd.pos.service.apiUsername", username);
        propertiesDao.setProperty("org.solent.oodd.pos.service.apiPassword", password);
        propertiesDao.setProperty("org.solent.oodd.pos.service.shopKeeperCard", shopKeeperCard);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Properties Jsp</title>
    </head>
    <body>
        <h1>Properties Jsp</h1>
        <p><%=message%></p>
        <form action="./properties.jsp" method="POST">
            <p>URL Property <input type="text" name="url" value="<%=url%>"></p>
            <p>Username Property <input type="text" name="username" value="<%=username%>"></p>
            <p>Password Property <input type="text" name="password" value="<%=password%>"></p>
            <p>Shop Keeper Card Property <input type="text" name="password" value="<%=shopKeeperCard%>"></p>
            <input type="hidden" name="action" value="updateProperties">

            <button class="btn" type="submit" >Update Properties</button>
        </form> 
    </body>
</html>