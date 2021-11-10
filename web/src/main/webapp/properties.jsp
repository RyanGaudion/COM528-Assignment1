<!--/*
 * Copyright 2021 lholmes.
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
 */-->
 
<%@page import="org.solent.oodd.pos.model.dto.Card"%>
<%@page import="org.solent.oodd.pos.dao.PropertiesDao"%>
<%@page import="org.solent.oodd.pos.dao.DaoObjectFactory"%>
<%@page import="com.fasterxml.jackson.databind.exc.InvalidFormatException"%>
<%@page import="org.solent.oodd.pos.model.service.Transaction"%>
<%@page import="org.solent.oodd.pos.model.service.IBankingService"%>
<%--<%@page import="org.solent.oodd.pos.web.WebObjectFactory"%>--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.solent.oodd.pos.model.dto.Card"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@page import = "java.io.IOException" %>

<%
    PropertiesDao propertiesDao = DaoObjectFactory.getPropertiesDao();
    Logger LOG = LogManager.getLogger(PropertiesDao.class);
    
    String url = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUrl");
    String username = propertiesDao.getProperty("org.solent.oodd.pos.service.apiUsername");
    String password = propertiesDao.getProperty("org.solent.oodd.pos.service.apiPassword");
    String shopKeeperCard = propertiesDao.getProperty("org.solent.oodd.pos.service.shopKeeperCard");
    String message = "";

    String action = (String) request.getParameter("action");
    try{
        if ("updateProperties".equals(action)) {
            message = "Properties updated sucessfully";
            url = (String) request.getParameter("url");
            username = (String) request.getParameter("username");
            password = (String) request.getParameter("password");
            shopKeeperCard = (String) request.getParameter("shopKeeperCard");

            propertiesDao.setProperty("org.solent.oodd.pos.service.apiUrl", url);
            propertiesDao.setProperty("org.solent.oodd.pos.service.apiUsername", username);
            propertiesDao.setProperty("org.solent.oodd.pos.service.apiPassword", password);
            propertiesDao.setProperty("org.solent.oodd.pos.service.shopKeeperCard", shopKeeperCard);
        }
    } catch(Error er) {
        LOG.error(er);
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

            <p>URL Property <input type="text" name="url" value="<%=url%>" required></p>
            <p>Username Property <input type="text" name="username" value="<%=username%>" required></p>
            <p>Password Property <input type="text" name="password" value="<%=password%>" required></p>
            <p>Shop Keeper Card Property <input type="text" name="shopKeeperCard" value="<%=shopKeeperCard%>" required></p>
            <input type="hidden" name="action" value="updateProperties">

            <button class="btn" type="submit" >Update Properties</button>
        </form> 
    </body>
</html>