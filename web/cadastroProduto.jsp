<%-- 
    Document   : cadastroProduto
    Created on : Dec 26, 2020, 5:43:40 PM
    Author     : Belmiro-Mungoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/cadastroProduto.css">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.maskMoney.min.js" type="text/javascript"></script>
        <title>Cadastro de Produto</title>
    </head>
    <body>
        <header>
            <a href="acessoLiberado.jsp">
                <img src="css/img/logo00.png" width="150px" height="150px" title="Home">
            </a>
            <a href="index.jsp">Sair</a>
        </header>
        <form action="ProdutoServlet" method="post" autocomplete="off" id="formProd"
              >
            <h1>Cadastro de Produto</h1> 
            <center><h2>${msg}</h2></center>
            <div class="field">
                <label for="idProd">ID</label>
                <input type="text" id="idProd" name="idProd" value="${prod.idProd}" readonly="readonly">
            </div>
            <div class="field">
                <label for="nomeProd">Nome do Produto</label>
                <input type="text" id="nomeProd" name="nomeProd" value="${prod.nomeProd}">
            </div>
            <div class="field">
                <div class="field-group">
                    <div class="field">
                        <label for="quantProd">Quantidade</label>
                        <input type="number" id="quantProd" name="quantProd" value="${prod.quantProd}">
                    </div>
                    <div class="field">
                        <label for="valorProd">Valor(MZN)</label>
                        <input type="text" id="valorProdd" name="valorProd" data-thousands=","
                               data-decimal="." value="${prod.valorProd}">
                    </div>
                </div>
            </div>
            <button type="submit" id="salvar"
                    onclick="return validarCampos() ? true : false">Salvar</button>
            <button type="submit" id="canc"
                    onclick="document.getElementById('formProd').action =
                                    'ProdutoServlet?accao=reset'">Cancelar</button>
        </form>
    <center>
        <table id="table1">
            <caption>Produtos Cadastrados</caption>
            <tr>
                <th>ID</th>
                <th>Nome do Produto</th>
                <th>Quantidade</th>
                <th>Valor(MZN)</th>
                <th>Acções</th>
            </tr>
            <c:forEach items="${produtos}" var="prod">
                <tr id="tr2">
                    <td id="td1"><c:out value="${prod.idProd}"></c:out></td>
                    <td id="td1"><c:out value="${prod.nomeProd}"></c:out></td>
                    <td id="td1"><c:out value="${prod.quantProd}"></c:out></td>
                    <td id="td1"><f:formatNumber type="number" maxFractionDigits="2" value="${prod.valorProd}"/></td>
                        <td id="td1">
                            <a href="ProdutoServlet?accao=delete&prod=${prod.idProd}" onclick="return confirm('Deseja Excluir Esse Registo?')">
                            <img src="css/img/delete.png" width="20px" height="20px" title="Exlcuir"></a>
                        <a href="ProdutoServlet?accao=edit&prod=${prod.idProd}">
                            <img src="css/img/edit.png" width="20px" height="20px" title="Editar"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </center>

    <script type="text/javascript">
        function validarCampos() {
            if (document.getElementById('nomeProd').value == '') {
                alert("Preencha o Campo Nome do Produto!!!");
                return false;

            } else if (document.getElementById('quantProd').value == '') {
                alert('Preencha o Campo Quantidade!!!');
                return false;

            } else if (document.getElementById('valorProd').value == '') {
                alert('Preencha o Campo Valor');
                return false;

            } else {
                document.getElementById('formProd').submit();
            }
            return true;
        }
    </script>
    <script type="text/javascript">
        $(function () {
            $('#valorProdd').maskMoney();
        })
    </script>
</body>
</html>
