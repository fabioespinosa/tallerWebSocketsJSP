<%-- 
    Document   : index
    Created on : Sep 17, 2016, 6:51:26 PM
    Author     : fabioespinosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hola ${name}</h1>
        <form action="/WhiteBoardApp/login" method="post">
            Porfavor ingrese un usuario existente o nuevo
            <input type="text" name="usuario"/>
            <input type="submit"/>
        </form>
    </body>
</html>
