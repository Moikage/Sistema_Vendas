<%-- 
    Document   : FormVenda
    Created on : 06/03/2018, 00:20:09
    Author     : jhean
--%>

<%@page import="Classes.ItemVenda"%>
<%@page import="java.util.ArrayList"%>
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
        input[type=date], select {
            width: 50%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=text], select {
            width: 25%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=number], select {
            width: 25%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 25%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        } 
        
    </style>
    <h2 style=" color: #255625">Dados da venda</h2>
    <form action="ServControl?var=respcriar&op=add" method="post">
        <input type="date" name="data"  placeholder="DATA da venda" value="${v.data}" required>
    <br>
    <select id="prod" name="prod">
        <option value="0" disabled select>Produto</option>
        <c:forEach var="p" items="${produtos}">
            <option value="${p.id}">
                ${param.produto != null && p.id eq param.produto ? 'selected' : ''}
                ${p.descricao}
            </option>
        </c:forEach>
    </select>
    <input type="number" name="qtd"  placeholder="Quantidade" required>
    <input type="number" name="desc" placeholder="Desconto do Produto" required>
    <input style="width: 10%" type="submit" value="add">
    <br>
</form>
<table>
    <tr>
        <th>Item</th>
        <th>Produto</th>
        <th>Valor</th>
        <th>Quantidade</th>
        <th>Desconto</th>
        <th>Total</th>
        <th style="text-align: center">Remover</th>
    </tr>
    <c:forEach items="${v.vendas}" var="prod" varStatus="cont">
        <tr>
            <td>${cont.index+1}</td>
            <td>${prod.produto.descricao}</td>
            <td>${prod.produto.valor}</td>
            <td>${prod.qtd}</td>
            <td>${prod.desconto}</td>
            <td>${prod.total()}</td>
            <td style="text-align: center"><a href="ServControl?var=del&op=${cont.index}">X</a></td>
        </tr>
    </c:forEach>
</table>
    <p style="font-style: italic; font-size: 20px; float: right">Soma: R$:(${v.total()})</p>
<form action="ServControl?var=respcriar&op=fim" method="post">
    <input style="background-color: #269abc" type="submit" value="Criar">
</form>
    <form action="ServControl?var=cancel" method="post">
    <input style="background-color: #4CAF50 " type="submit" value="Cancelar">
</form>

<jsp:include page="Rodape.jsp"></jsp:include>