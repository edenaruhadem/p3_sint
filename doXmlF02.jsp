<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<?xml version='1.0' encoding='utf-8' ?>");
<errores>
<warnings>
<warning>
        <c:forEach var = "i" items = ${feBean.alWarn} begin = "0" end = "${feBean.sizeWarn}">
        file <c:out value = "${feBean.alWarn(i)}"/><p>
        cause <c:out value = "${feBean.alWarn(i)}"/><p>
        </c:forEach>
    

<file>"+key+"</file>
<cause>"+Warnings.get(key)+"</cause>
</warning>






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





        out.println("<?xml version='1.0' encoding='utf-8' ?>");
        out.println("<errores>");
        out.println("<warnings>");
        Iterator it = Warnings.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<warning>");        
            out.println("<file>"+key+"</file>");
            out.println("<cause>"+Warnings.get(key)+"</cause>");
            out.println("</warning>");
        }           
        out.println("</warnings>");
        out.println("<errors>");
        it = Errores.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<error>");
            out.println("<file>"+key+"</file>");
            out.println("<cause>"+Errores.get(key)+"</cause>");
            out.println("</error>");
        }               
        out.println("</errors>");
        out.println("<fatalerrors>");
        it = EFatales.keySet().iterator();
        while(it.hasNext())
        {
            String key = (String)it.next();
            out.println("<fatalerror>");
            out.println("<file>"+key+"</file>");
            out.println("<cause>"+EFatales.get(key)+"</cause>");
            out.println("</fatalerror>");
        }              
        out.println("</fatalerrors>");
        out.println("</errores>");
    }//doXmlF02

    public void doGetFase11(HttpServletResponse res, String auto)throws IOException
    {

        Anios.clear();        
        Anios = getC1Anios();
        Collections.sort(Anios);
        if(auto==null)
        {
            doHtmlF11(res,Anios);  //Esto debe ser una jsp              
        }
        else if(auto.equals("si"))
        {
            doXmlF11(res,Anios); //Esto debe ser otra jsp
        }











       	    
        
                       	
                     	              
                       	              
        