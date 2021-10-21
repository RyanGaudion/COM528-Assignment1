<%-- 
    Document   : indexjsp
    Created on : 21 Oct 2021, 07:52:36
    Updated on : 21 Oct 2021, 12:07:00
    Authors     : rgaudion, rpriest
--%>
<%

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POS Device</title>
        <!-- jQuery UI theme -->
        <link rel="stylesheet" type="text/css" href="external/jquery-ui/jquery-ui.css">

        <!-- pinpad CSS -->
        <link rel="stylesheet" type="text/css" href="public/styles/jquery.ui.pinpad.css">
        <link rel="stylesheet" type="text/css" href="public/styles/index.css">

        <!-- external libraries -->
        <script src="external/jquery/jquery.js"></script>
        <script src="external/jquery-ui/jquery-ui.js"></script>
    </head>
    <body>
        <div id="main">
            <div id="pininput">
                <p>
                    <input id="pinpad" title="Input Here" maxlength="20">
                </p>
            </div>
        </div>

        <!-- pinpad widget -->
        <script src="public/scripts/index.js"></script>
        <script src="public/scripts/jquery.ui.pinpad.js"></script>
    </body>
</html>
