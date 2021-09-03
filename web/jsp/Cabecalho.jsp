<%-- 
    Document   : Cabecalho
    Created on : 05/03/2018, 21:02:27
    Author     : jhean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Primeira pagina</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/estylo.css" rel="stylesheet">
    </head>
    <style>
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #28a4c9;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover:not(.active) {
                background-color: #4CAF50;
            }
            
            .active {
                background-color: #4CAF50;
            }
            div{
                float: right;
                text-align: right;
                color: white;
                padding: 14px 16px;
                text-decoration: none;
            }
            div a{
                color: black;
            }
        </style>
    <body>
        <ul>
            <li><a class="active" href="ServControl?var=inicio" style="color: floralwhite">Inicio</a></li>
            <li><a href="ServControl?var=listar">Listar produtos</a></li>
            <li><a href="ServControl?var=buscar&n=${sessionScope['nome']}">Listar vendas</a></li>
            <li><a href="ServControl?var=criar&n=${sessionScope['nome']}">Criar venda</a></li>
            <div>Bem vindo (${sessionScope['nome']})
                <a href="ServControl?var=sair">sair</a>
                    <%--<li><a class="active">Bem vindo ${sessionScope['nome']}</a></li>--%>
                </div>
        </ul>
