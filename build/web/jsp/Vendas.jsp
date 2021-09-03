<%-- 
    Document   : Vendas
    Created on : 06/03/2018, 00:02:51
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
    <h1>Suas Vendas</h1>
    <table>
        <tr>
            <th>Id</th>
            <th>Data</th>
            <th>Ações</th>
        </tr>
        <c:forEach items='${venda}' var="v">
        <tr>
            <td>${v.id}</td>
            <td>${v.data}</td>
            <td><a href="ServControl?var=item&vid=${v.id}&idcl=${v.id_cliente}">Consultar</a></td>
        </tr>
        
    </c:forEach>
</table>

<jsp:include page="Rodape.jsp"></jsp:include>