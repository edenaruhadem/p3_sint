<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cancion"%>

<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='iml.css'></link>
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
                        
        
        <c:forEach var = "i" items = "${canBean.cancion}">        
        <input type = 'radio' name = 'pidc' value = "${i.getIdc(i)}">(poner num).- T&iacute;tulo ='${i.getTitulo(i)}' --- IDC ='${i.getIdc(i)}' --- G&eacute;nero ='${i.getGenero(i)}' --- Duraci&oacute;n ='${i.getDuracion(i)} seg.'</input><p>
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
              
        
        
        
        







