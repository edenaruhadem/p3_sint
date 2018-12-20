<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<html>
    <head>        
        <title>Sint: Pr&aacute;ctica 2. Consulta de canciones</title>
        <link rel='stylesheet' type='text/css' href='p3/iml.css'></link>
        <script src="p3/imlxslt.js"></script>
    </head>
    <body>
        <h1>Servicio de consulta de canciones. Transformaciones XSLT</h1>        
        <button onclick="Init('p3/iml2001.xml')">${aniBean.hanio.get("1")}</button>
        <button onclick="Init('p3/iml2002.xml')">${aniBean.hanio.get("2")}</button>
        <button onclick="Init('p3/iml2003.xml')">${aniBean.hanio.get("3")}</button>
        <button onclick="Init('p3/iml2004.xml')">${aniBean.hanio.get("4")}</button>
        <button onclick="Delete()">Borrar</button>
                
        <br></br>               
        <div id="tabla"></div>            
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

         
