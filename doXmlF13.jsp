<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cancion"%>

<canciones>
<c:forEach var = "i" items = "${canBean.cancion}">
<cancion idc='${i.getIdc(i)}' genero='${i.getGenero(i)}' duracion='${i.getDuracion(i)}'>${i.getTitulo(i)}</cancion>
</c:forEach>
</canciones>        
