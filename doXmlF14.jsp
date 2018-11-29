<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cancion"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<songs>
<c:forEach var = "i" items = "${resBean.hresultado}">
<song descripcion='${(i.getValue()).getDescripcion((i.getValue()))}' premios='${(i.getValue()).getPremios((i.getValue()))}'>${(i.getValue()).getTitulo((i.getValue()))}</song>
</c:forEach>
</songs>        