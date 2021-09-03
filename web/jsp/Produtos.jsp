<%-- 
    Document   : Produtos
    Created on : 05/03/2018, 22:11:57
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
<h1>Produtos</h1>
<table>
    <tr>
        <th>Cód.  produto</th>
        <th>Descricao</th>
        <th>Valor </th>
    </tr>
    <c:forEach items='${produtos}' var="prod">
        <tr>
            <td>${prod.id}</td>
            <td>${prod.descricao}</td>
            <td>R$ ${prod.valor}</td>
        </tr>
    </c:forEach>
</table>
        
<jsp:include page="Rodape.jsp"></jsp:include>