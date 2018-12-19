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
        <h2>Consulta 1: A&ntilde;o=${canBean.an}, Disco=${canBean.id}</h2>       
        <form name = 'miformfase13'>
        <h3>Selecciona una canci&oacute;n:</h3>
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>
        <input type = 'hidden' name = 'pfase' value = '14'></input>	
        <input type = 'hidden' name = 'panio' value = "${canBean.an}"></input>
        <input type = 'hidden' name = 'pidd' value = "${canBean.id}"></input>
                        
        
        <c:forEach var = "i" items = "${canBean.hcancion}">
        <c:choose>
        <c:when test="${(i.getKey()) == 1}">       
        <input type = 'radio' name = 'pidc' value = "${(i.getValue()).getIdc((i.getValue()))}"checked>${i.getKey()}.- T&iacute;tulo ='${(i.getValue()).getTitulo((i.getValue()))}' --- IDC ='${(i.getValue()).getIdc((i.getValue()))}' --- G&eacute;nero ='${(i.getValue()).getGenero((i.getValue()))}' --- Duraci&oacute;n ='${(i.getValue()).getDuracion((i.getValue()))} seg.'</input><p>
        </c:when>
        <c:otherwise>
        <input type = 'radio' name = 'pidc' value = "${(i.getValue()).getIdc((i.getValue()))}">${i.getKey()}.- T&iacute;tulo ='${(i.getValue()).getTitulo((i.getValue()))}' --- IDC ='${(i.getValue()).getIdc((i.getValue()))}' --- G&eacute;nero ='${(i.getValue()).getGenero((i.getValue()))}' --- Duraci&oacute;n ='${(i.getValue()).getDuracion((i.getValue()))} seg.'</input><p>
        </c:otherwise>
        </c:choose> 
        </c:forEach>       
        <br></br>
        <input type = 'submit' class = 'buttonSubmit'></input>            
        </form>

        <form>        
        <button type = 'submit' class = 'buttonAtras'>Atr&aacute;s</button>        
        <input type = 'hidden' name = 'p' value = 'd4r18c392b'></input>
        <input type = 'hidden' name = 'pfase' value = '12'></input>
        <input type = 'hidden' name = 'panio' value = "${canBean.an}"></input>
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
              
        
        
        
        







