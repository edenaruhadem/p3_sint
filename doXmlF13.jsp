<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cancion"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<canciones>
<c:forEach var = "i" items = "${canBean.hcancion}">
<cancion idc='${(i.getValue()).getIdc((i.getValue()))}' genero='${(i.getValue()).getGenero((i.getValue()))}' duracion='${(i.getValue()).getDuracion((i.getValue()))}'>${(i.getValue()).getTitulo((i.getValue()))}</cancion>
</c:forEach>
</canciones>        
