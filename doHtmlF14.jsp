<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cancion"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='p3/iml.css'></link>
    </head>
    <body>
        <h1>Servicio de consulta de canciones</h1>
        <form name = 'miformfase14'>
        <h2>Consulta 1: A&ntilde;o=${resBean.an}, Disco=${resBean.id}, Canci&oacute;n=${resBean.ic}</h2>    
        <h3>Este es el resultado:</h3>       
        <c:forEach var = "i" items = "${resBean.hresultado}">       
        ${i.getKey()}.- T&iacute;tulo = '${(i.getValue()).getTitulo((i.getValue()))}' --- Descripci&oacute;n = '${(i.getValue()).getDescripcion((i.getValue()))}' --- Premios = '${(i.getValue()).getPremios((i.getValue()))}'<p>
        </c:forEach>                   
        </form>
        <form>        
        <button type = 'submit' class = 'buttonAtras'>Atr&aacute;s</button>        
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>
        <input type = 'hidden' name = 'pfase' value = '13'></input>
        <input type = 'hidden' name = 'panio' value = "${resBean.an}"></input>
        <input type = 'hidden' name = 'pidd' value = "${resBean.id}"></input>
        </form>
        <form>        
        <button type = 'submit' class = 'buttonInicio'>Inicio</button>        
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>
        <input type = 'hidden' name = 'pfase' value = '01'></input>
        </form>
    </body>
    <footer>
    <p>sint48. @Diego R&iacute;os Castro.</p>                
    </footer>
</html>         
