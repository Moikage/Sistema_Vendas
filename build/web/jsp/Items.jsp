<%-- 
    Document   : Items
    Created on : 08/03/2018, 14:50:52
    Author     : jhean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Cabecalho.jsp"></jsp:include>

    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        th{
            border: 5px solid #269abc;
            text-align: left;
            padding: 8px;
            background-color: #c4e3f3;
        }
        td {
            border: 5px solid #269abc;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #449d44;
        }
    </style>
    <h1>Venda</h1>
    <table>
        <th>ID da venda</th>
        <th>Data da Venda</th>
        <tr>
            <td>${item.id}</td>
            <td>${item.data}</td>
        </tr>
    </table>
    <h1>Items</h1>
    <table>
        <th>Id</th>
        <th>ID do Produto</th>
        <th>Produto</th>
        <th>Quantidade</th>
        <th>desconto</th>
        <th>Valor</th>
        <th>Total</th>
        <c:forEach items='${item.vendas}' var="vend">
        <tr>
            <td>${vend.id}</td>
            <td>${vend.produto.id}</td>
            <td>${vend.produto.descricao}</td>
            <td>${vend.qtd}</td>
            <td>${vend.desconto}</td>
            <td>${vend.produto.valor}</td>
            <td>${vend.total()}</td>
        </tr>
    </c:forEach>
</table>
    <p style="font-style: italic; font-size: 20px; float: right">Soma: R$:(${item.total()})</p>
<br>
<jsp:include page="Rodape.jsp"></jsp:include>