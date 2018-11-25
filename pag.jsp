<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>        
        <title>Numero</title>
    </head>
    <body>
        <h1>Numero inicial</h1>
        ${laBean.num}<p>      
        <form action="P3M" method="post">
            <input name="Enviar" type="submit" value="up">
        </form>
    </body>
</html>