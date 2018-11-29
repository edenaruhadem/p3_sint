<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='iml.css'></link>
    </head>
    <body>
        <h1>Servicio de consulta de canciones</h1>
        <h2>Consulta 1</h2>    
        <h3>Selecciona un a&ntilde;o:</h3>
        <form name = 'miformfase11'>
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>    
        <input type = 'hidden' name = 'pfase' value = '12'></input>

        <c:forEach var = "i" items = "${aniBean.anio}">
        <input type = 'radio' name = 'panio' value = "${i}">(poner num).- ${i}</input><p>
        </c:forEach>
        <br></br>
        <input type = 'submit' class = 'buttonSubmit'></input>            
        </form>
        <form>        
        <button type = 'submit' class = 'buttonAtras'>Atr&aacute;s</button>        
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>
        <input type = 'hidden' name = 'pfase' value = '01'></input>
        </form>
    </body>
    <footer>
    <p>sint48. @Diego R&iacute;os Castro.</p>                
    </footer>
</html>       