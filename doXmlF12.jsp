<%@page contentType="text/xml"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="clases.Disco"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<discos>
<c:forEach var = "i" items = "${disBean.hdisco}">
<disco idd='${(i.getValue()).getIDD((i.getValue()))}' interprete='${(i.getValue()).getInterprete((i.getValue()))}' langs='${(i.getValue()).getIdiomas((i.getValue()))}'>${(i.getValue()).getTitulo((i.getValue()))}</disco>
</c:forEach>
</discos>        