<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Disco"%>

<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='iml.css'></link>
    </head>
    <body>
        <h1>Servicio de consulta de canciones</h1>
        <h2>Consulta 1: A&ntilde;o=${disBean.an}</h2>   
        <h3>Selecciona un disco:</h3>
        
        <form name = 'miformfase12'>
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>    
        <input type = 'hidden' name = 'pfase' value = '13'></input>
        <input type = 'hidden' name = 'panio' value ="${disBean.an}"></input>                
        
        <c:forEach var = "i" items = "${disBean.disco}" begin = "0" end = "3">
        <input type = 'radio' name = 'pidd' value = "${i.getIDD(i)}">(poner num).- T&iacute;tulo ='${i.getTitulo(i)}' --- IDD ='${i.getIDD(i)}' --- Int&eacute;rprete ='${i.getInterprete(i)}' --- Idiomas ='${i.getIdiomas(i)}'</input><p>
        </c:forEach>       
        <br></br>
        <input type = 'submit' class = 'buttonSubmit'></input>            
        </form>

        <form>        
        <button type = 'submit' class = 'buttonAtras'>Atr&aacute;s</button>        
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>
        <input type = 'hidden' name = 'pfase' value = '11'></input>
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
        
        







