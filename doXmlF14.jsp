<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Cancion"%>

<songs>
<c:forEach var = "i" items = "${resBean.resultado}">
<song descripcion='${i.getDescripcion(i)}' premios='${i.getPremios(i)}'>${i.getTitulo(i)}</song>
</c:forEach>
</songs>        