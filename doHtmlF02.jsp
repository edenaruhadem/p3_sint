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
        <h2>Se han encontrado "+${feBean.sizeWarn}+" ficheros con warnings.</h2>       
        <c:forEach var = "i" items = ${feBean.alWarn} begin = "0" end = "${feBean.sizeWarn}">
        <c:out value = "${feBean.alWarn(i)}"/><p>
        </c:forEach>

        <h2>Se han encontrado "+${feBean.sizeErr}+" ficheros con errores.</h2>       
        <c:forEach var = "i" items = ${feBean.alErr} begin = "0" end = "${feBean.sizeErr}">
        <c:out value = "${feBean.alErr(i)}"/><p>
        </c:forEach>

        <h2>Se han encontrado "+${feBean.sizeEF}+" ficheros con errores fatales.</h2>       
        <c:forEach var = "i" items = ${feBean.alEF} begin = "0" end = "${feBean.sizeEF}">
        <c:out value = "${feBean.alEF(i)}"/><p>
        </c:forEach>

        <button class = 'buttonAtras'onclick=\"window.location='/sint48/P2IM?p=d4r18c392b&pfase=01'\">Atr&aacute;s</button>              
    </body>
    <footer>
    <p>sint48. @Diego R&iacute;os Castro.</p>                
    </footer>
</html>











       	    
        
                       	
                     	              
                       	              
        