<%-- 
    Document   : receberNome
    Created on : Dec 14, 2020, 10:22:10 AM
    Author     : Belmiro-Mungoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-pt">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem-Vindo</title>
    </head>
    <body>
        <center>
            <h1>Bem-Vindo ao Sistema de Cadastros de:</h1>
            <a href="UsuarioServlet?accao=listar" style=" margin-right: 20px">
                <img src="css/img/group-1.png" title="Cadastro de Usuários">
            </a>
            <a href="ProdutoServlet?accao=listar" style=" margin-left: 20px">
                <img src="css/img/produto.png" title="Cadastro de Produtos">
            </a>
        </center>
</body>
</html>
