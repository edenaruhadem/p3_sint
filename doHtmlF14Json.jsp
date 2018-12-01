<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cancion"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='iml.css'></link>
    </head>
    <body>
        <h1>Servicio de consulta de canciones</h1>
        <!--<form name = 'miformfase14' action="/sint481/P3M?p=d4r18c392b&pfase=14&panio=2003&pidd=2003-001-001&pidc=2003-001-001-02">-->
        <h2>Consulta 1: A&ntilde;o=${resBean.an}, Disco=${resBean.id}, Canci&oacute;n=${resBean.ic}</h2>    
        <h3>Este es el resultado:</h3>                
        <button id="btna">Ascendente</button>
        <button id="btnd">Descendente</button>
        <div id="resultado"></div>
        <script src="main.js"></script>       
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
