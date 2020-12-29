<%-- 
    Document   : index
    Created on : Dec 9, 2020, 10:53:02 PM
    Author     : Belmiro-Mungoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>


        <form action="LoginServlet" method="post" autocomplete="off">
            <h1>Iniciar Sessão</h1>
            <center><h2>${msgs}</h2></center>
            <div class="field">
                <input type="text" id="nome" name="nome"
                       placeholder="Nome do Usuario">
            </div>

            <div class="field">
                <input type="password" id="pass" name="pass" 
                       placeholder="Insira a sua senha">
            </div>
            <button type="submit">Entrar</button>
        </form>

    </body>
</html>
