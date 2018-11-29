<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Disco"%>

<discos>
<c:forEach var = "i" items = "${disBean.disco}">
<disco idd='${i.getIDD(i)}' interprete='${i.getInterprete(i)}' langs='${i.getIdiomas(i)}'>${i.getTitulo(i)}</disco>
</c:forEach>
</discos>        