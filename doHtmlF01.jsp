<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>


<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='iml.css'></link>
    </head>
    <body>
        <h1>Servicio de consulta de canciones</h1>
        <h2>Bienvenido a este servicio.</h2>
        <form name = 'miformfase01' action=''>
        <a href = '/sint481/P3M?p=d4r18c392b&pfase=02'>Pulsa aqu&iacute; para ver los ficheros err&oacute;neos</a>
        <h3>Selecciona una consulta:</h3>
        <input type = 'radio' checked>Consulta1: Canciones de un int&eacute;rprete que duran menos que una dada</input>
        <br></br>
        <input type = 'submit' class = 'buttonSubmit'></input>
        <input type = 'hidden' name = 'p' value = d4r18c392b></input>
        <input type = 'hidden' name = 'pfase' value = '11'></input>

        </form>
        <form name = 'miformfase03' action=''>        
        <h3>XSLT</h3>
        <!--<c:forEach var = "i" items = "${aniBean.hanio}">
        <c:choose>
        <c:when test="${(i.getKey())==1}">
        <input type = 'radio' name = 'pxslt' value = "${i.getValue()}"checked>${i.getValue()}</input><p>
        </c:when>
        <c:otherwise>
        <input type = 'radio' name = 'pxslt' value = "${i.getValue()}">${i.getValue()}</input><p>
        </c:otherwise>
        </c:choose>              
        </c:forEach>-->        
        <input type = 'submit' value = 'IML 2 XSLT'></input>
        <input type = 'hidden' name = 'p' value = d4r18c392b></input>
        <input type = 'hidden' name = 'pfase' value = '03'></input>        
        </form>        
    </body>
    <footer>
    <p>sint48. @Diego R&iacute;os Castro.</p>                
    </footer>
</html>       