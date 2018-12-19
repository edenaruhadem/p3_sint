<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='p3/iml.css'></link>
    </head>
    <body>
        <h1>Servicio de consulta de canciones</h1>
        <h2>Se han encontrado ${errBean.tamwar} ficheros con warnings.</h2>       
        <c:forEach var = "i" items = "${errBean.warnings}">
        ${i.getKey()} -------- ${i.getValue()}<p>
        </c:forEach>

        <h2>Se han encontrado ${errBean.tamerr} ficheros con errores.</h2>       
        <c:forEach var = "i" items = "${errBean.errores}">
        ${i.getKey()} -------- ${i.getValue()}<p>
        </c:forEach>

        <h2>Se han encontrado ${errBean.tamfer} ficheros con errores fatales.</h2>       
        <c:forEach var = "i" items = "${errBean.erroresf}">
        ${i.getKey()} -------- ${i.getValue()}<p>
        </c:forEach>
        <form>
        <button class = 'buttonAtras'>Atr&aacute;s</button>
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>
        <input type = 'hidden' name = 'pfase' value = '01'></input>
        </form>                      
    </body>
    <footer>
    <p>sint48. @Diego R&iacute;os Castro.</p>                
    </footer>
</html>











       	    
        
                       	
                     	              
                       	              
        