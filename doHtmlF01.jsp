<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='iml.css'></link>
    </head>
    <body>
        <h1>Servicio de consulta de canciones</h1>
        <h2>Bienvenido a este servicio.</h2>
        <form name = 'miformfase01' action=''>
        <a href = '/sint48/P2IM?p=d4r18c392b&pfase=02'>Pulsa aqu&iacute; para ver los ficheros err&oacute;neos</a>
        <h3>Selecciona una consulta:</h3>
        <input type = 'radio' checked>Consulta1: Canciones de un int&eacute;rprete que duran menos que una dada</input>
        <br></br>
        <input type = 'submit' class = 'buttonSubmit'></input>
        <input type = 'hidden' name = 'p' value = d4r18c392b></input>
        <input type = 'hidden' name = 'pfase' value = '11'></input>	
        </form>        
    </body>
    <footer>
    <p>sint48. @Diego R&iacute;os Castro.</p>                
    </footer>
</html>       