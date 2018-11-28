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

        <c:forEach var = "i" items = "${aniBean.anio}" begin = "0" end = "3">
        <c:out value = "${i}"/><p>
        </c:forEach>
        <!--<p><input type = 'radio' name = 'panio' value = "+Anios.get(i)+" checked>"+Integer.toString(i+1)+".- "+Anios.get(i)+"</input></p>-->
        <!--${aniBean.anio}-->
        <!-- aqui meter la referencia de años. leerlos de la bean que los genera-->




        <br></br>
        <input type = 'submit' class = 'buttonSubmit'></input>            
        </form>
        <button type = 'button' class = 'buttonAtras' onclick=\"window.location='/sint48/P2IM?p=d4r18c392b&pfase=01'\">Atr&aacute;s</button>

    </body>
    <footer>
    <p>sint48. @Diego R&iacute;os Castro.</p>                
    </footer>
</html>


        
        
	            
        for(int i=0;i<Anios.size();i++)
        {
            if(i==0)
            {
                out.println("<p><input type = 'radio' name = 'panio' value = "+Anios.get(i)+" checked>"+Integer.toString(i+1)+".- "+Anios.get(i)+"</input></p>");
            }
            else out.println("<p><input type = 'radio' name = 'panio' value = "+Anios.get(i)+">"+Integer.toString(i+1)+".- "+Anios.get(i)+"</input></p>");            
        }       
        
        